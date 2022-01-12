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
					<img class="itemImage" :src="imageSource(adventure.id)" />
				</div>
				<div>
					<!-- Iz nekog razloga ovde mora obrnuto lat i lng -->
					<!-- I ovaj style ne izgleda da menja nista -->
					<GMapMap
						:center="{
							lat: adventure.geoLng,
							lng: adventure.geoLat,
						}"
						:zoom="10"
						map-type-id="roadmap"
						style=""
					>
						<GMapMarker
							:position="{
								lat: adventure.geoLng,
								lng: adventure.geoLat,
							}"
						/>
					</GMapMap>
				</div>
			</div>
			<div class="rightFlex">
				<h4>{{ adventure.name }}</h4>
				<div style="display: flex; justify-content: space-between">
					<p>${{ adventure.pricePerDay }}.00 / Day</p>
					<p>Rating: {{ rating }}</p>
				</div>
				<!-- Spacer -->
				<div
					style="border-bottom: solid #da9e46 1px; margin: 10px 0px"
				></div>
				<!-- Spacer -->
				<p class="smallText">Address</p>
				<p>{{ adventure.address }}</p>
				<p class="smallText">Location</p>
				<p>{{ adventure.location }}</p>
				<p class="smallText">Promo</p>
				<p>{{ adventure.promoDescription }}</p>
				<p class="smallText">Rules</p>
				<p>{{ adventure.rules }}</p>
				<p class="smallText">Info</p>
				<p v-for="(item) in servicePrice" :key="item">
					{{ item.service }} : ${{ item.price }}
				</p>
				<p class="smallText">Equipment</p>
				<p>{{ adventure.equipment }}</p>
				<p class="smallText">Person Limit</p>
				<p>{{ adventure.maxUsers }} People</p>
				<p class="smallText">Percentage Taken if Cancelled</p>
				<p>{{ adventure.percentTakenIfCancelled }}%</p>
				<p class="smallText">Instructor</p>
				<p>{{ instructor.name }}</p>
				<p class="smallText">Instructor Bio</p>
				<p>{{ adventure.instructorBio }}</p>
				<button @click="reserve()">Reserve</button>
			</div>
			<div v-if="!actionsHide" class="rightFlex">
				<h4>Adventure Deals</h4>
				<div
					style="border-bottom: solid #da9e46 1px; margin: 10px 0px"
				></div>
				<p v-if="adventureBookingDeal == ''">
					No booking deals available...
				</p>
				<div v-for="(abd, index) in adventureBookingDeal" :key="abd">
					<p class="smallText">Deal {{ index + 1 }}</p>
					<p class="entryLeftShort">Start : {{ abd.start }}</p>
					<p class="entryLeftShort">End : {{ abd.end }}</p>
					<p class="entryLeftShort">
						Discount :
						{{
							(
								(1 - abd.price / adventure.pricePerDay) *
								100
							).toFixed(2)
						}}%
					</p>
					<div style="text-align: center">
						<button
							@click="createBooking(abd.id)"
							style="margin-bottom: 20px"
						>
							Book
						</button>
					</div>
				</div>

				<button
					class="entryApporve"
					@click="subscribe()"
					style="margin-top: 10px"
				>
					Subscribe
				</button>
			</div>
		</div>
	</div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
export default {
	setup() {
		var adventure = ref({
			// Ovo se prenosi
			id: localStorage.id,
			instructorId: localStorage.instructorId,
			// Ovo se menja
			name: localStorage.name,
			pricePerDay: localStorage.pricePerDay,
			address: localStorage.address,
			// Mora jer GMapMap ne prima string
			geoLat: parseFloat(localStorage.geoLat),
			geoLng: parseFloat(localStorage.geoLng),
			location: localStorage.location,
			promoDescription: localStorage.promoDescription,
			instructorBio: localStorage.instructorBio,
			rules: localStorage.rules,
			priceAndInfo: localStorage.priceAndInfo,
			maxUsers: localStorage.maxUsers,
			equipment: localStorage.equipment,
			percentTakenIfCancelled: localStorage.percentTakenIfCancelled,
		});

		let priceAndInfoString = localStorage.priceAndInfo;
		let priceAndInfoArray = priceAndInfoString.split(";");
		let servicePrice = [];
		priceAndInfoArray.forEach((item, index) => {
			priceAndInfoArray[index] = item.split(":");
			servicePrice.push({
				service: priceAndInfoArray[index][0],
				price: priceAndInfoArray[index][1],
			});
		});

		// Za instructor.name gore
		var instructor = ref(null);
		axios
			.get("/api/fishingInstructor/get/" + adventure.value.instructorId)
			.then(function (response) {
				instructor.value = response.data;
			});

		var rating = ref(null);
		axios
			.post(
				"/api/revision/rating",
				{
					type: 4, // 0 je BOAT, 2 je COTTAGE, 4 je ADVENTURE
					id: parseInt(adventure.value.id),
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
		var adventureBookingDeal = ref(null);
		if (localStorage.userId != null) {
			actionsHide = false;
			axios
				.get("/api/booking/adventureBookingDeal/" + adventure.value.id)
				.then(function (response) {
					console.log(response.data);
					adventureBookingDeal.value = response.data;

					adventureBookingDeal.value.forEach((bookingDeal) => {
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
			adventure,
			instructor,
			rating,
			actionsHide,
			adventureBookingDeal,
			servicePrice,

			imageSource(id) {
				return require("../../assets/images/adventure" + id + ".png");
			},
			reserve() {
				alert("Not implemented yet!");
			},
			createBooking(entityId) {
				axios
					.post("/api/booking/quickAdventureBooking", {
						entityId: entityId,
						userId: parseInt(localStorage.userId),
					})
					.then(function (response) {
						if (response.data) {
							console.log(response.data);
							alert("Booking created!");
							window.location.assign(
								"/registeredUser/" + localStorage["emailHash"]
							);
						} else {
							alert("Booking not created!");
						}
					});
			},
			subscribe() {
				axios
					.post("/api/subscription/subscribe", {
						userId: parseInt(localStorage.userId),
						boatId: parseInt(localStorage.id),
						type: "ADVENTURE",
					})
					.then(function (response) {
						if (response.data) {
							console.log(response.data);
							alert("Subscribed!");
						} else {
							alert("Subscription failed!");
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