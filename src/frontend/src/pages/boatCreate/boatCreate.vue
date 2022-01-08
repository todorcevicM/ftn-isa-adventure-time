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
			<div class="rightFlex">
				<p class="smallText">Name</p>
				<input type="text" v-model="newBoat.name" />

				<p class="smallText">Price per Day</p>
				<input
					type="number"
					min="1"
					max="1000"
					v-model="newBoat.pricePerDay"
				/>
				<p class="smallText">Type</p>
				<input type="text" v-model="newBoat.type" />
				<p class="smallText">Length</p>
				<input
					type="number"
					min="1"
					max="100"
					v-model="newBoat.boatLength"
				/>
				<p class="smallText">Engine Number</p>
				<input
					type="number"
					min="1"
					max="100"
					v-model="newBoat.engineNumber"
				/>
				<p class="smallText">Engine Power</p>
				<input
					type="number"
					min="1"
					max="900"
					v-model="newBoat.enginePower"
				/>
				<p class="smallText">Max Speed</p>
				<input
					type="number"
					min="1"
					max="500"
					v-model="newBoat.maxSpeed"
				/>

				<p class="smallText">Address</p>
				<input type="text" v-model="newBoat.address" />
				<p class="smallText">Longitude</p>
				<input type="number" step="0.000001" v-model="newBoat.geoLng" />
				<p class="smallText">Latitude</p>
				<input type="number" step="0.000001" v-model="newBoat.geoLat" />

				<p class="smallText">Promo</p>
				<input type="text" v-model="newBoat.promoDescription" />

				<p class="smallText">Rules</p>
				<input type="text" v-model="newBoat.rules" />

				<p class="smallText">Info</p>
				<input type="text" v-model="newBoat.priceAndInfo" />

				<p class="smallText">Reservation Start</p>
				<input type="date" v-model="newBoat.reservationStart" />

				<p class="smallText">Reservation End</p>
				<input type="date" v-model="newBoat.reservationEnd" />

				<p class="smallText">Navigation Equipment</p>
				<input type="text" v-model="newBoat.navigationEquipment" />

				<p class="smallText">Equipment</p>
				<input type="text" v-model="newBoat.equipment" />

				<p class="smallText">Percentage Taken if Cancelled</p>
				<input
					type="number"
					min="1"
					max="100"
					v-model="newBoat.percentTakenIfCancelled"
				/>

				<p class="smallText">Person Limit</p>
				<input
					type="number"
					min="1"
					max="10"
					v-model="newBoat.maxUsers"
				/>
				<!-- Spacer -->
				<div style="margin-top: 15px"></div>
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
		var newBoat = ref({
			// Ovo se prenosi
			ownerId: localStorage.userId,
			// Ovo se menja
			name: "",
			pricePerDay: "",
			address: "",
			geoLng: "",
			geoLat: "",
			promoDescription: "",
			rules: "",
			priceAndInfo: "",
			navigationEquipment: "",
			equipment: "",
			reservationStart: "",
			reservationEnd: "",
			maxUsers: "",
			type: "",
			length: "",
			engineNumber: "",
			enginePower: "",
			maxSpeed: "",
			percentTakenIfCancelled: "",
		});

		// Za u <template>
		return {
			newBoat,
			submit() {
				if (
					this.newBoat.name == "" ||
					this.newBoat.pricePerDay == "" ||
					this.newBoat.type == "" ||
					this.newBoat.boatLength == "" ||
					this.newBoat.engineNumber == "" ||
					this.newBoat.enginePower == "" ||
					this.newBoat.maxSpeed == "" ||
					this.newBoat.address == "" ||
					this.newBoat.geoLat == "" ||
					this.newBoat.geoLng == "" ||
					this.newBoat.rules == "" ||
					this.newBoat.priceAndInfo == "" ||
					this.newBoat.promoDescription == "" ||
					this.newBoat.maxUsers == "" ||
					this.newBoat.reservationStart == "" ||
					this.newBoat.reservationEnd == "" ||
					this.newBoat.navigationEquipment == "" ||
					this.newBoat.equipment == "" ||
					this.newBoat.percentTakenIfCancelled == ""
				) {
					alert("Please fill out all inputs.");
					return;
				}
				// isNaN je nepotreban jer se koristi input type="number"
				if (
					this.newBoat.pricePerDay < 1 ||
					this.newBoat.pricePerDay > 1000 ||
					this.newBoat.maxUsers < 1 ||
					this.newBoat.maxUsers > 10 ||
					this.newBoat.percentTakenIfCancelled < 0 ||
					this.newBoat.percentTakenIfCancelled > 100 ||
					this.newBoat.boatLength < 1 ||
					this.newBoat.boatLength > 100 ||
					this.newBoat.engineNumber < 1 ||
					this.newBoat.engineNumber > 100 ||
					this.newBoat.enginePower < 1 ||
					this.newBoat.enginePower > 900 ||
					this.newBoat.maxSpeed < 1 ||
					this.newBoat.maxSpeed > 500
				) {
					alert("Please fill out numerical inputs correctly.");
					return;
				}
				if (
					new Date(this.newBoat.reservationStart).getTime() >=
					new Date(this.newBoat.reservationEnd).getTime()
				) {
					alert("Please enter valid dates.");
					return;
				}

				axios
					.post("/api/boats/update", this.newBoat)
					.then(function (response) {
						console.log(response.data);
						alert("Boat is created!");
						window.location.assign(
							"/boatOwner/" + localStorage.emailHash
						);
					});
			},
		};
	},
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Aleo:wght@300;400&display=swap");
body {
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
.leftFlex img {
	width: 650px;
	height: 360px;
	border-radius: 15px;
	object-fit: cover;
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
input,
select {
	/* width: 260px; */
	/* height: 32px; */
	border-radius: 5px;
	border: 1px solid rgb(122, 122, 122);
	font-size: 20px;
	background-color: #f0f0f0;
}
input:focus,
select:focus {
	outline: none !important;
	border: 1px solid #ad6800;
}
input:invalid {
	border: 2px solid #b11919;
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