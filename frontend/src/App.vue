<template>
  <div id="auth">
    <div v-if="is_auth()">
      Logged in as: {{ person_name }}
      <button @click="send()">BUT</button>
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
      person_name: null
    }
  },
  methods: {
    is_auth() {
      this.person_name = this.$cookies.get('Person')
      return this.$cookies.get('Authorization') != null
    },
    send() {
      axios.get("http://localhost:8080/wishes", {withCredentials: true}).then(res => console.log(res.data))
    }
  }
}
</script>

<style>

</style>
