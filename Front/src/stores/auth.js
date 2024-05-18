import { ref } from 'vue';
import { defineStore } from 'pinia';
const useAuthStore = defineStore('auth', {
    state: () => ({
        isLoggedIn: ref(false),
    }),
});
export { useAuthStore };
