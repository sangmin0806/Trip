<script setup>
import { defineProps, computed, ref } from 'vue';
import { useSidebarStore } from '@/stores/sidebar.js';
import { useAuthStore } from '@/stores/auth.js';
const authStore = useAuthStore();
const sidebarStore = useSidebarStore();

const props = defineProps({
    item: {
        type: Object,
        required: true,
    },
});
const isButtonDisabled = computed(() => {
    return sidebarStore.tripList.some((trip) => trip.contentId === props.item.contentId);
});

const addToTriplist = () => {
    sidebarStore.addTrip(props.item);
    sidebarStore.tripListActive = true;
};
</script>

<template>
    <li>{{ item.title }} {{ item.typeName }}<img :src="item.imageUrl" alt="Image" /></li>
    <button v-if="authStore.isLoggedIn" @click.prevent="addToTriplist()" :disabled="isButtonDisabled">추가</button>
</template>

<style scoped>
img {
    width: 150px;
    height: 150px;
}
</style>
