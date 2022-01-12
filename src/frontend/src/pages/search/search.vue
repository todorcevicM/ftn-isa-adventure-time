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
			<div class="rightFlex">
				<div>
					<p>I want to reserve a ...</p>
					<select v-model="type">
						<option type>Cottage</option>
						<option>Boat</option>
						<option>Adventure</option>
					</select>
				</div>
				<div>
					<p>Name</p>
					<input v-model="searched" />
				</div>
				<div>
					<p>Start Date</p>
					<input v-model="date" type="date" />
				</div>
				<div>
					<p>Start Time</p>
					<input v-model="time" type="time" />
				</div>
				<div>
					<p>How many days?</p>
					<input v-model="days" type="number" />
				</div>
				<div>
					<p>How many guests?</p>
					<input v-model="guests" type="number" />
				</div>
				<div>
					<p style="font-size: 14px; text-align: center">
						All fields must be filled
					</p>
				</div>
				<div>
					<button
						@click="search()"
						style="width: -webkit-fill-available"
					>
						Search
					</button>
				</div>
			</div>
		</div>
		<div class="lowerFlex" v-if="searchResults">
			<div class="table">
				<div class="header">
					<h3>Search Results</h3>
					<button @click="notImplemented()">Sort</button>
				</div>
				<div class="tableEntry" v-for="sr in searchResults" :key="sr">
					<div class="entryLeft">
						<p
							class="entryLeftShort"
							v-if="displayingRooms == false"
						>
							{{ sr.name }}
						</p>
						<p
							class="entryLeftShort"
							v-if="displayingRooms == true"
						>
							{{ sr.cottage.name }}
						</p>
						<p
							class="entryLeftShort"
							v-if="displayingRooms == false"
						>
							{{ sr.address }}
						</p>
						<p
							class="entryLeftShort"
							v-if="displayingRooms == true"
						>
							{{ sr.cottage.address }}
						</p>
						<p
							class="entryLeftShort"
							v-if="displayingRooms == false"
						>
							{{ sr.reservationStart }}
						</p>
						<p
							class="entryLeftShort"
							v-if="displayingRooms == true"
						>
							{{ sr.cottage.reservationStart }}
						</p>
						<p
							class="entryLeftShort"
							v-if="displayingRooms == false"
						>
							{{ sr.reservationEnd }}
						</p>
						<p
							class="entryLeftShort"
							v-if="displayingRooms == true"
						>
							{{ sr.cottage.reservationEnd }}
						</p>
						<p class="entryLeftShort">{{ sr.extraService }}</p>
						<p
							class="entryLeftShort"
							v-if="displayingRooms == true"
						>
							Room with {{ sr.room.numberOfBeds }} beds.
						</p>
						<p
							class="entryLeftShort"
							v-if="displayingRooms == false"
						>
							Price : ${{ sr.pricePerDay }}.00/Day
						</p>
						<p
							class="entryLeftShort"
							v-if="displayingRooms == true"
						>
							Price : ${{ sr.cottage.pricePerDay }}.00/Day
						</p>
						<p v-for="(item) in sr.sp" :key="item">
							<!-- TODO: djotaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaa-->
							<input type="checkbox" :checked="item.checked" />
							{{ item.service }} : ${{ item.price }}
						</p>
					</div>
					<button
						v-if="!actionsHide"
						class="entryApprove"
						@click="reserve(sr)"
					>
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
		var today = new Date();
		var searched = ref("");
		var type = ref("Cottage");
		var date = ref(
			today.getFullYear() +
				"-" +
				("0" + (today.getMonth() + 1)).slice(-2) +
				"-" +
				("0" + today.getDate()).slice(-2)
		);
		var time = ref(
			today.getHours() +
				":" +
				today.getMinutes() +
				":" +
				today.getSeconds()
		);
		var days = ref(1);
		var guests = ref(1);

		var searchResults = ref(null);
		var actionsHide = true;
		if (localStorage.userId != null) actionsHide = false;
		var displayingRooms = ref(false);

		return {
			searched,
			type,
			date,
			time,
			days,
			guests,
			searchResults,
			actionsHide,
			displayingRooms,
			search() {
				// TODO: Provera input polja

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
						searchResults.value = response.data;
						console.log(searchResults.value);
						

						// Formatiranje datuma
						var size = searchResults.value.length;
						for (let i = 0; i < size; i++) {
							// Provera da li je search-ovan Cottage, zbog DTO-a
							searchResults.value[i].sp = [];
							if (searchResults.value[i].cottage != null) {
								displayingRooms.value = true;
								let newStart =
									searchResults.value[
										i
									].cottage.reservationStart.split("T");
								let newStartSecondPart =
									newStart[1].split(".")[0];
								searchResults.value[
									i
								].cottage.reservationStart =
									newStartSecondPart + ", " + newStart[0];
								let newEnd =
									searchResults.value[
										i
									].cottage.reservationEnd.split("T");
								let newEndSecondPart = newEnd[1].split(".")[0];
								searchResults.value[i].cottage.reservationEnd =
									newEndSecondPart + ", " + newEnd[0];

								


								let priceAndInfoString = searchResults.value[i].cottage.priceAndInfo;
								let priceAndInfoArray = priceAndInfoString.split(";");
								priceAndInfoArray.forEach((item, index) => {
									priceAndInfoArray[index] = item.split(":");
									searchResults.value[i].sp.push({
										service: priceAndInfoArray[index][0],
										price: priceAndInfoArray[index][1],
									});
								});
							} else if (
								// Provera da li je search-ovan Adventure, zbog vremena rada instruktora
								searchResults.value[i].location != null
							) {
								// TODO: Prikazati vremena instruktora, jer Adventure nema sam u sebi reservationStart i End,
								// mora poseban request da se pravi
								// ili pak DTO novi da se napravi, a sta mislis o tome, reci mi, reci
								let priceAndInfoString = searchResults.value[i].priceAndInfo;
								console.log(priceAndInfoString);
								let priceAndInfoArray = priceAndInfoString.split(";");
								priceAndInfoArray.forEach((item, index) => {
									priceAndInfoArray[index] = item.split(":");
									searchResults.value[i].sp.push({
										service: priceAndInfoArray[index][0],
										price: priceAndInfoArray[index][1],
									});
								});
							} else {
								let newStart =
									searchResults.value[
										i
									].reservationStart.split("T");
								let newStartSecondPart =
									newStart[1].split(".")[0];
								searchResults.value[i].reservationStart =
									newStartSecondPart + ", " + newStart[0];
								let newEnd =
									searchResults.value[i].reservationEnd.split(
										"T"
									);
								let newEndSecondPart = newEnd[1].split(".")[0];
								searchResults.value[i].reservationEnd =
									newEndSecondPart + ", " + newEnd[0];

								let priceAndInfoString = searchResults.value[i].priceAndInfo;
								console.log(priceAndInfoString);
								let priceAndInfoArray = priceAndInfoString.split(";");
								priceAndInfoArray.forEach((item, index) => {
									priceAndInfoArray[index] = item.split(":");
									searchResults.value[i].sp.push({
										service: priceAndInfoArray[index][0],
										price: priceAndInfoArray[index][1],
									});
								});
							}
						}
					})
					.catch(function (error) {
						searchResults.value = null;
						console.log(error);
						alert("Nothing has been found, please try again.");
					});
			},
			reserve(object) {
				console.log("Object to reserve :");
				console.log(object);
				// TODO: Extra Service
				let userId = localStorage.userId;
				if (this.type == "Boat") {
					axios
						.post(
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
						)
						.then(function (response) {
							console.log("Response :");
							console.log(response.data);
							alert("Successfully reserved!");
							// TODO: Redirect nazad na profil registeredUser-a
						});
				} else if (this.type == "Cottage") {
					axios
						.post(
							"/api/booking/cottage/",
							{
								cottageId: object.id,
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
						)
						.then(function (response) {
							console.log("Response :");
							console.log(response.data);
							alert("Successfully reserved!");
						});
				} else if (this.type == "Adventure") {
					axios
						.post(
							"/api/booking/adventure/",
							{
								adventureId: object.id,
								reservationStart: this.date,
								time: this.time,
								days: this.days,
								guests: this.guests,
								ownerId: object.instructorId,
								userId: userId,
							},
							{
								headers: { "Content-Type": "application/json" },
							}
						)
						.then(function (response) {
							console.log("Response :");
							console.log(response.data);
							alert("Successfully reserved!");
						});
				} else {
					alert("Please select a type!");
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
</style><style>
@import url("https://fonts.googleapis.com/css2?family=Aleo:wght@300;400&display=swap");
body {
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

.rightFlex {
	height: min-content;
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

/* LEFT FLEX ELEMENTS */

.leftFlexEntry {
	display: flex;
	flex-direction: row;
	align-items: center;
}

.leftFlexEntry input {
	width: 40px;
	margin: 0 10px;
	text-align: center;
	font-size: 22px;
}

.leftFlexEntry button {
	margin: 0 0 0 30px;
	width: 110px;
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
	text-align: left;
}

.rightFlex .smallText,
.leftFlex .smallText {
	margin: 0;
	font-size: 20px;
	color: #9e6b1d;
}

input {
	height: 24px;
	border-radius: 5px;
	border: 1px solid rgb(122, 122, 122);
	font-size: 18px;
	background-color: #f0f0f0;
}

input:focus {
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
	margin: 0px 200px 100px;
	display: flex;
	flex-direction: column;
}

/* TABLE */

.table {
	margin-top: 20px;
}

.header {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}

.header button {
	margin: auto 0;
	width: 110px;
}

.tableEntry {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	height: 55px;
	border-bottom: solid 1px rgb(145, 145, 145);
}

.tableSubEntry {
	margin: 0;
	margin-top: 10px;
	color: #9e6b1d;
	font-size: 26px;
}

.entryLeft {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	margin: auto 0;
}

.entryLeftShort {
	width: 180px;
}

.entryLeftLong {
	width: 650px;
}

.entryRight {
	min-width: 260px;
	display: flex;
	flex-direction: row-reverse;
	justify-content: space-between;
	margin: auto 0;
}

.entryRight button {
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

/* Sa Home Page-a */

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
.lowestFlex {
	margin: 0px 200px 100px;
	display: flex;
	flex-direction: column;
}
</style>