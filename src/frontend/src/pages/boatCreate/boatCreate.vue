<template>
	<div>
		<div id="logo-container">
			<div class="underlined">
				<img src="../../assets/wheel.svg" />
				<p>Adventure Time</p>
			</div>
		</div>
		<div class="mainFlex">
			<div class="rightFlex">
				<p>Name</p>
				<input type="text" v-model="newBoat.newName"/>

				<p>Type</p>
				<input type="text" v-model="newBoat.newType"/>
				<p>Length</p>
				<input type="text" v-model="newBoat.newLength"/>
				<p>Engine Number</p>
				<input type="text" v-model="newBoat.newEngineNumber"/>
				<p>Engine Power</p>
				<input type="text" v-model="newBoat.newEnginePower"/>
				<p>Max Speed</p>
				<input type="text" v-model="newBoat.newMaxSpeed"/>



				<p>Address</p>
				<input type="text" v-model="newBoat.newAddress"/>
				<p>Longitude</p>
				<input type="text" v-model="newBoat.newGeoLng" />
				<p>Latitude</p>
				<input type="text" v-model="newBoat.newGeoLat" />

				<p>Promo</p>
				<input type="text" v-model="newBoat.newPromoDescription"/>

				<p>Rules</p>
				<input type="text" v-model="newBoat.newRules"/>

				<p>Info</p>
				<input type="text" v-model="newBoat.newPriceAndInfo"/>

				<p>Start</p>
				<input type="text" v-model="newBoat.newReservationStart" />

				<p>End</p>
				<input type="text" v-model="newBoat.newReservationEnd" />

				<p>Navigation Equipment</p>
				<input type="text" v-model="newBoat.newNavigationEquipment"/>

				<p>Equipment</p>
				<input type="text" v-model="newBoat.newEquipment"/>

				<p>Person limit</p>
				<input type="text" v-model="newBoat.newMaxUsers"/>
				
				<input type="text" v-model="newAdventure.newPercentTakenIfCancelled"/>
				<p>% taken if cancelled</p>
				

				<p>Owner : {{ boatOwnerName }}</p>

				<button
					@click="submit()"
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
		var emailHash = localStorage.emailHash;
		var boatOwnerName = ref(null);
		var boatOwnerIdFromPostRequest = ref(null);

		// TODO: ovde iz nekog razloga trazi boat kao da mu je email_hash zapravo id
		axios
			.get("/api/boatOwner/getByEmail/" + emailHash)
			.then(function (response) {
				boatOwnerName.value = response.data.name;
				console.log(response.data.id);
				localStorage["boatOwnerIdFromPostRequest"] = response.data.id;
			});
		
		// console.log(boatOwnerId.value);
		console.log(localStorage["boatOwnerIdFromPostRequest"]);

		var newBoat = ref({
			newName: "",
			newAddress: "",
			newGeoLng: "",
			newGeoLat: "",
			newPromoDescription: "",
			newRules: "",
			newPriceAndInfo: "",
			newNavigationEquipment: "",
			newEquipment: "",
			newReservationStart: "",
			newReservationEnd: "",
			newMaxUsers: "",
			newType: "",
			newLenght: "",
			newEngineNumber: "",
			newEnginePower: "",
			newMaxSpeed: "",
			newPercentTakenIfCancelled: "",

			newOwnerId: localStorage["boatOwnerIdFromPostRequest"],
		});

		// Za u <template>
		return {
			emailHash,
			boatOwnerName,
			boatOwnerIdFromPostRequest,
			newBoat,
			submit() {
				if (
					this.newBoat.newName == "" ||
					this.newBoat.newAddress == "" ||
					this.newBoat.newGeoLng == "" ||
					this.newBoat.newGeoLat == "" ||
					this.newBoat.newPromoDescription == "" ||
					this.newBoat.newRules == "" ||
					this.newBoat.newPriceAndInfo == "" ||
					this.newBoat.newNavigationEquipment == "" ||
					this.newBoat.newEquipment == "" ||
					this.newBoat.newPromoDescription == "" ||
					this.newBoat.newReservationStart == "" ||
					this.newBoat.newReservationEnd == "" ||
					this.newBoat.newMaxUsers == "" ||
					this.newBoat.newType == "" ||
					this.newBoat.newLength == "" ||
					this.newBoat.newEngineNumber == "" ||
					this.newBoat.newEnginePower == "" ||
					this.newPercentTakenIfCancelled == "" ||
					this.newBoat.newMaxSpeed == ""
				) {
					alert("Please fill out all inputs.");
					return;
				}
				var sendingBoat = this.newBoat;
				sendingBoat.name = this.newBoat.newName;
				sendingBoat.address = this.newBoat.newAddress;
				sendingBoat.promoDescription =
					this.newBoat.newPromoDescription;
				sendingBoat.rules = this.newBoat.newRules;
				sendingBoat.priceAndInfo =
					this.newBoat.newPriceAndInfo;
				sendingBoat.reservationStart =
					this.newBoat.newReservationStart;
				sendingBoat.reservationEnd =
					this.newBoat.newReservationEnd;
				sendingBoat.maxUsers = this.newBoat.newMaxUsers;
				sendingBoat.geoLng = this.newBoat.newGeoLng;
				sendingBoat.geoLat = this.newBoat.newGeoLat;
				sendingBoat.navigationEquipment = this.newBoat.newNavigationEquipment;
				sendingBoat.equipment = this.newBoat.newEquipment;
				sendingBoat.type = this.newBoat.newType;
				sendingBoat.lenght = this.newBoat.newLenght;
				sendingBoat.engineNumber = this.newBoat.newEngineNumber;
				sendingBoat.enginePower = this.newBoat.newEnginePower;
				sendingBoat.maxSpeed = this.newBoat.newMaxSpeed;
				sendingBoat.percentTakenIfCancelled = this.newBoat.newPercentTakenIfCancelled;
				sendingBoat.ownerId = this.newBoat.newOwnerId;

				axios
					.post("/api/boats/update", sendingBoat)
					.then(function (response) {
						console.log(response);
						console.log(response.data);
					});
				window.location.assign("/boatOwner/" + localStorage.emailHash);
			},
		};
	},
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Aleo:wght@300;400&display=swap");
body {
	/* background-image: url("../../assets/boat-time-background.jpg"); */
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
</style><style>
@import url("https://fonts.googleapis.com/css2?family=Aleo:wght@300;400&display=swap");

body {
	/* background-image: url("../../assets/boat-time-background.jpg"); */
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
	width: 350px;
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
	width: 190px;
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
.addition {
	background-color: rgb(108, 207, 108);
	width: 40px;
	margin: 0;
}
.addition:hover {
	background-color: rgb(49, 121, 49);
}
.deletion {
	background-color: rgb(194, 109, 109);
	width: 40px;
	margin: 0;
}
.deletion:hover {
	background-color: rgb(119, 51, 51);
}
.edit {
	background-color: rgb(106, 180, 223);
	width: 60px;
	margin: 0;
}
.edit:hover {
	background-color: rgb(48, 108, 143);
}
.roomsDiv {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	gap: 10px;
}
.roomDiv {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
}
</style>