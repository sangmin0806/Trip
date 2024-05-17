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
    const triplist = store.triplist.value;
    triplist.push(props.item);
    store.setTriplist(triplist);
    console.log(store.triplist)
};
</script>

<template>
  <li>{{ item.title }} {{ item.typeName }}<img :src="item.imageUrl" alt="Image" /></li>
  <button @click="addToTriplist">추가</button>
</template>

<style scoped>
img {
  width: 150px;
  height: 150px;
}
</style>
