<template>
	<div>
		<p>{{ boat }}</p>
		<p>{{ boat.name }}</p>
		<div>
			<img class="itemImage" :src="imageSource(boat.id)" />
		</div>
	</div>
</template> 

<script>
import { ref } from "vue";
import axios from "axios";
export default {
	setup() {
		var boat = ref(null);
		var urlArray = window.location.href.split("/");
		var id = urlArray[4];

		axios.get("/api/boat/get/" + id).then(function (response) {
			boat.value = response.data;
		});

		// Za u <template>
		return {
			boat,
			imageSource(id) {
				return require("../../assets/images/boat" + id + ".png");
			},
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
</style>