<script setup>
import { ref } from 'vue';
import {useSidebarStore } from '@/stores/sidebar.js';
import MyTripListItem from './MyTripListItem.vue';
const store = useSidebarStore();
const sidebarActive = ref(true);
</script>

<template>
  <div class="triplist" :class="{ active: sidebarActive  }">
    <div class="logo-menu">
      <h2 class="logo">으아아아악</h2>
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
            v-model="store.input"
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
        <my-trip-list-item v-for="(item, index) in store.triplist" :key="index" :item="item" />
      </ul>
    </div>
  </div>
</template>

<style scoped>
@import url('https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css');
@import url('@/assets/css/mytriplist.css');
</style>
