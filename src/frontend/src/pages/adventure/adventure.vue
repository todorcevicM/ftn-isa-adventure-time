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
				<div>
					<img class="itemImage" :src="imageSource(adventure.id)" />
				</div>
				<h4>{{ adventure.name }}</h4>
				<p>${{ adventure.pricePerDay }}.00 / Day</p>
				<button @click="reserve()">Reserve</button>
			</div>
			<div class="rightFlex">
				<p>Address : {{ adventure.address }}</p>
				<p style="font-size: 22px">
					({{ adventure.geoLng }}, {{ adventure.geoLat }})
				</p>
				<p>Promo : {{ adventure.promoDescription }}</p>
				<p>Rules : {{ adventure.rules }}</p>
				<p>Info : {{ adventure.priceAndInfo }}</p>
				<p>Equipment : {{ adventure.equipment }}</p>
				<p>{{ adventure.maxUsers }} person limit.</p>
				<p>
					{{ adventure.percentTakenIfCancelled }}% taken if cancelled.
				</p>
				<p>Instructor : {{ instructor.name }}</p>
				<p>Bio : {{ adventure.instructorBio }}</p>
			</div>
		</div>
	</div>
</template> 

<script>
import { ref } from "vue";
import axios from "axios";
export default {
	setup() {
		var urlArray = window.location.href.split("/");
		var id = urlArray[4];

		var adventure = ref(null);
		axios.get("/api/adventures/get/" + id).then(function (response) {
			adventure.value = response.data;
			localStorage["fishingInstructor"] = adventure.value.instructorId;
		});

		var instructor = ref(null);
		axios
			.get(
				"/api/fishingInstructor/get/" +
					localStorage["fishingInstructor"]
			)
			.then(function (response) {
				instructor.value = response.data;
			});

		// Za u <template>
		return {
			adventure,
			instructor,
			imageSource(id) {
				return require("../../assets/images/adventure" + id + ".png");
			},
			reserve() {
				alert("Not implemented yet!");
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
	justify-content: space-between;
}
.leftFlex {
	display: flex;
	flex-direction: column;
}
.leftFlex h4 {
	margin: 0;
	font-weight: 400;
	font-size: 50px;
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
}
.rightFlex p {
	margin: 4px 0;
	font-size: 36px;
}
button {
	margin: 0 auto;
	height: 40px;
	width: 140px;
	background-color: #da9e46;
	border: none;
	border-radius: 4px;
	font-family: Aleo;
	font-size: 24px;
}
button:hover {
	background-color: #9e6b1d;
	color: white;
	cursor: pointer;
}
</style>