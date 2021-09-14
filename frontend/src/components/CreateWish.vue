<template>
  <div id="create_wish">
    <input v-model="wishName" placeholder="Введите название цели"/>
    <br/>
    Название цели: {{ wishName }}
  </div>
</template>

<script>
export default {
  name: "CreateWIsh.vue",
  data() {
    return {
      wishName: ''
    }
  }
}
</script>

<style scoped>

</style>