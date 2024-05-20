<script setup></script>

<template>
    <div>
        <CarouselComponent />
        </div>
</template>

  <script>
  import CarouselComponent from '@/components/decoration/CarouselComponent.vue';
  
  export default {
    name: 'App',
    components: {
      CarouselComponent
    }
  };
  </script>
<style>
.image_slider {
  width: 80%; /* 슬라이더의 전체 너비를 80%로 설정 */
  margin: 0 auto; /* 슬라이더를 가운데 정렬 */
}

.carousel__item {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.carousel__item img {
  width: 100%; /* 이미지의 너비를 슬라이더 너비에 맞춤 */
  max-width: 600px; /* 이미지의 최대 너비를 600px로 설정 */
  height: auto;
}

.carousel__item figcaption {
  text-align: center;
}
</style>