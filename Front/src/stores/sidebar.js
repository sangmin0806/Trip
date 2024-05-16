import { ref } from 'vue';
import { defineStore } from 'pinia';
const sidebarActive = ref(false);
const sidelistShow = ref(false);
const useSidelistStore = defineStore({
  id: 'sidelist',
  state: () => ({
    sidelistShow: sidelistShow.value,
  }),
  actions: {
    toggleSidelist() {
      sidelistShow.value = !sidelistShow.value;
      this.sidelistShow = sidelistShow.value;
    },
    setSidelistActive(value) {
      sidelistShow.value = value;
      this.sidelistShow = value;
    },
  },
});
const useSidebarStore = defineStore({
  id: 'sidebar',
  state: () => ({
    sidebarActive: sidebarActive.value,
  }),
  actions: {
    toggleSidebar() {
      sidebarActive.value = !sidebarActive.value;
      this.sidebarActive = sidebarActive.value;
    },
    setSidebarActive(value) {
      sidebarActive.value = value;
      this.sidebarActive = value;
    },
  },
});

export { useSidebarStore, useSidelistStore };
