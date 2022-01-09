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

		<div>
			<div>
				<p>Name</p>
				<input v-model="searched" />
			</div>

			<div>
				<p>Select type of search</p>
				<select v-model="type">
					<option type>Cottage</option>
					<option>Boat</option>
					<option>Adventure</option>
				</select>
			</div>
			<div>
				<p>Date</p>
				<input v-model="date" type="date" />
			</div>
			<div>
				<p>Time</p>
				<input v-model="time" type="time" />
			</div>
			<div>
				<p>How many days</p>
				<input v-model="days" type="number" />
			</div>
			<div>
				<p>How many guests</p>
				<input v-model="guests" type="number" />
			</div>
			<div>
				<p>*All fields must be filled</p>
			</div>
			<div>
				<button @click="search()">Search</button>
			</div>

			<div class="table">
				<div class="tableEntry" v-for="sr in searchResults" :key="sr">
					<p class="entryName">{{ sr.name }}</p>
					<p class="entryName">{{ sr.address }}</p>
					<p class="entryRequestText">{{ sr.reservationStart }}</p>
					<p class="entryRequestText">{{ sr.reservationEnd }}</p>
					<p>{{ sr.extraService }}</p>
					<p>Price : {{ sr.pricePerDay }}.00</p>
					<button class="entryApprove" @click="reserve(sr)">
						Reserve
					</button>
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
		var searched = ref(null);
		var type = ref(null);
		var date = ref(null);
		var time = ref(null);
		var days = ref(null);
		var guests = ref(null);

		var searchResults = ref(null);

		return {
			searched,
			type,
			date,
			time,
			days,
			guests,
			searchResults,
			search() {
				// alert(this.searched);
				var searchParam =
					this.searched +
					";" +
					this.type +
					";" +
					this.date +
					";" +
					this.time +
					";" +
					this.days +
					";" +
					this.guests;
				axios
					.post("/api/booking/search", searchParam, {
						headers: { "Content-Type": "application/json" },
					})
					.then(function (response) {
						alert(response.data);
						searchResults.value = response.data;
						console.log(searchResults.value);
					});
			},
			reserve(object) {
				console.log(object);
				// TODO: user id + extra service
				let userId = localStorage.userId;
				let param =
					"user id umesto ovog string" +
					";" +
					this.guests +
					";" +
					"ovde ide extra service";
				if (this.type == "Boat") {
					axios.post(
						"/api/booking/boat",
						{
							boatId: object.id,
							reservationStart: this.date,
							time: this.time,
							days: this.days,
							guests: this.guests,
							ownerId: object.ownerId,
							userId: userId,
						},
						{
							headers: { "Content-Type": "application/json" },
						}
					);
				} else if (this.type == "Cottage") {
					axios.post("/api/booking/cottage/" + param, object, {
						headers: { "Content-Type": "application/json" },
					});
				} else if (this.type == "Adventure") {
					axios.post("/api/booking/adventure/" + param, object, {
						headers: { "Content-Type": "application/json" },
					});
				} else {
					alert("Please select a type");
				}
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
	/* height: 1900px; */
	margin: 0;
	border-radius: 24px;
	padding-bottom: 30px;
}
.categoryItems {
	display: flex;
	align-items: flex-end;
	justify-content: space-around;
	flex-wrap: wrap;
	row-gap: 20px;
}
.itemImage {
	margin: 0px 3px;
	border-radius: 15px;
	object-fit: cover;
	width: 360px;
	height: 360px;
	transition: all 0.2s ease-in-out;
}
.itemImage:hover {
	transform: scale(1.02);
	cursor: pointer;
}
.largeCategory {
	margin-left: 224px;
	margin-right: 224px;
}
.largeCategory a {
	margin-left: 30px;
	font-size: 24px;
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
.search {
	text-align: center;
	display: flex;
	align-items: center;
	flex-direction: column;
}
.search input {
	border-radius: 5px;
	margin-top: 10px;
}
.searchResult {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 370px;
	height: 470px;
	margin-top: 20px;
	border-radius: 15px;
	background-color: rgb(230, 230, 230);
}
.searchResult p {
	text-align: center;
	color: rgb(71, 71, 71);
	font-size: 35px;
}
.categoryItems div h4 {
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
	margin-top: 10px;
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

.rightFlex {
	height: 310px;
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
	margin: 20px 28em;
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

.lowestFlex {
	margin: 0px 200px;
	display: flex;
	flex-direction: column;
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
.tableHeader {
	margin-top: 30px;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}
</style>