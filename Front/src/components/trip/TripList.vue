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
const searchResponse = ref({ input: '', response: { data: [] } });
const onLoadKakaoMap = (mapRef) => {
    map.value = mapRef;
};
function searchHandle(input) {
    const param = {
        input: input,
        latitude: lat.value,
        longitude: lng.value,
    };
    search(
        param,
        (response) => {
            console.log(response);
            searchResponse.value = {
                input: param.input,
                response: response,
            };
        },
        (error) => {
            console.error(error);
        }
    );
}
function itemClickHandle(data) {
    const param = {
        input: data.input,
        latitude: lat.value,
        longitude: lng.value,
        contentTypeId:data.contentTypeId
    };
    search(
        param,
        (response) => {
            console.log(response);
            searchResponse.value = {
                input: param.input,
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
      <SideBar @search="searchHandle" @itemClick="itemClickHandle" />
      <side-list :input="searchResponse.input" :response="searchResponse.response" />
    </div>
    <KakaoMap
      :lat="lat"
      :lng="lng"
      @onLoadKakaoMap="onLoadKakaoMap"
      :draggable="true"
      class="kakao-map"
    >
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
