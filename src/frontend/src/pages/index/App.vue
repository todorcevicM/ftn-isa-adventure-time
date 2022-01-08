<template>
	<div>
		<div style="display: flex; justify-content: flex-end">
			<button id="logInButton" @click="openLogin()">Log in</button>
		</div>
		<div id="logo-container">
			<div class="underlined">
				<img src="../../assets/wheel.svg" />
				<p>Adventure Time</p>
			</div>
			<p style="font-size: 36px; text-align: center; margin: 7px">
				Plan your fishing trip with ease.
			</p>
		</div>
		<div
			v-if="!popupState && !loginState"
			style="font-size: 30px; margin: 50px 0 0 200px"
		>
			<p style="margin: 0">
				<a @click="openPopup()">Sign up</a> to schedule one of our
				adventures,
			</p>
			<p style="margin: 0; font-size: 23px">
				or continue browsing as a guest.
			</p>
		</div>

		<!-- Login -->
		<!-- <div v-if="loginState" style="height: 500px"></div> -->
		<div v-if="loginState" class="popupLogin">
			<div style="height: 20px"></div>
			<!-- Spacer -->
			<div class="popupLoginRow">
				<div class="inputField">
					<p>Email</p>
					<input
						v-model="userEmail"
						type="text"
						placeholder="johnjohnson@gmail.com"
					/>
				</div>
				<div class="inputField">
					<p>Password</p>
					<input v-model="firstPassword" type="password" />
				</div>
			</div>
			<div class="popupLoginRow">
				<button @click="loginUser()" class="actionButton">
					Log In
				</button>
			</div>
		</div>
		<!-- Optional Spacer -->
		<div v-if="loginState" style="height: 200px"></div>

		<!-- Registration -->
		<div v-if="popupState" class="popupRegister">
			<div style="height: 20px"></div>
			<!-- Spacer -->
			<div class="popupRegisterRow">
				<div class="inputField">
					<p>Register as</p>
					<select v-model="userType" type="">
						<option>Standard User</option>
						<option>Cottage Owner</option>
						<option>Boat Owner</option>
						<option>Fishing Instructor</option>
					</select>
				</div>
				<div class="inputField">
					<p>Email</p>
					<input
						v-model="userEmail"
						type="text"
						placeholder="johnjohnson@gmail.com"
					/>
				</div>
			</div>
			<div class="popupRegisterRow">
				<div class="inputField">
					<p>First Name</p>
					<input
						v-model="userFirstName"
						type="text"
						placeholder="John"
					/>
				</div>
				<div class="inputField">
					<p>Last Name</p>
					<input
						v-model="userLastName"
						type="text"
						placeholder="Johnson"
					/>
				</div>
			</div>
			<div class="popupRegisterRow">
				<div class="inputField">
					<p>Phone Number</p>
					<input
						v-model="userPhoneNumber"
						type="tel"
						placeholder="+381 65 123 123 12"
					/>
				</div>
				<div class="inputField">
					<p>Address</p>
					<input
						v-model="userAddress"
						type="text"
						placeholder="42 John Lane"
					/>
				</div>
			</div>
			<div class="popupRegisterRow">
				<div class="inputField">
					<p>City</p>
					<input
						v-model="userCity"
						type="text"
						placeholder="Johnville"
					/>
				</div>
				<div class="inputField">
					<p>Country</p>
					<input
						v-model="userCountry"
						type="text"
						placeholder="Canada"
					/>
				</div>
			</div>
			<div class="popupRegisterRow">
				<div class="inputField">
					<p>Password</p>
					<input v-model="firstPassword" type="password" />
				</div>
				<div class="inputField">
					<p>Repeat Password</p>
					<input
						v-model="repeatPassword"
						@input="
							passwordMatchCheck(firstPassword, repeatPassword)
						"
						type="password"
					/>
				</div>
			</div>
			<div class="spacer">
				<p>{{ matching }}</p>
			</div>
			<div v-if="userType != 'Standard User'" class="popupRegisterRow">
				<div class="inputFieldBig">
					<p>Reason for registration</p>
					<input v-model="userRegistrationReason" />
				</div>
			</div>
			<div class="popupRegisterRow">
				<button @click="registerUser()" class="actionButton">
					Sign Up
				</button>
			</div>
		</div>
		<div v-if="!popupState && !loginState" style="height: 500px"></div>
		<div
			v-if="signUpMessageOn"
			:class="{
				successfulBackground: signUpMessageKind == 'success',
				failedBackground: signUpMessageKind == 'failed',
			}"
			class="secondPopup"
		>
			<p>{{ signUpMessageText }}</p>
		</div>
		<div class="mainCard">
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
			<div class="largeCategory">
				<h1>Browse Cottages</h1>
				<a v-if="showAllCottagesToggle" @click="showAllCottages()"
					>Show All</a
				>
				<div class="categoryItems">
					<div
						v-for="i in cottageNumToDisplay ? cottages.length : 3"
						:key="i"
					>
						<img
							@click="imageRedirect(1, i)"
							class="itemImage"
							:src="imageSource(1, i)"
						/>
						<h4>{{ cottages[i - 1].name }}</h4>
						<h6>Rating: 5.00</h6>
						<h6>${{ cottages[i - 1].pricePerDay }}/day</h6>
						<h6>{{ cottages[i - 1].address }}</h6>
					</div>
				</div>
			</div>
			<div class="largeCategory">
				<h1>Browse Adventures</h1>
				<a v-if="showAllAdventuresToggle" @click="showAllAdventures()"
					>Show All</a
				>
				<div class="categoryItems">
					<div
						v-for="i in adventureNumToDisplay
							? adventures.length
							: 3"
						:key="i"
					>
						<img
							@click="imageRedirect(2, i)"
							class="itemImage"
							:src="imageSource(2, i)"
						/>
						<h4>{{ adventures[i - 1].name }}</h4>
						<h6>Rating: 5.00</h6>
						<h6>${{ adventures[i - 1].pricePerDay }}/day</h6>
						<h6>{{ adventures[i - 1].address }}</h6>
					</div>
				</div>
			</div>
			<div class="largeCategory">
				<h1>Browse Boats</h1>
				<a v-if="showAllBoatsToggle" @click="showAllBoats()"
					>Show All</a
				>
				<div class="categoryItems">
					<div
						v-for="i in boatNumToDisplay ? boats.length : 3"
						:key="i"
					>
						<img
							@click="imageRedirect(3, i)"
							class="itemImage"
							:src="imageSource(3, i)"
						/>
						<h4>{{ boats[i - 1].name }}</h4>
						<h6>Rating: 5.00</h6>
						<h6>${{ boats[i - 1].pricePerDay }}/day</h6>
						<h6>{{ boats[i - 1].address }}</h6>
					</div>
				</div>
			</div>
		</div>
		<!-- Spacers -->
		<div v-if="!showAllCottagesToggle" style="height: 550px"></div>
		<div v-if="!showAllAdventuresToggle" style="height: 550px"></div>
		<div v-if="!showAllBoatsToggle" style="height: 550px"></div>
		<!-- Spacers -->
		<div class="bottomCard">
			<p>
				Created by Nikola Matijević, Đorđe Stanković and Marko
				Todorčević
			</p>
			<a href="https://github.com/todorcevicM/ftn-isa-adventure-time"
				>Source Code Here!</a
			>
			<p>2021/22</p>
			<!-- &copy; je copyright -->
		</div>
	</div>
</template> 

<script>
import { ref } from "vue";
import axios from "axios";
export default {
	setup() {
		localStorage.clear();

		var cottages = ref(null);
		var adventures = ref(null);
		var boats = ref(null);
		var popupState = ref(null);
		var loginState = ref(null);
		var firstPassword = ref(null);
		var repeatPassword = ref(null); // Ako se inicijalizuje ovde na "Do passwords match?", kasnice update-ovanje za jedan
		var matching = ref(null);
		var userType = ref(null);
		var userEmail = ref(null);
		var userFirstName = ref(null);
		var userLastName = ref(null);
		var userPhoneNumber = ref(null);
		var userAddress = ref(null);
		var userCity = ref(null);
		var userCountry = ref(null);
		var userRegistrationReason = ref(null);
		// var signUpMessageOn = ref(null);
		// var signUpMessageKind = ref(null);
		// var signUpMessageText = ref(null);
		var cottageNumToDisplay = ref(null);
		var showAllCottagesToggle = ref(null);
		showAllCottagesToggle = true;
		var adventureNumToDisplay = ref(null);
		var showAllAdventuresToggle = ref(null);
		showAllAdventuresToggle = true;
		var boatNumToDisplay = ref(null);
		var showAllBoatsToggle = ref(null);
		showAllBoatsToggle = true;
		var searchQuery = ref(null);

		axios.get("/api/cottages/get").then(function (response) {
			cottages.value = response.data;
		});
		axios.get("/api/adventures/get").then(function (response) {
			adventures.value = response.data;
		});
		axios.get("/api/boats/get").then(function (response) {
			boats.value = response.data;
		});

		// Za u <template>
		return {
			cottages,
			adventures,
			boats,
			popupState,
			loginState,
			matching,
			firstPassword,
			repeatPassword,
			userType,
			userEmail,
			userFirstName,
			userLastName,
			userPhoneNumber,
			userAddress,
			userCity,
			userCountry,
			userRegistrationReason,
			// signUpMessageOn,
			// signUpMessageKind,
			// signUpMessageText,
			cottageNumToDisplay,
			showAllCottagesToggle,
			adventureNumToDisplay,
			showAllAdventuresToggle,
			boatNumToDisplay,
			showAllBoatsToggle,
			searchQuery,
			passwordMatchCheck(firstPassword, repeatPassword) {
				if (firstPassword == repeatPassword) {
					this.matching = "Passwords Match!";
					return this.matching;
				} else {
					this.matching = "Passwords don't match!";
					return this.matching;
				}
			},
			imageSource(type, id) {
				switch (type) {
					case 1:
						// console.log("CASE 1 ID ZA IMAGESOURCE : " + id);
						return require("../../assets/images/cottage" +
							id +
							".png");
					case 2:
						// console.log("CASE 2 ID ZA IMAGESOURCE : " + id);
						return require("../../assets/images/adventure" +
							id +
							".png");
					case 3:
						// console.log("CASE 3 ID ZA IMAGESOURCE : " + id);
						return require("../../assets/images/boat" +
							id +
							".png");
				}
			},
			openPopup() {
				popupState.value = true;
			},
			// TODO: Ovo se vise ne koristi, koristimo alert()
			// renderSecondPopup(on, kind, text) {
			// 	this.signUpMessageOn = on;
			// 	this.signUpMessageKind = kind;
			// 	this.signUpMessageText = text;
			// },
			registerUser() {
				if (
					this.userType == null ||
					this.userEmail == null ||
					this.userFirstName == null ||
					this.userLastName == null ||
					this.userPhoneNumber == null ||
					this.userAddress == null ||
					this.userCity == null ||
					this.userCountry == null ||
					this.firstPassword == null ||
					this.repeatPassword == null
				) {
					alert("All fields need to be filled, try again.");
					// this.renderSecondPopup(
					// 	true,
					// 	"failed",
					// 	"All fields need to be filled, try again."
					// );
				} else {
					// Provera korektnog formata Email-a
					// TODO: prolazi a@., a ne treba
					if (
						!(
							(
								this.userEmail.includes("@") &&
								this.userEmail.indexOf("@") !=
									this.userEmail.length - 1 &&
								this.userEmail.indexOf("@") != 0
							) // Proverava da li imamo nesto posle @ ili pre @
						)
					) {
						alert(
							"Email isn't in the correct form. Please fill out the form again."
						);
						return;
					}
					// this.signUpMessageOn = false;
					// this.signUpMessageKind = "failed";
					// this.signUpMessageText = "Sample Text";
					if (this.firstPassword == this.repeatPassword) {
						var user = {
							userType: "",
							email: this.userEmail,
							name: this.userFirstName,
							lastname: this.userLastName,
							telephoneNumber: this.userPhoneNumber,
							address: this.userAddress,
							city: this.userCity,
							country: this.userCountry,
							password: this.firstPassword,
							userRegistrationReason: this.userRegistrationReason,
						};
						switch (this.userType) {
							case "Standard User":
								user.userType = "registeredUser";
								break;
							case "Boat Owner":
								user.userType = "boatOwner";
								break;
							case "Cottage Owner":
								user.userType = "cottageOwner";
								break;
							case "Fishing Instructor":
								user.userType = "fishingInstructor";
								break;
						}

						axios
							.post("/api/user/create", user)
							.then(function (response) {
								// console.log("From AXIOS POST");
								// console.log(response);
								if (
									response.data ==
									"Error - User with that E-mail already exists."
								) {
									alert(
										"User with that E-mail already exists."
									);
									// TODO: Iz .then()-a nikako ne mogu niti da return-ujem promenljive koje se koriste u setup()-u, niti da zovem neku funkciju. Ne radi ni this. ni .value, ne mogu ni da izcupam vrednost iz response.data u neku promenljivu pa da nju prosledim nekoj drugoj funkciji, nista. Ne moze ni da se bind-uje this u scope .then()-a, nista ne moze da vidi.
									// Za sad radi alert(), i to je to.

									// this.renderSecondPopup(
									// 	true,
									// 	"failed",
									// 	"User with that E-mail already exists."
									// );
									// signUpMessageOn = true;
									// signUpMessageKind = "failed";
									// signUpMessageText =
									// 	"User with that E-mail already exists.";
									// return (
									// 	signUpMessageOn,
									// 	signUpMessageKind,
									// 	signUpMessageText
									// );
								} else {
									alert(
										"A registration request has been sent to the Administrator. Keep your eye open for a verification email!"
									);
									// TODO: Ovde je isti problem
									// popupState.value = false; // Zatvara sign up popup
									// signUpMessageOn = true;
									// signUpMessageKind = "success";
									// signUpMessageText =
									// 	"A registration request has been sent to the Administrator. Keep your eye open for a verification email!";
									// return (
									// 	signUpMessageOn,
									// 	signUpMessageKind,
									// 	signUpMessageText
									// );
								}
							});
					} else {
						alert("Passwords don't match, try again.");
						// this.renderSecondPopup(
						// 	true,
						// 	"failed",
						// 	"Passwords don't match, try again."
						// );
					}
				}
			},
			openLogin() {
				loginState.value = true;
			},
			loginUser() {
				if (this.userEmail == null || this.firstPassword == null) {
					alert("All fields need to be filled, try again.");
					// this.renderSecondPopup(
					// 	true,
					// 	"failed",
					// 	"All fields need to be filled, try again."
					// );
				} else {
					// Provera korektnog formata Email-a
					if (
						!(
							(
								this.userEmail.includes("@") &&
								this.userEmail.indexOf("@") !=
									this.userEmail.length - 1 &&
								this.userEmail.indexOf("@") != 0
							) // Proverava da li imamo nesto posle @
						)
					) {
						alert(
							"Email isn't in the correct form. Please fill out the form again."
						);
						return;
					}
					var credentials = [this.userEmail, this.firstPassword]; // Ovako jer se deserijalizuje u ArrayList
					console.log("Credentials: \n");
					console.log(credentials);
					axios
						.post("/api/user/login", credentials)
						.then(function (response) {
							for (const key in response.data) {
								if (!(key === "password")) {
									localStorage.setItem(
										key,
										response.data[key]
									);
								}
							}
							// Zbog userId ambiguity-a
							localStorage["userId"] = localStorage["id"];
							localStorage.removeItem("id");
							window.location.assign(
								"/" +
									localStorage.getItem("userType") +
									"/" +
									localStorage.getItem("emailHash")
							);
						})
						.catch(function (error) {
							console.log(error.response.status);
							switch (error.response.status) {
								case 401: // UNAUTHORIZED
									alert(
										"This user isn't authenticated. Please wait for the administrator to authenticate the account, and try again."
									);
									break;
								case 403: // FORBIDDEN
									alert("Account deleted.");
									break;
								case 404: // NOT_FOUND
									alert("This user doesn't exist.");
									break;
								case 406: // NOT_ACCEPTABLE
									alert("The password is wrong, try again.");
									break;
							}
						});
				}
			},
			showAllCottages() {
				this.showAllCottagesToggle = false;
				this.cottageNumToDisplay = true;
			},
			showAllAdventures() {
				this.showAllAdventuresToggle = false;
				this.adventureNumToDisplay = true;
			},
			showAllBoats() {
				this.showAllBoatsToggle = false;
				this.boatNumToDisplay = true;
			},
			imageRedirect(type, id) {
				switch (type) {
					case 1:
						window.location.href = "/cottage/" + id;
						break;
					case 2:
						window.location.href = "/adventure/" + id;
						break;
					case 3:
						window.location.href = "/boat/" + id;
						break;
				}
			},
		};
	},
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Aleo:wght@300;400&display=swap");
body {
	background-image: url("../../assets/adventure-time-background.jpg");
	/* background-color: #1a2022; */
	background-size: 100%;
	background-repeat: no-repeat;
	color: #10120e;
	font-family: Aleo;
	margin: 0;
}
#logo-container {
	margin: 0 70em 0 10em;
	text-align: center;
}
.underlined {
	display: inline-block;
	border-bottom: #ad6800 3px solid;
	height: 80px;
}
.underlined img {
	margin-bottom: -6px;
	margin-right: -7px;
}
.underlined p {
	margin-left: 10px;
	font-size: 75px;
	letter-spacing: -2px;
	display: inline;
}
a {
	color: #ad6800;
	text-decoration: none;
}
a:hover {
	color: #573b0d;
	cursor: pointer;
}
.mainCard {
	background-color: white;
	height: 1900px;
	margin: 0;
	border-top-left-radius: 24px;
	border-top-right-radius: 24px;
}
.categoryItems {
	display: flex;
	align-items: flex-end;
	justify-content: space-around;
	flex-wrap: wrap;
	row-gap: 20px;
}
.itemImage {
	margin: 0px 3px;
	border-radius: 15px;
	object-fit: cover;
	width: 360px;
	height: 360px;
	transition: all 0.2s ease-in-out;
}
.itemImage:hover {
	transform: scale(1.02);
	cursor: pointer;
}
h4 {
	font-size: 32px;
	font-weight: 400;
	margin: 0;
}
h6 {
	font-size: 26px;
	font-weight: lighter;
	margin: 0;
}
h1 {
	display: inline-block;
	font-size: 46px;
	letter-spacing: 1px;
	border-bottom: #ad6800 3px solid;
	margin-top: 10px;
}
.largeCategory {
	margin-left: 224px;
	margin-right: 224px;
}
.largeCategory a {
	margin-left: 30px;
	font-size: 24px;
}
.popupRegister {
	font-size: 22px;
	text-align: center;
	margin: 0px 36rem 20px 36rem;
	height: 570px;
	background-color: rgb(255, 255, 255);
	border-radius: 15px;
	display: flex;
	flex-direction: column;
}
.popupLogin {
	font-size: 22px;
	text-align: center;
	margin: 0px 36rem 20px 36rem;
	height: 200px;
	background-color: rgb(255, 255, 255);
	border-radius: 15px;
	display: flex;
	flex-direction: column;
}
.secondPopup {
	font-size: 22px;
	text-align: center;
	margin: 0px 36rem 60px 36rem;
	height: auto;
	border-radius: 15px;
	display: flex;
	flex-direction: column;
}
.successfulBackground {
	background-color: #c4e79d;
	border: 2px solid rgb(97, 160, 97);
}
.failedBackground {
	background-color: #e79d9d;
	border: 2px solid rgb(160, 97, 97);
}
.actionButton {
	color: white;
	font-size: 24px;
	font-family: Arial, Helvetica, sans-serif;
	background-color: #ad6800;
	border-radius: 5px;
	border: none;
	width: 200px;
	height: 46px;
}
.actionButton:hover {
	background-color: #583603;
	cursor: pointer;
}
.popupRegisterRow {
	display: flex;
	justify-content: space-around;
	align-items: center;
	width: 100%;
	height: 16.66%;
}
.popupLoginRow {
	display: flex;
	justify-content: space-around;
	align-items: center;
	width: 100%;
	height: 50%;
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
.inputField {
	width: 260px;
}
/* .inputFieldBig {
	width: inherit;
} */
.inputFieldBig input {
	width: 500px;
}
.inputField p,
.inputFieldBig p {
	margin: 0;
	text-align: left;
}
.spacer {
	height: 30px;
}
.spacer p {
	font-size: 16px;
	color: gray;
	margin: 0;
	margin-top: 6px;
}
#logInButton {
	font-size: 24px;
	font-family: Aleo;
	width: 115px;
	height: 40px;
	background: none;
	border: 2px solid #10120e;
	border-radius: 15px;
	margin: 30px 40px 0 0;
}
#logInButton:hover {
	background-color: #c4813069;
	cursor: pointer;
}
.bottomCard {
	margin-top: 30px;
	background-color: #3f3f3f;
	height: 150px;
	border-top-left-radius: 20px;
	border-top-right-radius: 20px;
	text-align: center;
	color: white;
	padding-top: 30px;
}
.bottomCard p {
	font-weight: 300;
	font-size: 24px;
	margin: 10px 0px;
}
.bottomCard a {
	font-size: 22px;
	color: orange;
}
.bottomCard a:hover {
	color: rgb(175, 61, 45);
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
</style>