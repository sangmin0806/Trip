<script setup>
import { defineProps, computed, ref } from 'vue';
import { useSidebarStore } from '@/stores/sidebar.js';
import { useAuthStore } from '@/stores/auth.js';
const authStore = useAuthStore();
const sidebarStore = useSidebarStore();

const props = defineProps({
    item: {
        type: Object,
        required: true,
    },
});
const isButtonDisabled = computed(() => {
    return sidebarStore.tripList.some((trip) => trip.contentId === props.item.contentId);
});

const addToTriplist = () => {
    sidebarStore.addTrip(props.item);
    sidebarStore.tripListActive = true;
};
</script>

<template>
  <div>
    <div><img :src="item.imageUrl" alt="Image" /></div>
    <div class="text-area">
      <div class="title-area">
        <div class="item-info">
          <strong class="title">{{ item.title }}</strong>
          <span class="typeName">{{ item.typeName }}</span>
        </div>
        <i
          class="bx bx-star"
          v-if="authStore.isLoggedIn"
          @click.prevent="addToTriplist()"
          :style="{ 'pointer-events': isButtonDisabled ? 'none' : 'auto', 'opacity': isButtonDisabled ? '0.5' : '1', 'cursor': isButtonDisabled ? 'default' : 'pointer'}"
        ></i>
      </div>
      <p>{{ item.address }}</p>
    </div>
  </div>
</template>

<style scoped>
i{
    font-size: 30px;
    font-weight: 100;
    color:lightcoral;
    pointer-events: au;
}
img {
    width: 93%;
    height: 200px;
    border-radius: 3px;
}
.text-area{
    width:100%;
    height: 100px;
    padding: 0px 10px;
    margin: 5px 0px;
}

.title-area{
    display: flex;
    justify-content: space-between;
    width:100%;
    padding: 10px 0px;
}

.title-area .title{
    display: block;
    text-align: left;
    max-width: 220px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    background: transparent;
    font-size: 20px;
    font-weight: 700;
    color: rgb(0, 104, 195);
}
.item-info{
    display: flex;
}
.title-area .typeName{
    display: flex;
    margin-left: 8px;
    font-size: 13px;
    color: rgb(143, 143, 143);
    align-items: center;
}
.text-area p{
    text-align: left;
    color: rgb(66, 66, 66);
    font-size: 15px;
    white-space: nowrap;
    text-overflow: ellipsis;
}
</style>
