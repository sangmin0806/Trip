<template>
  <div class="user-detail">
    <h1>User Detail</h1>
    <div v-if="user" class="user-info">
      <p v-for="(value, key) in user" :key="key">
        <span class="label">{{ key }}:</span> <span class="value">{{ value }}</span>
      </p>
    </div>
    <div v-else class="loading">
      <p>Loading...</p>
    </div>
  </div>
</template>

<script>
import { fetchUserDetail } from '@/assets/api/myPage/myPage.js';
import { useAuthStore } from '@/stores/auth.js';

export default {
  name: 'UserDetail',
  data() {
    return {
      user: null,
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
          console.log('User detail response:', response); // 응답 데이터 콘솔 출력
          this.user = response;
        } else {
          console.error('User is not logged in.');
        }
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
.user-detail {
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

.user-info {
  font-size: 18px;
  color: #555;
}

.user-info p {
  margin: 10px 0;
}

.label {
  font-weight: bold;
  color: #333;
}

.value {
  color: #666;
}

.loading {
  text-align: center;
  font-size: 18px;
  color: #999;
}
</style>
