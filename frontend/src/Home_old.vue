<template>
  <div id="auth">
    <div v-if="is_auth()">
      Logged in as: {{ person_name }}
      <p>
        <input v-model="wish_name"/>
        <button @click="create_wish()">create wish</button>
      </p>
    </div>
    <div v-else>
      Авторизоваться <a href="http://localhost:8080/oauth2/authorization/google">через Google</a>
    </div>

  </div>
</template>

<script>

import axios from "axios";

export default {
  name: 'Auth',
  data: function () {
    return {
      person_name: null,
      wish_name: null
    }
  },
  methods: {
    is_auth() {
      this.person_name = this.$cookies.get('Person')
      return this.$cookies.get('Authorization') != null
    },
    create_wish() {
      //TODO get person_id from backend
      axios.post("http://localhost:8080/wishes", {name: this.wish_name, person_id: 1}, {withCredentials: true}).then(res => console.log(res.data))
    }
  }
}
</script>

<style>

</style>
