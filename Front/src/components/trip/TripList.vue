<script setup>
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import { useRouter } from "vue-router";
import { ref,onMounted } from 'vue';
import TripSelect from "./TripSelect.vue";
const lat = ref(37.566826);
const lng = ref(126.9786567);
const map = ref();
const router = useRouter();
const tripList = ref([]);
const onLoadKakaoMap = (mapRef) => {
  map.value = mapRef;
};
const option = ref({
  key: "",
  word: "",
});
const selectOption = ref([
  { text: "검색기준", value: "" },
  { text: "여행지", value: "id" },
  { text: "제목", value: "title" },
]);
const sortOption = ref([
{ text: "관광지유형", value: "" },
  { text: "오름차순", value: "asc" },
  { text: "내림차순", value: "desc" }
]);

// onMounted(() => {
//   getArticleList();
// });
// const getArticleList = () => {
//   console.log("서버에서 글목록 얻어오자!!!");
//     listArticle(({ data }) => {

//       articles.value = data;
//     },
//     (error) => {
//       console.log(error);
//     }
//   );
// };


const panTo = () => {
  if (map.value) {
    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.value.panTo(new kakao.maps.LatLng(33.45058, 126.574942));
  }
};

const changeKey = (val) => {
  console.log("BoarList에서 선택한 조건 : " + val);
  option.value.key = val;
};
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <div class="row col-md-12 align-self-center mb-2 py-2 justify-content-between">
          <div class="col-md-2 text-start">
            <button type="button" class="btn btn-outline-primary btn-sm" @click="moveCreate">
              글쓰기
            </button>
          </div>
          <div class="col-md-8 ms-15">
            <form class="d-flex">
              <div class="me-4 col-md-2">
                <TripSelect :selectOption="selectOption" @onKeySelect="changeKey" />
              </div>
              <div class="input-group input-group-sm " style="height: 30px;">
                <input
                  type="text"
                  class="form-control form-control-sm"
                  v-model="option.word"
                  placeholder="검색어..."
                />
                <TripSelect :selectOption="selectOption" @onKeySelect="changeKey" />
                <TripSelect :sortOption="sortOption" @onKeySelect="changeKey" />
                <button
                  class="btn btn-dark"
                  style="height: 30px;"
                  type="button"
                  @click="getArticleList"
                >
                  검색
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
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
</template>

<style scoped>
.kakao-map{
  width: 100% !important;
  height: 100% !important;
  margin-bottom: 50px;
}
</style>
