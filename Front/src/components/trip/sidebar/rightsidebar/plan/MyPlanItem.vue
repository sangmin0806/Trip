<script setup>
import { ref } from 'vue';
import { useSidebarStore } from '@/stores/sidebar.js';
import { getPlan, deletePlan } from '@/api/trip/tripList';
const plan = ref({ data: [] });
const emit = defineEmits(['getPlanLists']);
const planActive = ref(false);
const props = defineProps({
    planList: {
        type: Object,
        required: true,
    },
});
async function planListHandle() {
    getPlan(
        parseInt(props.planList.id),
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
    <div class="container">
        <div class="date">📅 {{ planList.date }}</div>
        <h2 class="plan">{{ planList.title }} 일정</h2>
        <div class="title">
            <span><img class="image" :src="planList.url" @click="planListHandle" alt="Image" /></span>

            <div class="description" @click="planListHandle">{{ planList.description }}</div>
            <i class="bx bx-trash" @click.prevent="deletePlanHandle"></i>
        </div>

        <table v-if="planActive">
            <tbody>
                <tr v-for="(item, index) in plan" :key="index">
                    {{
                        index + 1
                    }}
                    <td colspan="3">
                        <div class="row-content">
                            <span><img :src="item.imageUrl" alt="Image" /></span>
                            <td>
                                <div class="text-area">
                                    <div class="item-info">
                                        <span class="date">{{ item.date }}</span>
                                        <strong class="trip-title"> {{ item.title }}</strong>
                                        <span class="typeName">{{ item.typeName }}</span>
                                    </div>
                                    <i class="bx bx-trash" @click.prevent="sidebarStore.removeTrip(item.contentId)"></i>
                                </div>
                            </td>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<style scoped>
.container {
    margin-bottom: 20px;
}

.container .title {
    display: flex;
    justify-content: space-between;
    border-radius: 5px;
    border: 2px solid white;
    background: transparent;
    width: 100%;
    max-height: 80px;
    text-overflow: ellipsis;
    overflow: hidden;
}
.title .description {
    display: flex;
    align-items: center;
    cursor: pointer;
}
.title i {
    font-size: 25px;
    display: flex;
    align-items: center;
}

table {
    border-collapse: separate;
    border-spacing: 10px 5px;
}
.container .date {
    margin-bottom: 10px;
}
.plan {
    align-items: center;
    margin-bottom: 10px;
}
.row-content {
    display: flex;
    align-items: center;
    border: 2px solid white; /* 각 tr 요소에 테두리 추가 */
    border-radius: 5px;
}
tr {
    border: 2px solid white !important;
}
td {
    width: 100%;
}
.item-info {
    max-height: 100px;
}

img {
    width: 120px;
    height: 100px;
    border-radius: 3px;
}
.image {
    cursor: pointer;
}

.text-area {
    display: flex;
    justify-content: space-between;
    width: 100%;
    height: 100px;
    padding: 0px 10px;
}
.text-area i {
    padding-top: 70px;
    margin-left: 5px;
    font-size: 30px;
    font-weight: 100;
    color: rgb(143, 143, 143);
    cursor: pointer;
}

.text-area .trip-title {
    display: block;
    text-align: left;
    max-width: 220px;
    max-height: 100px;
    text-overflow: ellipsis;
    overflow: hidden;
    font-size: 14px;
    font-weight: 700;
}

.text-area .typeName {
    display: flex;
    margin-left: 8px;
    font-size: 13px;
    color: rgb(143, 143, 143);
    align-items: center;
}
.text-area p {
    text-align: left;
    color: rgb(66, 66, 66);
    font-size: 15px;
    white-space: nowrap;
    text-overflow: ellipsis;
}
</style>
