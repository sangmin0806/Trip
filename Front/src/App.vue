<script setup>
import { RouterLink, RouterView } from 'vue-router';
import { ref } from 'vue';
import Modal from '@/components/user/Modal.vue';
import { logoutSubmit } from "@/assets/api/user";

const isModalActive = ref(false);
const loginFormVisible = ref(true);
const registerFormVisible = ref(false);
const isLoggedIn = ref(false);

function logout() {
  logoutSubmit(
    (data) => {
      isModalActive.value = false;
      loginFormVisible.value = false;
      isLoggedIn.value = false;
      reset();
    },
    (error) => {
      // Handle error
    }
  );
}
function reset() {
  user.value = {
    userId: "",
    userPwd: "",
    saveid: false,
    userName: "",
    email: "",
    agree: ""
  };
}
function closeModal() {
  isModalActive.value = false;
  reset();
}

function showLoginForm() {
  isModalActive.value = true;
  loginFormVisible.value = true;
  registerFormVisible.value = false;
}


function handleLoginSuccess() {
  isModalActive.value = false;
  loginFormVisible.value = false;
  isLoggedIn.value = true;
}

function handleRegisterSuccess() {
  loginFormVisible.value = true;
  registerFormVisible.value = false;
}
</script>

<template>
  <main>
    <header>
      <RouterLink to="/" class="link"><h2 class="logo">Trip</h2></RouterLink>
      <nav class="navigation">
        <RouterLink :to="{name:'board-list'}" class="link">공지사항</RouterLink>
        <RouterLink :to="{name: 'trip'}" class="link">여행지 조회</RouterLink>
        <RouterLink :to="{name: 'trip-recommend'}" class="link" v-if="isLoggedIn"
          >추천 코스</RouterLink
        >
        <RouterLink :to="{name: 'my-page'}" class="link" v-if="isLoggedIn">마이페이지</RouterLink>
        <button @click="showLoginForm" class="btnLogin-popup" v-if="!isLoggedIn">Login</button>
        <button @click="logout" v-if="isLoggedIn" class="btnLogout-popup">Logout</button>
      </nav>
    </header>
    <Modal
      :isActive="isModalActive"
      :loginFormVisible="loginFormVisible"
      :registerFormVisible="registerFormVisible"
      @update:loginFormVisible="loginFormVisible = $event"
      @update:registerFormVisible="registerFormVisible = $event"
      @close="closeModal"
      @loginSuccess="handleLoginSuccess"
      @registerSuccess="handleRegisterSuccess"
    />
    <RouterView />
  </main>
</template>

<style scoped>
@import url('@/assets/css/main.css');
</style>
