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
				<p>Cottage Owner</p>
			</div>
			<div class="rightFlex">
				<p>Email</p>
				<p class="smallText">{{ user.email }}</p>
				<p>Address</p>
				<p class="smallText">{{ user.address }}</p>
				<p>City</p>
				<p class="smallText">{{ user.city }}</p>
				<p>Country</p>
				<p class="smallText">{{ user.country }}</p>
				<p>Telephone Number</p>
				<p class="smallText">{{ user.telephoneNumber }}</p>
				<button @click="updateDetails()">Update Details</button>
			</div>
		</div>
		<div class="lowerFlex">
			<!-- Spacer -->
			<div style="height: 80px"></div>
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
			.get("/api/cottageOwner/getByEmail/" + localStorage["emailHash"])
			.then(function (response) {
				user.value = response.data;
			});

		return {
			user,
			wantsDeletion() {
				alert("Not implemented yet!");
			},
			updateDetails() {
				alert("Not implemented yet!");
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
.rightFlex {
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
</style> 