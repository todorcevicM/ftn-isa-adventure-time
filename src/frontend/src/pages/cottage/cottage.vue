<template>
	<div>
		<div id="logo-container">
			<a href="/" style="color: inherit">
				<div class="underlined">
					<img src="../../assets/wheel.svg" />
					<p>Adventure Time</p>
				</div>
			</a>
		</div>
		<div class="mainFlex">
			<div class="leftFlex">
				<div>
					<img class="itemImage" :src="imageSource(cottage.id)" />
				</div>
				<div>
					<img
						class="itemImage"
						src="../../assets/images/interior.png"
					/>
				</div>
				<div>
					<!-- Iz nekog razloga ovde mora obrnuto lat i lng -->
					<!-- I ovaj style ne izgleda da menja nista -->
					<GMapMap
						:center="{ lat: cottage.geoLng, lng: cottage.geoLat }"
						:zoom="10"
						map-type-id="roadmap"
						style=""
					>
						<GMapMarker
							:position="{
								lat: cottage.geoLng,
								lng: cottage.geoLat,
							}"
						/>
					</GMapMap>
				</div>
			</div>
			<div class="rightFlex">
				<div>
					<h4>{{ cottage.name }}</h4>
					<div style="display: flex; justify-content: space-between">
						<p>${{ cottage.pricePerDay }}.00 / Day</p>
						<p>Rating: {{ rating }}</p>
					</div>
					<!-- Spacer -->
					<div
						style="
							border-bottom: solid #da9e46 1px;
							margin: 10px 0px;
						"
					></div>
					<!-- Spacer -->
					<p class="smallText">Address</p>
					<p>{{ cottage.address }}</p>
					<p class="smallText">Promo</p>
					<p>{{ cottage.promoDescription }}</p>
					<p class="smallText">Rules</p>
					<p>{{ cottage.rules }}</p>
					<p class="smallText">Info</p>
					<p>{{ cottage.priceAndInfo }}</p>
					<p class="smallText">Rooms</p>
					<p v-for="(item, key) in rooms" :key="item">
						Room {{ key + 1 }} : {{ item.numberOfBeds }} beds.
					</p>
					<p class="smallText">Reservation Start</p>
					<p>{{ cottage.reservationStart }}</p>
					<p class="smallText">Reservation End</p>
					<p>{{ cottage.reservationEnd }}</p>
					<p class="smallText">Person Limit</p>
					<p>{{ cottage.maxUsers }} People</p>
					<p class="smallText">Owner</p>
					<p>{{ owner.name }}</p>
				</div>
			</div>
			<div v-if="!actionsHide">
				<h3>Cottage Deals</h3>
				<div
					class="tableEntry"
					v-for="cbd in cottageBookingDeal"
					:key="cbd"
				>
					<div class="entryLeft">
						<p class="entryLeftShort">{{ cbd.start }}</p>
						<p class="entryLeftShort">{{ cbd.end }}</p>
						<p class="entryLeftShort">
							Popust :
							{{ (1 - cbd.price / cottage.pricePerDay) * 100 }}%
						</p>
					</div>
					<div class="entryRight">
						<button
							class="entryDeny"
							@click="createBooking(cbd.id)"
						>
							Book
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
export default {
	setup() {
		var cottage = ref({
			// Ovo se prenosi
			id: localStorage.id,
			ownerId: localStorage.ownerId,
			// Ovo se menja
			name: localStorage.name,
			pricePerDay: localStorage.pricePerDay,
			address: localStorage.address,
			// Mora jer GMapMap ne prima string
			geoLat: parseFloat(localStorage.geoLat),
			geoLng: parseFloat(localStorage.geoLng),
			promoDescription: localStorage.promoDescription,
			rules: localStorage.rules,
			priceAndInfo: localStorage.priceAndInfo,
			maxUsers: localStorage.maxUsers,
			reservationStart: localStorage.reservationStart,
			reservationEnd: localStorage.reservationEnd,
		});

		// Formatiranje datuma
		let newStart = cottage.value.reservationStart.split("T");
		let newStartSecondPart = newStart[1].split(".")[0];
		cottage.value.reservationStart =
			newStartSecondPart + ", " + newStart[0];
		let newEnd = cottage.value.reservationEnd.split("T");
		let newEndSecondPart = newEnd[1].split(".")[0];
		cottage.value.reservationEnd = newEndSecondPart + ", " + newEnd[0];

		var rooms = ref(null);
		axios
			.get("/api/rooms/getAllByCottageId/" + cottage.value.id)
			.then(function (response) {
				rooms.value = response.data;
			});

		// Za owner.name gore
		var owner = ref(null);
		axios
			.get("/api/cottageOwner/get/" + cottage.value.ownerId)
			.then(function (response) {
				owner.value = response.data;
			});

		var rating = ref(null);
		axios
			.post(
				"/api/revision/rating",
				{
					type: 2, // 0 je BOAT, 2 je COTTAGE, 4 je ADVENTURE
					id: parseInt(cottage.value.id),
				},
				{
					headers: {
						"Content-Type": "application/json",
					},
				}
			)
			.then(function (response) {
				rating.value = response.data.toFixed(2);
				if (rating.value == -1.0) {
					rating.value = "None";
				}
			});

		var actionsHide = true;
		var cottageDeals = ref(null);
		if (localStorage.userId != null) {
			actionsHide = false;
			axios
				.get("/api/booking/cottageBookingDeal/" + cottage.value.id)
				.then(function (response) {
					console.log(response.data);
					cottageDeals.value = response.data;

					cottageDeals.value.forEach((bookingDeal) => {
						let newStart = bookingDeal.start.split("T");
						let newStartSecondPart = newStart[1].split(".")[0];
						bookingDeal.start =
							newStartSecondPart + ", " + newStart[0];
						let newEnd = bookingDeal.end.split("T");
						let newEndSecondPart = newEnd[1].split(".")[0];
						bookingDeal.end = newEndSecondPart + ", " + newEnd[0];
					});
				});
		}

		return {
			cottage,
			owner,
			rooms,
			rating,
			actionsHide,
			cottageDeals,
			imageSource(id) {
				try {
					return require("../../assets/images/cottage" + id + ".png");
				} catch (e) {
					// TODO: return praznu sliku ili nesto tako
					return require("../../assets/images/cottage1.png");
				}
			},
			createBooking(entityId) {
				axios
					.post("/api/booking/quickCottageBooking", {
						entityId: entityId,
						userId: parseInt(localStorage.userId),
					})
					.then(function (response) {
						if (response.data) {
							console.log(response.data);
							alert("Booking created!");
						} else {
							alert("Booking not created!");
						}
					});
			},
		};
	},
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Aleo:wght@300;400&display=swap");

body {
	/* background-image: url("../../assets/adventure-time-background.jpg"); */
	background-color: #e6e4df;
	background-size: 100%;
	background-repeat: no-repeat;
	color: #10120e;
	font-family: Aleo;
	margin: 0;
}

#logo-container {
	margin-top: 8px;
	text-align: center;
}

.underlined {
	display: inline-block;
	border-bottom: #ad6800 3px solid;
	height: 43px;
}

.underlined img {
	height: 40px;
	margin-bottom: -6px;
	margin-right: -7px;
}

.underlined p {
	margin-left: 10px;
	font-size: 40px;
	letter-spacing: -1px;
	display: inline;
}

.mainFlex {
	margin: 50px 200px;
	display: flex;
	justify-content: space-around;
}

.leftFlex {
	display: flex;
	flex-direction: column;
}

h4 {
	margin: 0;
	font-weight: 400;
	font-size: 42px;
}

.leftFlex p {
	margin: 0;
	font-size: 27px;
}

.leftFlex > div > img {
	width: 650px;
	height: 360px;
	border-radius: 15px;
	object-fit: cover;
}

.vue-map {
	border-radius: 15px !important;
	width: 650px !important;
	height: 360px !important;
}

.rightFlex {
	height: min-content;
	min-width: 320px;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	background-color: rgb(241, 241, 241);
	padding: 20px;
	border-radius: 15px;
	border: 2px solid #da9e46;
}

.rightFlex p {
	margin: 4px 0;
	font-size: 24px;
}

.rightFlex .smallText {
	margin: 0;
	font-size: 20px;
	color: #9e6b1d;
}

button {
	margin: 0 auto;
	height: 40px;
	width: 140px;
	background-color: #da9e46;
	border: none;
	border-radius: 4px;
	font-family: Aleo;
	font-size: 24px;
	transition: 0.15s;
}

button:hover {
	background-color: #9e6b1d;
	color: white;
	cursor: pointer;
}
</style>