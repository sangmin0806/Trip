<template>
    <header>
        <link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Cormorant+Garamond:wght@500&display=swap" rel="stylesheet">
    </header>
    <!-- Intro -->
    <div id="intro">
        <div id="title">
            <h1 ref="headline">
                <span class="titlespan" id="span1">여</span><span class="fade-out" id="span1">행의 설렘을 담아,</span><br />
                <span class="titlespan" id="span2">기</span><span class="fade-out" id="span2">다림 없는 특별한 여정,</span><br />
                <span class="titlespan" id="span3">로</span><span class="fade-out" id="span3">망을 현실로 만드는 플랫폼!</span>
            </h1>
        </div>
        <div>
            <p>
                "여행의 새로운 패러다임을 선도하는 혁신적 여행 추천 플랫폼, 당신만의 특별한 여정을 시작하세요!"
            </p>
        </div>
        <ul class="actions">
            <li>
                <button class="custom-btn btn-15" @click="scrollToMain">Read More</button>
            </li>
        </ul>
    </div>
    <div id="main">
        <!-- Main content here -->
    </div>
</template>

<script setup>
import { ref } from 'vue';

const headline = ref(null);

const scrollToMain = () => {
    const mainSection = document.getElementById('main');
    if (mainSection) {
        mainSection.scrollIntoView({ behavior: 'smooth' });
    }
};
</script>

<style scoped>
@font-face {
    font-family: 'WAGURITTF';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2403@1.0/WAGURITTF.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'PartialSansKR-Regular';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2307-1@1.1/PartialSansKR-Regular.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}

.actions {
    padding-bottom: 30vw;
}

#title {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: auto;
    margin: 0 auto;
}

#intro {
    line-height: 1;
    color: white;
    text-align: center;
    /* 텍스트를 가운데 정렬 */
}

h1 {
    text-align: left;
    /* 텍스트를 가운데 정렬 */
    margin-top: 5vh;
    font-size: 5vw;
    font-family: 'WAGURITTF';
    text-shadow: 2px 2px gray;
    margin-bottom: 20px;
}

#intro>div>p {
    font-size: 2rem;
    font-family: 'WAGURITTF';
    text-shadow: 1px 1px gray;
}

.titlespan {
    font-family: 'PartialSansKR-Regular';
    font-size: 6vw;
}

/* 페이드 아웃 애니메이션 정의 */
@keyframes fadeOut {
    from {
        opacity: 1;
    }

    to {
        opacity: 0;
    }
}

@keyframes centerAnimation {
    from {
        transform: translateX(0%);
        color: white;
        font-size: 5vw;
    }

    to {
        transform: translateX(29vw);
        color: darkblue;
        font-size: 6vw;
    }
}

.fade-out {
    display: inline-block;
    /* span 요소가 제대로 동작하도록 inline-block 설정 */
    /* animation: fadeOut 1s forwards; */
    /* 1초 동안 페이드 아웃 */
}

.fade-out#span1 {
    animation: fadeOut 1s forwards;
    animation-delay: 1s;
}

.fade-out#span2 {
    animation: fadeOut 1s forwards;
    animation-delay: 2s;
}

.fade-out#span3 {
    animation: fadeOut 1s forwards;
    animation-delay: 3s;
}

.titlespan#span1 {
    animation: centerAnimation 1s forwards;
    animation-delay: 3.9s;
}

.titlespan#span2 {
    animation: centerAnimation 1s forwards;
    animation-delay: 4s;
}

.titlespan#span3 {
    animation: centerAnimation 1s forwards;
    animation-delay: 4.1s;
}

span.titlespan {
    display: inline-block;
    /* span 태그가 inline 요소에서 block 요소로 변환되어 애니메이션 적용 가능 */
    animation: centerAnimation 1s forwards;
    animation-delay: 4s;
}

.btn-15 {
    background: #000;
    color: #fff;
    z-index: 1;
}

.btn-15:after {
    position: absolute;
    content: "";
    width: 0;
    height: 100%;
    top: 0;
    right: 0;
    z-index: -1;
    background: #e0e5ec;
    transition: all 0.3s ease;
}

.btn-15:hover {
    color: #000;
}

.btn-15:hover:after {
    left: 0;
    width: 100%;
}

.btn-15:active {
    top: 2px;
}

.custom-btn {
    width: 130px;
    height: 40px;
    padding: 10px 25px;
    border: 2px solid #000;
    font-family: 'Lato', sans-serif;
    font-weight: 500;
    background: transparent;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
}
</style>