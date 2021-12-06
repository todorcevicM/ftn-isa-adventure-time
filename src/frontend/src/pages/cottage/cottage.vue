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
			</div>
			<div class="rightFlex">
				<p>{{ cottage.address }}</p>
				<p>{{ cottage.geoLng }}</p>
				<p>{{ cottage.geoLat }}</p>
				<p>{{ cottage.promoDescription }}</p>
				<p>{{ cottage.rules }}</p>
				<p>Price: ${{ cottage.pricePerDay }}.00 / Day</p>
				<p>{{ cottage.priceAndInfo }}</p>
				<p>{{ cottage.reservationStart }}</p>
				<p>{{ cottage.reservationEnd }}</p>
				<p>{{ cottage.maxUsers }}</p>
				<p>{{ cottage.owner_id }}</p>
			</div>
		</div>
	</div>
</template> 

<script>
import { ref } from "vue";
import axios from "axios";
export default {
	setup() {
		var cottage = ref(null);
		var urlArray = window.location.href.split("/");
		var id = urlArray[4];

		axios.get("/api/cottages/get/" + id).then(function (response) {
			cottage.value = response.data;
		});

		// Za u <template>
		return {
			cottage,
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
	margin: 0;
	font-size: 30px;
}
</style>