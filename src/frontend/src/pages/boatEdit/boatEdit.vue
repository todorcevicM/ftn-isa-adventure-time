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
				<input type="text" v-if="updateToggle" v-model="newBoat.name" />

				<p class="smallText">Price per Day</p>
				<p v-if="!updateToggle">${{ boat.pricePerDay }}.00 / Day</p>
				<input
					type="number"
					min="1"
					max="1000"
					v-if="updateToggle"
					v-model="newBoat.pricePerDay"
				/>

				<p class="smallText">Type</p>
				<p v-if="!updateToggle">
					{{ boat.type }}
				</p>
				<input type="text" v-model="newBoat.type" v-if="updateToggle" />
				<p class="smallText">Length</p>
				<p v-if="!updateToggle">
					{{ boat.boatLength }}
				</p>
				<input
					type="number"
					min="1"
					max="100"
					v-model="newBoat.boatLength"
					v-if="updateToggle"
				/>
				<p class="smallText">Engine Number</p>
				<p v-if="!updateToggle">
					{{ boat.engineNumber }}
				</p>
				<input
					type="number"
					min="1"
					max="100"
					v-model="newBoat.engineNumber"
					v-if="updateToggle"
				/>
				<p class="smallText">Engine Power</p>
				<p v-if="!updateToggle">
					{{ boat.enginePower }}
				</p>
				<input
					type="number"
					min="1"
					max="900"
					v-model="newBoat.enginePower"
					v-if="updateToggle"
				/>
				<p class="smallText">Max Speed</p>
				<p v-if="!updateToggle">
					{{ boat.maxSpeed }}
				</p>
				<input
					type="number"
					min="1"
					max="500"
					v-model="newBoat.maxSpeed"
					v-if="updateToggle"
				/>
				<p class="smallText">Address</p>
				<p v-if="!updateToggle">
					{{ boat.address }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.address"
				/>

				<p class="smallText">Longitude</p>
				<p v-if="!updateToggle">{{ boat.geoLng }}</p>
				<input
					type="number"
					step="0.000001"
					v-model="newBoat.geoLng"
					v-if="updateToggle"
				/>
				<p class="smallText">Latitude</p>
				<p v-if="!updateToggle">{{ boat.geoLat }}</p>
				<input
					type="number"
					step="0.000001"
					v-model="newBoat.geoLat"
					v-if="updateToggle"
				/>

				<p class="smallText">Promo</p>
				<p v-if="!updateToggle">
					{{ boat.promoDescription }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.promoDescription"
				/>

				<p class="smallText">Rules</p>
				<p v-if="!updateToggle">
					{{ boat.rules }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.rules"
				/>

				<p class="smallText">Extra Services</p>
				<p v-for="item in servicePrice" :key="item">
					{{ item.service }} : ${{ item.price }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newBoat.priceAndInfo"
				/>

				<p class="smallText">Reservation Start</p>
				<p v-if="!updateToggle">
					{{ formattedReservationStart }}
				</p>
				<input
					type="date"
					v-if="updateToggle"
					v-model="newBoat.reservationStart"
				/>

				<p class="smallText">Reservation End</p>
				<p v-if="!updateToggle">
					{{ formattedReservationEnd }}
				</p>
				<input
					type="date"
					v-if="updateToggle"
					v-model="newBoat.reservationEnd"
				/>

				<p class="smallText">Navigation Equipment</p>
				<p v-if="!updateToggle">
					{{ boat.navigationEquipment }}
				</p>
				<input
					type="text"
					v-model="newBoat.navigationEquipment"
					v-if="updateToggle"
				/>

				<p class="smallText">Equipment</p>
				<p v-if="!updateToggle">{{ boat.equipment }}</p>
				<input
					type="text"
					v-model="newBoat.equipment"
					v-if="updateToggle"
				/>

				<p class="smallText">Person Limit</p>
				<p v-if="!updateToggle">{{ boat.maxUsers }} People</p>
				<input
					type="number"
					min="1"
					max="10"
					v-if="updateToggle"
					v-model="newBoat.maxUsers"
				/>

				<p class="smallText">Percentage Taken if Cancelled</p>
				<p v-if="!updateToggle">
					{{ boat.percentTakenIfCancelled }}
				</p>
				<input
					type="number"
					min="1"
					max="100"
					v-if="updateToggle"
					v-model="newBoat.percentTakenIfCancelled"
				/>

				<!-- Spacer -->
				<div style="margin-top: 15px"></div>
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
			<div class="rightFlex">
				<p>Create a Quick Booking</p>
				<p class="smallText">Start Date</p>
				<input type="date" v-model="action.start" />
				<p class="smallText">End Date</p>
				<input type="date" v-model="action.end" />
				<p class="smallText">Extra Services</p>
				<div class="roomsDiv">
					<div
						class="roomDiv"
						v-for="(item, key) in actionServiceToAdd"
						:key="item"
					>
						<p>
							Extra Service {{ key + 1 }} : {{ item.service }} : ${{ item.price }}.
						</p>
						<button class="edit" @click="updateService(key)">
							Edit
						</button>
						<button class="deletion" @click="deleteService(key)">
							-
						</button>
					</div>
					<div class="roomDiv">
						<p></p>
						<button class="addition" @click="addService()">+</button>
					</div>
				</div>
				<p class="smallText">Price ($)</p>
				<input
					type="number"
					v-model="action.price"
					min="1"
					max="1000"
				/>
				<p class="smallText">Duration (Days)</p>
				<input
					type="number"
					v-model="action.validDuration"
					min="1"
					max="1000"
				/>
				<button @click="createAction()" style="margin-top: 20px">
					Create
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
		var updateToggle = ref(null);

		var boat = ref({
			// Ovo se prenosi
			id: localStorage.id,
			ownerId: localStorage.ownerId,
			// Ovo se menja
			name: localStorage.name,
			pricePerDay: localStorage.pricePerDay,
			address: localStorage.address,
			geoLat: localStorage.geoLat,
			geoLng: localStorage.geoLng,
			promoDescription: localStorage.promoDescription,
			rules: localStorage.rules,
			priceAndInfo: localStorage.priceAndInfo,
			maxUsers: localStorage.maxUsers,
			reservationStart: localStorage.reservationStart,
			reservationEnd: localStorage.reservationEnd,
			navigationEquipment: localStorage.navigationEquipment,
			equipment: localStorage.equipment,
			percentTakenIfCancelled: localStorage.percentTakenIfCancelled,
			type: localStorage.type,
			boatLength: localStorage.boatLength,
			engineNumber: localStorage.engineNumber,
			enginePower: localStorage.enginePower,
			maxSpeed: localStorage.maxSpeed,
		});

		let priceAndInfoString = localStorage.priceAndInfo;
		let priceAndInfoArray = priceAndInfoString.split(";");
		let servicePrice = [];
		priceAndInfoArray.forEach((item, index) => {
			if (item.length > 0) {
				priceAndInfoArray[index] = item.split(":");
				servicePrice.push({
					service: priceAndInfoArray[index][0],
					price: priceAndInfoArray[index][1],
				});
			}
		});

		let newStart = boat.value.reservationStart.split("T");
		let newStartSecondPart = newStart[1].split(".")[0];
		var formattedReservationStart = newStartSecondPart + ", " + newStart[0];
		let newEnd = boat.value.reservationEnd.split("T");
		let newEndSecondPart = newEnd[1].split(".")[0];
		var formattedReservationEnd = newEndSecondPart + ", " + newEnd[0];

		// Za punjenje input-a na pocetku
		var newBoat = boat;

		var actionServiceToAdd = ref([]);

		var action = ref({
			start: "",
			end: "",
			price: "",
			validDuration: "",
			extraServices: "",
		});

		// Za u <template>
		return {
			boat,
			newBoat,
			formattedReservationStart,
			formattedReservationEnd,
			updateToggle,
			uploadedImage,
			canUpload,
			selectedFile: null,
			action,
			actionServiceToAdd,
			servicePrice,

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
					.post("/api/boats/update/", this.newBoat)
					.then(function (response) {
						for (const key in response.data) {
							if (!(key === "password")) {
								localStorage.setItem(key, response.data[key]);
							}
						}
						window.location.reload();
					});
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
			createAction() {
				// isNaN je nepotreban jer se koristi input type="number"
				let priceAndInfo = "";
				actionServiceToAdd.value.forEach(servicePrice => {
					priceAndInfo += servicePrice.service + ":" + servicePrice.price + ";";
				})
				this.action.extraServices = priceAndInfo;

				if (
					this.action.price < 1 ||
					this.action.price > 1000 ||
					this.action.validDuration < 1 ||
					this.action.validDuration > 1000
				) {
					alert("Please fill out numerical inputs correctly.");
					return;
				}
				if (
					new Date(this.action.start).getTime() >=
					new Date(this.action.end).getTime()
				) {
					alert("Please enter valid dates.");
					return;
				}
				axios
					.post("/api/booking/createAction", {
						boat: this.boat.id,
						start: this.action.start,
						end: this.action.end,
						price: this.action.price,
						type: "BOAT",
						validDuration: this.action.validDuration,
						extraServices: this.action.extraServices
					})
					.then(function (response) {
						console.log(response.data);
						alert("Quick booking created!");
					});
			},
			addService() {
				var serv = prompt("Enter a service: ");
				var pric = prompt("Enter a price: ");
				if (
					isNaN(pric.toString()) == true ||
					pric <= 0
				) {
					alert("Please enter a correct price.");
					return;
				}
				this.actionServiceToAdd.push({ 
					service: serv, 
					price: pric,	
				});
			},
			updateService(key) {
				var serv = prompt("Enter a new service: ");
				var pric = prompt("Enter a new price: ");
				if (
					isNaN(pric.toString()) == true ||
					pric <= 0
				) {
					alert("Please enter a correct price.");
					return;
				}
				this.actionServiceToAdd[key].service = serv;
				this.actionServiceToAdd[key].price = pric;
			},
			deleteService(key) {
				this.actionServiceToAdd.splice(key, 1);
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