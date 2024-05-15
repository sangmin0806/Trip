<script setup>
import { defineProps } from 'vue';

const props = defineProps({
    item: {
        type: Object,
        required: true,
    },
});
</script>

<template>
    <li>{{ item.title }} {{ item.typeName }}</li>
</template>

<style scoped></style>
