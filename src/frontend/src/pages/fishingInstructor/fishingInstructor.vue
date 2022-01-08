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
				<h4>{{ user.name }} {{ user.lastname }}</h4>
				<p class="smallText">Fishing Instructor</p>
				<p style="font-size: 18px">{{ user.email }}</p>
				<!-- Spacer -->
				<div style="height: 40px"></div>
				<!-- TODO: ovaj class -->
				<div class="percentage">
					<p>Business Reports</p>
					<button @click="notImplemented()" style="width: 200px">
						Show
					</button>
				</div>
				<!-- Spacer -->
				<div style="height: 40px"></div>
				<!-- TODO: ovaj class -->
				<div class="percentage">
					<p>New Report</p>
					<button @click="notImplemented()" style="width: 200px">
						Fill
					</button>
				</div>
				<!-- Spacer -->
				<div style="height: 40px"></div>
				<!-- TODO: ovaj class -->
				<div class="percentage">
					<p>Quick Reservation</p>
					<button @click="notImplemented()" style="width: 200px">
						Create
					</button>
				</div>
				<!-- Spacer -->
				<div style="height: 40px"></div>
				<!-- TODO: ovaj class -->
				<div class="percentage">
					<p>New Reservation</p>
					<button @click="notImplemented()" style="width: 200px">
						Create
					</button>
				</div>
				<!-- Spacer -->
				<div style="height: 40px"></div>
				<!-- TODO: ovaj class -->
				<div class="percentage">
					<p>Occupancy Calendar</p>
					<button @click="notImplemented()" style="width: 200px">
						Show
					</button>
				</div>
				<!-- Spacer -->
				<div style="height: 40px"></div>
			</div>
			<div class="rightFlex">
				<p class="smallText">Address</p>
				<p v-if="!updateToggle">{{ user.address }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newAddress"
				/>

				<p class="smallText">City</p>
				<p v-if="!updateToggle">{{ user.city }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newCity"
				/>

				<p class="smallText">Country</p>
				<p v-if="!updateToggle">{{ user.country }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newCountry"
				/>

				<p class="smallText">Telephone Number</p>
				<p v-if="!updateToggle">
					{{ user.telephoneNumber }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newTelephoneNumber"
				/>

				<p class="smallText">Start Work Time</p>
				<p v-if="!updateToggle">
					{{ formattedDateStart }}
					<!-- {{ user.startWorkPeriod }} -->
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newStartWorkPeriod"
				/>

				<p class="smallText">End Work Time</p>
				<p v-if="!updateToggle">
					{{ formattedDateEnd }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newEndWorkPeriod"
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
			<div class="search" style="text-align: center">
				<img
					src="../../assets/wheel.svg"
					style="width: 48px; margin-top: 18px"
				/>
				<input
					type="text"
					placeholder="Search..."
					v-model="searchQuery"
				/>
				<div v-if="searchQuery" class="searchResult">
					<p>No results found...</p>
				</div>
			</div>
			<div
				style="
					display: flex;
					flex-direction: row;
					justify-content: space-between;
				"
			>
				<h3>Past Adventure Bookings</h3>
				<button style="margin: 0">Sort</button>
			</div>
			<div class="table">
				<div v-for="pab in pastAdventureBookings" :key="pab">
					<p class="entryName" style="font-size: 26px; margin: 0">
						{{ pab.adventureName }}
					</p>
					<div
						class="tableEntry"
						v-for="booking in pab.adventureBookings"
						:key="booking"
					>
						<p>User ID: {{ booking.registeredUserId }}</p>
						<p>{{ booking.extraService }}</p>
						<p>Price : {{ booking.price }}.00</p>

						<button
							class="entryApprove"
							@click="viewUser(booking.registeredUserId)"
						>
							View User
						</button>
					</div>
				</div>
			</div>


			<!-- Spacer -->
			<div style="height: 80px"></div>
			<div
				style="
					display: flex;
					flex-direction: row;
					justify-content: space-between;">
				<h3>Current Adventure Bookings</h3>
				<button style="margin: 0">Sort</button>
			</div>
			<div class="table">
				<div v-for="cc in currentCustomers" :key="cc">
					<p>User : {{ cc.lastname }}, {{ cc.name }} </p>
					<button class="entryApprove" @click="createNewBooking(cc.id)">
						Create New Booking
					</button>
				</div>
			</div>


			<!-- Spacer -->
			<div style="height: 80px"></div>
			<div class="table">
				<h3>Adventures</h3>
				<div
					class="tableEntry"
					v-for="adventure in adventures"
					:key="adventure"
				>
					<p class="entryName">{{ adventure.name }}</p>
					<!-- TODO: class ovog dugmeta djota -->
					<button
						class="entryApprove"
						@click="viewAdventure(adventure.id)"
					>
						View
					</button>
					<button
						class="entryDeny"
						@click="deleteAdventure(adventure.id)"
					>
						Delete
					</button>
				</div>
				<div style="display: flex">
					<button class="entryApprove" @click="addNewAdventure()">
						Add New Adventure
					</button>
				</div>
			</div>
			<!-- Spacer -->
			<div style="height: 80px"></div>
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
			<button @click="deleteAccount()">Delete My Account</button>
		</div>
	</div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
export default {
	setup() {
		var user = ref(null);
		var unformattedDateStart = ref(null);
		var unformattedDateEnd = ref(null);
		var formattedDateStart = ref(null);
		var formattedDateEnd = ref(null);

		axios
			.get(
				"/api/fishingInstructor/getByEmail/" + localStorage["emailHash"]
			)
			.then(function (response) {
				console.log(response.data);
				user.value = response.data;

				unformattedDateStart.value = user.value.startWorkPeriod;
				unformattedDateEnd.value = user.value.endWorkPeriod;

				// Formatiranje datuma
				let newStart = unformattedDateStart.value.split("T");
				let newStartSecondPart = newStart[1].split(".")[0];
				formattedDateStart.value =
					newStartSecondPart + ", " + newStart[0];
				let newEnd = unformattedDateEnd.value.split("T");
				let newEndSecondPart = newEnd[1].split(".")[0];
				formattedDateEnd.value = newEndSecondPart + ", " + newEnd[0];
			});
		var newUser = ref({
			newAddress: localStorage.address,
			newCity: localStorage.city,
			newCountry: localStorage.country,
			newTelephoneNumber: localStorage.telephoneNumber,
			newStartWorkPeriod: localStorage.startWorkPeriod,
			newEndWorkPeriod: localStorage.endWorkPeriod,
		});
		var updateToggle = ref(null);
		var firstPassword = ref(null);
		var repeatPassword = ref(null);
		var matching = ref(null);
		var passwordChangeToggle = ref(null);
		var adventures = ref(null);
		axios.get("/api/adventures/get").then(function (response) {
			console.log(response.data);
			adventures.value = response.data;
		});

		var pastAdventureBookings = ref(null);
		axios
			.get(
				"/api/fishingInstructor/pastAdventureBookings/" +
					localStorage["userId"]
			)
			.then(function (response) {
				console.log(response.data);
				pastAdventureBookings.value = response.data;
			});

		var currentCustomers = ref(null);
		axios
			.get("/api/fishingInstructor/currentCustomers/" + localStorage["userId"])
			.then(function (response) {
				console.log(response.data);
				currentCustomers.value = response.data;
			});


		var searchQuery = ref(null);
		return {
			user,
			pastAdventureBookings,
			currentCustomers,
			newUser,
			updateToggle,
			firstPassword,
			repeatPassword,
			matching,
			passwordChangeToggle,
			adventures,
			searchQuery,
			unformattedDateStart,
			unformattedDateEnd,
			formattedDateStart,
			formattedDateEnd,
			notImplemented() {
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
					this.newUser.newTelephoneNumber == "" ||
					this.newUser.newStartWorkPeriod == "" ||
					this.newUser.newEndWorkPeriod == ""
				) {
					alert("Please fill out all inputs.");
					return;
				}
				var sendingUser = this.user;
				sendingUser.address = this.newUser.newAddress;
				sendingUser.city = this.newUser.newCity;
				sendingUser.country = this.newUser.newCountry;
				sendingUser.telephoneNumber = this.newUser.newTelephoneNumber;
				sendingUser.startWorkPeriod = this.newUser.newStartWorkPeriod;
				sendingUser.endWorkPeriod = this.newUser.newEndWorkPeriod;
				sendingUser.userType = "fishingInstructor";
				console.log(sendingUser);
				localStorage["sendingUser"] = JSON.stringify(sendingUser);
				console.log(localStorage["sendingUser"]);

				axios
					.post("/api/user/update", localStorage["sendingUser"], {
						headers: {
							"Content-Type": "application/json",
						},
					})
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
				sendingUser.userType = "fishingInstructor";
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
			viewUser(id) {
				console.log(id);
				window.location.href = "/registeredUserProfile/" + id;
			},
			deleteAdventure(id) {
				axios
					.post("/api/adventures/delete/" + id)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
						var booked = response.data;
						if (booked == false) {
							alert(
								"There exist bookings with this item. Deletion is unavailable."
							);
							return;
						} else {
							alert("Deleted.");
							window.location.reload();
						}
					});
			},
			viewAdventure(id) {
				axios
					.get("/api/adventures/get/" + id)
					.then(function (response) {
						for (const key in response.data) {
							if (!(key === "password")) {
								localStorage.setItem(key, response.data[key]);
							}
						}
						window.location.href = "/adventureEdit/" + id;
					});
			},
			addNewAdventure() {
				window.location.assign(
					"/adventureCreate/" + localStorage.emailHash
				);
				console.log(localStorage.emailHash);
			},
			deleteAccount() {
				var reason = prompt("Enter reason for deletion: ");
				axios
					.post(
						"/api/user/createDeletionRequest/" + this.user.id,
						{ type: "FISHING_INSTRUCTOR", reason: reason },
						{ headers: { "Content-Type": "application/json" } }
					)
					.then(function (response) {
						if (response.data == true) {
							alert(
								"The request for deletion has been submitted."
							);
						} else {
							alert(
								"Your services are currently reserved, the account cannot be deleted."
							);
						}
					})
					.catch(function (error) {
						if (error.response.status == 429) {
							// TOO_MANY_REQUESTS
							alert(
								"A deletion request for your account already exists."
							);
							return;
						} else {
							alert(
								"There's been an error while submitting the request : " +
									error
							);
						}
					});
			},
			createNewBooking(forUser) {
				localStorage.setItem("instructorId", localStorage.userId);
				localStorage.setItem("whichUser", forUser);
				localStorage.setItem("type", "FISHING_INSTRUCTOR");
				window.location.href = "/bookingCreate/";
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
}

.leftFlex img {
	width: 800px;
	height: 450px;
	border-radius: 15px;
	object-fit: cover;
}

.rightFlex {
	height: 440px;
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

.rightFlex .smallText,
.leftFlex .smallText {
	margin: 0;
	font-size: 20px;
	color: #9e6b1d;
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

.search {
	text-align: center;
	display: flex;
	align-items: center;
	flex-direction: column;
}
.search input {
	border-radius: 5px;
	margin-top: 10px;
}
.searchResult {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 370px;
	height: 470px;
	margin-top: 20px;
	border-radius: 15px;
	background-color: rgb(230, 230, 230);
}
.searchResult p {
	text-align: center;
	color: rgb(71, 71, 71);
	font-size: 35px;
}
input,
select {
	width: 260px;
	height: 32px;
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