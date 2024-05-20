<template>
  <div class="image_slider" slideWidth="100%">
    <Carousel :items-to-show="2" :items-to-scroll = "1" :wrap-around="true" :autoplay="3000">
      <Slide v-for="(slide, index) in items[0].menus" :key="index">
        <div class="carousel__item">
          <figure>
            <img :src="slide.image" :alt="slide.alt" />
            <figcaption>
              <em>{{ slide.caption }}</em>
              <span>{{ slide.description }}</span>
            </figcaption>
          </figure>
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
import 'vue3-carousel/dist/carousel.css';
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel';

// 이미지 import
import image1 from '@/assets/img/slideimg-1.jpg';
import image2 from '@/assets/img/slideimg-2.jpg';
import image3 from '@/assets/img/slideimg-3.jpg';

export default {
  components: {
    Carousel,
    Slide,
    Pagination,
    Navigation
  },
  data() {
    return {
      items: [
        {
          title: 'Carousel Slider',
          description: 'Carousel을 이용한 이미지 슬라이드 효과',
          menus: [
            {
              image: image1,
              alt: 'image1',
              caption: 'Responsive Site',
              description: '슬라이드 플러그인을 이용한 반응형 이미지 슬라이드'
            },
            {
              image: image2,
              alt: 'image2',
              caption: 'Responsive Site',
              description: '슬라이드 플러그인을 이용한 반응형 이미지 슬라이드'
            },
            {
              image: image3,
              alt: 'image3',
              caption: 'Responsive Site',
              description: '슬라이드 플러그인을 이용한 반응형 이미지 슬라이드'
            }
          ]
        }
      ]
    };
  }
};
</script>

<style>
.image_slider {
  width: 100%;
}

.carousel__item {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.carousel__item img {
  width: 100%;
  height: 200px; /* 고정된 높이 설정 */
  object-fit: cover; /* 이미지를 잘라서 공간을 채움 */
}

.carousel__item figcaption {
  text-align: center;
}
</style>
