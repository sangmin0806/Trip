<template>
  <div class="user-edit">
    <h1>Edit User</h1>
    <form @submit.prevent="handleSubmit" class="edit-form">
      <div class="form-group">
        <label for="userId">User ID:</label>
        <input type="text" v-model="user.userId" id="userId" readonly />
      </div>
      <div class="form-group">
        <label for="name">Name:</label>
        <input
          type="text"
          v-model="user.userName"
          id="name"
          placeholder="Enter your name"
          required
        />
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input
          type="email"
          v-model="user.email"
          id="email"
          placeholder="Enter your email"
          required
        />
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input
          type="password"
          v-model="user.userPwd"
          id="password"
          placeholder="Enter your password"
          required
        />
      </div>
      <button type="submit" class="btn-save">Save</button>
    </form>
  </div>
</template>

<script>
import { fetchUserDetail, updateUserDetail } from '@/api/user/myPage.js';
import { useAuthStore } from '@/stores/auth.js';

export default {
  name: 'UserEdit',
  data() {
    return {
      user: {
        userId: '',
        userName: '',
        email: '',
        userPwd: '',
      },
    };
  },
  async created() {
    await this.getUser();
  },
  methods: {
    async getUser() {
      try {
        const authStore = useAuthStore();
        const userId = authStore.userId; // 로그인된 사용자의 ID 가져오기
        if (userId) {
          const response = await fetchUserDetail(userId); // 유저 ID로 사용자 정보 가져오기
          this.user = response;
        } else {
          console.error('User is not logged in.');
        }
      } catch (error) {
        console.error(error);
      }
    },
    async handleSubmit() {
      try {
        await updateUserDetail(this.user);
        alert('User updated successfully');
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
.user-edit {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
  text-align: center;
  color: #333;
}

.edit-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 15px;
}

label {
  font-weight: bold;
  margin-bottom: 5px;
  display: block;
  color: #333;
}

input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 5px;
  box-sizing: border-box;
}

input:focus {
  border-color: #aaa;
  outline: none;
}

.btn-save {
  background: #4caf50;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background 0.3s;
}

.btn-save:hover {
  background: #45a049;
}
</style>
