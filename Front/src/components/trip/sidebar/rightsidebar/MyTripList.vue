<script setup>
import { ref, onMounted, watch } from 'vue';
import { useSidebarStore } from '@/stores/sidebar.js';
import { useAuthStore } from '@/stores/auth.js';
import { registerPlan, getPlanLists } from '@/api/trip/tripList.js';
import MyTripItem from './picktrip/MyTripItem.vue';
import MyPlanItem from './plan/MyPlanItem.vue';
const sidebarStore = useSidebarStore();
const authStore = useAuthStore();
const title = ref('');
const description = ref('');
const date = ref('');
const planActive = ref(false);
const myPlan = ref({ data: [] });

const toggleMyPlan = () => {
    planActive.value = !planActive.value;
};

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
    const firstItem = sidebarStore.tripList[0];
    console.log(firstItem);
    const thumbnailImageId = firstItem ? firstItem.thumbnailImageId : null;
    const param = {
        title: title.value,
        description: description.value,
        date: date.value,
        contentIdList: sidebarStore.tripList.map((item) => item.contentId),
        thumbnailImageId: thumbnailImageId,
    };
    registerPlan(
        param,
        (response) => {
            PlanListsHandle();
            title.value = '';
            description.value = '';
            date.value = '';
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
            <button class="toggle" @click.prevent="toggleMyPlan">
                <p v-if="!planActive">My Plan</p>
                <p v-if="planActive">Add Plan</p>
                <i class="bx bx-paper-plane"></i>
            </button>
        </div>
        <i
            :class="['toggle-btn', sidebarStore.tripListActive ? 'bx bx-chevron-right' : 'bx bx-chevron-left']"
            @click="toggleSidebar"
        ></i>
        <div class="heading">
            <span class="info" v-if="planActive">My Plan</span>
            <span class="info" v-if="!planActive">Add Plan</span>
        </div>
        <div class="scroll">
            <ul class="plan-list" v-if="planActive">
                <li v-for="(item, index) in myPlan" :key="index">
                    <my-plan-item :planList="item" @getPlanLists="PlanListsHandle" />
                </li>
            </ul>
            <table class="list" v-if="!planActive">
                <tbody>
                    <my-trip-item
                        v-for="(item, index) in sidebarStore.tripList"
                        :key="index"
                        :item="item"
                        :index="index"
                    />
                </tbody>
            </table>
            <div class="save-area" v-if="!planActive">
                <div>
                    <input type="date" v-model="date" class="date" />
                    <input type="text" v-model="title" class="link-name" required placeholder="Plan 이름" />
                    <textarea v-model="description" class="link-name" required placeholder="설명"></textarea>
                </div>
                <button class="save" @click.prevent="registerPlanHandle">
                    <p>Save</p>
                    <i class="bx bx-calendar-heart"></i>
                </button>
            </div>
        </div>
    </div>
</template>

<style scoped>
@import url('https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css');
@import url('@/assets/css/mytriplist.css');
</style>
