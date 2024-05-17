<script setup>
import {useSidebarStore } from '@/stores/sidebar.js';
const store = useSidebarStore();
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
</template>

<style scoped></style>
