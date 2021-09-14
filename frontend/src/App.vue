<template>
  <div id="app">
    <h1> Создать цель </h1>
    <input v-model="wishName" placeholder="Введите название цели">
    <button v-on:click="createWish">Создать</button>
  </div>
</template>

<script>

export default {
  name: 'App',
  data: function () {
    return {
      wishName: ''
    }
  },
  methods: {
    createWish() {
      console.log(this.wishName)
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  vertical-align: middle;
  color: #2c3e50;
}
</style>
