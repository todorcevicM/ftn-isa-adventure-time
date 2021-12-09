<template>
	<div>
		<div id="logo-container">
			<div class="underlined">
				<img src="../../assets/wheel.svg" />
				<p>Adventure Time</p>
			</div>
		</div>
		<div class="mainFlex">
			<div class="leftFlex">
				<div>
					<img class="itemImage" :src="imageSource(cottage.id)" />
				</div>
				<div>
					<img class="itemImage" src="../../assets/images/interior.png" />
				</div>
				<h4>{{ cottage.name }}</h4>
				<p>${{ cottage.pricePerDay }}.00 / Day</p>
				<p>Rating: 5.00</p>
			</div>
			<div class="rightFlex">
				<p>Address</p>
				<p class="smallText" v-if="!updateToggle">{{cottage.address}}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newCottage.newAddress"
				/>
							
				<p class="smallText">
					({{ cottage.geoLng }}, {{ cottage.geoLat }})
				</p>

				<p>Promo</p>
				<p class="smallText" v-if="!updateToggle"> {{ cottage.promoDescription }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newCottage.newPromoDescription"
				/>

				<p>Rules</p>
				<p class="smallText" v-if="!updateToggle">{{ cottage.rules }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newCottage.newRules"
				/>

				<p>Info</p>
				<p class="smallText" v-if="!updateToggle">{{ cottage.priceAndInfo }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newCottage.newPriceAndInfo"
				/>

				<p>Rooms :</p>
				<p v-for="(item, key) in rooms" class="smallText" :key="item">
					Room {{ key + 1 }} : {{ item.numberOfBeds }} beds.
				</p>

				<p>Start</p>
				<p class="smallText" v-if="!updateToggle">{{ cottage.reservationStart }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newCottage.newReservationStart"
				/>

				<p>End</p>
				<p class="smallText" v-if="!updateToggle">{{ cottage.reservationEnd }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newCottage.newReservationEnd"
				/>

				<p>Person limit</p>
				<p class="smallText" v-if="!updateToggle">{{ cottage.maxUsers }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newCottage.newMaxUsers"
				/>
				<p class="smallText" v-if="!updateToggle">{{cottage.percentTakenIfCancelled}}</p>
				<input 
					type="text"
					v-if="updateToggle"
					v-model="newCottage.newPercentTakenIfCancelled"
				/>
				<p>% taken if cancelled. </p>

				<p>Owner : {{ owner.name }}</p>


				<button @click="updateDetails()" v-if="!updateToggle">
					Update Details
				</button>
				<button @click="sendUpdatedDetails()" v-if="updateToggle"
					style="background-color: rgb(108, 207, 108)"
				>
					Submit
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
			var urlArray = window.location.href.split("/");
			var id = urlArray[4];

			var cottage = ref(null);
			axios.get("/api/cottages/get/" + id).then(function (response) {
				for (const key in response.data) {
					if (!(key === "password")) {
						localStorage.setItem(
							key,
							response.data[key]
						);
					}
				}
				cottage.value = response.data;
				localStorage["cottageOwner"] = cottage.value.ownerId;
			});

			var rooms = ref(null);
			axios
				.get("/api/rooms/getAllByCottageId/" + id)
				.then(function (response) {
					rooms.value = response.data;
				});

			var owner = ref(null);
			axios
				.get("/api/cottageOwner/get/" + localStorage["cottageOwner"])
				.then(function (response) {
					owner.value = response.data;
				});

			var updateToggle = ref(null);
			var newCottage = ref({
				newName: localStorage.name,
				newAddress: localStorage.address,
				newRules: localStorage.rules,
				newPriceAndInfo: localStorage.priceAndInfo,
				newPromoDescription: localStorage.promoDescription,
				newReservationStart: localStorage.reservationStart,
				newReservationEnd: localStorage.reservationEnd,
				newMaxUsers: localStorage.maxUsers,
				newPercentTakenIfCancelled: localStorage.percentTakenIfCancelled,
			});

			// Za u <template>
			return {
				cottage,
				owner,
				rooms,
				updateToggle,
				newCottage, 

				imageSource(id) {
					return require("../../assets/images/cottage" + id + ".png");
				},

				updateDetails() {
					this.updateToggle = true;
				},
				sendUpdatedDetails() {
					if (
						this.newCottage.newName == "" ||
						this.newCottage.newAddress == "" ||
						this.newCottage.newRules == "" ||
						this.newCottage.newPriceAndInfo == "" ||
						this.newCottage.newPromoDescription == "" || 
						this.newCottage.newReservationStart == "" ||
						this.newCottage.newReservationEnd == "" ||
						this.newCottage.newMaxUsers == "" ||
						this.newCottage.newPercentTakenIfCancelled == ""
					) {
						alert("Please fill out all inputs.");
						return;
					}
					var sendingCottage = this.cottage;
					sendingCottage.name = this.newCottage.newName;
					sendingCottage.address = this.newCottage.newAddress;
					sendingCottage.rules = this.newCottage.newRules;
					sendingCottage.priceAndInfo = this.newCottage.newPriceAndInfo;
					sendingCottage.promoDescription = this.newCottage.newPromoDescription;
					sendingCottage.reservationStart = this.newCottage.newReservationStart;
					sendingCottage.reservationEnd = this.newCottage.newReservationEnd;
					sendingCottage.maxUsers = this.newCottage.newMaxUsers;
					sendingCottage.percentTakenIfCancelled = this.newCottage.newPercentTakenIfCancelled;
					axios
						.post("/api/cottages/update", sendingCottage)
						.then(function (response) {
							console.log("Response : ");
							console.log(response.data);
						});
					window.location.reload();
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
		justify-content: space-between;
	}

	.leftFlex {
		display: flex;
		flex-direction: column;
	}

	h4 {
		margin: 0;
		font-weight: 400;
		font-size: 50px;
	}

	.leftFlex p {
		margin: 0;
		font-size: 27px;
		font-weight: 100;
	}

	.leftFlex img {
		width: 800px;
		height: 450px;
		border-radius: 15px;
		object-fit: cover;
	}

	.rightFlex {
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
		font-size: 36px;
	}

	.rightFlex .smallText {
		margin: 0;
		font-size: 22px;
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