<script setup>
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import { useSidebarStore } from '@/stores/sidebar.js';
import { ref } from 'vue';
import { search, getLocation } from '@/assets/api/trip/tripSearch.js';
import SideBar from './sidebar/leftsidebar/SideBar.vue';
import SideList from './sidebar/leftsidebar/SideList.vue';
import MyTripList from './sidebar/rightsidebar/MyTripList.vue';
const sidebarStore = useSidebarStore();
const lat = ref(37.566826);
const lng = ref(126.9786567);
const map = ref();
const markerList = ref([]);
const searchResponse = ref({ response: { data: [] } });

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
                visible: false,
            },
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

async function searchHandle() {
    const input = sidebarStore.input;
    console.log(input);
    const locateParam = {
        input: input,
    };
    try {
        const location = await getLocation(locateParam);
        if (!location || location === '') {
            return;
        }

        lat.value = location.latitude;
        lng.value = location.longitude;
    } catch (error) {
        return;
    }
    const param = {
        input: input,
        latitude: lat.value,
        longitude: lng.value,
    };
    search(
        param,
        (response) => {
            if (response.data.length === 0) {
                searchResponse.value = {
                    response: {
                        data: [],
                    },
                };
                return;
            }
            sidebarStore.setSidebarActive(true);
            console.log(response);

            searchResponse.value = {
                response: response,
            };
            placesSearchCB(response.data);
        },
        (error) => {
            console.error(error);
            return;
        }
    );
}
async function itemClickHandle() {
    const input = sidebarStore.input;
    const contentTypeId = sidebarStore.contentTypeId;
    const locateParam = {
        input: input,
    };
    try {
        const location = await getLocation(locateParam);
        if (!location || location === '') {
            return;
        }
        lat.value = location.latitude;
        lng.value = location.longitude;
    } catch (error) {
        return;
    }
    const param = {
        input: input,
        latitude: lat.value,
        longitude: lng.value,
        contentTypeId: contentTypeId,
    };
    search(
        param,
        (response) => {
            if (response.data.length === 0) {
                searchResponse.value = {
                    response: {
                        data: [],
                    },
                };
                return;
            }
            sidebarStore.setSidebarActive(true);
            console.log(response);
            searchResponse.value = {
                response: response,
            };
            placesSearchCB(response.data);
        },
        (error) => {
            return;
        }
    );
}
</script>

<template>
    <div class="container">
        <div>
            <side-bar @itemClick="itemClickHandle" />
            <side-list @search="searchHandle" :response="searchResponse.response" />
            <my-trip-list />
        </div>
        <KakaoMap :lat="lat" :lng="lng" @onLoadKakaoMap="onLoadKakaoMap" :draggable="true" class="kakao-map">
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
