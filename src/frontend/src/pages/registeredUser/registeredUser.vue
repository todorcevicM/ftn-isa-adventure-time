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
				<h4>{{ user.name }} {{ user.lastname }}</h4>
				<p>Registered User</p>
				<p style="font-size: 18px">{{ user.email }}</p>
				<p style="color: rgb(96, 96, 236)">Platinum Account</p>
				<p>Penalties: 0</p>
				<!-- Spacer -->
				<div style="height: 40px"></div>
				<div class="percentage">
					<p>Business Reports</p>
					<button @click="wantsDeletion()" style="width: 200px">
						Show Reports
					</button>
				</div>
			</div>
			<div class="rightFlex">
				<p>Address</p>
				<p class="smallText" v-if="!updateToggle">{{ user.address }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newAddress"
				/>

				<p>City</p>
				<p class="smallText" v-if="!updateToggle">{{ user.city }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newCity"
				/>

				<p>Country</p>
				<p class="smallText" v-if="!updateToggle">{{ user.country }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newCountry"
				/>

				<p>Telephone Number</p>
				<p class="smallText" v-if="!updateToggle">
					{{ user.telephoneNumber }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newTelephoneNumber"
				/>

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
		<div class="lowerFlex">
			<!-- past boat booking -->
			<div class="table">
				<h3>Past Boat Bookings</h3>
				<button>Sort</button>
				<div
					class="tableEntry"
					v-for="pbb in pastBoatBookings"
					:key="pbb"
				>
					<p class="entryName">{{ pbb.start }}</p>
					<p class="entryRequestText">{{ pbb.end }}</p>
					<p>{{ pbb.extraService }}</p>
					<p>Cena: {{ pbb.price }}.00</p>
					<button class="entryApprove" @click="approve()">
						Approve
					</button>
					<button class="entryDeny" @click="deny()">Deny</button>
				</div>
			</div>
			<!-- past adventure booking -->
			<div class="table">
				<h3>Past Adventure Bookings</h3>
				<button>Sort</button>
				<div
					class="tableEntry"
					v-for="pab in pastAdventureBookings"
					:key="pab"
				>
					<p class="entryName">{{ pab.start }}</p>
					<p class="entryRequestText">{{ pab.end }}</p>
					<p>{{ pab.extraService }}</p>
					<p>Cena: {{ pab.price }}.00</p>
					<button class="entryApprove" @click="approve()">
						Approve
					</button>
					<button class="entryDeny" @click="deny()">Deny</button>
				</div>
			</div>
			<!-- past room booking -->
			<div class="table">
				<h3>Past Room Bookings</h3>
				<button>Sort</button>
				<div
					class="tableEntry"
					v-for="prb in pastRoomBookings"
					:key="prb"
				>
					<p class="entryName">{{ prb.start }}</p>
					<p class="entryRequestText">{{ prb.end }}</p>
					<p>{{ prb.extraService }}</p>
					<p>Cena: {{ prb.price }}.00</p>
					<button class="entryApprove" @click="approve()">
						Approve
					</button>
					<button class="entryDeny" @click="deny()">Deny</button>
				</div>
			</div>

			<!-- future boat booking -->
			<div class="table">
				<h3>Future Boat Bookings</h3>
				<button>Sort</button>
				<div
					class="tableEntry"
					v-for="pbb in futureBoatBookings"
					:key="pbb"
				>
					<p class="entryName">{{ pbb.start }}</p>
					<p class="entryRequestText">{{ pbb.end }}</p>
					<p>{{ pbb.extraService }}</p>
					<p>Cena: {{ pbb.price }}.00</p>
					<button class="entryApprove" @click="approve()">
						Cancel
					</button>
					<button class="entryDeny" @click="deny()">Deny</button>
				</div>
			</div>
			<!-- future adventure booking -->
			<div class="table">
				<h3>Future Adventure Bookings</h3>
				<button>Sort</button>
				<div
					class="tableEntry"
					v-for="pab in futureAdventureBookings"
					:key="pab"
				>
					<p class="entryName">{{ pab.start }}</p>
					<p class="entryRequestText">{{ pab.end }}</p>
					<p>{{ pab.extraService }}</p>
					<p>Cena: {{ pab.price }}.00</p>
					<button class="entryApprove" @click="approve()">
						Cancel
					</button>
					<button class="entryDeny" @click="deny()">Deny</button>
				</div>
			</div>
			<!-- future room booking -->
			<div class="table">
				<h3>Future Room Bookings</h3>
				<button>Sort</button>
				<div
					class="tableEntry"
					v-for="prb in futureAdventureBookings"
					:key="prb"
				>
					<p class="entryName">{{ prb.start }}</p>
					<p class="entryRequestText">{{ prb.end }}</p>
					<p>{{ prb.extraService }}</p>
					<p>Cena: {{ prb.price }}.00</p>
					<button class="entryApprove" @click="approve()">
						Cancel
					</button>
					<button class="entryDeny" @click="deny()">Deny</button>
				</div>
			</div>

			<!-- sub boat -->
			<div class="table">
				<h3>Subscribed Boat</h3>
				<button>Sort</button>
				<div class="tableEntry" v-for="pbb in subBoat" :key="pbb">
					<p class="entryName">{{ pbb.name }}</p>
					<p class="entryRequestText">{{ pbb.promoDescription }}</p>
					<p>{{ pbb.extraService }}</p>
					<p>Cena: {{ pbb.pricePerDay }}.00</p>
					<button class="entryApprove" @click="approve()">
						Approve
					</button>
					<button class="entryDeny" @click="deny()">Deny</button>
				</div>
			</div>
			<!-- sub adventure -->
			<div class="table">
				<h3>Subscribed Adventure</h3>
				<button>Sort</button>
				<div class="tableEntry" v-for="pab in subAdventure" :key="pab">
					<p class="entryName">{{ pab.name }}</p>
					<p class="entryRequestText">{{ pab.promoDescription }}</p>
					<p>{{ pab.extraService }}</p>
					<p>Cena: {{ pab.pricePerDay }}.00</p>
					<button class="entryApprove" @click="approve()">
						Approve
					</button>
					<button class="entryDeny" @click="deny()">Deny</button>
				</div>
			</div>
			<!-- sub cottage -->
			<div class="table">
				<h3>Subscribed Cottage</h3>
				<button>Sort</button>
				<div class="tableEntry" v-for="prb in subCottage" :key="prb">
					<p class="entryName">{{ prb.name }}</p>
					<p class="entryRequestText">{{ prb.promoDescription }}</p>
					<p>{{ prb.extraService }}</p>
					<p>Cena: {{ prb.pricePerDay }}.00</p>
					<button class="entryApprove" @click="approve()">
						Approve
					</button>
					<button class="entryDeny" @click="deny()">Deny</button>
				</div>
			</div>

			<!-- Spacer -->
			<div style="height: 80px"></div>
			<button @click="wantsDeletion()">Submit a Complaint</button>
			<!-- Password Change -->
			<button @click="showPasswordChange()" v-if="!passwordChangeToggle">
				Change My Password
			</button>
			<div v-if="passwordChangeToggle" class="passwordChange">
				<div
					style="
						display: flex;
						flex-direction: row;
						justify-content: space-around;
					"
				>
					<div>
						<p>Enter new password :</p>
						<input type="password" v-model="firstPassword" />
					</div>
					<div>
						<p>Repeat password :</p>
						<input
							type="password"
							v-model="repeatPassword"
							@input="
								passwordMatchCheck(
									firstPassword,
									repeatPassword
								)
							"
						/>
					</div>
				</div>
				<div class="spacer">
					<p>{{ matching }}</p>
				</div>
				<button @click="updatePassword()">Update</button>
			</div>
			<button @click="wantsDeletion()">Delete My Account</button>
		</div>
	</div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
export default {
	setup() {
		var user = ref(null);
		axios
			.get("/api/registeredUser/getByEmail/" + localStorage["emailHash"])
			.then(function (response) {
				user.value = response.data;
				localStorage["userId"] = user.value.id;
			});
		var newUser = ref({
			newAddress: localStorage.address,
			newCity: localStorage.city,
			newCountry: localStorage.country,
			newTelephoneNumber: localStorage.telephoneNumber,
		});
		var updateToggle = ref(null);

		var firstPassword = ref(null);
		var repeatPassword = ref(null);
		var matching = ref(null);
		var passwordChangeToggle = ref(null);

		var pastBoatBookings = ref(null);
		axios
			.get(
				"/api/registeredUser/pastBoatBookings/" + localStorage["userId"]
			)
			.then(function (response) {
				pastBoatBookings.value = response.data;
			});

		var pastAdventureBookings = ref(null);
		axios
			.get(
				"/api/registeredUser/pastAdventureBookings/" +
					localStorage["userId"]
			)
			.then(function (response) {
				pastAdventureBookings.value = response.data;
			});

		var pastRoomBookings = ref(null);
		axios
			.get(
				"/api/registeredUser/pastRoomBookings/" + localStorage["userId"]
			)
			.then(function (response) {
				pastRoomBookings.value = response.data;
			});

		var futureBoatBookings = ref(null);
		axios
			.get(
				"/api/registeredUser/futureBoatBookings/" +
					localStorage["userId"]
			)
			.then(function (response) {
				futureBoatBookings.value = response.data;
			});

		var futureAdventureBookings = ref(null);
		axios
			.get(
				"/api/registeredUser/futureAdventureBookings/" +
					localStorage["userId"]
			)
			.then(function (response) {
				futureAdventureBookings.value = response.data;
			});

		var futureRoomBookings = ref(null);
		axios
			.get(
				"/api/registeredUser/futureRoomBookings/" +
					localStorage["userId"]
			)
			.then(function (response) {
				futureRoomBookings.value = response.data;
			});

		var subBoat = ref(null);
		axios
			.get("/api/registeredUser/subBoat/" + localStorage["userId"])
			.then(function (response) {
				console.log(response.data);
				subBoat.value = response.data;
			});

		var subAdventure = ref(null);
		axios
			.get("/api/registeredUser/subAdventure/" + localStorage["userId"])
			.then(function (response) {
				subAdventure.value = response.data;
			});

		var subCottage = ref(null);
		axios
			.get("/api/registeredUser/subCottage/" + localStorage["userId"])
			.then(function (response) {
				subCottage.value = response.data;
			});

		return {
			user,
			pastBoatBookings,
			pastAdventureBookings,
			pastRoomBookings,
			futureBoatBookings,
			futureAdventureBookings,
			futureRoomBookings,
			subBoat,
			subAdventure,
			subCottage,
			newUser,
			updateToggle,
			firstPassword,
			repeatPassword,
			matching,
			passwordChangeToggle,
			wantsDeletion() {
				alert("Not implemented yet!");
			},
			updateDetails() {
				this.updateToggle = true;
			},
			sendUpdatedDetails() {
				if (
					this.newUser.newAddress == "" ||
					this.newUser.newCity == "" ||
					this.newUser.newCountry == "" ||
					this.newUser.newTelephoneNumber == ""
				) {
					alert("Please fill out all inputs.");
					return;
				}
				var sendingUser = this.user;
				sendingUser.address = this.newUser.newAddress;
				sendingUser.city = this.newUser.newCity;
				sendingUser.country = this.newUser.newCountry;
				sendingUser.telephoneNumber = this.newUser.newTelephoneNumber;
				sendingUser.userType = "registeredUser";
				console.log(sendingUser);
				axios
					.post("/api/user/update", sendingUser)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
					});
				window.location.reload();
			},
			passwordMatchCheck(firstPassword, repeatPassword) {
				if (firstPassword == repeatPassword) {
					this.matching = "Passwords Match!";
					return this.matching;
				} else {
					this.matching = "Passwords don't match!";
					return this.matching;
				}
			},
			updatePassword() {
				if (this.matching == "Passwords don't match!") {
					alert("Passwords don't match!");
					return;
				} else if (this.repeatPassword == null) {
					alert("All fields need to be filled.");
					return;
				}
				var sendingUser = this.user;
				sendingUser.password = this.repeatPassword;
				sendingUser.userType = "registeredUser";
				console.log(sendingUser);
				axios
					.post("/api/user/update", sendingUser)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
					});
				window.location.reload();
			},
			showPasswordChange() {
				this.passwordChangeToggle = true;
			},
		};
	},
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Aleo:wght@400&display=swap");

body {
	/* background-image: url("../../assets/adventure-time-background.jpg"); */
	background-color: #e6e4df;
	background-size: 100%;
	background-repeat: no-repeat;
	color: #10120e;
	font-family: Aleo;
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
	margin: 50px 200px 0px;
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

h3 {
	margin: 0;
	font-weight: 400;
	font-size: 35px;
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

.rightFlex,
.firstLogin,
.passwordChange {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	background-color: rgb(241, 241, 241);
	padding: 20px;
	border-radius: 15px;
	border: 2px solid #da9e46;
}

.firstLogin {
	margin: 20px 40em;
}

.passwordChange {
	margin: 20px 28em;
}

.rightFlex p,
.firstLogin p,
.passwordChange p {
	margin: 4px 0;
	font-size: 25px;
}

.rightFlex .smallText {
	margin: 0;
	font-size: 22px;
}

.rightFlex input,
.firstLogin input,
.passwordChange input,
.percentage input {
	height: 24px;
	border-radius: 5px;
	border: 1px solid rgb(122, 122, 122);
	font-size: 18px;
	background-color: #f0f0f0;
}

.rightFlex input:focus,
.firstLogin input:focus,
.passwordChange input:focus,
.percentage input:focus {
	outline: none !important;
	border: 1px solid #ad6800;
}

.firstLogin button,
.passwordChange button {
	margin-top: 10px;
	width: 170px;
}

.firstLogin .spacer,
.passwordChange .spacer {
	height: 30px;
	text-align: center;
}

.firstLogin .spacer p,
.passwordChange .spacer p {
	font-size: 16px;
	color: gray;
	margin: 0;
	margin-top: 6px;
}

button {
	margin: 0 auto;
	height: 40px;
	width: 290px;
	background-color: #da9e46;
	border: none;
	border-radius: 4px;
	font-family: Aleo;
	font-size: 24px;
	transition: 0.15s;
	margin-top: 20px;
}

button:hover {
	background-color: #9e6b1d;
	color: white;
	cursor: pointer;
}

.lowerFlex {
	margin: 0px 200px;
	display: flex;
	flex-direction: column;
}

.lowerFlex h3 {
	border-bottom: solid 1px rgb(145, 145, 145);
}

.table {
	margin-top: 20px;
}

.tableEntry {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	height: 55px;
	border-bottom: solid 1px rgb(145, 145, 145);
}

.tableEntry .entryName {
	margin: auto 0;
	width: 180px;
}

.tableEntry .entryRequestText {
	width: 800px;
	margin: auto 0;
}

.tableEntry button {
	width: 110px;
	margin: auto 0;
	font-size: 20px;
}

.entryApprove {
	background-color: rgb(108, 207, 108);
}

.entryApprove:hover {
	background-color: rgb(49, 121, 49);
}

.entryDeny {
	background-color: rgb(194, 109, 109);
}

.entryDeny:hover {
	background-color: rgb(119, 51, 51);
}

.percentage,
.businessReports {
	display: flex;
	flex-direction: row;
	align-items: center;
}

.percentage input,
.businessReports input {
	width: 40px;
	margin: 0 10px;
	text-align: center;
	font-size: 22px;
}

.percentage button,
.businessReports button {
	margin: 0 0 0 30px;
	width: 110px;
}
</style>