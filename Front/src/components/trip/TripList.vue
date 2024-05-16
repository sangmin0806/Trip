<script setup>
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import { useRouter } from 'vue-router';

import {useSidebarStore,useSidelistStore  } from '@/stores/sidebar.js';
import { ref } from 'vue';
import { search } from '@/assets/api/trip/tripSearch.js';

import SideBar from './sidebar/SideBar.vue';
import SideList from './sidebar/SideList.vue';


const sidebarStore = useSidebarStore();
const sidelistStore = useSidelistStore();

const lat = ref(37.566826);
const lng = ref(126.9786567);
const map = ref();
const markerList = ref([]);
const router = useRouter();
const tripList = ref([]);
const searchResponse = ref({ input: '', response: { data: [] } });


const onLoadKakaoMap = (mapRef) => {
  map.value = mapRef;
};
const placesSearchCB = (data) => {
    markerList.value = [];
    const bounds = new kakao.maps.LatLngBounds();

    for (let marker of data) {
      const markerItem = {
        lat: marker.latitude,
        lng: marker.longitude,
        infoWindow: {
        content: marker.title,
          visible: false
        }
      };
      markerList.value.push(markerItem);
      bounds.extend(new kakao.maps.LatLng(Number(marker.latitude), Number(marker.longitude)));
    }
    map.value?.setBounds(bounds);

};

const onClickMapMarker = (markerItem) => {
  if (markerItem.infoWindow?.visible !== null && markerItem.infoWindow?.visible !== undefined) {
    markerItem.infoWindow.visible = !markerItem.infoWindow.visible;
  } else {
    markerItem.infoWindow.visible = true;
  }
};

function searchHandle(input) {
    sidebarStore.setSidebarActive(true);
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
            placesSearchCB(response.data)
        },
        (error) => {
            console.error(error);
        }
    );
}
function itemClickHandle(data) {
    sidebarStore.setSidebarActive(true);
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
            placesSearchCB(response.data)
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
      <side-list
        v-if="sidelistStore.sidelistShow"
        class="sidelist"
        :class="{ visible: sidelistStore.sidelistShow }"
        :input="searchResponse.input"
        :response="searchResponse.response"
      />
    </div>
    <KakaoMap
      :lat="lat"
      :lng="lng"
      @onLoadKakaoMap="onLoadKakaoMap"
      :draggable="true"
      class="kakao-map"
    >
      <KakaoMapMarker
        v-for="(marker, index) in markerList"
        :key="marker.key === undefined ? index : marker.key"
        :lat="marker.lat"
        :lng="marker.lng"
        :infoWindow="marker.infoWindow"
        :clickable="true"
        @onClickKakaoMapMarker="onClickMapMarker(marker)"
      ></KakaoMapMarker>
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
