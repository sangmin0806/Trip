<script setup>
import { ref } from 'vue';
import { useSidebarStore } from '@/stores/sidebar.js';
import { getPlan, deletePlan } from '@/assets/api/trip/tripList';
const emit = defineEmits(['getPlanLists']);
const plan = ref({ data: [] });
const planActive = ref(false);
const props = defineProps({
    item: {
        type: Object,
        required: true,
    },
});
async function planListHandle() {
    getPlan(
        parseInt(props.item.id),
        (response) => {
            console.log(response);
            plan.value = response.data;
            planActive.value = !planActive.value;
        },
        (error) => {}
    );
}
async function deletePlanHandle() {
    deletePlan(
        parseInt(props.item.id),
        (response) => {
            emit('getPlanLists');
            planActive.value = !planActive.value;
        },
        (error) => {}
    );
}
</script>

<template>
    <li @click="planListHandle" class="plan">{{ item.title }} {{ item.description }}</li>
    <button @click.prevent="deletePlanHandle">삭제</button>
    <ul v-if="planActive">
        <li v-for="(item, index) in plan" :key="index" :item="item">
            {{ item.title }}<img :src="item.imageUrl" alt="Image" />
        </li>
    </ul>
</template>

<style scoped>
.plan {
    cursor: pointer;
}
img {
    width: 150px;
    height: 150px;
}
</style>
