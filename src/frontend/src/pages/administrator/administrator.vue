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
		<div v-if="checkFirstLogin() == 1" class="firstLogin">
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
							passwordMatchCheck(firstPassword, repeatPassword)
						"
					/>
				</div>
			</div>
			<div class="spacer">
				<p>{{ matching }}</p>
			</div>
			<button @click="updatePassword()">Update</button>
		</div>
		<div class="mainFlex">
			<div class="leftFlex">
				<h4>{{ user.name }} {{ user.lastname }}</h4>
				<p class="smallText">Administrator</p>
				<p style="font-size: 18px">{{ user.email }}</p>
				<!-- Spacer -->
				<div style="height: 40px"></div>
				<div class="percentage">
					<p style="margin-right: 12px">
						Percentage taken on reservation:
					</p>
					<p v-if="!percentageUpdateToggle">{{ percentage.value }}</p>
					<input
						type="text"
						v-if="percentageUpdateToggle"
						v-model="percentage.value"
					/>
					<p>%</p>
					<button
						v-if="!percentageUpdateToggle"
						@click="percentageUpdate()"
					>
						Update
					</button>
					<button
						v-if="percentageUpdateToggle"
						@click="percentageUpdateSubmit()"
						class="entryApprove"
					>
						Submit
					</button>
				</div>
				<!-- Spacer -->
				<div style="height: 40px"></div>
				<div class="businessReports">
					<p>Business Reports</p>
					<button @click="notImplemented()" style="width: 200px">
						Show Reports
					</button>
				</div>
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
			<div class="table">
					<h3>Revisions</h3>

					<div
						class="tableEntry"
						v-for="r in revisions"
						:key="r"
					>
						<p class="entryRequestText">
							{{ r.revision }}
						</p>
						<button class="entryApprove" @click="approveRevision(r.id)">
							Approve
						</button>
						<button class="entryDeny" @click="denyRevision(r.id)">Deny</button>
					</div>
				</div>
			<div class="table">
					<h3>Appeals</h3>

					<div
						class="tableEntry"
						v-for="a in appeals"
						:key="a"
					>
						<p class="entryRequestText">
							{{ a.appeal }}
						</p>
						<button class="entryApprove" @click="answerAppeal(a.id)">
							Answer
						</button>
					</div>
				</div>
			<div class="table">
				<h3>Registration Requests</h3>

				<div
					class="tableEntry"
					v-for="req in registrationRequests"
					:key="req"
				>
					<p class="entryName">{{ req.name }}</p>
					<p class="entryRequestText">
						{{ req.userRegistrationReason }}
					</p>
					<button class="entryApprove" @click="approveRegistrationRequest(req.id, req.userType)">
						Approve
					</button>
					<button class="entryDeny" @click="denyRegistrationRequest(req.id, req.userType)">Deny</button>
				</div>
			</div>
			<div class="table">
				<h3>Cottages</h3>
				<div
					class="tableEntry"
					v-for="cottage in cottages"
					:key="cottage"
				>
					<p class="entryName">{{ cottage.name }}</p>
					<button
						class="entryDeny"
						@click="deleteCottage(cottage.id)"
					>
						Delete
					</button>
				</div>
			</div>
			<div class="table">
				<h3>Cottage Owners</h3>
				<div
					class="tableEntry"
					v-for="cottageOwner in cottageOwners"
					:key="cottageOwner"
				>
					<p class="entryName">{{ cottageOwner.name }}</p>
					<button
						class="entryDeny"
						@click="deleteCottageOwner(cottageOwner.id)"
					>
						Delete
					</button>
				</div>
			</div>
			<div class="table">
				<h3>Adventures</h3>
				<div
					class="tableEntry"
					v-for="adventure in adventures"
					:key="adventure"
				>
					<p class="entryName">{{ adventure.name }}</p>
					<button
						class="entryDeny"
						@click="deleteAdventure(adventure.id)"
					>
						Delete
					</button>
				</div>
			</div>
			<div class="table">
				<h3>Fishing Instructors</h3>
				<div
					class="tableEntry"
					v-for="fishingInstructor in fishingInstructors"
					:key="fishingInstructor"
				>
					<p class="entryName">{{ fishingInstructor.name }}</p>
					<button
						class="entryDeny"
						@click="deleteFishingInstructor(fishingInstructor.id)"
					>
						Delete
					</button>
				</div>
			</div>
			<div class="table">
				<h3>Boats</h3>
				<div class="tableEntry" v-for="boat in boats" :key="boat">
					<p class="entryName">{{ boat.name }}</p>
					<button class="entryDeny" @click="deleteBoat(boat.id)">
						Delete
					</button>
				</div>
			</div>
			<div class="table">
				<h3>Boat Owners</h3>
				<div
					class="tableEntry"
					v-for="boatOwner in boatOwners"
					:key="boatOwner"
				>
					<p class="entryName">{{ boatOwner.name }}</p>
					<button
						class="entryDeny"
						@click="deleteBoatOwner(boatOwner.id)"
					>
						Delete
					</button>
				</div>
			</div>
			<div class="table">
				<h3>Registered Users</h3>
				<div
					class="tableEntry"
					v-for="registeredUser in registeredUsers"
					:key="registeredUser"
				>
					<p class="entryName">{{ registeredUser.name }}</p>
					<button
						class="entryDeny"
						@click="deleteRegisteredUser(registeredUser.id)"
					>
						Delete
					</button>
				</div>
			</div>
			<div class="table">
				<h3>Deletion Requests</h3>
				<div
					class="tableEntry"
					v-for="deletionRequest in deletionRequests"
					:key="deletionRequest"
				>
					<p class="entryName">{{ deletionRequest.name }}</p>
					<p class="entryName">{{ deletionRequest.lastname }}</p>
					<p class="entryRequestText">
						{{ deletionRequest.reason }}
					</p>
					<button
						class="entryApprove"
						@click="
							deleteUser(
								deletionRequest.requesterId,
								deletionRequest.forType
							)
						"
					>
						Approve
					</button>
					<button
						class="entryDeny"
						@click="
							denyDeletionRequest(
								deletionRequest.requesterId,
								deletionRequest.forType
							)
						"
					>
						Deny
					</button>
				</div>
			</div>
			<div v-if="isSuperAdmin == 1" class="table">
				<h3>Administrator List</h3>
				<div class="tableEntry" v-for="admin in admins" :key="admin">
					<p class="entryName">{{ admin.name }}</p>
					<button
						v-if="admin.id != 1"
						class="entryDeny"
						@click="deleteUser(admin.id, 'ADMINISTRATOR')"
					>
						Delete
					</button>
				</div>
				<div style="text-align: center">
					<button
						@click="addNewAdministratorToggleFunction()"
						v-if="!addNewAdministratorToggle"
					>
						Add New Administrator
					</button>
					<div
						v-if="addNewAdministratorToggle"
						class="passwordChange"
					>
						<div
							style="
								display: flex;
								flex-direction: row;
								justify-content: space-around;
								flex-wrap: wrap;
							"
						>
							<div>
								<p>First Name</p>
								<input
									type="text"
									v-model="newAdminFirstName"
									placeholder="John"
								/>
							</div>
							<div>
								<p>Last Name</p>
								<input
									type="text"
									v-model="newAdminLastName"
									placeholder="Johnson"
								/>
							</div>
							<div>
								<p>Phone Number</p>
								<input
									type="text"
									v-model="newAdminPhoneNumber"
									placeholder="+381 65 123 123 12"
								/>
							</div>
							<div>
								<p>Address</p>
								<input
									type="text"
									v-model="newAdminAddress"
									placeholder="42 John Lane"
								/>
							</div>
							<div>
								<p>City</p>
								<input
									type="text"
									v-model="newAdminCity"
									placeholder="Johnville"
								/>
							</div>
							<div>
								<p>Country</p>
								<input
									type="text"
									v-model="newAdminCountry"
									placeholder="Canada"
								/>
							</div>
							<div>
								<p>Email</p>
								<input
									type="text"
									v-model="newAdminEmail"
									placeholder="johnjohnson@gmail.com"
								/>
							</div>
						</div>
						<button @click="addNewAdministrator()">Add</button>
					</div>
				</div>
			</div>
			<!-- Spacer -->
			<div style="height: 80px"></div>
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
			.get("/api/administrator/get/" + localStorage["emailHash"])
			.then(function (response) {
				user.value = response.data;
				localStorage.address = user.value.address;
				localStorage.city = user.value.city;
				localStorage.country = user.value.country;
				localStorage.telephoneNumber = user.value.telephoneNumber;
				// userType iz ovog user-a je null jer je tako namesteno da bi se izbegao DTO
				// Posto znamo da radimo sa ovim tipom user-a,
				// samo u send metodi stavljamo userType koji treba
				isSuperAdmin.value = user.value.id;
			});
		var newUser = ref({
			newAddress: localStorage.address,
			newCity: localStorage.city,
			newCountry: localStorage.country,
			newTelephoneNumber: localStorage.telephoneNumber,
		});
		var updateToggle = ref(null);

		var registrationRequests = ref(null);
		axios.get("/api/user/getUnauthenticated").then(function (response) {
			registrationRequests.value = response.data;
		});

		var firstPassword = ref(null);
		var repeatPassword = ref(null);
		var matching = ref(null);
		var passwordChangeToggle = ref(null);

		var cottages = ref(null);
		axios.get("/api/cottages/get").then(function (response) {
			cottages.value = response.data;
		});
		var cottageOwners = ref(null);
		axios.get("/api/cottageOwner/get").then(function (response) {
			cottageOwners.value = response.data;
		});
		var adventures = ref(null);
		axios.get("/api/adventures/get").then(function (response) {
			adventures.value = response.data;
		});
		var fishingInstructors = ref(null);
		axios.get("/api/fishingInstructor/get").then(function (response) {
			fishingInstructors.value = response.data;
		});
		var boats = ref(null);
		axios.get("/api/boats/get").then(function (response) {
			boats.value = response.data;
		});
		var boatOwners = ref(null);
		axios.get("/api/boatOwner/get").then(function (response) {
			boatOwners.value = response.data;
		});
		var registeredUsers = ref(null);
		axios.get("/api/registeredUser/get").then(function (response) {
			registeredUsers.value = response.data;
		});

		var percentage = ref(null);
		var percentageUpdateToggle = ref(null);
		axios.get("/api/get/constant/" + "ptosr").then(function (response) {
			percentage.value = response.data;
		});

		var deletionRequests = ref(null);
		axios.get("/api/user/getDeletionRequests").then(function (response) {
			deletionRequests.value = response.data;
		});

		var isSuperAdmin = ref(null);
		var admins = ref(null);
		axios
			.get("/api/administrator/getAllNotDeleted")
			.then(function (response) {
				admins.value = response.data;
			});
		var addNewAdministratorToggle = ref(null);
		var newAdminEmail,
			newAdminFirstName,
			newAdminLastName,
			newAdminPhoneNumber,
			newAdminAddress,
			newAdminCity,
			newAdminCountry = ref(null);

		var revisions = ref(null);
		axios.get("/api/revision/getAllNotDeniedNotApproved").then(function (response) {
			revisions.value = response.data;
		});
		var appeals = ref(null);
		axios.get("/api/appeal/getAll").then(function (response) {
			appeals.value = response.data;
		});
	


		return {
			user,
			newUser,
			updateToggle,
			registrationRequests,
			firstPassword,
			repeatPassword,
			matching,
			passwordChangeToggle,
			cottages,
			cottageOwners,
			adventures,
			fishingInstructors,
			boats,
			boatOwners,
			registeredUsers,
			percentage,
			percentageUpdateToggle,
			deletionRequests,
			isSuperAdmin,
			admins,
			addNewAdministratorToggle,
			newAdminEmail,
			newAdminFirstName,
			newAdminLastName,
			newAdminPhoneNumber,
			newAdminAddress,
			newAdminCity,
			newAdminCountry,
			revisions,
			appeals,
			checkFirstLogin() {
				if (this.user.password == "0") {
					return 1;
				} else {
					return 0;
				}
			},
			notImplemented() {
				alert("Not implemented yet!");
			},
			updateDetails() {
				this.updateToggle = true;
			},
			approve() {
				alert("Not implemented yet!");
			},
			deny() {
				alert("Not implemented yet!");
				// TODO: Tacka 3.25, mora da se otvori dijalog gde admin upisuje razlog za deny.
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
				sendingUser.userType = "administrator";
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
				sendingUser.userType = "administrator";
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
			// Deletion
			deleteCottage(id) {
				axios
					.post("/api/cottages/delete/" + id)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
						var booked = response.data;
						if (booked == false) {
							alert(
								"There exist bookings with this item. Deletion is unavailable."
							);
							return;
						} else {
							alert("Deleted.");
							window.location.reload();
						}
					});
			},
			deleteCottageOwner(id) {
				axios
					.post("/api/cottageOwner/delete/" + id)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
					});
				window.location.reload();
			},
			deleteAdventure(id) {
				axios
					.post("/api/adventures/delete/" + id)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
						var booked = response.data;
						if (booked == false) {
							alert(
								"There exist bookings with this item. Deletion is unavailable."
							);
							return;
						} else {
							alert("Deleted.");
							window.location.reload();
						}
					});
			},
			deleteFishingInstructor(id) {
				axios
					.post("/api/fishingInstructor/delete/" + id)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
					});
				window.location.reload();
			},
			deleteBoat(id) {
				axios.post("/api/boats/delete/" + id).then(function (response) {
					console.log("Response : ");
					console.log(response.data);
					var booked = response.data;
					if (booked == false) {
						alert(
							"There exist bookings with this item. Deletion is unavailable."
						);
						return;
					} else {
						alert("Deleted.");
						window.location.reload();
					}
				});
			},
			deleteBoatOwner(id) {
				axios
					.post("/api/boatOwner/delete/" + id)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
					});
				window.location.reload();
			},
			deleteRegisteredUser(id) {
				axios
					.post("/api/registeredUser/delete/" + id)
					.then(function (response) {
						console.log("Response : ");
						console.log(response.data);
					});
				window.location.reload();
			},
			percentageUpdate() {
				this.percentageUpdateToggle = true;
			},
			percentageUpdateSubmit() {
				var percent = this.percentage.value;
				// this.percentage = alert("Percentage: " + this.percentage.value);
				axios
					.post("/api/update/constant/" + "ptosr" + "/" + percent)
					.then(function (response) {
						console.log("Response : " + response.data);
						// alert(response.data);
						window.location.reload();
					});
			},
			deleteUser(id, type) {
				// alert(id + " " + type);
				axios
					.post("/api/user/deleteUser/" + id, type, {
						headers: { "Content-Type": "application/json" },
					})
					.then(function (response) {
						if (response.data == true) {
							alert(
								"User has been deleted, and an email has been sent."
							);
							window.location.reload();
						} else {
							alert("There's been an error while deleting.");
						}
					})
					.catch(function (error) {
						alert(
							"There's been an error while deleting : " + error
						);
					});
			},
			denyDeletionRequest(id, type) {
				var reason = prompt("Enter reason for denial :");
				axios
					.post(
						"/api/user/denyUserDeletion/" + id,
						{ type: type, reason: reason },
						{
							headers: { "Content-Type": "application/json" },
						}
					)
					.then(function (response) {
						if (response.data == true) {
							alert("User has been notified.");
							window.location.reload();
						} else {
							alert(
								"There's been an error while notifying the user."
							);
						}
					})
					.catch(function (error) {
						alert(
							"There's been an error while notifying the user : " +
								error
						);
					});
			},
			addNewAdministratorToggleFunction() {
				this.addNewAdministratorToggle = true;
			},
			addNewAdministrator() {
				if (
					this.newAdminEmail == null ||
					this.newAdminFirstName == null ||
					this.newAdminLastName == null ||
					this.newAdminPhoneNumber == null ||
					this.newAdminAddress == null ||
					this.newAdminCity == null ||
					this.newAdminCountry == null
				) {
					alert("All fields need to be filled, try again.");
				} else {
					// Provera korektnog formata Email-a
					// TODO: prolazi a@., a ne treba
					if (
						!(
							(
								this.newAdminEmail.includes("@") &&
								this.newAdminEmail.indexOf("@") !=
									this.newAdminEmail.length - 1 &&
								this.newAdminEmail.indexOf("@") != 0
							) // Proverava da li imamo nesto posle @ ili pre @
						)
					) {
						alert(
							"Email isn't in the correct form. Please fill out the form again."
						);
						return;
					}
					axios
						.post("/api/user/create", {
							userType: "administrator",
							email: this.newAdminEmail,
							name: this.newAdminFirstName,
							lastname: this.newAdminLastName,
							telephoneNumber: this.newAdminPhoneNumber,
							address: this.newAdminAddress,
							city: this.newAdminCity,
							country: this.newAdminCountry,
							password: "0",
							userRegistrationReason: "",
						})
						.then(function (response) {
							if (
								response.data ==
								"Error - User with that E-mail already exists."
							) {
								alert("User with that E-mail already exists.");
							} else {
								alert(
									"A new administrator account has been created."
								);
								window.location.reload();
							}
						});
				}
			},
			approveRegistrationRequest(id, type) {
				// alert(id + " " + type);
				axios
					.post("/api/user/approveRegistrationRequest/" + id, type, {
						headers: { "Content-Type": "application/json" },
					})
					.then(function (response) {
						if (response.data == true) {
							alert(
								"User has been created, and an email has been sent."
							);
							window.location.reload();
						} else {
							alert("There's been an error while creating.");
						}
					})
					.catch(function (error) {
						alert(
							"There's been an error while creating : " + error
						);
					});
			},
			denyRegistrationRequest(id, type) {
				var reason = prompt("Enter reason for denial :");
				axios
					.post(
						"/api/user/denyRegistrationRequest/" + id,
						{ type: type, reason: reason },
						{
							headers: { "Content-Type": "application/json" },
						}
					)
					.then(function (response) {
						if (response.data == true) {
							alert("User has been notified.");
							window.location.reload();
						} else {
							alert(
								"There's been an error while notifying the user."
							);
						}
					})
					.catch(function (error) {
						alert(
							"There's been an error while notifying the user : " +
								error
						);
					});
			},
			approveRevision(revision_id) {
				axios
					.post("/api/revision/approveRevision/" + revision_id)
					.then(function (response) {
						if (response.data == true) {
							alert("Revision has been approved.");
							window.location.reload();
						} else {
							alert("There's been an error while approving.");
						}
					})
					.catch(function (error) {
						alert(
							"There's been an error while approving : " + error
						);
					});
			},
			answerAppeal(appeal_id) {
				let answer = prompt("Enter answer :");
				if (answer == null) {
					alert("Answer can't be empty.");
					return;
				}	

				axios.post("/api/appeal/answerAppeal/" + appeal_id, answer).then(function (
					response
				) {
					if (response.data == true) {
						alert("Appeal has been answered.");
						window.location.reload();
					} else {
						alert("There's been an error while answering.");
					}
				});
			},
			denyRevision(revision_id) {
				axios.post("/api/revision/denyRevision/" + revision_id).then(
					function (response) {
						if (response.data == true) {
							alert("Revision has been denied.");
							window.location.reload();
						} else {
							alert("There's been an error while denying.");
						}
					}
				)
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
}

.leftFlex img {
	width: 800px;
	height: 450px;
	border-radius: 15px;
	object-fit: cover;
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
	margin: 0px 200px 100px;
	display: flex;
	flex-direction: column;
}

.lowerFlex h3 {
	border-bottom: solid 1px rgb(145, 145, 145);
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
</style>
