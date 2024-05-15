<script setup>
import { ref, defineEmits } from 'vue';

const sidebarActive = ref(false);
const searchInput = ref('');
const emit = defineEmits(['search']);
const toggleSidebar = () => {
    sidebarActive.value = !sidebarActive.value;
};

const items = [
    { name: '관광지', id: 12 },
    { name: '문화시설', id: 14 },
    { name: '축제공연행사', id: 15 },
    { name: '여행코스', id: 25 },
    { name: '레포츠', id: 28 },
    { name: '숙박', id: 32 },
    { name: '쇼핑', id: 38 },
    { name: '음식점', id: 39 },
    { name: 'e', id: 9 },
];
const search = () => {
    if (searchInput.value.trim() === '') {
        return;
    }
    emit('search', searchInput.value);
};
</script>

<template>
    <nav class="sidebar" :class="{ active: sidebarActive }">
        <div class="logo-menu">
            <h2 class="logo">검색</h2>
            <i class="bx bx-menu toggle-btn" @click="toggleSidebar"></i>
        </div>

        <ul class="list">
            <li class="list-item" id="search">
                <a href="#" :style="{ '--i': 1 }">
                    <i class="bx bx-search"></i>
                    <input
                        type="text"
                        v-model="searchInput"
                        @keyup.enter="search"
                        class="link-name"
                        required
                        placeholder="장소 검색"
                    />
                </a>
            </li>
            <li class="list-item" v-for="(item, index) in items" :key="index" :style="{ '--i': index + 2 }">
                <a href="#" @click.prevent="handleClick(item)">
                    <i class="bx bx-grid-alt"></i>
                    <span class="link-name">{{ item.name }}</span>
                </a>
            </li>
        </ul>
    </nav>
</template>

<style scoped>
@import url('https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css');
@import url('@/assets/css/sidebar.css');
</style>
