<script setup>
import { ref } from 'vue';

const sidebarActive = ref(false);

const toggleSidebar = () => {
    sidebarActive.value = !sidebarActive.value;
};
</script>

<template>
    <div class="sidebar" :class="{ active: sidebarActive }">
        <div class="logo-menu">
            <h2 class="logo">검색</h2>
        </div>
    </div>
    <div class="toggle-btn-container" :class="{ active: sidebarActive }">
        <i
            :class="['toggle-btn', sidebarActive ? 'bx bx-chevron-left' : 'bx bx-chevron-right']"
            @click="toggleSidebar"
        ></i>
    </div>
</template>

<style scoped>
@import url('https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css');
@import url('@/assets/css/sidelist.css');
</style>
