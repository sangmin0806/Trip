<script setup>
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import { useSidebarStore } from '@/stores/sidebar.js';
import { onMounted, ref } from 'vue';
import { search, getLocation } from '@/api/trip/tripSearch.js';
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
function getNaverMapUrl(title) {
  return `https://map.naver.com/v5/search/${encodeURIComponent(title)}`;
}
function closeInfoWindow(index) {
    const markerItem = markerList.value[index];
    markerItem.infoWindow.visible = false;
}

const placesSearchCB = (data) => {

    markerList.value = [];
    const bounds = new kakao.maps.LatLngBounds();
    for (let marker of data) {
        const markerItem = {
            lat: marker.latitude,
            lng: marker.longitude,
            infoWindow: {
                content: `
                <div style="padding: 10px; background-color: white; border: 1px solid #ccc; border-radius: 5px">
                    <div style="font-weight: bold; margin-bottom: 5px">
                        ${marker.title}
                        <span class="close-button" style="float: right; cursor: pointer" data-index="${markerList.value.length}" title="닫기">X</span>
                    </div>
                    <div style="display: flex">
                        <div style="margin-right: 10px">
                            <img src="${marker.imageUrl}" width="73" height="70" />
                        </div>
                        <div style="flex-grow: 1">
                            <div style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap">주소 : ${marker.address}</div>
                            <div style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap">유형 : ${marker.typeName}</div>
                            <div style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap">tel : ${marker.tel}</div>
                            <div><a href="${getNaverMapUrl(marker.title)}" target="_blank" style="color: blue">네이버 지도 검색</a></div>
                        </div>
                    </div>
                </div>`,
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

onMounted(() => {
    document.addEventListener('click', (event) => {
        if (event.target.classList.contains('close-button')) {
            const index = event.target.getAttribute('data-index');
            closeInfoWindow(index);
        }
    });
});
</script>

<template>
  <div class="container">
    <div>
      <side-bar @itemClick="itemClickHandle" />
      <side-list @search="searchHandle" :response="searchResponse.response" />
      <my-trip-list />
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
        :key="marker.key === undefined ? index :
      marker.key"
        :lat="marker.lat"
        :lng="marker.lng"
        :infoWindow="marker.infoWindow"
        :clickable="true"
        @onClickKakaoMapMarker="onClickMapMarker(marker)"
      />
    </KakaoMap>
  </div>
</template>

<style scoped>
.container {
    padding:0;
    position: absolute;
    display: flex;
    flex-direction: column;
    left: 0;
    width: 100%;
    height: 91%;
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
.kakao-overlay{
    position: absolute;
    z-index: 1000;
}
</style>
