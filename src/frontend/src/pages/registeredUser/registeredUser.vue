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
				<h4>{{ user.name }} {{ user.lastname }}</h4>
				<p class="smallText">Registered User</p>
				<p style="font-size: 18px">{{ user.email }}</p>
				<!-- Spacer -->
				<div style="height: 20px"></div>
				<p style="color: rgb(96, 96, 236)">Platinum Account</p>
				<!-- Spacer -->
				<div style="height: 20px"></div>
				<p>Penalties: 0</p>
			</div>
			<div class="rightFlex">
				<p class="smallText">Address</p>
				<p v-if="!updateToggle">{{ user.address }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newAddress"
				/>

				<p class="smallText">City</p>
				<p v-if="!updateToggle">{{ user.city }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newCity"
				/>

				<p class="smallText">Country</p>
				<p v-if="!updateToggle">{{ user.country }}</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newCountry"
				/>

				<p class="smallText">Telephone Number</p>
				<p v-if="!updateToggle">
					{{ user.telephoneNumber }}
				</p>
				<input
					type="text"
					v-if="updateToggle"
					v-model="newUser.newTelephoneNumber"
				/>

				<button @click="updateDetails()" v-if="!updateToggle">
					Update Details
				</button>
				<button
					@click="sendUpdatedDetails()"
					v-if="updateToggle"
					style="background-color: rgb(108, 207, 108)"
				>
					Submit
				</button>
			</div>
		</div>
		<div class="lowerFlex">
			<!-- past boat booking -->
			<div class="tableHeader">
				<h3>Past Boat Bookings</h3>
				<button style="margin: 0" @click="notImplemented()">
					Sort
				</button>
			</div>
			<div class="table">
				<div
					class="tableEntry"
					v-for="pbb in pastBoatBookingsDTO"
					:key="pbb"
				>
					<p class="entryName">{{ pbb.start }}</p>
					<p class="entryRequestText">{{ pbb.end }}</p>
					<p>{{ pbb.boatBooking.extraService }}</p>
					<p>Price : {{ pbb.boatBooking.price }}.00</p>
					<button
						class="entryApprove"
						@click="sendRevision(pbb.boatBooking.id, 'BOAT')"
						v-if="!pbb.revision.revised"
					>
						Revise
					</button>
					<button
						class="entryApprove"
						@click="sendAppeal(pbb.boatBooking.id, 'BOAT')"
					>
						Appeal
					</button>
				</div>
			</div>
			<!-- past adventure booking -->
			<div class="tableHeader">
				<h3>Past Adventure Bookings</h3>
				<button style="margin: 0" @click="notImplemented()">
					Sort
				</button>
			</div>
			<div class="table">
				<div
					class="tableEntry"
					v-for="pab in pastAdventureBookingsDTO"
					:key="pab"
				>
					<p class="entryName">{{ pab.start }}</p>
					<p class="entryRequestText">{{ pab.end }}</p>
					<p>{{ pab.adventureBooking.extraService }}</p>
					<p>Price : {{ pab.adventureBooking.price }}.00</p>
					<button
						class="entryApprove"
						@click="
							sendRevision(pab.adventureBooking.id, 'ADVENTURE')
						"
						v-if="!pab.revision.revised"
					>
						Revise
					</button>
					<button
						class="entryApprove"
						@click="
							sendAppeal(pab.adventureBooking.id, 'ADVENTURE')
						"
					>
						Appeal
					</button>
				</div>
			</div>
			<!-- past room booking -->
			<div class="tableHeader">
				<h3>Past Room Bookings</h3>
				<button style="margin: 0" @click="notImplemented()">
					Sort
				</button>
			</div>
			<div class="table">
				<div
					class="tableEntry"
					v-for="prb in pastRoomBookingsDTO"
					:key="prb"
				>
					<p class="entryName">{{ prb.start }}</p>
					<p class="entryRequestText">{{ prb.end }}</p>
					<p>{{ prb.roomBooking.extraService }}</p>
					<p>Price : {{ prb.roomBooking.price }}.00</p>
					<button
						class="entryApprove"
						@click="sendRevision(prb.roomBooking.id, COTTAGE)"
						v-if="!prb.revision.revised"
					>
						Revise
					</button>
					<button
						class="entryApprove"
						@click="sendAppeal(prb.roomBooking.id, COTTAGE)"
					>
						Appeal
					</button>
				</div>
			</div>

			<!-- future boat booking -->
			<div class="tableHeader">
				<h3>Future Boat Bookings</h3>
				<button style="margin: 0" @click="notImplemented()">
					Sort
				</button>
			</div>
			<div class="table">
				<div
					class="tableEntry"
					v-for="pbb in futureBoatBookings"
					:key="pbb"
				>
					<p class="entryName">{{ pbb.start }}</p>
					<p class="entryRequestText">{{ pbb.end }}</p>
					<p>{{ pbb.extraService }}</p>
					<p>Price : {{ pbb.price }}.00</p>
					<button class="entryDeny" @click="notImplemented()">
						Cancel
					</button>
				</div>
			</div>
			<!-- future adventure booking -->
			<div class="tableHeader">
				<h3>Future Adventure Bookings</h3>
				<button style="margin: 0" @click="notImplemented()">
					Sort
				</button>
			</div>
			<div class="table">
				<div
					class="tableEntry"
					v-for="pab in futureAdventureBookings"
					:key="pab"
				>
					<p class="entryName">{{ pab.start }}</p>
					<p class="entryRequestText">{{ pab.end }}</p>
					<p>{{ pab.extraService }}</p>
					<p>Price : {{ pab.price }}.00</p>
					<button class="entryDeny" @click="notImplemented()">
						Cancel
					</button>
				</div>
			</div>
			<!-- future room booking -->
			<div class="tableHeader">
				<h3>Future Room Bookings</h3>
				<button style="margin: 0" @click="notImplemented()">
					Sort
				</button>
			</div>
			<div class="table">
				<div
					class="tableEntry"
					v-for="prb in futureAdventureBookings"
					:key="prb"
				>
					<p class="entryName">{{ prb.start }}</p>
					<p class="entryRequestText">{{ prb.end }}</p>
					<p>{{ prb.extraService }}</p>
					<p>Price : {{ prb.price }}.00</p>
					<button class="entryDeny" @click="notImplemented()">
						Cancel
					</button>
				</div>
			</div>

			<!-- sub boat -->
			<div class="tableHeader">
				<h3>Subscribed Boats</h3>
				<button style="margin: 0" @click="notImplemented()">
					Sort
				</button>
			</div>
			<div class="table">
				<div class="tableEntry" v-for="pbb in subBoat" :key="pbb">
					<p class="entryName">{{ pbb.name }}</p>
					<p class="entryRequestText">{{ pbb.promoDescription }}</p>
					<p>{{ pbb.extraService }}</p>
					<p>Price : {{ pbb.pricePerDay }}.00</p>
					<button class="entryDeny" @click="unsubscribe(pbb.id, pbb.ownerId, 'BOAT')">
						Cancel
					</button>
				</div>
			</div>
			<!-- sub adventure -->
			<div class="tableHeader">
				<h3>Subscribed Adventures</h3>
				<button style="margin: 0" @click="notImplemented()">
					Sort
				</button>
			</div>
			<div class="table">
				<div class="tableEntry" v-for="pab in subAdventure" :key="pab">
					<p class="entryName">{{ pab.name }}</p>
					<p class="entryRequestText">{{ pab.promoDescription }}</p>
					<p>{{ pab.extraService }}</p>
					<p>Price : {{ pab.pricePerDay }}.00</p>
					<button class="entryDeny" @click="unsubscribe(pab.id, pab.instructorId,'ADVENTURE')">
						Cancel
					</button>
				</div>
			</div>
			<!-- sub cottage -->
			<div class="tableHeader">
				<h3>Subscribed Cottages</h3>
				<button style="margin: 0" @click="notImplemented()">
					Sort
				</button>
			</div>
			<div class="table">
				<div class="tableEntry" v-for="prb in subCottage" :key="prb">
					<p class="entryName">{{ prb.name }}</p>
					<p class="entryRequestText">{{ prb.promoDescription }}</p>
					<p>{{ prb.extraService }}</p>
					<p>Price : {{ prb.pricePerDay }}.00</p>
					<button class="entryDeny" @click="unsubscribe(prb.id, prb.ownerId, 'COTTAGE')">
						Cancel
					</button>
				</div>
			</div>
		</div>
		<!-- Spacer -->
		<div style="margin-top: 60px"></div>
		<!-- Main Card kopiran sa App.vue -->
		<div class="mainCard">
			<div class="search" style="text-align: center">
				<img
					src="../../assets/wheel.svg"
					style="width: 48px; margin-top: 18px"
				/>
				<input
					type="text"
					placeholder="Search..."
					v-model="searchQuery"
				/>
				<div v-if="searchQuery" class="searchResult">
					<p>No results found...</p>
				</div>
			</div>
			<div class="largeCategory">
				<h1>Browse Cottages</h1>
				<a v-if="showAllCottagesToggle" @click="showAllCottages()"
					>Show All</a
				>
				<div class="categoryItems">
					<div
						v-for="i in cottageNumToDisplay ? cottages.length : 3"
						:key="i"
					>
						<img
							@click="imageRedirect(1, i)"
							class="itemImage"
							:src="imageSource(1, i)"
						/>
						<h4>{{ cottages[i - 1].name }}</h4>
						<h6>Rating: 5.00</h6>
						<h6>${{ cottages[i - 1].pricePerDay }}/day</h6>
						<h6>{{ cottages[i - 1].address }}</h6>
					</div>
				</div>
			</div>
			<div class="largeCategory">
				<h1>Browse Adventures</h1>
				<a v-if="showAllAdventuresToggle" @click="showAllAdventures()"
					>Show All</a
				>
				<div class="categoryItems">
					<div
						v-for="i in adventureNumToDisplay
							? adventures.length
							: 3"
						:key="i"
					>
						<img
							@click="imageRedirect(2, i)"
							class="itemImage"
							:src="imageSource(2, i)"
						/>
						<h4>{{ adventures[i - 1].name }}</h4>
						<h6>Rating: 5.00</h6>
						<h6>${{ adventures[i - 1].pricePerDay }}/day</h6>
						<h6>{{ adventures[i - 1].address }}</h6>
					</div>
				</div>
			</div>
			<div class="largeCategory">
				<h1>Browse Boats</h1>
				<a v-if="showAllBoatsToggle" @click="showAllBoats()"
					>Show All</a
				>
				<div class="categoryItems">
					<div
						v-for="i in boatNumToDisplay ? boats.length : 3"
						:key="i"
					>
						<img
							@click="imageRedirect(3, i)"
							class="itemImage"
							:src="imageSource(3, i)"
						/>
						<h4>{{ boats[i - 1].name }}</h4>
						<h6>Rating: 5.00</h6>
						<h6>${{ boats[i - 1].pricePerDay }}/day</h6>
						<h6>{{ boats[i - 1].address }}</h6>
					</div>
				</div>
			</div>
		</div>

		<!-- ------- -->
		<div class="lowestFlex">
			<!-- Spacer -->
			<div style="height: 80px"></div>
			<button @click="notImplemented()">Submit a Complaint</button>
			<!-- Password Change -->
			<button @click="showPasswordChange()" v-if="!passwordChangeToggle">
				Change My Password
			</button>
			<div v-if="passwordChangeToggle" class="passwordChange">
				<div
					style="
						display: flex;
						flex-direction: row;
						justify-content: space-around;
					"
				>
					<div>
						<p>Enter new password :</p>
						<input type="password" v-model="firstPassword" />
					</div>
					<div>
						<p>Repeat password :</p>
						<input
							type="password"
							v-model="repeatPassword"
							@input="
								passwordMatchCheck(
									firstPassword,
									repeatPassword
								)
							"
						/>
					</div>
				</div>
				<div class="spacer">
					<p>{{ matching }}</p>
				</div>
				<button @click="updatePassword()">Update</button>
			</div>
			<button @click="deleteAccount()">Delete My Account</button>
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
			.get("/api/registeredUser/getByEmail/" + localStorage["emailHash"])
			.then(function (response) {
				user.value = response.data;
			});
		var newUser = ref({
			newAddress: localStorage.address,
			newCity: localStorage.city,
			newCountry: localStorage.country,
			newTelephoneNumber: localStorage.telephoneNumber,
		});
		var updateToggle = ref(null);

		var firstPassword = ref(null);
		var repeatPassword = ref(null);
		var matching = ref(null);
		var passwordChangeToggle = ref(null);

		// TODO: revision da sakrijem dugme

		var pastBoatBookingsDTO = ref(null);
		axios
			.get(
				"/api/registeredUser/pastBoatBookings/" + localStorage["userId"]
			)
			.then(function (response) {
				pastBoatBookingsDTO.value = response.data;

				// Formatiranje datuma
				var size = pastBoatBookingsDTO.value.length;
				for (let i = 0; i < size; i++) {
					let newStart =
						pastBoatBookingsDTO.value[i].boatBooking.start.split(
							"T"
						);
					let newStartSecondPart = newStart[1].split(".")[0];
					pastBoatBookingsDTO.value[i].start =
						newStartSecondPart + ", " + newStart[0];
					let newEnd =
						pastBoatBookingsDTO.value[i].boatBooking.end.split("T");
					let newEndSecondPart = newEnd[1].split(".")[0];
					pastBoatBookingsDTO.value[i].end =
						newEndSecondPart + ", " + newEnd[0];
				}
			});

		var pastAdventureBookingsDTO = ref(null);
		axios
			.get(
				"/api/registeredUser/pastAdventureBookings/" +
					localStorage["userId"]
			)
			.then(function (response) {
				pastAdventureBookingsDTO.value = response.data;

				// Formatiranje datuma
				var size = pastAdventureBookingsDTO.value.length;
				for (let i = 0; i < size; i++) {
					let newStart =
						pastAdventureBookingsDTO.value[
							i
						].adventureBooking.start.split("T");
					let newStartSecondPart = newStart[1].split(".")[0];
					pastAdventureBookingsDTO.value[i].start =
						newStartSecondPart + ", " + newStart[0];
					let newEnd =
						pastAdventureBookingsDTO.value[
							i
						].adventureBooking.end.split("T");
					let newEndSecondPart = newEnd[1].split(".")[0];
					pastAdventureBookingsDTO.value[i].end =
						newEndSecondPart + ", " + newEnd[0];
				}
			});

		var pastRoomBookingsDTO = ref(null);
		axios
			.get(
				"/api/registeredUser/pastRoomBookings/" + localStorage["userId"]
			)
			.then(function (response) {
				pastRoomBookingsDTO.value = response.data;

				// Formatiranje datuma
				var size = pastRoomBookingsDTO.value.length;
				for (let i = 0; i < size; i++) {
					let newStart =
						pastRoomBookingsDTO.value[i].roomBooking.start.split(
							"T"
						);
					let newStartSecondPart = newStart[1].split(".")[0];
					pastRoomBookingsDTO.value[i].start =
						newStartSecondPart + ", " + newStart[0];
					let newEnd =
						pastRoomBookingsDTO.value[i].roomBooking.end.split("T");
					let newEndSecondPart = newEnd[1].split(".")[0];
					pastRoomBookingsDTO.value[i].end =
						newEndSecondPart + ", " + newEnd[0];
				}
			});

		var futureBoatBookings = ref(null);
		axios
			.get(
				"/api/registeredUser/futureBoatBookings/" +
					localStorage["userId"]
			)
			.then(function (response) {
				futureBoatBookings.value = response.data;

				// Formatiranje datuma
				var size = futureBoatBookings.value.length;
				for (let i = 0; i < size; i++) {
					let newStart = futureBoatBookings.value[i].start.split("T");
					let newStartSecondPart = newStart[1].split(".")[0];
					futureBoatBookings.value[i].start =
						newStartSecondPart + ", " + newStart[0];
					let newEnd = futureBoatBookings.value[i].end.split("T");
					let newEndSecondPart = newEnd[1].split(".")[0];
					futureBoatBookings.value[i].end =
						newEndSecondPart + ", " + newEnd[0];
				}
			});

		var futureAdventureBookings = ref(null);
		axios
			.get(
				"/api/registeredUser/futureAdventureBookings/" +
					localStorage["userId"]
			)
			.then(function (response) {
				futureAdventureBookings.value = response.data;

				// Formatiranje datuma
				var size = futureAdventureBookings.value.length;
				for (let i = 0; i < size; i++) {
					let newStart =
						futureAdventureBookings.value[i].start.split("T");
					let newStartSecondPart = newStart[1].split(".")[0];
					futureAdventureBookings.value[i].start =
						newStartSecondPart + ", " + newStart[0];
					let newEnd =
						futureAdventureBookings.value[i].end.split("T");
					let newEndSecondPart = newEnd[1].split(".")[0];
					futureAdventureBookings.value[i].end =
						newEndSecondPart + ", " + newEnd[0];
				}
			});

		var futureRoomBookings = ref(null);
		axios
			.get(
				"/api/registeredUser/futureRoomBookings/" +
					localStorage["userId"]
			)
			.then(function (response) {
				futureRoomBookings.value = response.data;

				// Formatiranje datuma
				var size = futureRoomBookings.value.length;
				for (let i = 0; i < size; i++) {
					let newStart = futureRoomBookings.value[i].start.split("T");
					let newStartSecondPart = newStart[1].split(".")[0];
					futureRoomBookings.value[i].start =
						newStartSecondPart + ", " + newStart[0];
					let newEnd = futureRoomBookings.value[i].end.split("T");
					let newEndSecondPart = newEnd[1].split(".")[0];
					futureRoomBookings.value[i].end =
						newEndSecondPart + ", " + newEnd[0];
				}
			});

		var subBoat = ref(null);
		axios
			.get("/api/registeredUser/subBoat/" + localStorage["userId"])
			.then(function (response) {
				subBoat.value = response.data;
			});

		var subAdventure = ref(null);
		axios
			.get("/api/registeredUser/subAdventure/" + localStorage["userId"])
			.then(function (response) {
				subAdventure.value = response.data;
			});

		var subCottage = ref(null);
		axios
			.get("/api/registeredUser/subCottage/" + localStorage["userId"])
			.then(function (response) {
				subCottage.value = response.data;
			});

		var cottages = ref(null);
		var adventures = ref(null);
		var boats = ref(null);
		var cottageNumToDisplay = ref(null);
		var showAllCottagesToggle = ref(null);
		showAllCottagesToggle = true;
		var adventureNumToDisplay = ref(null);
		var showAllAdventuresToggle = ref(null);
		showAllAdventuresToggle = true;
		var boatNumToDisplay = ref(null);
		var showAllBoatsToggle = ref(null);
		showAllBoatsToggle = true;
		var searchQuery = ref(null);
		axios.get("/api/cottages/get").then(function (response) {
			cottages.value = response.data;
		});
		axios.get("/api/adventures/get").then(function (response) {
			adventures.value = response.data;
		});
		axios.get("/api/boats/get").then(function (response) {
			boats.value = response.data;
		});
		return {
			user,
			pastBoatBookingsDTO,
			pastAdventureBookingsDTO,
			pastRoomBookingsDTO,
			futureBoatBookings,
			futureAdventureBookings,
			futureRoomBookings,
			subBoat,
			subAdventure,
			subCottage,
			newUser,
			updateToggle,
			firstPassword,
			repeatPassword,
			matching,
			passwordChangeToggle,
			searchQuery,
			cottages,
			adventures,
			boats,
			cottageNumToDisplay,
			showAllCottagesToggle,
			adventureNumToDisplay,
			showAllAdventuresToggle,
			boatNumToDisplay,
			showAllBoatsToggle,
			notImplemented() {
				alert("Not implemented yet!");
			},
			updateDetails() {
				this.updateToggle = true;
			},
			sendUpdatedDetails() {
				if (
					this.newUser.newAddress == "" ||
					this.newUser.newCity == "" ||
					this.newUser.newCountry == "" ||
					this.newUser.newTelephoneNumber == ""
				) {
					alert("Please fill out all inputs.");
					return;
				}
				var sendingUser = this.user;
				sendingUser.address = this.newUser.newAddress;
				sendingUser.city = this.newUser.newCity;
				sendingUser.country = this.newUser.newCountry;
				sendingUser.telephoneNumber = this.newUser.newTelephoneNumber;
				sendingUser.userType = "registeredUser";
				console.log(sendingUser);
				axios
					.post("/api/user/update", sendingUser)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
					});
				window.location.reload();
			},
			passwordMatchCheck(firstPassword, repeatPassword) {
				if (firstPassword == repeatPassword) {
					this.matching = "Passwords Match!";
					return this.matching;
				} else {
					this.matching = "Passwords don't match!";
					return this.matching;
				}
			},
			updatePassword() {
				if (this.matching == "Passwords don't match!") {
					alert("Passwords don't match!");
					return;
				} else if (this.repeatPassword == null) {
					alert("All fields need to be filled.");
					return;
				}
				var sendingUser = this.user;
				sendingUser.password = this.repeatPassword;
				sendingUser.userType = "registeredUser";
				console.log(sendingUser);
				axios
					.post("/api/user/update", sendingUser)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
					});
				window.location.reload();
			},
			showPasswordChange() {
				this.passwordChangeToggle = true;
			},
			showAllCottages() {
				this.showAllCottagesToggle = false;
				this.cottageNumToDisplay = true;
			},
			showAllAdventures() {
				this.showAllAdventuresToggle = false;
				this.adventureNumToDisplay = true;
			},
			showAllBoats() {
				this.showAllBoatsToggle = false;
				this.boatNumToDisplay = true;
			},
			imageRedirect(type, id) {
				switch (type) {
					case 1:
						window.location.href = "/cottage/" + id;
						break;
					case 2:
						window.location.href = "/adventure/" + id;
						break;
					case 3:
						window.location.href = "/boat/" + id;
						break;
				}
			},
			imageSource(type, id) {
				switch (type) {
					case 1:
						// console.log("CASE 1 ID ZA IMAGESOURCE : " + id);
						return require("../../assets/images/cottage" +
							id +
							".png");
					case 2:
						// console.log("CASE 2 ID ZA IMAGESOURCE : " + id);
						return require("../../assets/images/adventure" +
							id +
							".png");
					case 3:
						// console.log("CASE 3 ID ZA IMAGESOURCE : " + id);
						return require("../../assets/images/boat" +
							id +
							".png");
				}
			},
			deleteAccount() {
				axios
					.post(
						"/api/user/deleteUser/" + this.user.id,
						"REGISTERED_USER",
						{ headers: { "Content-Type": "application/json" } }
					)
					.then(function (response) {
						if (response.data == true) {
							alert(
								"Your account has been deleted. You will be redirected to the home page."
							);
							window.location.href = "http://localhost:3000/";
						} else {
							alert(
								"There are currently active reservations in your name, the account cannot be deleted."
							);
						}
					})
					.catch(function (error) {
						alert(
							"There's been an error while submitting the request : " +
								error
						);
					});
			},
			sendRevision(bookingId, type) {
				let revision = prompt("Please enter your revision text");
				if (revision == null) {
					alert("You didn't enter any text.");
					return;
				}
				let rating = prompt("Please enter your rating (1-5)");
				if (rating == null || rating < 1 || rating > 5) {
					alert("Rating must be between 1 and 5");
					return;
				}
				axios
					.post(
						"/api/revision/sendRevision",
						{
							bookingId: bookingId,
							revision: revision,
							rating: rating,
							type: type,
						},
						{ headers: { "Content-Type": "application/json" } }
					)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
						alert("Revision sent!");
					});
			},
			sendAppeal(bookingId, type) {
				let appeal = prompt("Please enter your appeal text");
				if (appeal == null) {
					alert("You didn't enter any text.");
					return;
				}
				axios
					.post(
						"/api/appeal/sendAppeal",
						{
							bookingId: bookingId,
							appeal: appeal,
							type: type,
						},
						{ headers: { "Content-Type": "application/json" } }
					)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
						alert("Appeal sent!");
					});
			},
			unsubscribe(bookingId, ownerId, type) {
				axios
					.post(
						"/api/subscription/unsubscribe",
						{
							userId: this.user.id,
							ownerId: ownerId,
							type: type,
						},
						{ headers: { "Content-Type": "application/json" } }
					)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
						alert("You have been unsubscribed from this booking.");
						window.location.reload();
					});
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