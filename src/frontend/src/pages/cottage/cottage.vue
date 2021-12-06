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
					<img class="itemImage" :src="imageSource(cottage.id)" />
				</div>
				<h4>{{ cottage.name }}</h4>
				<p>${{ cottage.pricePerDay }}.00 / Day</p>
			</div>
			<div class="rightFlex">
				<p>Address : {{ cottage.address }}</p>
				<p style="font-size: 22px">
					({{ cottage.geoLng }}, {{ cottage.geoLat }})
				</p>
				<p>Promo : {{ cottage.promoDescription }}</p>
				<p>Rules : {{ cottage.rules }}</p>
				<p>Info : {{ cottage.priceAndInfo }}</p>
				<p>Start : {{ cottage.reservationStart }}</p>
				<p>End : {{ cottage.reservationEnd }}</p>
				<p>{{ cottage.maxUsers }} person limit.</p>
				<p>Owner : {{ owner.name }}</p>
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

		var cottage = ref(null);
		axios.get("/api/cottages/get/" + id).then(function (response) {
			cottage.value = response.data;
			localStorage["cottageOwner"] = cottage.value.ownerId;
			// Mora localStorage da bi se izbegao limit scope-a .then()-a
		});

		var owner = ref(null);
		axios
			.get("/api/cottageOwner/get/" + localStorage["cottageOwner"])
			.then(function (response) {
				owner.value = response.data;
			});

		// Za u <template>
		return {
			cottage,
			owner,
			imageSource(id) {
				return require("../../assets/images/cottage" + id + ".png");
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
</style>