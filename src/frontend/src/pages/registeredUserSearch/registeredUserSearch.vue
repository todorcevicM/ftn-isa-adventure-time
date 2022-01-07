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
				<input v-model="searched" />
			</div>

			<div>
				<select v-model="selected">
					<option selected>Cottage</option>
					<option>Boat</option>
					<option>Adventure</option>
				</select>
			</div>
			<div>
				<input v-model="date" type="date" />
			</div>
			<div>
				<input v-model="time" type="time" />
			</div>
			<div>
				<input v-model="days" type="number" />
			</div>
			<div>
				<input v-model="guest" type="number" />
			</div>
			<div>
				<button @click="search()">Search</button>
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
		var selected = ref(null);
		var date = ref(null);
		var time = ref(null);
		var days = ref(null);
		var guest = ref(null);
		return {
			searched,
			selected,
			date,
			time,
			days,
			guest,
			search() {
				alert(this.searched);
				var searchParam =
					this.searched +
					";" +
					this.selected +
					";" +
					this.date +
					";" +
					this.time +
					";" +
					this.days +
					";" +
					this.guest;
				axios
					.post("/api/reservation/search", searchParam, {
						headers: { "Content-Type": "application/json" },
					})
					.then(function (response) {
						alert(response.data);
					});
			},
		};
	},
};
</script>