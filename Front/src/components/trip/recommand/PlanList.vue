<script setup>
import { ref } from 'vue';
import { getPlan } from '@/api/trip/tripList';

const plan = ref({ data: [] });
const planActive = ref(false);
const props = defineProps({
    item: {
        type: Object,
        required: true,
    },
});
async function toggle() {
    planActive.value = !planActive.value;
}
</script>

<template>
  <figure class="card" :class="['card', { expanded: planActive }]">
    <div class="image"><img :src="item.url" alt="image" /></div>
    <figcaption>
      <h3>{{ item.title }}</h3>
      <div v-if="planActive">
        <p>{{ item.description }}</p>
      </div>
    </figcaption>
  </figure>

  <a @click.prevent="toggle">More</a>
</template>

<style scoped>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'YeongdeokSea', 'Permanent Marker', sans-serif;
}

.card {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #000;
    max-width: 350px;
    min-width: 300px;
    margin: 10px;
    background: transparent;
    overflow: hidden;
    border-radius: 30px;
    box-shadow: 10px 10px 15px #7f8080;
}
.card.expanded {
    width: 350px;
}
.card img {
    position: relative;
    width: 300px;
    height: 250px;
    vertical-align: top;
    transition: 0.25s ease;
}
.card.expanded img {
    transition: 0.25s ease;
}
.card:hover img {
    transform: scale(1.1);
}
.card figcaption {
    position: absolute;
    padding: 15px 20px 40px;
    bottom: 0;
    z-index: 1;
    width: 100%;
}
.card figcaption::before {
    content: '';
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: linear-gradient(90deg, #7aa9b6, #fff);
    opacity: 0.8;
    z-index: -1;
}
.card h3 {
    display: inline-block;

    margin-bottom: 5px;
}
.card p {
    font-size: 1.2em;
    line-height: 1.6em;
}
.card a {
    position: absolute;
    bottom: 10px;
    display: inline-block;
    font-size: 0.75em;
    color: #fff;
    background: rgb(143, 143, 143);
    padding: 5px 10px;
    text-decoration: none;
    border-radius: 6px;
    z-index: 1;
    transition: 0.5s;
    cursor: pointer;
}
.card a:hover {
    background: #7f8080;
}
</style>
