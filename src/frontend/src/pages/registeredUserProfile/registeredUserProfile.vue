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
				<p class="smallText">Registered User Profile</p>
				<p style="font-size: 18px">{{ user.email }}</p>
				<!-- Spacer -->
				<div style="height: 20px"></div>
				<p style="color: rgb(96, 96, 236)">Platinum Account</p>
				<!-- Spacer -->
				<div style="height: 20px"></div>
				<p>Penalties: 0</p>
			</div>
			<div class="rightFlex">
				<p class="smallText">Address</p>
				<p>{{ user.address }}</p>
				<p class="smallText">City</p>
				<p>{{ user.city }}</p>
				<p class="smallText">Country</p>
				<p>{{ user.country }}</p>
				<p class="smallText">Telephone Number</p>
				<p>{{ user.telephoneNumber }}</p>
			</div>
		</div>
		<div class="lowerFlex"></div>
	</div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
export default {
	setup() {
		var urlArray = window.location.href.split("/");
		var id = urlArray[4];

		var user = ref(null);
		axios
			.get("/api/registeredUser/getUser/" + id)
			.then(function (response) {
				user.value = response.data;
			});

		return {
			user,
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
</style>