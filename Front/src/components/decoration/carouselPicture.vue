<template>
  <div class="carousel-container">
    <Carousel :wrap-around="true" :transition="2000" :autoplay="50">
      <Slide v-for="(slide, index) in slides" :key="index">
        <div class="carousel__item">
          <img :src="slide" alt="Slide Image" />
        </div>
      </Slide>

      <template #addons>
        <Navigation />
        <Pagination />
      </template>
    </Carousel>
  </div>
</template>

<script>
import { defineComponent } from 'vue';
import { Carousel, Navigation, Pagination, Slide } from 'vue3-carousel';

import 'vue3-carousel/dist/carousel.css';

// 이미지 파일을 import
import slideImg1 from '@/assets/img/slideimg-1.jpg';
import slideImg2 from '@/assets/img/slideimg-2.jpg';
import slideImg3 from '@/assets/img/slideimg-3.jpg';

export default defineComponent({
  name: 'carouselPicture',
  components: {
    Carousel,
    Slide,
    Pagination,
    Navigation,
  },
  data() {
    return {
      slides: [slideImg1, slideImg2, slideImg3],
    };
  },
});
</script>

<style scoped>
.carousel-container {
  width: 100%; /* 부모 컨테이너가 전체 너비를 차지하도록 설정 */
  max-width: 1200px; /* 최대 너비 설정 (필요에 따라 조정) */
  margin: 0 auto; /* 중앙 정렬 */
  padding: 20px; /* 내부 여백 설정 */
  box-sizing: border-box; /* 박스 모델 설정 */
}

.carousel__item {
  height: 200px; /* 슬라이드 아이템의 높이를 낮게 설정 */
  width: 100%; /* 슬라이드 아이템의 너비 설정 */
  background-color: var(--vc-clr-primary);
  color: var(--vc-clr-white);
  font-size: 20px;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden; /* 슬라이드 아이템의 내용을 자르기 */
  box-sizing: border-box; /* 박스 모델 설정 */
}

.carousel__item img {
  width: 100%; /* 이미지가 슬라이드 아이템의 너비를 채우도록 설정 */
  height: 100%; /* 이미지가 슬라이드 아이템의 높이를 채우도록 설정 */
  object-fit: cover; /* 이미지를 컨테이너에 맞춰 자르기 */
  border-radius: 8px; /* 이미지에 둥근 모서리 추가 */
}

.carousel__slide {
  padding: 10px;
  display: flex; /* 슬라이드 아이템을 중앙에 배치하기 위해 flex 사용 */
  justify-content: center;
  align-items: center;
  width: 100%; /* 슬라이드가 전체 너비를 차지하도록 설정 */
  box-sizing: border-box; /* 박스 모델 설정 */
  
}

.carousel__prev,
.carousel__next {
  box-sizing: content-box;
  border: 5px solid white;
}
</style>
