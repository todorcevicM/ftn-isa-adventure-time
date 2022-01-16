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
				<!-- Spacer -->
				<div style="margin-top: 20px"></div>
				<div v-if="canUpload">
					<p>Add a new image:</p>
					<input
						type="file"
						@change="onFileChange"
					/>
					<button @click="uploadImage(cottage.id)">
						Upload
					</button>

				</div>
				<img
					v-if="uploadedImageToggle"
					class="itemImage"
					:src="addedImageSource(cottage.id)"
				/>
			</div>
			<div class="rightFlex">
				<p class="smallText">Name</p>
				<p v-if="!updateToggle">
					{{ cottage.name }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newCottage.name"
				/>
				<p class="smallText">Price per Day</p>
				<p v-if="!updateToggle">${{ cottage.pricePerDay }}.00 / Day</p>
				<input
					type="number"
					min="1"
					max="1000"
					v-if="updateToggle"
					v-model="newCottage.pricePerDay"
				/>
				<p class="smallText">Address</p>
				<p v-if="!updateToggle">
					{{ cottage.address }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newCottage.address"
				/>

				<p class="smallText">Longitude</p>
				<p v-if="!updateToggle">{{ cottage.geoLng }}</p>
				<input
					type="number"
					step="0.000001"
					v-model="newCottage.geoLng"
					v-if="updateToggle"
				/>
				<p class="smallText">Latitude</p>
				<p v-if="!updateToggle">{{ cottage.geoLat }}</p>
				<input
					type="number"
					step="0.000001"
					v-model="newCottage.geoLat"
					v-if="updateToggle"
				/>

				<p class="smallText">Promo</p>
				<p v-if="!updateToggle">
					{{ cottage.promoDescription }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newCottage.promoDescription"
				/>

				<p class="smallText">Rules</p>
				<p v-if="!updateToggle">
					{{ cottage.rules }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newCottage.rules"
				/>

				<p class="smallText">Extra Services</p>
				<p v-for="item in servicePrice" :key="item">
					{{ item.service }} : ${{ item.price }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newCottage.priceAndInfo"
				/>

				<p class="smallText">Rooms</p>
				<div class="roomsDiv">
					<div
						class="roomDiv"
						v-for="(item, key) in rooms"
						:key="item"
					>
						<p>
							Room {{ key + 1 }} : {{ item.numberOfBeds }} beds.
						</p>
						<button
							v-if="updateToggle"
							class="edit"
							@click="updateRoom(item.id)"
						>
							Edit
						</button>
						<button
							v-if="updateToggle"
							class="deletion"
							@click="deleteRoom(item.id)"
						>
							-
						</button>
					</div>
					<div class="roomDiv">
						<p></p>
						<button
							v-if="updateToggle"
							class="addition"
							@click="addRoom()"
						>
							+
						</button>
					</div>
				</div>

				<p class="smallText">Reservation Start</p>
				<p v-if="!updateToggle">
					{{ formattedReservationStart }}
				</p>
				<input
					type="date"
					v-if="updateToggle"
					v-model="newCottage.reservationStart"
				/>

				<p class="smallText">Reservation End</p>
				<p v-if="!updateToggle">
					{{ formattedReservationEnd }}
				</p>
				<input
					type="date"
					v-if="updateToggle"
					v-model="newCottage.reservationEnd"
				/>

				<p class="smallText">Person Limit</p>
				<p v-if="!updateToggle">{{ cottage.maxUsers }} People</p>
				<input
					type="number"
					min="1"
					max="10"
					v-if="updateToggle"
					v-model="newCottage.maxUsers"
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
							Extra Service {{ key + 1 }} : {{ item.service }} :
							${{ item.price }}.
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
						<button class="addition" @click="addService()">
							+
						</button>
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
		var uploadedImage = false;
		var canUpload = ref(null);
		var updateToggle = ref(null);

		var cottage = ref({
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

		// Formatiranje datuma
		let newStart = cottage.value.reservationStart.split("T");
		let newStartSecondPart = newStart[1].split(".")[0];
		var formattedReservationStart = newStartSecondPart + ", " + newStart[0];
		let newEnd = cottage.value.reservationEnd.split("T");
		let newEndSecondPart = newEnd[1].split(".")[0];
		var formattedReservationEnd = newEndSecondPart + ", " + newEnd[0];

		// Za punjenje input-a na pocetku
		var newCottage = cottage;

		var actionServiceToAdd = ref([]);

		var rooms = ref(null);
		axios
			.get("/api/rooms/getAllByCottageId/" + cottage.value.id)
			.then(function (response) {
				rooms.value = response.data;
			});

		var action = ref({
			start: "",
			end: "",
			price: "",
			validDuration: "",
			extraServices: "",
		});

		var uploadedImageToggle = ref(null);
		axios.post("/api/image/existsByIdAndType/" + cottage.value.id, "COTTAGE").then(function (response) {
			uploadedImageToggle.value = response.data;
			canUpload.value = !response.data;
		});

		return {
			cottage,
			newCottage,
			formattedReservationStart,
			formattedReservationEnd,
			rooms,
			updateToggle,
			uploadedImage,
			canUpload,
			selectedFile: null,
			action,
			actionServiceToAdd,
			servicePrice,
			uploadedImageToggle,

			imageSource(id) {
				try {
					return require("../../assets/images/cottage" + id + ".png");
				} catch (e) {
					return require("../../assets/images/default_cottage.png");
				}
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
				var cottageId = localStorage.id;
				axios
					.post("/api/rooms/create/" + cottageId, numOfBeds, {
						headers: {
							"Content-Type": "application/json",
						},
					})
					.then(function (response) {
						console.log(response.data);
						window.location.reload();
					});
			},
			updateRoom(roomId) {
				var numOfBeds = prompt("Enter new number of beds :");
				if (
					isNaN(numOfBeds.toString()) == true ||
					numOfBeds <= 0 ||
					numOfBeds >= 10
				) {
					alert("Please enter a correct number of beds.");
					return;
				}
				axios.post("/api/rooms/update/" + roomId, numOfBeds, {
					headers: {
						"Content-Type": "application/json",
					},
				});
				window.location.reload();
			},
			deleteRoom(roomId) {
				axios
					.post("/api/rooms/delete/" + roomId)
					.then(function (response) {
						if (response.data == false) {
							alert("This room is booked and cannot be deleted.");
							return;
						} else {
							alert("Room deleted.");
							window.location.reload();
						}
					});
			},
			updateDetails() {
				axios
					.get("/api/cottages/checkAvailable/" + cottage.value.id)
					.then(function (response) {
						if (!response.data) {
							alert(
								"This cottage is not available for editing as a booking currently exists."
							);
						} else {
							updateToggle.value = true;
						}
					});
			},
			sendUpdatedDetails() {
				if (
					this.newCottage.name == "" ||
					this.newCottage.pricePerDay == "" ||
					this.newCottage.address == "" ||
					this.newCottage.geoLat == "" ||
					this.newCottage.geoLng == "" ||
					this.newCottage.rules == "" ||
					this.newCottage.priceAndInfo == "" ||
					this.newCottage.promoDescription == "" ||
					this.newCottage.reservationStart == "" ||
					this.newCottage.reservationEnd == "" ||
					this.newCottage.maxUsers == ""
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

				axios
					.post("/api/cottages/update", this.newCottage)
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
				console.log(e);
				var files = e.target.files || e.dataTransfer.files;
				console.log(files[0]);
				if (!files.length) return;
				this.selectedFile = files[0];
				this.canUpload = true;
				console.log(this.canUpload);
			},
			uploadImage(id) {
				if (this.canUpload) {
					const formData = new FormData();
					formData.append('file', this.selectedFile);
					console.log("Form data : ");
					var name = "cottage_" + id;
					axios
						.post("/api/image/save/" + name, formData)
						.then(function (response) {
							console.log(response.data);
						});
					this.uploadedImageToggle = true;
					this.canUpload = false;
					window.location.reload();
				}
			},
			addedImageSource(id) {
				try {
					return require("../../assets/images/cottage_" +
						id +
						".png");
				} catch (err) {
					console.log(err);
					return;
				}
			},
			createAction() {
				let priceAndInfo = "";
				actionServiceToAdd.value.forEach((servicePrice) => {
					priceAndInfo +=
						servicePrice.service + ":" + servicePrice.price + ";";
				});
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
						boat: this.cottage.id,
						start: this.action.start,
						end: this.action.end,
						price: this.action.price,
						type: "COTTAGE",
						validDuration: this.action.validDuration,
						extraServices: this.action.extraServices,
					})
					.then(function (response) {
						console.log(response.data);
						alert("Quick booking created!");
					});
			},
			addService() {
				var serv = prompt("Enter a service: ");
				var pric = prompt("Enter a price: ");
				if (isNaN(pric.toString()) == true || pric <= 0) {
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
				if (isNaN(pric.toString()) == true || pric <= 0) {
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