<script setup>
import { useSidebarStore } from '@/stores/sidebar.js';

const sidebarStore = useSidebarStore();
const props = defineProps({
    item: {
        type: Object,
        required: true,
    },
    index: Number,
});
</script>

<template>
  <tr>
    {{
            index + 1
    }}
    <td colspan="3">
      <div class="row-content">
        <span><img :src="item.imageUrl" alt="Image" /></span>
        <td>
          <div class="text-area">
            <div class="item-info">
              <strong class="title"> {{ item.title }}</strong>
              <span class="typeName">{{ item.typeName }}</span>
            </div>
            <i class="bx bx-trash" @click.prevent="sidebarStore.removeTrip(item.contentId)"></i>
          </div>
        </td>
      </div>
    </td>
  </tr>
</template>

<style scoped>
.row-content {
    display: flex;
    align-items: center;
    border: 2px solid white;
    border-radius: 5px;
}
tr {
    border: 2px solid white !important;
}
td {
    width: 100%;
}
.item-info {
    max-height: 100px;
}
.title {
    max-height: 80px;
    background: transparent;
    text-overflow: ellipsis;
}

img {
    width: 120px;
    height: 100px;
    border-radius: 3px;
}
.text-area {
    display: flex;
    justify-content: space-between;
    width: 100%;
    height: 100px;
    padding: 0px 10px;
}
i {
    padding-top: 70px;
    margin-left: 5px;
    font-size: 30px;
    font-weight: 100;
    color: rgb(143, 143, 143);
    cursor: pointer;
}

.text-area .title {
    display: block;
    text-align: left;
    max-width: 220px;
    overflow: hidden;
    text-overflow: ellipsis;
    font-size: 14px;
    font-weight: 700;
}

.text-area .typeName {
    display: flex;
    margin-left: 8px;
    font-size: 13px;
    color: rgb(143, 143, 143);
    align-items: center;
}
.text-area p {
    text-align: left;
    color: rgb(66, 66, 66);
    font-size: 15px;
    white-space: nowrap;
    text-overflow: ellipsis;
}
</style>
