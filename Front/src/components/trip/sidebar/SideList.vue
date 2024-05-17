<script setup>
import { ref, defineProps,defineEmits } from 'vue';
import {useSidebarStore } from '@/stores/sidebar.js';
import ListItem from './ListItem.vue';

const sidebarStore = useSidebarStore();
const emit = defineEmits(['search']);
const props = defineProps({
    response: {
        type: Object,
        required: false,
        default: () => ({ data: [] }),
    },
});

const sidebarActive = ref(true);

const toggleSidebar = () => {
    sidebarActive.value = !sidebarActive.value;
};

const search = () => {
    emit('search');
};
</script>

<template>
  <div class="sidebar" :class="{ active: sidebarActive  }">
    <div class="logo-menu">
      <h2 class="logo">{{ sidebarStore.input }}</h2>
    </div>
    <i
      :class="['toggle-btn', sidebarActive ? 'bx bx-chevron-left' : 'bx bx-chevron-right']"
      @click="toggleSidebar"
    ></i>
    <ul class="list">
      <li class="list-item" id="search">
        <a href="#" :style="{ '--i': 1 }">
          <i class="bx bx-search"></i>
          <input
            type="text"
            v-model="sidebarStore.input"
            @keyup.enter="search"
            class="link-name"
            required
            placeholder="장소 검색"
          />
        </a>
      </li>
    </ul>
    <div class="scroll">
      <ul class="list">
        <ListItem v-for="(item, index) in response.data" :key="index" :item="item" />
      </ul>
    </div>
  </div>
</template>

<style scoped>
@import url('https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css');
@import url('@/assets/css/sidelist.css');
</style>
