<script setup>
import { computed } from 'vue';

const props = defineProps({ currentPage: Number, totalPage: Number });
const emit = defineEmits(['pageChange']);

const navigationSize = 10;  // 항상 최대 10개의 페이지 번호를 표시

const startPage = computed(() => {
  return Math.floor((props.currentPage - 1) / navigationSize) * navigationSize + 1;
});

const endPage = computed(() => {
  const lastPage = startPage.value + navigationSize - 1;
  return props.totalPage < lastPage ? props.totalPage : lastPage;
});

function range(start, end) {
  const list = [];
  for (let i = start; i <= end; i++) list.push(i);
  return list;
}

function onPageChange(pg) {
  if (pg > 0 && pg <= props.totalPage) {
    emit('pageChange', pg);
  }
}
</script>

<template>
  <div class="row">
    <ul class="pagination justify-content-center">
      <li class="page-item" :class="{ disabled: props.currentPage === 1 }">
        <a class="page-link" @click="onPageChange(1)" aria-label="First">
          처음
        </a>
      </li>
      <li class="page-item" :class="{ disabled: props.currentPage === 1 }">
        <a class="page-link" @click="onPageChange(props.currentPage - 1)" aria-label="Previous">
          이전
        </a>
      </li>
      <template v-for="pg in range(startPage.value, endPage.value)" :key="pg">
        <li :class="props.currentPage === pg ? 'page-item active' : 'page-item'">
          <a class="page-link" @click="onPageChange(pg)">{{ pg }}</a>
        </li>
      </template>
      <li class="page-item" :class="{ disabled: props.currentPage === props.totalPage }">
        <a class="page-link" @click="onPageChange(props.currentPage + 1)" aria-label="Next">
          다음
        </a>
      </li>
      <li class="page-item" :class="{ disabled: props.currentPage === props.totalPage }">
        <a class="page-link" @click="onPageChange(props.totalPage)" aria-label="Last">
          마지막
        </a>
      </li>
    </ul>
  </div>
</template>

<style scoped>
a {
  cursor: pointer;
}
</style>
