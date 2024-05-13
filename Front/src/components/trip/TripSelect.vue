<script setup>
import { ref } from "vue";
import { defineProps, defineEmits } from "vue";

defineProps({
  selectOption: Array,
  sortOption: Array,
});

const emit = defineEmits(["onKeySelect"]);

const key = ref("");

const onSelect = () => {
  emit("onKeySelect", key.value);
};
</script>

<template>
  <select
    v-if="selectOption"
    v-model="key"
    class="form-select form-select-sm ms-3 me-0 w-20"
    @change="onSelect"
    style="height: 30px;"
  >
    <option
      v-for="option in selectOption"
      :key="option.value"
      :value="option.value"
      :disabled="option.value === ''"
    >
      {{ option.text }}
    </option>
  </select>

  <select
    v-if="sortOption"
    v-model="key"
    class="form-select form-select-sm ms-3 me-0 w-20"
    @change="onSelect"
    style="height: 30px;"
  >
    <option
      v-for="option in sortOption"
      :key="option.value"
      :value="option.value"
      :disabled="option.value === ''"
    >
      {{ option.text }}
    </option>
  </select>
</template>

<style scoped></style>
