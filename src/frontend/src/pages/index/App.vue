<template>
  <div>
    <div id="logo-container">
      <div class="underlined">
        <img
          src="../../assets/wheel.svg"
          style="margin-bottom: -6px; margin-right: -7px"
        />
        <p style="font-size: 75px; letter-spacing: -2px; display: inline">
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
      <p style="margin: 0; font-size: 23px">or continue browsing as a guest.</p>
      <!-- v-for c in cottages bi iteriralo kroz sve, hocemo samo prva 3 -->
      <!-- i in 3 ide od 1 do 3, ne od 0 do 2 -->
      <div v-for="i in 3" :key="i">
        <img :src="imageSource(i)" style="width: 150px" />
        <h4>{{ cottages[i - 1].name }}</h4>
        <p>${{ cottages[i - 1].pricePerDay }}/day</p>
        <p>{{ cottages[i - 1].address }}</p>
      </div>
    </div>
    <div id="card"></div>
    <!-- <p>Message from backend :</p>
    <code>{{ msg }}</code>
    <h1>i rember 👌😊🦩</h1>
    <button @click="log">Log!</button> -->
  </div>
</template> 

<script>
import { ref } from "vue";
import axios from "axios";
export default {
  setup() {
    var cottages = ref(null);

    axios.get("/api/cottages/get").then(function (response) {
      cottages.value = response.data;
    });

    // Za u <template>
    return {
      cottages,
      imageSource(id) {
        return require("../../assets/images/cottage" + id + ".png");
      },
    };
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Aleo:wght@400&display=swap");
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
  border-bottom: #ad6800 3px solid;
  height: 80px;
}
#card {
  background-color: white;
  height: 400px;
  margin: 0;
  border-radius: 24px;
}
a {
  color: #ad6800;
  text-decoration: none;
}
a:hover {
  color: #53380e;
}
</style>