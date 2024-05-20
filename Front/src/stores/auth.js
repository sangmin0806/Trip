import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    isLoggedIn: ref(false),
    userId: ref(null),
  }),
  actions: {
    setUserId(id) {
      this.userId = id;
      this.isLoggedIn = true;
    },
    clearUserId() {
      this.userId = null;
      this.isLoggedIn = false;
    },
  },
});
