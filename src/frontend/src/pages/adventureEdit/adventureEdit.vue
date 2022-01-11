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
				<!-- Spacer -->
				<div style="margin-top: 20px"></div>
				<p v-if="!uploadedImage">Add a new image:</p>
				<input
					v-if="!uploadedImage"
					type="file"
					@change="onFileChange"
				/>
				<button v-if="canUpload" @click="uploadImage(adventure.id)">
					Upload
				</button>

				<img
					v-if="uploadedImage"
					class="itemImage"
					:src="addedImageSource(adventure.id)"
				/>

				<div>
					<p>Create an action</p>
					<p class="smallText">Action Start</p>
					<input type="date" v-model="action.start" />
					<p class="smallText">Action End</p>
					<input type="date" v-model="action.end" />
					<p class="smallText">Action Price</p>
					<input type="number" v-model="action.price" />
					<p class="smallText">Action Duration in Days</p>
					<input type="number" v-model="action.validDuration" />
					<button @click="createAction()">Create</button>
				</div>
			</div>
			<div class="rightFlex">
				<p class="smallText">Name</p>
				<p v-if="!updateToggle">
					{{ adventure.name }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newAdventure.name"
				/>
				<p class="smallText">Price per Day</p>
				<p v-if="!updateToggle">
					${{ adventure.pricePerDay }}.00 / Day
				</p>
				<input
					type="number"
					min="1"
					max="1000"
					v-if="updateToggle"
					v-model="newAdventure.pricePerDay"
				/>
				<p class="smallText">Address</p>
				<p v-if="!updateToggle">
					{{ adventure.address }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newAdventure.address"
				/>

				<p class="smallText">Longitude</p>
				<p v-if="!updateToggle">{{ adventure.geoLng }}</p>
				<input
					type="number"
					step="0.000001"
					v-model="newAdventure.geoLng"
					v-if="updateToggle"
				/>
				<p class="smallText">Latitude</p>
				<p v-if="!updateToggle">{{ adventure.geoLat }}</p>
				<input
					type="number"
					step="0.000001"
					v-model="newAdventure.geoLat"
					v-if="updateToggle"
				/>

				<p class="smallText">Location</p>
				<p v-if="!updateToggle">{{ adventure.location }}</p>
				<input
					type="text"
					v-model="newAdventure.location"
					v-if="updateToggle"
				/>

				<p class="smallText">Promo</p>
				<p v-if="!updateToggle">
					{{ adventure.promoDescription }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newAdventure.promoDescription"
				/>

				<p class="smallText">Rules</p>
				<p v-if="!updateToggle">
					{{ adventure.rules }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newAdventure.rules"
				/>

				<p class="smallText">Info</p>
				<p v-if="!updateToggle">
					{{ adventure.priceAndInfo }}
				</p>
				<input
					type="text"
					v-model="newAdventure.priceAndInfo"
					v-if="updateToggle"
				/>

				<p class="smallText">Equipment</p>
				<p v-if="!updateToggle">
					{{ adventure.equipment }}
				</p>
				<input
					type="text"
					v-model="newAdventure.equipment"
					v-if="updateToggle"
				/>

				<p class="smallText">Person Limit</p>
				<p v-if="!updateToggle">{{ adventure.maxUsers }} People</p>
				<input
					type="number"
					min="1"
					max="10"
					v-if="updateToggle"
					v-model="newAdventure.maxUsers"
				/>

				<p class="smallText">Percentage Taken if Cancelled</p>
				<p v-if="!updateToggle">
					{{ adventure.percentTakenIfCancelled }}
				</p>
				<input
					type="number"
					min="0"
					max="100"
					v-if="updateToggle"
					v-model="newAdventure.percentTakenIfCancelled"
				/>

				<p class="smallText">Instructor Bio</p>
				<p v-if="!updateToggle">
					{{ adventure.instructorBio }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newAdventure.instructorBio"
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

		var adventure = ref({
			// Ovo se prenosi
			id: localStorage.id,
			instructorId: localStorage.instructorId,
			// Ovo se menja
			name: localStorage.name,
			pricePerDay: localStorage.pricePerDay,
			address: localStorage.address,
			geoLat: localStorage.geoLat,
			geoLng: localStorage.geoLng,
			location: localStorage.location,
			promoDescription: localStorage.promoDescription,
			instructorBio: localStorage.instructorBio,
			rules: localStorage.rules,
			priceAndInfo: localStorage.priceAndInfo,
			maxUsers: localStorage.maxUsers,
			equipment: localStorage.equipment,
			percentTakenIfCancelled: localStorage.percentTakenIfCancelled,
		});

		// Adventure nema Date-a za formatiranje

		// Za punjenje input-a na pocetku
		var newAdventure = adventure;

		var action = ref({
			start: "",
			end: "",
			price: "",
			validDuration: "",
		});

		// Za u <template>
		return {
			adventure,
			newAdventure,
			updateToggle,
			uploadedImage,
			canUpload,
			selectedFile: null,
			action,
			imageSource(id) {
				try {
					return require("../../assets/images/adventure" +
						id +
						".png");
				} catch (err) {
					return require("../../assets/images/adventure1.png");
				}
			},
			updateDetails() {
				this.updateToggle = true;
			},
			sendUpdatedDetails() {
				if (
					this.newAdventure.name == "" ||
					this.newAdventure.pricePerDay == "" ||
					this.newAdventure.address == "" ||
					this.newAdventure.geoLat == "" ||
					this.newAdventure.geoLng == "" ||
					this.newAdventure.location == "" ||
					this.newAdventure.rules == "" ||
					this.newAdventure.priceAndInfo == "" ||
					this.newAdventure.promoDescription == "" ||
					this.newAdventure.equipment == "" ||
					this.newAdventure.maxUsers == "" ||
					this.newAdventure.percentTakenIfCancelled == "" ||
					this.newAdventure.instructorBio == ""
				) {
					alert("Please fill out all inputs.");
					return;
				}
				// isNaN je nepotreban jer se koristi input type="number"
				if (
					this.newAdventure.pricePerDay < 1 ||
					this.newAdventure.pricePerDay > 1000 ||
					this.newAdventure.maxUsers < 1 ||
					this.newAdventure.maxUsers > 10 ||
					this.newAdventure.percentTakenIfCancelled < 0 ||
					this.newAdventure.percentTakenIfCancelled > 100
				) {
					alert("Please fill out numerical inputs correctly.");
					return;
				}

				axios
					.post("/api/adventures/update/", this.newAdventure)
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
				var api = "adventure_" + id + ".png";
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
					return require("../../assets/images/adventure_" +
						id +
						".png");
				} catch (err) {
					return require("../../assets/images/adventure1.png");
				}
			},
			createAction() {
				axios
					.post("/api/booking/createAction", {
						boat: this.adventure.id,
						start: this.action.start,
						end: this.action.end,
						price: this.action.price,
						type: "ADVENTURE",
						validDuration: this.action.validDuration,
					})
					.then(function (response) {
						console.log(response.data);
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