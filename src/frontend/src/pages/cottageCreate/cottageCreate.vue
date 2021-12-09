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
				<input type="text" v-model="newCottage.newName" />

				<p>Address</p>
				<input type="text" v-model="newCottage.newAddress" />

				<p>Longitude</p>
				<input type="text" v-model="newCottage.newGeoLng" />
				<p>Latitude</p>
				<input type="text" v-model="newCottage.newGeoLat" />

				<p>Promo</p>
				<input type="text" v-model="newCottage.newPromoDescription" />

				<p>Rules</p>
				<input type="text" v-model="newCottage.newRules" />

				<p>Info</p>
				<input type="text" v-model="newCottage.newPriceAndInfo" />

				<p>Rooms :</p>
				<div class="roomsDiv">
					<div
						class="roomDiv"
						v-for="(item, key) in roomsToAdd"
						:key="item"
					>
						<p class="smallText">
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

				<p>Start</p>
				<input type="text" v-model="newCottage.newReservationStart" />

				<p>End</p>
				<input type="text" v-model="newCottage.newReservationEnd" />

				<p>Person limit</p>
				<input type="text" v-model="newCottage.newMaxUsers" />

				<p>Owner : {{ cottageOwnerName }}</p>

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
		var cottageOwnerName = ref(null);
		var cottageOwnerId = ref(null);
		axios
			.get("/api/cottageOwner/getByEmail/" + emailHash)
			.then(function (response) {
				cottageOwnerName.value = response.data.name;
				cottageOwnerId.value = response.data.id;
				localStorage.setItem("cottageOwnerId", response.data.id);
			});

		var newCottage = ref({
			newName: "",
			newAddress: "",
			newGeoLng: "",
			newGeoLat: "",
			newPromoDescription: "",
			newRules: "",
			newPriceAndInfo: "",
			newReservationStart: "",
			newReservationEnd: "",
			newMaxUsers: "",
			newOwnerId: localStorage.cottageOwnerId,
		});
		var roomsToAdd = ref([]);

		// Za u <template>
		return {
			emailHash,
			cottageOwnerName,
			cottageOwnerId,
			newCottage,
			roomsToAdd,
			submit() {
				// POST za cottage koji vraca id
				// POST za sobe sa tim id-em

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
				if (this.roomsToAdd.length == 0) {
					alert("Please add at least one room.");
					return;
				}

				var sendingCottage = this.newCottage;
				// var sendingCottage = this.newCottage.value;
				sendingCottage.name = this.newCottage.newName;
				sendingCottage.address = this.newCottage.newAddress;
				sendingCottage.rules = this.newCottage.newRules;
				sendingCottage.priceAndInfo = this.newCottage.newPriceAndInfo;
				sendingCottage.promoDescription =
					this.newCottage.newPromoDescription;
				sendingCottage.reservationStart =
					this.newCottage.newReservationStart;
				sendingCottage.reservationEnd =
					this.newCottage.newReservationEnd;
				sendingCottage.maxUsers = this.newCottage.newMaxUsers;
				sendingCottage.ownerId = this.newCottage.newOwnerId;

				axios
					.post("/api/cottages/update", sendingCottage)
					.then(function (response1) {
						console.log("Response 1 : ");
						console.log(response1.data);
						console.log(response1.data.id);
						axios
							.post(
								"/api/rooms/createAll/" + response1.data.id,
								roomsToAdd.value
							)
							.then(function (response2) {
								console.log("Response 2 : ");
								console.log(response2.data);
							});
					});

				alert("Cottage is created!");
				window.location.assign(
					"/cottageOwner/" + localStorage.emailHash
				);
			},
			addRoom() {
				var numOfBeds = prompt("Enter number of beds: ");
				this.roomsToAdd.push({ numberOfBeds: numOfBeds });
				console.log(this.roomsToAdd);
			},
			deleteRoom(key) {
				this.roomsToAdd.splice(key, 1);
			},
			updateRoom(key) {
				var updatedValue = prompt("Enter number of rooms");
				this.roomsToAdd[key].numberOfBeds = updatedValue;
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