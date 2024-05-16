<script setup>
import { ref, defineProps } from 'vue';
import {useSidebarStore } from '@/stores/sidebar.js';
import ListItem from './ListItem.vue';
const store = useSidebarStore();
const props = defineProps({
    response: {
        type: Object,
        required: false,
        default: () => ({ data: [] }),
    },
    input: {
        type: String,
        required: false,
        default: '',
    },
});
const toggleSidebar = () => {
    store.toggleSidebar(); // Pinia store의 toggleSidebar 액션 호출
};
</script>

<template>
  <div class="sidebar" :class="{ active: store.sidebarActive }">
    <div class="logo-menu">
      <h2 class="logo">{{ input }}</h2>
    </div>
    <i
      :class="['toggle-btn', store.sidebarActive ? 'bx bx-chevron-left' : 'bx bx-chevron-right']"
      @click="toggleSidebar"
    ></i>
    <ul>
      <ListItem v-for="(item, index) in response.data" :key="index" :item="item" />
    </ul>
  </div>
</template>

<style scoped>
@import url('https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css');
@import url('@/assets/css/sidelist.css');
</style>
