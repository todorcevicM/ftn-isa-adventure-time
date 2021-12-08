<template>
	<div>
		<div id="logo-container">
			<div class="underlined">
				<img src="../../assets/wheel.svg" />
				<p>Adventure Time</p>
			</div>
			<div v-if="checkFirstLogin() == 1" class="firstLogin">
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
		</div>
		<div class="mainFlex">
			<div class="leftFlex">
				<!-- <div>
					<img class="itemImage" :src="imageSource(adventure.id)" />
				</div> -->
				<h4>{{ user.name }} {{ user.lastname }}</h4>
				<p>Administrator</p>
				<p style="font-size: 18px">{{ user.email }}</p>
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
			<div class="table">
				<!-- Registration Requests -->
				<h3>Registration Requests</h3>

				<div
					class="tableEntry"
					v-for="req in registrationRequests"
					:key="req"
				>
					<p class="entryName">{{ req.name }}</p>
					<p class="entryRequestText">
						{{ req.userRegistrationReason }}
					</p>
					<button class="entryApprove" @click="approve()">
						Approve
					</button>
					<button class="entryDeny" @click="deny()">Deny</button>
				</div>
			</div>
			<!-- Spacer -->
			<div style="height: 80px"></div>
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
		var updateToggle = ref(null);
		axios
			.get("/api/administrator/get/" + localStorage["emailHash"])
			.then(function (response) {
				user.value = response.data;
				localStorage.address = user.value.address;
				localStorage.city = user.value.city;
				localStorage.country = user.value.country;
				localStorage.telephoneNumber = user.value.telephoneNumber;
				// userType iz ovog user-a je null jer je tako namesteno da bi se izbegao DTO
				// Posto znamo da radimo sa ovim tipom user-a,
				// samo u send metodi stavljamo userType koji treba
			});
		var newUser = ref({
			newAddress: localStorage.address,
			newCity: localStorage.city,
			newCountry: localStorage.country,
			newTelephoneNumber: localStorage.telephoneNumber,
		});

		var registrationRequests = ref(null);
		axios.get("/api/user/getUnauthenticated").then(function (response) {
			registrationRequests.value = response.data;
		});

		var firstPassword = ref(null);
		var repeatPassword = ref(null);
		var matching = ref(null);
		var passwordChangeToggle = ref(null);

		return {
			user,
			registrationRequests,
			updateToggle,
			newUser,
			firstPassword,
			repeatPassword,
			matching,
			passwordChangeToggle,
			checkFirstLogin() {
				if (this.user.password == "0") {
					return 1;
				} else {
					return 0;
				}
			},
			wantsDeletion() {
				alert("Not implemented yet!");
			},
			updateDetails() {
				this.updateToggle = true;
			},
			approve() {
				alert("Not implemented yet!");
			},
			deny() {
				alert("Not implemented yet!");
				// TODO: Tacka 3.25, mora da se otvori dijalog gde admin upisuje razlog za deny.
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
				sendingUser.userType = "administrator";
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
				sendingUser.userType = "administrator";
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
.passwordChange input {
	height: 24px;
	border-radius: 5px;
	border: 1px solid rgb(122, 122, 122);
	font-size: 18px;
	background-color: #f0f0f0;
}
.rightFlex input:focus,
.firstLogin input:focus,
.passwordChange input:focus {
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
.tableEntry {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	height: 55px;
	border-bottom: solid 1px rgb(145, 145, 145);
}
.tableEntry .entryName {
	margin: auto 0;
	width: 160px;
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
.tableEntry .entryApprove {
	background-color: rgb(108, 207, 108);
}
.tableEntry .entryDeny {
	background-color: rgb(194, 109, 109);
}
</style> 