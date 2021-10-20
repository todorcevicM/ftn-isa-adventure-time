<template>
  <div>
    <h1>i forgor 💀</h1>
    <p>Message from backend :</p>
    <code>{{ msg }}</code>
    <h1>i rember 👌😊🦩</h1>
    <button @click="log">Log!</button>
    marko
  </div>
</template>

<script>
// import HelloWorld from "./components/HelloWorld.vue";
export default {
  name: "App",
  data() {
    return {
      msg: "",
      fac: false,
    };
  },
  methods: {
    log() {
      console.log("Random result :");
      if (Math.random() > 0.5) this.fac = true;
      else this.fac = false;
      console.log(this.fac);
    },
  },
  mounted() {
    fetch("/api/person")
      .then((response) => response.text())
      .then((data) => {
        this.msg = data;
      });
  },
};
</script>

<style>
body {
  background-color: rgb(19, 19, 19);
  color: white;
  font-family: monospace;
  font-size: 18px;
}
code {
  background-color: rgb(10, 10, 10);
  color: rgb(125, 214, 125);
  padding: 2px;
  border-radius: 5px;
}
</style>