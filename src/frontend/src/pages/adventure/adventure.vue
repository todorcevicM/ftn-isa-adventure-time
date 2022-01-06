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
				<div>
					<img class="itemImage" :src="imageSource(adventure.id)" />
				</div>
				<div>
					<!-- Iz nekog razloga ovde mora obrnuto lat i lng -->
					<!-- I ovaj style ne izgleda da menja nista -->
					<GMapMap
						:center="{
							lat: adventure.geoLng,
							lng: adventure.geoLat,
						}"
						:zoom="10"
						map-type-id="roadmap"
						style=""
					>
						<GMapMarker
							:position="{
								lat: adventure.geoLng,
								lng: adventure.geoLat,
							}"
						/>
					</GMapMap>
				</div>
			</div>
			<div class="rightFlex">
				<h4>{{ adventure.name }}</h4>
				<div style="display: flex; justify-content: space-between">
					<p>${{ adventure.pricePerDay }}.00 / Day</p>
					<p>Rating: 5.00</p>
				</div>
				<!-- Spacer -->
				<div
					style="border-bottom: solid #da9e46 1px; margin: 10px 0px"
				></div>
				<!-- Spacer -->
				<p class="smallText">Address</p>
				<p>{{ adventure.address }}</p>
				<p class="smallText">Location</p>
				<p>{{ adventure.location }}</p>
				<p class="smallText">Promo</p>
				<p>{{ adventure.promoDescription }}</p>
				<p class="smallText">Rules</p>
				<p>{{ adventure.rules }}</p>
				<p class="smallText">Info</p>
				<p>{{ adventure.priceAndInfo }}</p>
				<p class="smallText">Equipment</p>
				<p>{{ adventure.equipment }}</p>
				<p class="smallText">Person Limit</p>
				<p>{{ adventure.maxUsers }} People</p>
				<p class="smallText">Percentage Taken if Cancelled</p>
				<p>{{ adventure.percentTakenIfCancelled }}%</p>
				<p class="smallText">Instructor</p>
				<p>{{ instructor.name }}</p>
				<p class="smallText">Instructor Bio</p>
				<p>{{ adventure.instructorBio }}</p>
				<button @click="reserve()">Reserve</button>
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
	justify-content: space-around;
}
.leftFlex {
	display: flex;
	flex-direction: column;
}
h4 {
	margin: 0;
	font-weight: 400;
	font-size: 42px;
}
.leftFlex p {
	margin: 0;
	font-size: 27px;
	font-weight: 100;
}

.leftFlex > div > img {
	width: 650px;
	height: 360px;
	border-radius: 15px;
	object-fit: cover;
}

.vue-map {
	border-radius: 15px !important;
	width: 650px !important;
	height: 360px !important;
}

.rightFlex {
	height: min-content;
	min-width: 320px;
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
	font-size: 24px;
}

.rightFlex .smallText {
	margin: 0;
	font-size: 20px;
	color: #9e6b1d;
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
	transition: 0.15s;
}
button:hover {
	background-color: #9e6b1d;
	color: white;
	cursor: pointer;
}
</style>