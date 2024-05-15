<script setup>
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import { search } from '@/assets/api/trip/tripSearch.js';

import SideBar from './sidebar/SideBar.vue';
import SideList from './sidebar/SideList.vue';
const lat = ref(37.566826);
const lng = ref(126.9786567);
const map = ref();
const router = useRouter();
const tripList = ref([]);
const searchResponse = ref(null);
const onLoadKakaoMap = (mapRef) => {
    map.value = mapRef;
};
function searchHandle(title) {
    const param = {
        title: title,
        latitude: lat.value,
        longitude: lng.value,
    };
    search(
        param,
        (response) => {
            console.log(response);
            searchResponse.value = {
                title: param.title,
                response: response,
            };
        },
        (error) => {
            console.error(error);
        }
    );
}
</script>

<template>
    <div class="container">
        <div>
            <SideBar @search="searchHandle" />
            <side-list v-if="searchResponse" :title="searchResponse.title" :response="searchResponse.response" />
        </div>
        <KakaoMap :lat="lat" :lng="lng" @onLoadKakaoMap="onLoadKakaoMap" :draggable="true" class="kakao-map">
            <KakaoMapMarker :lat="lat" :lng="lng"></KakaoMapMarker>
        </KakaoMap>
    </div>
</template>

<style scoped>
.container {
    padding: 80px 0 0;
    position: absolute;
    display: flex;
    flex-direction: column;
    left: 0;
    width: 100%;
    height: 100%;
    max-width: 2000px;
    max-height: 100%;
}
@media (max-width: 1000px) {
    .container {
        max-width: 100%;
    }
}

.kakao-map {
    width: 100% !important;
    height: 100% !important;
}
</style>
