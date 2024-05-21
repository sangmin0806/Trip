<script setup>
const props = defineProps({
    item: {
        type: Object,
        required: true,
    },
});
</script>

<template>
  <figure class="card">
    <div class="image"><img :src="item.url" alt="image" /></div>
    <figcaption>
      <h3>dosijfosij</h3>
      <p>dlfkjsoeijfoiwejfeowjfowejfowowofwoiejfowejfio</p>
    </figcaption>
    <a href="#">Read More</a>
  </figure>
</template>

<style scoped>
*{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Lato', sans-serif;
}
.card{
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #000;
    max-width: 310px;
    min-width: 25;
    margin: 10px;
    overflow: hidden;
    border-radius: 30px;
    box-shadow: 10px 10px 15px #7f8080;
}
.card img{
    position: relative;
    width: 400px;
    height: 300px;
    vertical-align: top;
    transition: 0.25s ease;
}
.card:hover img{
    transform: scale(1.1);
}
.card figcaption{
    position: absolute;
    padding: 15px 20px 40px;
    bottom: 0;
    z-index: 1;
}
.card figcaption::before{
    content: '';
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: linear-gradient(90deg,#b2c4c9,#fff);
    opacity: 0.8;
    z-index: -1;
}
.card h3{
    display: inline-block;
    margin-bottom: 5px
}
.card p{
    font-size: 0.8em;
    line-height: 1.6em;

}
.card a{
    position: absolute;
    bottom: 10px;
    display: inline-block;
    font-size: 0.75em;
    color: #fff;
    background: #413b3b;
    padding: 5px 10px;
    text-decoration: none;
    border-radius: 6px;
    z-index: 1;
    transition: 0.5s;
}
.card a:hover{
    background: #242020;
}
</style>
