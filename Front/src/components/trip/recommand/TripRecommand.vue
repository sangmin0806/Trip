<script setup>
import { ref,onMounted } from 'vue'
import OpenAI from 'openai'
import { getPlanLists } from '@/api/trip/tripList.js'
import PlanItem from "./PlanItem.vue"

const  VITE_OPENAI_API_KEY  = import.meta.env.VITE_OPENAI_API_KEY;

const address = ref('')
const concept = ref('')
const result = ref('')
const isLoading = ref(false)
const planLists = ref({ data: [] });

async function PlanListsHandle() {
    getPlanLists(
        (response) => {
            planLists.value = response.data;
            console.log(planLists.value);
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
    result.value = '주소를 입력해주세요.'
    return
  }
  try {
    isLoading.value = true
    const openai = new OpenAI({
      apiKey: VITE_OPENAI_API_KEY,
      dangerouslyAllowBrowser: true,
    })

    const prompt = `${
      address.value
    }로 여행갈껀데 ${JSON.stringify(
        planLists.value
    )}의 여행리스트중에서  ${JSON.stringify(
        concept.value
    )}컨셉의 여행지에 대해 소개해줘`

    const response = await openai.chat.completions.create({
      messages: [
        {
          role: 'user',
          content: prompt,
        },
      ],
      model: 'gpt-3.5-turbo',
    })
    result.value = response.choices[0].message.content
  } catch (error) {
    console.log('chatGPT: 🚨 에러가 발생했습니다.', error)
    result.value = 'chatGPT: 🚨 에러가 발생했습니다.'
  } finally {
    isLoading.value = false
  }
}
</script>

<template>
  <div class="container">
    <div class="body">
      <div class="card" v-for="(item, index) in planLists" :key="index">
        <plan-item :item="item" />
      </div>
    </div>
    <div>
      <input type="text" v-model="address" placeholder="여행지를 입력하세요..." />
      <input type="text" v-model="concept" placeholder="컨셉을 입력하세요..." />
      <br />
      <button @click="getGPTResponse" :disabled="isLoading">
        {{ isLoading ? 'Loading...' : 'Get GPT Response' }}
      </button>
      <br />
      <div v-if="result">
        <h3>GPT Response:</h3>
        <p>{{ result }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container{
    height: 800px;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.body{
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 50vh;
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
button {
  padding: 10px 20px;
  font-size: 16px;
}

button:disabled {
  background-color: #ddd;
  cursor: not-allowed;
}

h3 {
  margin-top: 20px;
}
li{
    z-index: 9999;
}
</style>
