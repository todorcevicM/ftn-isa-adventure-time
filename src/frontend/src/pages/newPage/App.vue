<template>
  <div>
    <div id="logo-container">
      <div class="underlined">
        <img
          src="../../assets/wheel.svg"
          style="margin-bottom: -6px; margin-right: -7px"
        />
        <p style="font-size: 75px; letter-spacing: -2px; display: inline">
          NEWPAGE
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

      <p style="margin-top: 50; font-size: 23px">
        Return from GET fetch: {{ returnGET }}
      </p>
      <p style="margin-top: 50; font-size: 23px">
        Return from POST fetch: {{ returnPOST }}
      </p>
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
    const returnGET = ref(null); // TODO: Sta ovo znaci?
    const returnPOST = ref(null); // TODO: Sta ovo znaci?
    axios.get("/api/get").then((data) => (returnGET.value = data.data)); // TODO: Kakva je ovo sintaksa
    var x = 3;
    axios.post("/api/post", x).then((data) => (returnPOST.value = data.data)); // Ovo data => ... je valjda isto sto i function(data){...} ❗❗❗
    // axios.post("/api/post", {})
    //   .then(function (data) {
    //     returnPOST.value = data.data;
    //   })

    // Ovo u Notion :
    // axios
    //   .post("/api/post", {})
    //   .then(function (response) {
    //     console.log(response + " AAAAAAAAAAAAAAAAA");
    //   })
    //   .catch(function (error) {
    //     console.log(error + " BBBBBBBBBBBBBBBB");
    //   });

    // Za u <template>
    return {
      returnGET,
      returnPOST,
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