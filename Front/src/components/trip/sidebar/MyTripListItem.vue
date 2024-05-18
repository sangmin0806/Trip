<script setup>
import { useSidebarStore } from '@/stores/sidebar.js';

const sidebarStore = useSidebarStore();
const props = defineProps({
    item: {
        type: Object,
        required: true,
    },
});
</script>

<template>
    <li>
        {{ item.title }}
    </li>
    <button @click.prevent="sidebarStore.removeTrip(item.contentId)">삭제</button>
</template>

<style scoped></style>
