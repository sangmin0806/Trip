<script setup>
import { defineProps } from 'vue';
import { useSidebarStore } from '@/stores/sidebar.js';
const store = useSidebarStore();
const props = defineProps({
    item: {
        type: Object,
        required: true,
    },
});
const addToTriplist = () => {
    store.addTrip(props.item);
    console.log(store.triplist)
};
</script>

<template>
  <li>{{ item.title }} {{ item.typeName }}<img :src="item.imageUrl" alt="Image" /></li>
  <button @click.prevent="addToTriplist()">추가</button>
</template>

<style scoped>
img {
  width: 150px;
  height: 150px;
}
</style>
