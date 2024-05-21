<script setup></script>

<template>
  <div class="full-width">
    <carouselPicture />
  </div>
</template>

<script>
import carouselPicture from '@/components/decoration/carouselPicture.vue';

export default {
  name: 'HomeView',
  components: {
    carouselPicture
  }
};
</script>

<style scoped>
.full-width {
  width: 100%; /* 너비 100% 설정 */
  display: flex; /* Flexbox 레이아웃 사용 */
  justify-content: center; /* 중앙 정렬 */
  align-items: center; /* 수직 중앙 정렬 (선택 사항) */
}
</style>
