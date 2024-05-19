<script setup>
import { ref, onMounted, watch } from 'vue';
import { useSidebarStore } from '@/stores/sidebar.js';
import { useAuthStore } from '@/stores/auth.js';
import { registerPlan, getPlanLists } from '@/assets/api/trip/tripList.js';
import MyTripListItem from './picktrip/MyTripListItem.vue';
import MyPlanList from './plan/MyPlanList.vue';
const sidebarStore = useSidebarStore();
const authStore = useAuthStore();
const title = ref('');
const description = ref('');
const myPlan = ref({ data: [] });
const toggleSidebar = () => {
    if (!authStore.isLoggedIn) {
        alert('로그인 후에 이용해주세요!');
        return;
    }
    sidebarStore.tripListActive = !sidebarStore.tripListActive;
    if (sidebarStore.tripListActive) {
        PlanListsHandle();
    }
};

async function registerPlanHandle() {
    const param = {
        title: title.value,
        description: description.value,
        contentIdList: sidebarStore.tripList.map((item) => item.contentId),
    };
    registerPlan(
        param,
        (response) => {
            PlanListsHandle();
            title.value = '';
            description.value = '';
            sidebarStore.clearTrips();
        },
        (error) => {
            console.log(error);
        }
    );
}
async function PlanListsHandle() {
    getPlanLists(
        (response) => {
            console.log(response.data);
            myPlan.value = response.data;
        },
        (error) => {
            console.log(error);
        }
    );
}
onMounted(() => {
    PlanListsHandle();
});
watch(
    () => authStore.isLoggedIn,
    (newValue, oldValue) => {
        if (newValue) {
            PlanListsHandle();
        } else {
            sidebarStore.tripListActive = false;
        }
    }
);
</script>

<template>
    <div class="triplist" :class="{ active: sidebarStore.tripListActive }">
        <div class="logo-menu">
            <h2 class="logo">Planner</h2>
        </div>
        <i
            :class="['toggle-btn', sidebarStore.tripListActive ? 'bx bx-chevron-right' : 'bx bx-chevron-left']"
            @click="toggleSidebar"
        ></i>
        <div class="scroll">
            <ul class="plan-list">
                <my-plan-list
                    v-for="(item, index) in myPlan"
                    :key="index"
                    :item="item"
                    @getPlanLists="PlanListsHandle"
                />
            </ul>
            <h3>여행지 추가</h3>
            <ul class="list">
                <my-trip-list-item v-for="(item, index) in sidebarStore.tripList" :key="index" :item="item" />
            </ul>
            <input type="text" v-model="title" class="link-name" required placeholder="리스트 이름" />
            <input type="textarea" v-model="description" class="link-name" required placeholder="설명" />
            <button @click.prevent="registerPlanHandle">저장</button>
        </div>
    </div>
</template>

<style scoped>
@import url('https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css');
@import url('@/assets/css/mytriplist.css');
</style>
