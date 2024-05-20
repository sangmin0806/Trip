<script setup>
import { ref, defineProps, defineEmits } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { loginFormSubmit, registerFormSubmit } from '@/api/user/user.js';

const props = defineProps({
    isActive: Boolean,
    loginFormVisible: Boolean,
    registerFormVisible: Boolean,
});

const emit = defineEmits([
    'update:loginFormVisible',
    'update:registerFormVisible',
    'close',
    'loginSuccess',
    'registerSuccess',
]);
const router = useRouter();

const user = ref({
    userId: '',
    userPwd: '',
    saveid: false,
    userName: '',
    email: '',
    agree: '',
});

function reset() {
    user.value = {
        userId: '',
        userPwd: '',
        saveid: false,
        userName: '',
        email: '',
        agree: '',
    };
}
const route = useRoute();
function handleLogin() {
    loginFormSubmit(
        user.value,
        (data) => {
            reset();
            emit('loginSuccess');
            router.push(route.path);
        },
        (error) => {
            alert('로그인에 실패했습니다. 다시 시도해주세요.');
            user.value.userPwd = '';
        }
    );
}

function handleRegister() {
    registerFormSubmit(
        user.value,
        (data) => {
            reset();
            emit('registerSuccess');
        },
        (error) => {
            // Handle error
            alert('회원가입에 실패했습니다. 다시 시도해주세요.');
        }
    );
}

function closeModal() {
    reset();
    emit('close');
}

function showLoginForm() {
    reset();
    emit('update:loginFormVisible', true);
    emit('update:registerFormVisible', false);
}

function showRegisterForm() {
    reset();
    emit('update:loginFormVisible', false);
    emit('update:registerFormVisible', true);
}
</script>

<template>
  <div
    id="Modal"
    class="wrapper"
    :class="{ 'active-popup': isActive }"
    :style="{ height: registerFormVisible ? '600px' : '440px' }"
    style="z-index: 9999"
  >
    <div class="modal-content" :class="{ active: registerFormVisible }">
      <span class="icon-close" @click="closeModal"><ion-icon name="close"></ion-icon></span>

      <div v-if="loginFormVisible" class="form-box login">
        <h2>Login</h2>
        <form @submit.prevent="handleLogin">
          <div class="input-box">
            <span class="icon"><ion-icon name="mail"></ion-icon></span>
            <input type="text" v-model="user.userId" name="userId" required />
            <label>ID</label>
          </div>
          <div class="input-box">
            <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
            <input type="password" v-model="user.userPwd" name="userPwd" required />
            <label>Password</label>
          </div>
          <div class="remember-forgot">
            <label>
              <input type="checkbox" v-model="user.saveid" name="saveid" /> Remember me
            </label>
            <a href="#">Forgot Password?</a>
          </div>
          <button type="submit" class="btn">Login</button>
          <div class="login-register">
            <p>
              Don't have an account?
              <a href="#" @click.prevent="showRegisterForm">Register</a>
            </p>
          </div>
        </form>
      </div>

      <div v-if="registerFormVisible" class="form-box register">
        <h2>Registration</h2>
        <form @submit.prevent="handleRegister">
          <div class="input-box">
            <span class="icon"><ion-icon name="person"></ion-icon></span>
            <input type="text" v-model="user.userId" name="userId" required />
            <label>ID</label>
          </div>
          <div class="input-box">
            <span class="icon"><ion-icon name="lock-closed"></ion-icon></span>
            <input type="password" v-model="user.userPwd" name="userPwd" required />
            <label>Password</label>
          </div>
          <div class="input-box">
            <span class="icon"><ion-icon name="person"></ion-icon></span>
            <input type="text" v-model="user.userName" name="userName" required />
            <label>Username</label>
          </div>
          <div class="input-box">
            <span class="icon"><ion-icon name="mail"></ion-icon></span>
            <input type="email" v-model="user.email" name="email" required />
            <label>Email</label>
          </div>
          <div class="remember-forgot">
            <label>
              <input type="checkbox" v-model="user.agree" /> I agree to the terms & conditions
            </label>
          </div>
          <button type="submit" class="btn">Register</button>
          <div class="login-register">
            <p>
              Already have an account?
              <a href="#" @click.prevent="showLoginForm">Login</a>
            </p>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url('@/assets/css/main.css');
</style>
