<script setup>
import { ref, onMounted } from 'vue';
import OpenAI from 'openai';
import { getPlanLists } from '@/api/trip/tripList.js';
import PlanList from './PlanList.vue';

const VITE_OPENAI_API_KEY = import.meta.env.VITE_OPENAI_API_KEY;

const address = ref('');
const concept = ref('');
const result = ref('');
const isLoading = ref(false);
const planLists = ref({ data: [] });

async function PlanListsHandle() {
    getPlanLists(
        (response) => {
            planLists.value = response.data;
        },
        (error) => {
            console.log(error);
        }
    );
}
onMounted(() => {
    PlanListsHandle();
});
const getGPTResponse = async () => {
    if (!address.value) {
        result.value = '주소를 입력해주세요.';
        return;
    }
    try {
        isLoading.value = true;
        const openai = new OpenAI({
            apiKey: VITE_OPENAI_API_KEY,
            dangerouslyAllowBrowser: true,
        });

        const prompt = `${address.value}로 여행갈껀데 ${JSON.stringify(
            planLists.value
        )}의 여행리스트중에서  ${JSON.stringify(concept.value)}컨셉에 어울리는 여행리스트 골라서 추천해줘. 여행 리스트 제목을 포함해서. 여행 리스트이미지는 알려주지말고, 그리고 해당 리스트 소개해줘`;

        const response = await openai.chat.completions.create({
            messages: [
                {
                    role: 'user',
                    content: prompt,
                },
            ],
            model: 'gpt-3.5-turbo',
        });
        result.value = response.choices[0].message.content;
        address.value=''
        concept.value=''
    } catch (error) {
        result.value = 'chatGPT: 🚨 에러가 발생했습니다.';
    } finally {
        isLoading.value = false;
    }
};
const scrollBody = (direction) => {
    const scrollAmount = 300;
    const container = document.querySelector('.body');
    if (direction === 'left') {
        container.scrollBy({ left: -scrollAmount, behavior: 'smooth' });
    } else if (direction === 'right') {
        container.scrollBy({ left: scrollAmount, behavior: 'smooth' });
    }
};
</script>

<template>
  <div class="container">
    <div class="scroll-container">
      <div class="scroll-left">
        <i class="bx bx-chevron-left" @click="scrollBody('left')"></i>
      </div>
      <div class="body">
        <div class="card" v-for="(item, index) in planLists" :key="index">
          <plan-list :item="item" />
        </div>
      </div>
      <div class="scroll-right">
        <i class="bx bx-chevron-right" @click="scrollBody('right')"></i>
      </div>
    </div>
    <div class="ai">
      <input type="text" v-model="address" placeholder="여행지를 입력하세요..." />
      <input type="text" v-model="concept" placeholder="컨셉을 입력하세요..." />
      <br />
      <div class="icons-container">
        <div class="icons">
          <i class="bx bx-comment-dots">{{ isLoading ? 'Loading...' : '추천 코스' }}</i
          ><i class="bx bxs-hand-right" @click="getGPTResponse" :disabled="isLoading"></i>
        </div>
      </div>

      <br />
      <div class="result" v-if="result">
        <h3>AI 추천:</h3>
        <textarea>{{ result }}</textarea>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
    width: 100%;
    height: 800px;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.scroll-container{
    display: flex;
    width: 100%;
}
.body {
    display: flex;
    justify-content: space-between;
    width: 100%;
    align-items: center;
    min-height: 50vh;
    min-width: 800px;
    overflow-x: hidden;
}
.scroll-right{
    display: flex;
    align-items: center;
    margin-left: 30px;
}
.scroll-left{
    display: flex;
    align-items: center;
    margin-right: 30px;
}
.icons-container{

    display: flex;
    justify-content: center;
    text-align: center;
    align-items: center;
}
.icons{
    margin-top: 30px;
    width: 300px;
    background: #e3e3e3;
}
.ai {
    margin-top: 50px;
    width: 100%;

}
.bx.bx-comment-dots{
    padding: 10px;
}
.bx.bx-comment-dots::before{
    margin-right: 10px;
}
.bx.bxs-hand-right{
    padding: 0px;
    cursor: pointer;
}
.card {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #000;
    width: 100%;
    border: none;
    margin: 10px;
    padding-top: 0px;
    max-width: 350px;
    min-width: 300px;
    background: transparent;
}
i{
    font-size: 50px;
}
.ai i {
    padding: 10px 20px;
    font-size: 24px;

}
.ai input{
    border: none;
    border-radius: 5px;
    margin-left: 30px;
    height: 40px;
    font-size: 26px;
    outline: none;
    padding: 5px;
}

i:disabled {
    background-color: #ddd;
    cursor: not-allowed;
}

h3 {
    margin-top: 20px;
}
li {
    z-index: 9999;
}
.result{
    width: 100%;
    max-height: 250px;
    overflow-y: auto;
    background: rgba(255, 255, 255, 0.8);
    border-radius: 10px;
    box-sizing: border-box;
}
.result textarea{
    width: 100%;
    height: 120px;
    background: transparent;
    border: none;

}
</style>
