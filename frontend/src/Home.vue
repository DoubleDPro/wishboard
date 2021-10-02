<template>
  <div id="home">
    <div class="content">
      <div class="header">
        <h1 style="font-family: 'Agency FB',serif"> This is your Wishboard </h1>
        <p style="font-family: 'Agency FB',serif"> and it helps you to come your dream true </p>
      </div>
      <div class="sign_content">
        <div v-if="is_start" class="sign">
          <div class="sign_in">
            <v-btn @click="sign_in_enter()" width="180px" elevation="5">Sign in</v-btn>
          </div>
          <div class="sign_up">
            <v-btn @click="sign_up_enter()" width="180px" elevation="5">Sign up</v-btn>
          </div>
        </div>
        <div v-else-if="is_sign_in" class="sign">
          <div class="sign_in">
            <v-btn width="180px" elevation="5">Sign in by Email</v-btn>
          </div>
          <div class="sign_up">
            <v-btn width="180px" elevation="5">Sign in by Google</v-btn>
          </div>
          <div class="sign_up">
            <v-btn width="180px" elevation="5">Sign in by Facebook</v-btn>
          </div>
          <div class="sign_up">
            <v-btn width="180px" elevation="5">Sign in by VK</v-btn>
          </div>
        </div>
        <div v-else-if="is_sign_up" class="sign">
          <div class="sign_in">
            <v-text-field v-model="name" style="width: 200px" label="Fullname"/>
          </div>
          <div class="sign_up">
            <v-text-field v-model="email" style="width: 200px" label="Email"/>
          </div>
          <div class="sign_up">
            <v-text-field style="width: 200px"
                          v-model="pass"
                          :rules="[rules.required, rules.min]"
                          :type="is_show_pass ? 'text' : 'password'"
                          label="Password"
                          hint="At least 8 characters"
                          counter
                          @click:append="is_show_pass = !is_show_pass"/>
          </div>
          <div class="sign_up">
            <v-btn @click="sign_up()" width="200px" elevation="5">Sign up</v-btn>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: 'Home',
  data: function () {
    return {
      is_start: true,
      is_sign_in: false,
      is_sign_up: false,
      is_show_pass: false,
      is_sign_up_suc: false,
      name: '',
      email: '',
      pass: '',
      rules: {
        required: value => !!value || 'Required',
        min: v => v.length >= 8 || 'Min 8 characters'
      }
    }
  },
  methods: {
    sign_in_enter() {
      this.is_sign_in = true;
      this.is_start = false;
    },
    sign_up_enter() {
      this.is_sign_up = true;
      this.is_start = false;
    },
    sign_up() {
      axios.post("http://localhost:8080/reg",
          {name: this.name, email: this.email, pass: this.pass})
      .then(res => console.log(res))
      .catch(error => console.log(error.response))
    }
  }
}
</script>

<style>
#home {
  display: flex;
  margin: auto;
  width: 50%;
  height: 100%;
  align-items: center;
}

.content {
  width: 100%;
}

.header {
  margin: auto;
  text-align: center;
}

.sign_content {
  margin: auto;
  width: 100%;
  display: flex;
}

.sign {
  margin: auto;
  width: 80%;
  padding-top: 10%;
  align-items: center;
}

.sign_in {
  margin: auto;
  width: fit-content;
}

.sign_up {
  margin: auto;
  width: fit-content;
  padding-top: 5%;
}
</style>
