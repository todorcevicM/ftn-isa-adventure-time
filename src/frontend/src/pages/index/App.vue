<template>
	<div>
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
		<div style="height: 670px; font-size: 30px; margin: 50px 0 0 200px">
			<p style="margin: 0">
				<a href="#">Sign up</a> to schedule one of our adventures,
			</p>
			<p style="margin: 0; font-size: 23px">
				or continue browsing as a guest.
			</p>
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
}
#logo-container {
	margin: 3em 70em 0 10em;
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
	color: #53380e;
}
.mainCard {
	background-color: white;
	height: 1400px;
	margin: 0;
	border-radius: 24px;
}
.categoryItems {
	display: flex;
	align-items: flex-end;
	justify-content: space-between;
}
.itemImage {
	border-radius: 15px;
	object-fit: cover;
	width: 366px;
	height: 366px;
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
</style>