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
				<input type="text" v-model="newAdventure.newName"/>
				<p>Address</p>
				<input type="text" v-model="newAdventure.newAddress"/>
				<p>Longitude</p>
				<input type="text" v-model="newAdventure.newGeoLng" />
				<p>Latitude</p>
				<input type="text" v-model="newAdventure.newGeoLat" />

				<p>Promo</p>
				<input type="text" v-model="newAdventure.newPromoDescription"/>

				<p>Rules</p>
				<input type="text" v-model="newAdventure.newRules"/>

				<p>Info</p>
				<input type="text" v-model="newAdventure.newPriceAndInfo"/>

				<p>Equipment</p>
				<input type="text" v-model="newAdventure.newEquipment"/>

				<p>Person limit</p>
				<input type="text" v-model="newAdventure.newMaxUsers"/>

				<input type="text" v-model="newAdventure.newPercentTakenIfCancelled"/>
				<p>% taken if cancelled</p>
				

				<p>Owner : {{ fishingInstructorName }}</p>

				<p>Bio :</p>
				<input type="text" v-model="newAdventure.newInstructorBio"/>

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
		var fishingInstructorName = ref(null);
		var fishingInstructorId = ref(null);

		axios
			.get("/api/fishingInstructor/getByEmail/" + emailHash)
			.then(function (response) {
				fishingInstructorName.value = response.data.name;
				fishingInstructorId.value = response.data.id;
				console.log(fishingInstructorName.value);
				localStorage.setItem("fishingInstructorId", response.data.id);
			});

		var newAdventure = ref({
			newName: "",
			newAddress: "",
			newGeoLng: "",
			newGeoLat: "",
			newPromoDescription: "",
			newRules: "",
			newPriceAndInfo: "",
			newEquipment: "",
			newMaxUsers: "",
			newPercentTakenIfCancelled: "",
			newInstructorBio: "",
			newInstructorId: localStorage.fishingInstructorId,
		});

		// Za u <template>
		return {
			emailHash,
			fishingInstructorName,
			fishingInstructorId,
			newAdventure,
			submit() {
				if (
					this.newAdventure.newName == "" ||
					this.newAdventure.newAddress == "" ||
					this.newAdventure.newGeoLng == "" ||
					this.newAdventure.newGeoLat == "" ||
					this.newAdventure.newPromoDescription == "" ||
					this.newAdventure.newRules == "" ||
					this.newAdventure.newPriceAndInfo == "" ||
					this.newAdventure.newEquipment == "" ||
					this.newAdventure.newMaxUsers == "" ||
					this.newAdventure.newInstructorBio == "" ||
					this.newAdventure.newPercentTakenIfCancelled == ""
				) {
					alert("Please fill out all inputs.");
					return;
				}
				var sendingAdventure = this.newAdventure;
				sendingAdventure.name = this.newAdventure.newName;
				sendingAdventure.address = this.newAdventure.newAddress;
				sendingAdventure.promoDescription =
					this.newAdventure.newPromoDescription;
				sendingAdventure.instructorBio =
					this.newAdventure.newInstructorBio;
				sendingAdventure.rules = this.newAdventure.newRules;
				sendingAdventure.priceAndInfo =
					this.newAdventure.newPriceAndInfo;
				sendingAdventure.equipment = this.newAdventure.newEquipment;
				sendingAdventure.maxUsers = this.newAdventure.newMaxUsers;
				sendingAdventure.percentTakenIfCancelled =
					this.newAdventure.newPercentTakenIfCancelled;

				axios
					.post("/api/adventures/update", sendingAdventure)
					.then(function (response) {
						console.log(response);
						console.log(response.data);
					});
				window.location.assign("/fishingInstructor/" + localStorage.emailHash);
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
</style><style>
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