<script setup>
import { RouterLink, RouterView } from 'vue-router';
import { ref } from 'vue';
import Modal from '@/components/user/Modal.vue';
import { logoutSubmit } from '@/api/user/user';
import { useAuthStore } from '@/stores/auth.js';
import { useRouter } from 'vue-router';

const isModalActive = ref(false);
const loginFormVisible = ref(true);
const registerFormVisible = ref(false);
const authStore = useAuthStore();
const router = useRouter();

function logout() {
<<<<<<< HEAD
  logoutSubmit(
    (data) => {
      authStore.clearUserId(); // 사용자 정보 초기화
      isModalActive.value = false;
      loginFormVisible.value = false;
      authStore.isLoggedIn = false;
      router.push('/'); // 로그아웃 후 홈으로 이동
    },
    (error) => {
      // Handle error
      console.error(error);
    }
  );
}

function closeModal() {
  isModalActive.value = false;
}

function showLoginForm() {
  isModalActive.value = true;
  loginFormVisible.value = true;
  registerFormVisible.value = false;
}

function handleLoginSuccess() {
  isModalActive.value = false;
  loginFormVisible.value = false;
  authStore.isLoggedIn = true;
}

function handleRegisterSuccess() {
  loginFormVisible.value = true;
  registerFormVisible.value = false;
=======
    logoutSubmit(
        (data) => {
            authStore.clearUserId(); // 사용자 정보 초기화
            isModalActive.value = false;
            loginFormVisible.value = false;
            authStore.isLoggedIn = false;
            router.push('/'); // 로그아웃 후 홈으로 이동
        },
        (error) => {
            // Handle error
            console.error(error);
        }
    );
}

function closeModal() {
    isModalActive.value = false;
}

function showLoginForm() {
    isModalActive.value = true;
    loginFormVisible.value = true;
    registerFormVisible.value = false;
}

function handleLoginSuccess() {
    isModalActive.value = false;
    loginFormVisible.value = false;
    authStore.isLoggedIn = true;
}

function handleRegisterSuccess() {
    loginFormVisible.value = true;
    registerFormVisible.value = false;
>>>>>>> eae5f170fa800f9fece9628269b8568f1913840c
}
</script>

<template>
  <div class="app-container">
    <header class="header">
      <RouterLink to="/" class="link">
        <h2 class="logo">Trip</h2>
      </RouterLink>
      <nav class="navigation">
        <RouterLink :to="{ name: 'board-list' }" class="link">공지사항</RouterLink>
        <RouterLink :to="{ name: 'trip' }" class="link">여행지 조회</RouterLink>
<<<<<<< HEAD
        <RouterLink :to="{ name: 'trip-recommend' }" class="link" v-if="authStore.isLoggedIn">추천 코스</RouterLink>
        <RouterLink :to="{ name: 'myPage' }" class="link" v-if="authStore.isLoggedIn">마이페이지</RouterLink>
=======
        <RouterLink :to="{ name: 'trip-recommend' }" class="link" v-if="authStore.isLoggedIn"
          >추천 코스</RouterLink
        >
        <RouterLink :to="{ name: 'myPage' }" class="link" v-if="authStore.isLoggedIn"
          >마이페이지</RouterLink
        >
>>>>>>> eae5f170fa800f9fece9628269b8568f1913840c
        <button @click="showLoginForm" class="btnLogin-popup" v-if="!authStore.isLoggedIn">
          Login
        </button>
        <button @click="logout" v-if="authStore.isLoggedIn" class="btnLogout-popup">Logout</button>
      </nav>
    </header>
    <main class="main-content">
<<<<<<< HEAD
      <Modal :isActive="isModalActive" :loginFormVisible="loginFormVisible" :registerFormVisible="registerFormVisible"
        @update:loginFormVisible="loginFormVisible = $event" @update:registerFormVisible="registerFormVisible = $event"
        @close="closeModal" @loginSuccess="handleLoginSuccess" @registerSuccess="handleRegisterSuccess" />
=======
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
>>>>>>> eae5f170fa800f9fece9628269b8568f1913840c
      <RouterView />
    </main>
  </div>
</template>

<style scoped>
@import url('@/assets/css/main.css');
</style>
