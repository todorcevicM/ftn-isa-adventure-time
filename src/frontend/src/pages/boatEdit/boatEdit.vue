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
					<img class="itemImage" :src="imageSource(boat.id)" />
				</div>
				<div>
					<img
						class="itemImage"
						src="../../assets/images/boatinterior.jpg"
					/>
				</div>
				<!-- Spacer -->
				<div style="margin-top: 20px"></div>
				<p v-if="!uploadedImage">Add a new image:</p>
				<input
					v-if="!uploadedImage"
					type="file"
					@change="onFileChange"
				/>
				<button v-if="canUpload" @click="uploadImage(boat.id)">
					Upload
				</button>

				<img
					v-if="uploadedImage"
					class="itemImage"
					:src="addedImageSource(boat.id)"
				/>
			</div>
			<div class="rightFlex">
				<p class="smallText">Name</p>
				<p v-if="!updateToggle">
					{{ boat.name }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.newName"
				/>

				<p class="smallText">Price per Day</p>
				<p v-if="!updateToggle">${{ boat.pricePerDay }}.00 / Day</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.newPricePerDay"
				/>

				<p class="smallText">Type</p>
				<p v-if="!updateToggle">
					{{ boat.type }}
				</p>
				<input
					type="text"
					v-model="newBoat.newType"
					v-if="updateToggle"
				/>
				<p class="smallText">Length</p>
				<p v-if="!updateToggle">
					{{ boat.length }}
				</p>
				<input
					type="text"
					v-model="newBoat.newLength"
					v-if="updateToggle"
				/>
				<p class="smallText">Engine Number</p>
				<p v-if="!updateToggle">
					{{ boat.engineNumber }}
				</p>
				<input
					type="text"
					v-model="newBoat.newEngineNumber"
					v-if="updateToggle"
				/>
				<p class="smallText">Engine Power</p>
				<p v-if="!updateToggle">
					{{ boat.enginePower }}
				</p>
				<input
					type="text"
					v-model="newBoat.newEnginePower"
					v-if="updateToggle"
				/>
				<p class="smallText">Max Speed</p>
				<p v-if="!updateToggle">
					{{ boat.maxSpeed }}
				</p>
				<input
					type="text"
					v-model="newBoat.newMaxSpeed"
					v-if="updateToggle"
				/>

				<p class="smallText">Address</p>
				<p v-if="!updateToggle">
					{{ boat.address }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.newAddress"
				/>

				<!-- TODO: I ovde i dole mora da se stavlja -->
				<!-- <p class="smallText">Longitude</p>
				<p>{{ boat.geoLng }}</p>
				<input
					type="text"
					v-model="newBoat.newGeoLng"
					v-if="updateToggle"
				/>
				<p class="smallText">Latitude</p>
				<p>{{ boat.geoLat }}</p>
				<input
					type="text"
					v-model="newBoat.newGeoLat"
					v-if="updateToggle"
				/> -->

				<p class="smallText">Promo</p>
				<p v-if="!updateToggle">
					{{ boat.promoDescription }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.newPromoDescription"
				/>

				<p class="smallText">Rules</p>
				<p v-if="!updateToggle">
					{{ boat.rules }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.newRules"
				/>

				<p class="smallText">Info</p>
				<p v-if="!updateToggle">
					{{ boat.priceAndInfo }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.newPriceAndInfo"
				/>

				<p class="smallText">Reservation Start</p>
				<p v-if="!updateToggle">
					{{ boat.reservationStart }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.newReservationStart"
				/>

				<p class="smallText">Reservation End</p>
				<p v-if="!updateToggle">
					{{ boat.reservationEnd }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.newReservationEnd"
				/>

				<p class="smallText">Navigation Equipment</p>
				<p v-if="!updateToggle">
					{{ boat.navigationEquipment }}
				</p>
				<input
					type="text"
					v-model="newBoat.newNavigationEquipment"
					v-if="updateToggle"
				/>

				<p class="smallText">Equipment</p>
				<p v-if="!updateToggle">{{ boat.equipment }}</p>
				<input
					type="text"
					v-model="newBoat.newEquipment"
					v-if="updateToggle"
				/>

				<p class="smallText">Person Limit</p>
				<p v-if="!updateToggle">{{ boat.maxUsers }} People</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.newMaxUsers"
				/>

				<p class="smallText">Percentage Taken if Cancelled</p>
				<p v-if="!updateToggle">
					{{ boat.percentTakenIfCancelled }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.newPercentTakenIfCancelled"
				/>

				<p class="smallText">Owner</p>
				<p>{{ owner.name }}</p>

				<button @click="updateDetails()" v-if="!updateToggle">
					Update Details
				</button>
				<button
					@click="sendUpdatedDetails()"
					v-if="updateToggle"
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
		var uploadedImage = ref(false);
		var canUpload = ref(false);
		var urlArray = window.location.href.split("/");
		var id = urlArray[4];
		console.log(id);

		var boat = ref(null);
		axios.get("/api/boats/get/" + id).then(function (response) {
			for (const key in response.data) {
				if (!(key === "password")) {
					localStorage.setItem(key, response.data[key]);
				}
			}
			boat.value = response.data;
			localStorage["boatOwner"] = boat.value.ownerId;

			// Formatiranje datuma
			let newStart = boat.value.reservationStart.split("T");
			let newStartSecondPart = newStart[1].split(".")[0];
			boat.value.reservationStart =
				newStartSecondPart + ", " + newStart[0];
			let newEnd = boat.value.reservationEnd.split("T");
			let newEndSecondPart = newEnd[1].split(".")[0];
			boat.value.reservationEnd = newEndSecondPart + ", " + newEnd[0];
		});

		var owner = ref(null);
		axios
			.get("/api/boatOwner/get/" + localStorage["boatOwner"])
			.then(function (response) {
				owner.value = response.data;
			});

		var updateToggle = ref(null);
		var newBoat = ref({
			newName: localStorage.name,
			newPricePerDay: localStorage.pricePerDay,
			newAddress: localStorage.address,
			newPromoDescription: localStorage.promoDescription,
			newRules: localStorage.rules,
			newPriceAndInfo: localStorage.priceAndInfo,
			newMaxUsers: localStorage.maxUsers,
			newReservationStart: localStorage.reservationStart,
			newReservationEnd: localStorage.reservationEnd,
			newNavigationEquipment: localStorage.navigationEquipment,
			newEquipment: localStorage.equipment,
			newPercentTakenIfCancelled: localStorage.percentTakenIfCancelled,
			newType: localStorage.type,
			newLength: localStorage.length,
			newEngineNumber: localStorage.engineNumber,
			newEnginePower: localStorage.enginePower,
			newMaxSpeed: localStorage.maxSpeed,
		});

		// Za u <template>
		return {
			boat,
			owner,
			updateToggle,
			newBoat,
			uploadedImage,
			canUpload,
			selectedFile: null,
			imageSource(id) {
				try {
					return require("../../assets/images/boat" + id + ".png");
				} catch (err) {
					return require("../../assets/images/boat1.png");
				}
			},
			updateDetails() {
				this.updateToggle = true;
			},
			sendUpdatedDetails() {
				if (
					this.newBoat.newName == "" ||
					this.newBoat.newPricePerDay == "" ||
					this.newBoat.type == "" ||
					this.newBoat.length == "" ||
					this.newBoat.engineNumber == "" ||
					this.newBoat.enginePower == "" ||
					this.newBoat.maxSpeed == "" ||
					this.newBoat.newAddress == "" ||
					this.newBoat.newRules == "" ||
					this.newBoat.newPriceAndInfo == "" ||
					this.newBoat.newPromoDescription == "" ||
					this.newBoat.newMaxUsers == "" ||
					this.newBoat.newReservationStart == "" ||
					this.newBoat.newReservationEnd == "" ||
					this.newBoat.newNavigationEquipment == "" ||
					this.newBoat.newEquipment == "" ||
					this.newBoat.newPercentTakenIfCancelled == ""
				) {
					alert("Please fill out all inputs.");
					return;
				}
				var sendingBoat = this.boat;
				sendingBoat.name = this.newBoat.newName;
				sendingBoat.pricePerDay = this.newBoat.newPricePerDay;
				sendingBoat.type = this.newBoat.newType;
				sendingBoat.length = this.newBoat.newLength;
				sendingBoat.engineNumber = this.newBoat.newEngineNumber;
				sendingBoat.enginePower = this.newBoat.newEnginePower;
				sendingBoat.maxSpeed = this.newBoat.newMaxSpeed;
				sendingBoat.address = this.newBoat.newAddress;
				sendingBoat.promoDescription = this.newBoat.newPromoDescription;
				sendingBoat.rules = this.newBoat.newRules;
				sendingBoat.priceAndInfo = this.newBoat.newPriceAndInfo;
				sendingBoat.maxUsers = this.newBoat.newMaxUsers;
				sendingBoat.reservationStart = this.newBoat.newReservationStart;
				sendingBoat.reservationEnd = this.newBoat.newReservationEnd;
				sendingBoat.navigationEquipment =
					this.newBoat.newNavigationEquipment;
				sendingBoat.equipment = this.newBoat.newEquipment;
				sendingBoat.percentTakenIfCancelled =
					this.newBoat.newPercentTakenIfCancelled;

				axios
					.post("/api/boats/update/", sendingBoat)
					.then(function (response) {
						console.log(response);
						console.log(response.data);
					});
				window.location.reload();
			},
			onFileChange(e) {
				var files = e.target.files || e.dataTransfer.files;
				if (!files.length) return;
				this.selectedFile = files[0];
			},
			uploadImage(id) {
				const newFormData = new FormData();
				newFormData.append("file", this.selectedFile);
				var api = "boat_" + id + ".png";
				axios
					.post("/api/image/save/" + api, newFormData, {})
					.then(function (response) {
						console.log(response.data);
					});
				this.uploadedImage = true;
				console.log(this.uploadedImage);
			},
			addedImageSource(id) {
				try {
					return require("../../assets/images/boat_" + id + ".png");
				} catch (err) {
					return require("../../assets/images/boat1.png");
				}
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
	/* width: 140px; */
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
	width: 260px;
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
</style>