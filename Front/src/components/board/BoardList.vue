<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { listArticle } from "@/api/board/board.js";

import VSelect from "./common/VSelect.vue";
import BoardListItem from "./item/BoardListItem.vue";
import PageNavigation from "./common/PageNavigation.vue";
import "@/assets/css/board.css"; // CSS 파일을 불러옵니다.

const router = useRouter();

const selectOption = ref([
  { text: "검색조건", value: "" },
  { text: "글번호", value: "article_no" },
  { text: "제목", value: "subject" },
  { text: "작성자아이디", value: "user_id" },
]);

const articles = ref([]);
const currentPage = ref(1);
const totalPage = ref(0);
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
const param = ref({
  pgno: currentPage.value,
  spp: VITE_ARTICLE_LIST_SIZE,
  key: "",
  word: "",
});

onMounted(() => {
  getArticleList();
});

const changeKey = (val) => {
  console.log("BoarList에서 선택한 조건 : " + val);
  param.value.key = val;
};

const getArticleList = () => {
  console.log("서버에서 글목록 얻어오자!!!", param.value);
  listArticle(
    param.value,
    ({ data }) => {
      articles.value = data.articles;
      currentPage.value = Number(data.pgno); // 숫자로 변환
      totalPage.value = Number(data.navigation.totalPageCount); // 숫자로 변환
    },
    (error) => {
      console.log(error);
    }
  );
};

const onPageChange = (val) => {
  console.log(val + "번 페이지로 이동 준비 끝!!!");
  currentPage.value = Number(val); // 숫자로 변환
  param.value.pgno = val;
  getArticleList();
};

const moveWrite = () => {
  router.push({ name: "board-write" });
};
</script>

<template>
  <div class="container">
    <div class="header">
      <h2 class="my-3 py-3 shadow-sm text-center">
        <h1 class="sky">여행 후기</h1>
      </h2>
    </div>
    <div class="content">
      <div class="row justify-content-center">
        <div class="col-lg-10">
          <div class="row align-self-center mb-2">
            <div class="col-md-2 text-start">
              <button type="button" class="btn btn-sm" @click="moveWrite">글쓰기</button>
            </div>
            <div class="col-md-5 offset-5">
              <form class="d-flex">
                <VSelect :selectOption="selectOption" @onKeySelect="changeKey" />
                <div class="input-group input-group-sm">
                  <input
                    type="text"
                    class="form-control"
                    v-model="param.word"
                    placeholder="검색어..."
                  />
                  <button class="btn btn-dark" type="button" @click="getArticleList">검색</button>
                </div>
              </form>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr class="text-center">
                <th scope="col">글번호</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">조회수</th>
                <th scope="col">작성일</th>
              </tr>
            </thead>
            <tbody>
              <BoardListItem
                v-for="article in articles"
                :key="article.articleNo"
                :article="article"
              ></BoardListItem>
            </tbody>
          </table>
        </div>
        <PageNavigation
          :current-page="Number(currentPage)"
          :total-page="Number(totalPage)"
          @pageChange="onPageChange"
        >
        </PageNavigation>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
