<script setup>
import { ref } from 'vue';
import { useSidebarStore } from '@/stores/sidebar.js';
import { useAuthStore } from '@/stores/auth.js';
import { putTripList } from '@/assets/api/trip/tripList.js';
import MyTripListItem from './MyTripListItem.vue';
const sidebarStore = useSidebarStore();
const authStore = useAuthStore();
const title = ref('');
const detail = ref('');
const toggleSidebar = () => {
    if (!authStore.isLoggedIn) {
        alert('로그인 후에 이용해주세요!');
        return;
    }
    sidebarStore.tripListActive = !sidebarStore.tripListActive;
};

async function putTripListHandle() {
    const param = {
        title: title.value,
        detail: detail.value,
        contentIdList: sidebarStore.tripList.map((item) => item.contentId),
    };
    putTripList(
        param,
        (response) => {
            console.log(response);
            sidebarStore.clearTrips();
        },
        (error) => {
            console.log(error);
        }
    );
}
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
            <ul class="list">
                <my-trip-list-item v-for="(item, index) in sidebarStore.tripList" :key="index" :item="item" />
            </ul>
            <input type="text" v-model="title" class="link-name" required placeholder="리스트 이름" />
            <input type="textarea" v-model="detail" class="link-name" required placeholder="설명" />
            <button @click.prevent="putTripListHandle">저장</button>
        </div>
    </div>
</template>

<style scoped>
@import url('https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css');
@import url('@/assets/css/mytriplist.css');
</style>
