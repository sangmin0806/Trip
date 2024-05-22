<script setup>
import { ref, defineEmits } from 'vue';
import { useSidebarStore } from '@/stores/sidebar.js';
const store = useSidebarStore();

const emit = defineEmits(['itemClick']);

const items = [
    { name: '관광지', id: 12 },
    { name: '문화시설', id: 14 },
    { name: '축제행사', id: 15 },
    { name: '여행코스', id: 25 },
    { name: '레포츠', id: 28 },
    { name: '숙박', id: 32 },
    { name: '쇼핑', id: 38 },
    { name: '음식점', id: 39 },
];
const handleClick = (item) => {
    store.setContentTypeId(item.id);
    emit('itemClick');
};
</script>

<template>
    <nav class="sidebar">
        <div class="logo-menu">
            <i class="bx bx-menu toggle-btn"></i>
        </div>

        <ul class="list">
            <li class="list-item" v-for="(item, index) in items" :key="index" :style="{ '--i': index + 2 }">
                <a href="#" @click.prevent="handleClick(item)">
                    <i class="bx bx-category-alt"></i>
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
