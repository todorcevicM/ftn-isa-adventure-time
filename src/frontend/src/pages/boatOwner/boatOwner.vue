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
				<p class="smallText">Boat Owner</p>
				<p style="font-size: 18px">{{ user.email }}</p>
				<!-- Spacer -->
				<div style="height: 40px"></div>
				<div class="leftFlexEntry">
					<p>Business Reports</p>
					<button @click="getReportYear()">Year</button>
					<button @click="getReportMonth()">Month</button>
					<button @click="getReportWeek()">Week</button>
				</div>
				<!-- Spacer -->
				<div style="height: 40px"></div>
				<div class="leftFlexEntry">
					<p>Occupancy Calendar</p>
					<button @click="calendar()">Show</button>
				</div>
				<!-- Spacer -->
				<div style="height: 20px"></div>
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
			<!-- Sending Reports -->
			<div class="passwordChange" v-if="reportToggle == true">
				<div
					style="
						display: flex;
						flex-direction: row;
						justify-content: space-between;
					"
				>
					<p>User didn't show up?</p>
					<input v-model="cb1" type="checkbox" style="width: 22px" />
				</div>
				<div
					style="
						display: flex;
						flex-direction: row;
						justify-content: space-between;
					"
				>
					<p>Is this report a complaint?</p>
					<input v-model="cb2" type="checkbox" style="width: 22px" />
				</div>
				<p>Report Text</p>
				<input v-model="reportText" type="text" />
				<button
					class="entryApprove"
					@click="sendReport(reportUserId)"
					style="width: 170px"
				>
					Send Report
				</button>
			</div>
			<!-- Tables -->
			<div class="table">
				<div class="header">
					<h3>Past Boat Bookings</h3>
					<button @click="notImplemented()">Sort</button>
				</div>
				<div v-for="pbb in pastBoatBookings" :key="pbb">
					<p class="tableSubEntry">
						{{ pbb.boatName }}
					</p>
					<div
						class="tableEntry"
						v-for="booking in pbb.userNameBoatBookingDTO"
						:key="booking"
					>
						<div class="entryLeft">
							<p class="entryLeftShort">
								{{ booking.userName }}
							</p>
							<p class="entryLeftShort">
								{{ booking.boatBooking.extraService }}
							</p>
						</div>
						<div class="entryRight">
							<!-- Mora obrnuti redosled -->
							<button
								class="entryApprove"
								@click="
									viewUser(
										booking.boatBooking.registeredUserId
									)
								"
							>
								View User
							</button>
							<button
								v-if="
									!booking.boatBooking.reportMade &&
									reportToggle == false
								"
								class="entryApprove"
								@click="
									makeReport(
										booking.boatBooking.registeredUserId,
										booking.boatBooking.id
									)
								"
								style="width: 140px"
							>
								Make Report
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="table">
				<div class="header">
					<h3>Current Boat Bookings</h3>
					<button @click="notImplemented()">Sort</button>
				</div>
				<div v-for="bnbb in boatNameBoatBookingDTO" :key="bnbb">
					<p class="tableSubEntry">
						{{ bnbb.boatName }}
					</p>
					<div
						class="tableEntry"
						v-for="booking in bnbb.userNameBoatBookingDTO"
						:key="booking"
					>
						<div class="entryLeft">
							<p class="entryLeftShort">
								{{ booking.userName }}
							</p>
							<p class="entryLeftShort">
								{{ bnbb.start }}
							</p>
							<p class="entryLeftShort">
								{{ bnbb.end }}
							</p>
						</div>
						<div class="entryRight">
							<button
								class="entryApprove"
								style="width: 260px"
								@click="
									createNewBooking(
										booking.boatBooking.registeredUserId
									)
								"
							>
								Create New Booking
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="table">
				<div class="header">
					<h3>Boats</h3>
					<button @click="notImplemented()">Sort</button>
				</div>
				<div class="tableEntry" v-for="boat in boats" :key="boat">
					<div class="entryLeft">
						<p class="entryLeftShort">{{ boat.name }}</p>
						<p class="entryLeftLong">{{ boat.address }}</p>
					</div>
					<div class="entryRight">
						<!-- Mora obrnuti redosled -->
						<button class="entryDeny" @click="deleteBoat(boat.id)">
							Delete
						</button>
						<button class="entryApprove" @click="viewBoat(boat.id)">
							View
						</button>
					</div>
				</div>
				<div style="display: flex">
					<button class="entryApprove" @click="addNewBoat()">
						Add New Boat
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
		axios
			.get("/api/boatOwner/getByEmail/" + localStorage["emailHash"])
			.then(function (response) {
				user.value = response.data;
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
			.get("/api/boatOwner/pastBoatBookings/" + localStorage["userId"])
			.then(function (response) {
				pastBoatBookings.value = response.data;
			});
		var boats = ref(null);
		axios
			.get("/api/boats/getAllByOwnerId/" + localStorage["userId"])
			.then(function (response) {
				boats.value = response.data;
			});
		var boatNameBoatBookingDTO = ref(null);
		axios
			.get("/api/boatOwner/currentCustomers/" + localStorage["userId"])
			.then(function (response) {
				boatNameBoatBookingDTO.value = response.data;
				boatNameBoatBookingDTO.value.forEach(function (
					boatNameBoatBooking
				) {
					boatNameBoatBooking.start = new Date(
						boatNameBoatBooking.userNameBoatBookingDTO[0].boatBooking.start
					)
						.toString()
						.substring(0, 15);

					boatNameBoatBooking.end = new Date(
						boatNameBoatBooking.userNameBoatBookingDTO[0].boatBooking.end
					)
						.toString()
						.substring(0, 15);
				});
			});

		var searchQuery = ref(null);
		var reportToggle = ref(false);
		var reportUserId = ref(null);
		var reportBookingId = ref(null);
		var reportText = ref(null);
		var cb1 = ref(false);
		var cb2 = ref(false);
		return {
			user,
			pastBoatBookings,
			boatNameBoatBookingDTO,
			newUser,
			updateToggle,
			firstPassword,
			repeatPassword,
			matching,
			passwordChangeToggle,
			boats,
			searchQuery,
			reportToggle,
			reportUserId,
			reportBookingId,
			reportText,
			cb1,
			cb2,
			notImplemented() {
				alert("Not implemented yet!");
			},
			calendar() {
				window.location.href = "/calendar";
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
				sendingUser.userType = "boatOwner";
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
				sendingUser.userType = "boatOwner";
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
				window.location.href = "/registeredUserProfile/" + id;
			},
			deleteBoat(id) {
				axios.post("/api/boats/delete/" + id).then(function (response) {
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
			viewBoat(id) {
				axios.get("/api/boats/get/" + id).then(function (response) {
					for (const key in response.data) {
						if (!(key === "password")) {
							localStorage.setItem(key, response.data[key]);
						}
					}
					window.location.href = "/boatEdit/" + id;
				});
			},
			addNewBoat() {
				window.location.assign("/boatCreate/" + localStorage.emailHash);
			},
			deleteAccount() {
				var reason = prompt("Enter reason for deletion: ");
				axios
					.post(
						"/api/user/createDeletionRequest/" + this.user.id,
						{ type: "BOAT_OWNER", reason: reason },
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
			createNewBooking(userId) {
				localStorage.setItem("whichUser", userId);
				localStorage.setItem("type", "BOAT_OWNER");
				window.location.href = "/bookingCreate/";
			},
			makeReport(id, bookingId) {
				reportBookingId = bookingId;
				reportToggle.value = true;
				reportUserId.value = id;
			},
			sendReport(reportUserId) {
				axios
					.post(
						"/api/report/makeReport/",
						{
							userId: reportUserId,
							type: "BOAT",
							text: reportText.value,
							bookingId: reportBookingId,
							cb1: cb1.value,
							cb2: cb2.value,
						},
						{ headers: { "Content-Type": "application/json" } }
					)
					.then(function (/* response */) {
						// console.log(response.data);
						alert("Report created.");
						window.location.reload();
					});
			},
			getReportYear() {
				axios
					.get("/api/boatOwner/profitYear/" + localStorage["userId"])
					.then(function (response) {
						if (response.data > 0) {
							alert("Profit for last year: " + response.data);
						} else {
							alert("Something is wrong, ask Mike Oxlong");
						}
					});
			},
			getReportMonth() {
				axios
					.get("/api/boatOwner/profitMonth/" + localStorage["userId"])
					.then(function (response) {
						if (response.data > 0) {
							alert("Profit for last month: " + response.data);
						} else {
							alert("Something is wrong, ask Mike Oxlong");
						}
					});
			},
			getReportWeek() {
				axios
					.get("/api/boatOwner/profitWeek/" + localStorage["userId"])
					.then(function (response) {
						if (response.data > 0) {
							alert("Profit for last week: " + response.data);
						} else {
							alert("Something is wrong, ask Mike Oxlong");
						}
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

.rightFlex {
	height: min-content;
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

/* LEFT FLEX ELEMENTS */

.leftFlexEntry {
	display: flex;
	flex-direction: row;
	align-items: center;
}

.leftFlexEntry input {
	width: 40px;
	margin: 0 10px;
	text-align: center;
	font-size: 22px;
}

.leftFlexEntry button {
	margin: 0 0 0 30px;
	width: 110px;
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
	margin: 20px auto;
	width: 530px;
}

.rightFlex p,
.firstLogin p,
.passwordChange p {
	margin: 4px 0;
	font-size: 25px;
	text-align: left;
}

.rightFlex .smallText,
.leftFlex .smallText {
	margin: 0;
	font-size: 20px;
	color: #9e6b1d;
}

input {
	height: 24px;
	border-radius: 5px;
	border: 1px solid rgb(122, 122, 122);
	font-size: 18px;
	background-color: #f0f0f0;
}

input:focus {
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
	margin: 0px 200px 100px;
	display: flex;
	flex-direction: column;
}

/* TABLE */

.table {
	margin-top: 20px;
}

.header {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.header button {
	margin: auto 0;
	width: 110px;
}

.tableEntry {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	height: 55px;
	border-bottom: solid 1px rgb(145, 145, 145);
}

.tableSubEntry {
	margin: 0;
	margin-top: 10px;
	color: #9e6b1d;
	font-size: 26px;
}

.entryLeft {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	margin: auto 0;
}

.entryLeftShort {
	width: 180px;
}

.entryLeftLong {
	width: 650px;
}

.entryRight {
	min-width: 260px;
	display: flex;
	flex-direction: row-reverse;
	justify-content: space-between;
	margin: auto 0;
}

.entryRight button {
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
</style>