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
				<input type="text" v-model="newCottage.name" />

				<p class="smallText">Price per Day</p>
				<input
					type="number"
					min="1"
					max="1000"
					v-model="newCottage.pricePerDay"
				/>
				<p class="smallText">Address</p>
				<input type="text" v-model="newCottage.address" />

				<p class="smallText">Longitude</p>
				<input
					type="number"
					step="0.000001"
					v-model="newCottage.geoLng"
				/>
				<p class="smallText">Latitude</p>
				<input
					type="number"
					step="0.000001"
					v-model="newCottage.geoLat"
				/>

				<p class="smallText">Promo</p>
				<input type="text" v-model="newCottage.promoDescription" />

				<p class="smallText">Rules</p>
				<input type="text" v-model="newCottage.rules" />

				<p class="smallText">Rooms</p>
				<div class="roomsDiv">
					<div
						class="roomDiv"
						v-for="(item, key) in roomsToAdd"
						:key="item"
					>
						<p>
							Room {{ key + 1 }} : {{ item.numberOfBeds }} beds.
						</p>
						<button class="edit" @click="updateRoom(key)">
							Edit
						</button>
						<button class="deletion" @click="deleteRoom(key)">
							-
						</button>
					</div>
					<div class="roomDiv">
						<p></p>
						<button class="addition" @click="addRoom()">+</button>
					</div>
				</div>

				<p class="smallText">Extra Services</p>
				<div class="roomsDiv">
					<div
						class="roomDiv"
						v-for="(item, key) in servicesToAdd"
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

				<p class="smallText">Reservation Start</p>
				<input type="date" v-model="newCottage.reservationStart" />

				<p class="smallText">Reservation End</p>
				<input type="date" v-model="newCottage.reservationEnd" />

				<p class="smallText">Person Limit</p>
				<input
					type="number"
					min="1"
					max="10"
					v-model="newCottage.maxUsers"
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
		var roomsToAdd = ref([]);

		var newCottage = ref({
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
			reservationStart: "",
			reservationEnd: "",
			maxUsers: "",
		});

		var servicesToAdd = ref([]);

		// Za u <template>
		return {
			newCottage,
			roomsToAdd,
			servicesToAdd,

			submit() {
				if (
					this.newCottage.name == "" ||
					this.newCottage.pricePerDay == "" ||
					this.newCottage.address == "" ||
					this.newCottage.rules == "" ||
					this.newCottage.promoDescription == "" ||
					this.newCottage.reservationStart == "" ||
					this.newCottage.reservationEnd == "" ||
					this.newCottage.maxUsers == "" ||
					this.newCottage.geoLng == "" ||
					this.newCottage.geoLat == ""
				) {
					alert("Please fill out all inputs.");
					return;
				}
				// isNaN je nepotreban jer se koristi input type="number"
				if (
					this.newCottage.pricePerDay < 1 ||
					this.newCottage.pricePerDay > 1000 ||
					this.newCottage.maxUsers < 1 ||
					this.newCottage.maxUsers > 10
				) {
					alert("Please fill out numerical inputs correctly.");
					return;
				}
				if (
					new Date(this.newCottage.reservationStart).getTime() >=
					new Date(this.newCottage.reservationEnd).getTime()
				) {
					alert("Please enter valid dates.");
					return;
				}
				if (this.roomsToAdd.length == 0) {
					alert("Please add at least one room.");
					return;
				}

				let priceAndInfo = "";
				servicesToAdd.value.forEach(servicePrice => {
					priceAndInfo += servicePrice.service + ":" + servicePrice.price + ";";
				})
				this.newCottage.priceAndInfo = priceAndInfo;

				axios
					.post("/api/cottages/update", this.newCottage)
					.then(function (response1) {
						axios
							.post(
								"/api/rooms/createAll/" + response1.data.id,
								roomsToAdd.value
							)
							.then(function (response2) {
								console.log("Response 2 : ");
								console.log(response2.data);
								alert("Cottage is created!");
								window.location.assign(
									"/cottageOwner/" + localStorage.emailHash
								);
							});
					});
			},
			addRoom() {
				var numOfBeds = prompt("Enter number of beds: ");
				if (
					isNaN(numOfBeds.toString()) == true ||
					numOfBeds <= 0 ||
					numOfBeds >= 10
				) {
					alert("Please enter a correct number of beds.");
					return;
				}
				this.roomsToAdd.push({ numberOfBeds: numOfBeds });
			},
			updateRoom(key) {
				var numOfBeds = prompt("Enter new number of beds :");
				if (
					isNaN(numOfBeds.toString()) == true ||
					numOfBeds <= 0 ||
					numOfBeds >= 10
				) {
					alert("Please enter a correct number of beds.");
					return;
				}
				this.roomsToAdd[key].numberOfBeds = numOfBeds;
			},
			deleteRoom(key) {
				this.roomsToAdd.splice(key, 1);
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
				this.servicesToAdd.push({ 
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
				this.servicesToAdd[key].service = serv;
				this.servicesToAdd[key].price = pric;
			},
			deleteService(key) {
				this.servicesToAdd.splice(key, 1);
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