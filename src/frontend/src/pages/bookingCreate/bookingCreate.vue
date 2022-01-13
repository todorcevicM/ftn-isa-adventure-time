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
		<div class="mainFlex" style="justify-content: space-evenly">
			<div class="rightFlex">
				<div
					v-for="a in entities"
					:key="a"
					style="display: flex; justify-content: space-between"
				>
					<p
						class="entryName"
						style="font-size: 26px; margin: 0; margin-right: 20px"
					>
						{{ a.name }}
					</p>
					<input
						style="width: 24px"
						type="radio"
						:value="a"
						v-model="selectedEntity"
					/>
				</div>
			</div>
			<div class="rightFlex">
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
					<input v-model="days" type="number" min="1" max="1000" />
				</div>
				<div>
					<p>How many guests?</p>
					<input v-model="guests" type="number" min="1" max="1000" />
				</div>
				<div>
					<button
						@click="createBooking()"
						style="width: -webkit-fill-available"
					>
						Create Booking
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
		const user = ref(null);
		var selectedEntity = ref(null);
		var date = ref(null);
		var time = ref(null);
		var days = ref(null);
		var guests = ref(null);

		var entities = ref(null);
		if (localStorage.type == "FISHING_INSTRUCTOR") {
			axios
				.get("/api/adventures/getAllByInstructorId/" + localStorage.userId)
				.then((result) => {
					entities.value = result.data;
				});
		} else if (localStorage.type == "COTTAGE_OWNER") {
			axios
				.get("/api/cottages/getAllByOwnerId/" + localStorage.userId)
				.then((result) => {
					entities.value = result.data;
				});
		} else if (localStorage.type == "BOAT_OWNER") {
			axios
				.get("/api/boats/getAllByOwnerId/" + localStorage.userId)
				.then((result) => {
					entities.value = result.data;
				});
		}

		return {
			user,
			entities,
			selectedEntity,
			date,
			time,
			days,
			guests,

			notImplemented() {
				alert("Not implemented yet!");
			},

			createBooking() {
				if (
					this.selectedEntity == null ||
					this.date == "" ||
					this.time == "" ||
					this.days == "" ||
					this.guests == ""
				) {
					alert("Please fill out all inputs.");
					return;
				}
				// isNaN je nepotreban jer se koristi input type="number"
				if (
					this.days < 1 ||
					this.days > 1000 ||
					this.guests < 1 ||
					this.guests > 1000
				) {
					alert("Please fill out numerical inputs correctly.");
					return;
				}
				if (localStorage.type == "FISHING_INSTRUCTOR") {
					axios
						.post(
							"/api/booking/adventure",
							{
								entityId: selectedEntity.value.id,
								date: date.value,
								time: time.value,
								days: days.value,
								guest: guests.value,
								entityOwnerId: localStorage.userId,
								userId: localStorage.whichUser,
							},
							{ headers: { "Content-Type": "application/json" } }
						)
						.then(function (response) {
							if (response.data == true) {
								alert("Booking created!");
								window.location.assign(
									"/fishingInstructor/" +
										localStorage["emailHash"]
								);
							} else {
								alert("Error creating booking!");
							}
						});
				} else if (localStorage.type == "COTTAGE_OWNER") {
					axios
						.post(
							"/api/booking/room",
							{
								entityId: selectedEntity.value.id,
								date: date.value,
								time: time.value,
								days: days.value,
								guest: guests.value,
								entityOwnerId: localStorage.userId,
								userId: localStorage.whichUser,
							},
							{ headers: { "Content-Type": "application/json" } }
						)
						.then(function (response) {
							if (response.data == true) {
								alert("Booking created!");
								window.location.assign(
									"/cottageOwner/" + localStorage["emailHash"]
								);
							} else {
								alert("Error creating booking!");
							}
						});
				} else if (localStorage.type == "BOAT_OWNER") {
					axios
						.post(
							"/api/booking/boat",
							{
								entityId: selectedEntity.value.id,
								date: date.value,
								time: time.value,
								days: days.value,
								guest: guests.value,
								entityOwnerId: localStorage.userId,
								userId: localStorage.whichUser,
							},
							{ headers: { "Content-Type": "application/json" } }
						)
						.then(function (response) {
							if (response.data == true) {
								alert("Booking created!");
								window.location.assign(
									"/boatOwner/" + localStorage["emailHash"]
								);
							} else {
								alert("Error creating booking!");
							}
						});
				}
			},
		};
	},
};
</script>

<style>
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
	margin: 20px auto;
	width: 530px;
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
input:invalid {
	border: 2px solid #b11919;
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