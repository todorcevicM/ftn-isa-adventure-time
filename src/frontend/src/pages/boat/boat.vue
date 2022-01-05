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
					<img class="itemImage" :src="imageSource(boat.id)" />
				</div>
				<div>
					<!-- <img class="itemImage" src="../../assets/images/interior.png" /> -->
				</div>
				<h4>{{ boat.name }}</h4>
				<p>${{ boat.pricePerDay }}.00 / Day</p>
				<p>Rating: 5.00</p>
				<button @click="reserve()">Reserve</button>
			</div>
			<div class="rightFlex">
				<p>Type : {{ boat.type }} </p>
				<p>Length : {{ boat.length }} </p>
				<p>Engine Number : {{ boat.engineNumber }} </p>
				<p>Engine Power : {{ boat.enginePower }} </p>
				<p>Max Speed : {{ boat.maxSpeed }} </p>
				<p>Address : {{ boat.address }}</p>
				<p class="smallText">
					({{ boat.geoLng }}, {{ boat.geoLat }})
				</p>
				<p>Promo : {{ boat.promoDescription }}</p>
				<p>Rules : {{ boat.rules }}</p>
				<p>Info : {{ boat.priceAndInfo }}</p>
				<p>Start : {{ boat.reservationStart }}</p>
				<p>End : {{ boat.reservationEnd }}</p>
				<p>Navigation Equipment : {{ boat.navigationEquipment }}</p>
				<p>Equipment : {{ boat.equipment }}</p>
				<p>{{ boat.maxUsers }} person limit.</p>
				<p>{{ boat.percentTakenIfCancelled }}% taken if cancelled.</p>

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

			var boat = ref(null);
			axios.get("/api/boats/get/" + id).then(function (response) {
				boat.value = response.data;				
				localStorage["boatOwner"] = boat.value.ownerId;
			});

			var owner = ref(null);
			axios
				.get("/api/boatOwner/get/" + localStorage["boatOwner"])
				.then(function (response) {
					owner.value = response.data;
				});

			return {
				boat,
				owner,
				imageSource(id) {
					try {
						return require("../../assets/images/boat" + id + ".png");
					}
					catch (e) {
						// TODO: return praznu sliku ili nesto tako
						return require("../../assets/images/boat1.png")
					}
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

	h4 {
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