<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { ref } from 'vue';
import {loginFormSubmit,logoutSubmit,registerFormSubmit} from "@/assets/api/user"
const isModalActive = ref(false);
const loginFormVisible = ref(true);
const registerFormVisible = ref(false);
const isLoggedIn = ref(false);

const user = ref({
  userId: "",
  userPwd: "",
  saveid: false,
  userName: "",
  email: "",
  agree:""
})
function login() {
  console.log(user.value)
  loginFormSubmit(user.value,
    (data) => {
      isModalActive.value = false;
      loginFormVisible.value = false;
      isLoggedIn.value = true;
    },
    (error) => {
    }
  );
}
function logout() {
  logoutSubmit(
    (data) => {
      isModalActive.value = false;
      loginFormVisible.value = false;
      isLoggedIn.value = false;
      user.value.userId = "";
      user.value.userPwd = "";
      user.value.saveid = false;
    },
    (error) => {
    }
  )
  isModalActive.value = false;
}
function closeModal() {
  isModalActive.value = false;
}

function showLoginForm() {
  isModalActive.value = true;
  loginFormVisible.value = true;
  registerFormVisible.value = false;
}

function showRegisterForm() {
  loginFormVisible.value = false;
  registerFormVisible.value = true;
  user.value.userId = "";
  user.value.userPwd = "";
  user.value.saveid = false;
}
function register() {
  console.log(user.value)
  registerFormSubmit(user.value,
    (data) => {
      loginFormVisible.value = true;
      registerFormVisible.value = false;
      user.value.userId = "";
      user.value.userPwd = "";
      user.value.saveid = false;
    },
    (error) => {
    }
  );
}
</script>

<template>
  <main>
    <header>
      <RouterLink to="/" class="link"><h2 class="logo">Trip</h2></RouterLink>
      <nav class="navigation">
        <RouterLink :to="{name:'board-list'}" class="link">공지사항</RouterLink>
        <RouterLink :to="{name: 'trip-list'}" class="link">여행지 조회</RouterLink>
        <RouterLink :to="{name: 'trip-recommend'}" class="link" v-if="isLoggedIn"
          >추천 코스</RouterLink
        >
        <RouterLink :to="{name: 'my-page'}" class="link" v-if="isLoggedIn">마이페이지</RouterLink>
        <button @click="showLoginForm" class="btnLogin-popup" v-if="!isLoggedIn">Login</button>
        <button @click="logout" v-if="isLoggedIn" class="btnLogout-popup">Logout</button>
      </nav>
    </header>
    <div
      id="Modal"
      class="wrapper"
      :class="{ 'active-popup': isModalActive}"
      :style="{ height: registerFormVisible ? '600px' : '440px' }"
    >
      <div class="modal-content" :class="{ 'active': registerFormVisible } ">
        <span class="icon-close" @click="closeModal"><ion-icon name="close"></ion-icon></span>

        <div v-if="loginFormVisible" class="form-box login">
          <h2>Login</h2>
          <form @submit.prevent="login">
            <div class="input-box">
              <span class="icon"><ion-icon name="mail"></ion-icon></span>
              <input type="text" id="userId" v-model="user.userId" name="userId" required />
              <label>ID</label>
            </div>
            <div class="input-box">
              <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
              <input type="password" id="userPwd" v-model="user.userPwd" name="userPwd" required />
              <label>Password</label>
            </div>
            <div class="remember-forgot">
              <label
                ><input type="checkbox" id="saveid" v-model="user.saveid" name="saveid" /> Remember
                me</label
              >
              <a href="#">Forgot Password?</a>
            </div>
            <button type="submit" class="btn" id="loginBtn">Login</button>
            <div class="login-register">
              <p>
                Don't have an account?<a href="#" class="register-link" @click="showRegisterForm">
                  Register
                </a>
              </p>
            </div>
          </form>
        </div>

        <div v-if="registerFormVisible" class="form-box register">
          <h2>Registration</h2>
          <form @submit.prevent="register">
            <div class="input-box">
              <span class="icon"><ion-icon name="person"></ion-icon></span>
              <input type="text" id="userId2" v-model="user.userId" name="userId" required />
              <label>ID</label>
            </div>
            <div class="input-box">
              <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
              <input type="password" id="userPwd2" v-model="user.userPwd" name="userPwd" required />
              <label>Password</label>
            </div>
            <div class="input-box">
              <span class="icon"><ion-icon name="person"></ion-icon></span>
              <input type="text" id="userName" v-model="user.userName" name="userName" required />
              <label>Username</label>
            </div>
            <div class="input-box">
              <span class="icon"><ion-icon name="mail"></ion-icon></span>
              <input type="email" id="email" name="email" v-model="user.email" required />
              <label>Email</label>
            </div>
            <div class="remember-forgot">
              <label
                ><input type="checkbox" v-model="user.agree" /> I agree to the terms &
                conditions</label
              >
            </div>
            <button type="button" class="btn" id="registerBtn" @click="register">Register</button>
            <div class="login-register">
              <p>
                Already have an account?<a href="#" class="login-link" @click="showLoginForm">
                  Login
                </a>
              </p>
            </div>
          </form>
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped>
@import url('@/assets/css/main.css');
</style>
