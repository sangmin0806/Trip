<script setup>
import { ref } from 'vue';

const sidebarActive = ref(false);

const toggleSidebar = () => {
  sidebarActive.value = !sidebarActive.value;
};
</script>

<template>
  <nav class="sidebar" :class="{ 'active': sidebarActive }">
    <div class="logo-menu">
      <h2 class="logo">검색</h2>
      <i class="bx bx-menu toggle-btn" @click="toggleSidebar"></i>
    </div>

    <ul class="list">
      <li class="list-item">
        <a href="#">
          <i class="bx bx-user"></i> <span class="link-name" style="--i:1;">여행지 검색</span></a
        >
      </li>
      <li class="list-item" id="search">
        <a href="#">
          <i class="bx bx-search"></i>
          <input type="text" class="link-name" placeholder="장소 검색" />
        </a>
      </li>

      <li class="list-item">
        <a href="#">
          <i class="bx bx-user"></i> <span class="link-name" style="--i:2;">User</span></a
        >
      </li>
      <li class="list-item">
        <a href="#">
          <i class="bx bx-grid-alt"></i>
          <span class="link-name" style="--i:3;">Messages</span></a
        >
      </li>
      <li class="list-item">
        <a href="#">
          <i class="bx bx-grid-alt"></i>
          <span class="link-name" style="--i:4;">Analytics</span></a
        >
      </li>
      <li class="list-item">
        <a href="#">
          <i class="bx bx-grid-alt"></i>
          <span class="link-name" style="--i:5;">a</span></a
        >
      </li>
      <li class="list-item">
        <a href="#">
          <i class="bx bx-grid-alt"></i>
          <span class="link-name" style="--i:6;">b</span></a
        >
      </li>
      <li class="list-item">
        <a href="#">
          <i class="bx bx-grid-alt"></i>
          <span class="link-name" style="--i:7;">c</span></a
        >
      </li>
      <li class="list-item">
        <a href="#">
          <i class="bx bx-grid-alt"></i>
          <span class="link-name" style="--i:8;">d</span></a
        >
      </li>
      <li class="list-item">
        <a href="#">
          <i class="bx bx-grid-alt"></i>
          <span class="link-name" style="--i:9;">e</span></a
        >
      </li>
    </ul>
  </nav>
</template>

<style scoped>
@import url('https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css');
@import url('@/assets/css/sidebar.css');
</style>
