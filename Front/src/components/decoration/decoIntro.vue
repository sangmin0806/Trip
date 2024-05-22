<template>
    <header></header>
    <!-- Intro -->
    <div id="intro">
        <div id="title">
            <h1 ref="headline">
                <span class="titlespan" id="span1">나</span><span class="fade-out" id="span1">의 특별한 여정,</span><br />
                <span class="titlespan" id="span2">침</span><span class="fade-out" id="span2">착한 시간 속에서,</span><br />
                <span class="titlespan" id="span3">반</span><span class="fade-out" id="span3">짝이는 여정을 떠나요!</span>
            </h1>
        </div>
        <div id="text-main">
            <p class="smalltext" ref="smalltext"></p>
        </div>
        <div class="actions">
            <button class="custom-btn btn-15" @click="scrollToMain">Scroll</button>
        </div>
    </div>
    <div id="main">
        <!-- Main content here -->
    </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';

const headline = ref(null);
const smalltext = ref(null);
const content = "여행의 새로운 패러다임을 선도하는 혁신적 여행 추천 플랫폼, 당신만의 특별한 여정을 시작하세요!";
let i = 0;

const scrollToMain = () => {
    const mainSection = document.getElementById('main');
    if (mainSection) {
        mainSection.scrollIntoView({ behavior: 'smooth' });
    }
};

function typing() {
    if (i < content.length) {
        let txt = content.charAt(i);
        smalltext.value.innerHTML += txt;
        i++;
    }
}

onMounted(() => {
    setInterval(typing, 50);

    // 중앙 좌표 계산
    const rect = headline.value.getBoundingClientRect();
    const centerX = rect.left + rect.width / 2;

    // CSS 변수 설정
    document.documentElement.style.setProperty('--center-x', `${centerX}px`);
});
</script>


<style scoped>
@import url('@/assets/css/decoration/DecoMain.css');
</style>
