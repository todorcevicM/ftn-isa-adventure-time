<template>
	<div>
		<div style="display: flex; justify-content: flex-end">
			<button id="logInButton">Log in</button>
		</div>
		<div id="logo-container">
			<div class="underlined">
				<img
					src="../../assets/wheel.svg"
					style="margin-bottom: -6px; margin-right: -7px"
				/>
				<p
					style="
						font-size: 75px;
						letter-spacing: -2px;
						display: inline;
					"
				>
					Adventure Time
				</p>
			</div>
			<p style="font-size: 36px; text-align: center; margin: 7px">
				Plan your fishing trip with ease.
			</p>
		</div>
		<div v-if="!popupState" style="font-size: 30px; margin: 50px 0 0 200px">
			<p style="margin: 0">
				<a @click="openPopup()">Sign up</a> to schedule one of our
				adventures,
			</p>
			<p style="margin: 0; font-size: 23px">
				or continue browsing as a guest.
			</p>
		</div>
		<div v-if="!popupState" style="height: 520px"></div>
		<div v-if="popupState" class="popup">
			<div style="height: 20px"></div>
			<!-- Spacer -->
			<div class="popupRow">
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
			<div class="popupRow">
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
			<div class="popupRow">
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
			<div class="popupRow">
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
						placeholder="California"
					/>
				</div>
			</div>
			<div class="popupRow">
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
			<div v-if="userType != 'Standard User'" class="popupRow">
				<div class="inputFieldBig">
					<p>Reason for registration</p>
					<input v-model="userRegistrationReason" />
				</div>
			</div>
			<div class="popupRow">
				<button @click="registerUser()" id="signUpButton">
					Sign Up
				</button>
			</div>
		</div>
		<div class="mainCard">
			<div style="text-align: center">
				<img
					src="../../assets/wheel.svg"
					style="width: 48px; margin-top: 18px"
				/>
			</div>
			<div class="largeCategory">
				<h1>Browse Cottages</h1>
				<div class="categoryItems">
					<!-- v-for c in cottages bi iteriralo kroz sve, hocemo samo prva 3 -->
					<!-- i in 3 ide od 1 do 3, ne od 0 do 2 -->
					<div v-for="i in 3" :key="i">
						<img class="itemImage" :src="imageSource(1, i)" />
						<h4>{{ cottages[i - 1].name }}</h4>
						<h6>${{ cottages[i - 1].pricePerDay }}/day</h6>
						<h6>{{ cottages[i - 1].address }}</h6>
					</div>
				</div>
			</div>
			<div class="largeCategory">
				<h1>Browse Adventures</h1>
				<div class="categoryItems">
					<div v-for="i in 3" :key="i">
						<img class="itemImage" :src="imageSource(2, i)" />
						<h4>{{ adventures[i - 1].name }}</h4>
						<h6>${{ adventures[i - 1].pricePerDay }}/day</h6>
						<h6>{{ adventures[i - 1].address }}</h6>
					</div>
				</div>
			</div>
		</div>
	</div>
</template> 

<script>
import { ref } from "vue";
import axios from "axios";
export default {
	setup() {
		var cottages = ref(null);
		var adventures = ref(null);
		var popupState = ref(null);
		var firstPassword = ref(null);
		var repeatPassword = ref(null);
		var matching = ref(null);
		var userType = ref(null);
		// Ako se inicijalizuje ovde na "Do passwords match?",
		// kasnice update-ovanje za jedan
		var userEmail = ref(null);
		var userFirstName = ref(null);
		var userLastName = ref(null);
		var userPhoneNumber = ref(null);
		var userAddress = ref(null);
		var userCity = ref(null);
		var userCountry = ref(null);
		var userRegistrationReason = ref(null);

		axios.get("/api/cottages/get").then(function (response) {
			cottages.value = response.data;
		});
		axios.get("/api/adventures/get").then(function (response) {
			adventures.value = response.data;
		});

		// Za u <template>
		return {
			cottages,
			adventures,
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
						return require("../../assets/images/cottage" +
							id +
							".png");
					case 2:
						return require("../../assets/images/adventure" +
							id +
							".png");
				}
			},
			popupState,
			openPopup() {
				popupState.value = true;
			},
			// closePopup() {
			// 	popupState.value = false;
			// },
			registerUser() {
				console.log("From registerUser() : ");
				console.log(this.firstPassword);
				console.log(this.repeatPassword);
				if (this.firstPassword == this.repeatPassword) {
					var user = {
						email: this.userEmail,
						name: this.userFirstName,
						lastname: this.userLastName,
						telephoneNumber: this.userPhoneNumber,
						address: this.userAddress,
						city: this.userCity,
						country: this.userCountry,
						password: this.firstPassword,
						type: this.userType,
						userRegistrationReason: this.userRegistrationReason,
					};
					axios
						.post("/api/register/create", user)
						.then(function (response) {
							// console.log("From AXIOS POST");
							// console.log(response);
							if (
								response.data ==
								"Error - User with that E-mail already exists."
							) {
								// Ovo radi
								alert("User with that E-mail already exists.");
							}
							popupState.value = false; // Zatvara popup
						});
				} else {
					alert("Passwords don't match!");
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
	background-color: #1a2022;
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
	height: 1400px;
	margin: 0;
	border-top-left-radius: 24px;
	border-top-right-radius: 24px;
}
.categoryItems {
	display: flex;
	align-items: flex-end;
	justify-content: space-between;
}
.itemImage {
	border-radius: 15px;
	object-fit: cover;
	width: 360px;
	height: 360px;
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
}
.largeCategory {
	margin-left: 224px;
	margin-right: 224px;
}
.popup {
	font-size: 22px;
	text-align: center;
	margin: 0px 36rem 100px 36rem;
	height: 610px;
	background-color: rgb(255, 255, 255);
	border-radius: 15px;
	display: flex;
	flex-direction: column;
}
#signUpButton {
	color: white;
	font-size: 24px;
	font-family: Arial, Helvetica, sans-serif;
	background-color: #ad6800;
	border-radius: 5px;
	border: none;
	width: 200px;
	height: 46px;
}
#signUpButton:hover {
	background-color: #583603;
	cursor: pointer;
}
.popupRow {
	display: flex;
	justify-content: space-around;
	align-items: center;
	width: 100%;
	height: 16.66%;
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
</style>