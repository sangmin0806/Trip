<template>
  <div class="home-container">
    <div class="decoIntro">
      <decoIntro />
    </div>
    <div class="top-section">
      <div class="left-component">
        <decoList />
      </div>
      <div class="right-component">
        <secondComponent />
      </div>
    </div>
    <div class="full-width">
      <carouselPicture />
    </div>
  </div>
</template>

<script setup>
import decoList from '@/components/decoration/decoList.vue';
import secondComponent from '@/components/decoration/decoChat.vue';
import carouselPicture from '@/components/decoration/carouselPicture.vue';
import decoIntro from '@/components/decoration/decoIntro.vue';
</script>

<style scoped>
.home-container {
  width: 100%;
}

.top-section {
  display: flex;
  justify-content: space-between;
  padding: 20px;
}

.left-component,
.right-component {
  width: 48%;
  height: 400px;
}

.full-width {
  width: 100%;
  /* 너비 100% 설정 */
  display: flex;
  /* Flexbox 레이아웃 사용 */
  justify-content: center;
  /* 중앙 정렬 */
  align-items: center;
  /* 수직 중앙 정렬 (선택 사항) */
  border-radius: 8px;
}
</style>
