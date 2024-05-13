<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div class="col-lg-10">
    <h2 class="my-3 py-3 shadow-sm bg-light text-center">
      <mark class="sky">글목록</mark>
    </h2>
  </div>
  <div class="container">
    <RouterView />
  </div>
</template>

<style scoped>
.container{
  position: absolute;
  display: flex;
  flex-direction: column;
  left : 0;
  width: 100%;
  height: 80%;
  margin-top: 100px;
  max-width: 2000px
}
@media (max-width: 1000px) {
  .container {
    max-width: 100%;
    max-height: 60%;
  }
}
</style>
