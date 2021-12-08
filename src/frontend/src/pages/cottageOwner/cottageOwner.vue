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
				<h4>{{ user.name }} {{ user.lastname }}</h4>
				<p>Cottage Owner</p>
			</div>
			<div class="rightFlex">
				<p>Email</p>
				<p class="smallText">{{ user.email }}</p>
				<p>Address</p>
				<p class="smallText">{{ user.address }}</p>
				<p>City</p>
				<p class="smallText">{{ user.city }}</p>
				<p>Country</p>
				<p class="smallText">{{ user.country }}</p>
				<p>Telephone Number</p>
				<p class="smallText">{{ user.telephoneNumber }}</p>
				<button @click="updateDetails()">Update Details</button>
			</div>
		</div>
		<div class="lowerFlex">
			<div class="table">
				<!-- past room booking -->
				<div class="table">
					<h3>Past Room Bookings</h3>
					<button>Sort</button>
					<div
						class="tableEntry"
						v-for="prb in pastRoomBookings"
						:key="prb"
					>
						<p class="entryName">{{ prb.cottageName }}</p>
						<div
							class="tableEntry"
							v-for="booking in prb.roomBookings"
							:key="booking"
						>
							<!-- <p class="entryRequestText">{{ booking.start }}</p>
							<p class="entryRequestText">{{ booking.end }}</p> -->
							<p>{{ booking.registeredUserId }}</p>
							<p>{{ booking.extraService }}</p>
							<p>Cena: {{ booking.price }}.00</p>

							<button
								class="entryApprove"
								@click="viewUser(booking.registeredUserId)"
							>
								View User
							</button>
							<button class="entryDeny" @click="deny()">
								Deny
							</button>
						</div>
					</div>
				</div>
			</div>
			<!-- Spacer -->
			<div style="height: 80px"></div>
			<button @click="wantsDeletion()">Delete My Account</button>
			<button @click="wantsDeletion()">Change My Password</button>
		</div>
	</div>
</template> 

<script>
import { ref } from "vue";
import axios from "axios";
export default {
	setup() {
		var user = ref(null);
		axios
			.get("/api/cottageOwner/getByEmail/" + localStorage["emailHash"])
			.then(function (response) {
				user.value = response.data;
				console.log(user.value);
			});

		var pastRoomBookings = ref(null);
		axios
			.get("/api/cottageOwner/pastRoomBookings/" + localStorage["userId"])
			.then(function (response) {
				console.log(response.data);
				pastRoomBookings.value = response.data;
			});

		return {
			user,
			pastRoomBookings,
			wantsDeletion() {
				alert("Not implemented yet!");
			},
			updateDetails() {
				alert("Not implemented yet!");
			},
			// TODO: ovde treba da se prikazu ti dobavljeni podaci
			viewUser(registeredUserId) {
				axios
					.get("/api/registeredUser/getUser/" + registeredUserId)
					.then(function (response) {
						console.log(response.data);
					});
				console.log(registeredUserId);
			},
		};
	},
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Aleo:wght@400&display=swap");
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
	font-size: 28px;
}
.rightFlex .smallText {
	margin: 0;
	font-size: 22px;
}
.rightFlex input {
	height: 24px;
	border-radius: 5px;
	border: 1px solid rgb(122, 122, 122);
	font-size: 18px;
	background-color: #f0f0f0;
}
.rightFlex input:focus {
	outline: none !important;
	border: 1px solid #ad6800;
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
.lowerFlex h3 {
	border-bottom: solid 1px rgb(145, 145, 145);
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
	width: 160px;
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
.tableEntry .entryApprove {
	background-color: rgb(108, 207, 108);
}
.tableEntry .entryDeny {
	background-color: rgb(194, 109, 109);
}
</style> 