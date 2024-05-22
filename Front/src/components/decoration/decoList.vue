<script setup>
import { ref, onMounted } from "vue";
import { listArticle2 } from "@/api/board/board.js";

const articles = ref([]);
const totalArticlesToShow = 5; // 보여줄 게시글 수

const getArticleList = () => {
  listArticle2(
    ({ data }) => {
      articles.value = data.articles.slice(0, totalArticlesToShow);
    },
    (error) => {
      console.error(error);
    }
  );
};

onMounted(() => {
  getArticleList();
});


</script>

<template>
  <section class="notice">
    <div class="page-title">
      <div class="container">
        <h3>최신 공지사항</h3>
      </div>
    </div>
    <!-- board list area -->
    <div id="board-list">
      <div class="container">
        <table class="board-table">
          <thead>
            <tr>
              <th scope="col" class="th-num">번호</th>
              <th scope="col" class="th-title">제목</th>
              <th scope="col" class="th-date">등록일</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(article) in articles" :key="article.articleNo">
              <td>{{ article.articleNo }}</td>
              <th>
                <router-link :to="{ name: 'article-view', params: { articleno: article.articleNo } }"
                  class="article-title link-dark">
                  {{ article.subject }}
                </router-link>
                <p>{{ article.user_id }}</p>
              </th>
              <td>{{ article.registerTime }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </section>
</template>

<style scoped>
.notice {
  padding: 20px 0;
  /* 상단 여백 조절 */
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 15px;
}

.page-title {
  margin-bottom: 20px;
  /* 제목과 테이블 간격 조절 */
}

.page-title h3 {
  font-size: 20px;
  color: #333333;
  font-weight: 400;
  text-align: center;
}

#board-list .container {
  overflow-y: auto;
  /* 스크롤 가능하게 설정 */
}

.board-table {
  font-size: 13px;
  width: 100%;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

.board-table a {
  color: #333;
  display: inline-block;
  line-height: 1.4;
  word-break: break-all;
  vertical-align: middle;
}

.board-table a:hover {
  text-decoration: underline;
}

.board-table th {
  text-align: center;
}

.board-table .th-num {
  width: 20%;
  text-align: center;
}

.board-table .th-date {
  width: 20%;
}

.board-table th,
.board-table td {
  padding: 10px 0;
}

.board-table tbody td {
  border-top: 1px solid #e7e7e7;
  text-align: center;
}

.board-table tbody th {
  padding-left: 20px;
  padding-right: 10px;
  border-top: 1px solid #e7e7e7;
  text-align: left;
}

.board-table tbody th p {
  display: none;
}

/* General button styles */
.btn {
  display: inline-block;
  padding: 0 30px;
  font-size: 15px;
  font-weight: 400;
  background: transparent;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  touch-action: manipulation;
  cursor: pointer;
  user-select: none;
  border: 1px solid transparent;
  text-transform: uppercase;
  border-radius: 0;
  transition: all 0.3s;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover,
.btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

/* Reset styles */
* {
  list-style: none;
  text-decoration: none;
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}

.clearfix:after {
  content: '';
  display: block;
  clear: both;
}

.container {
  width: 100%;
  margin: 0 auto;
}

.blind {
  position: absolute;
  overflow: hidden;
  clip: rect(0 0 0 0);
  margin: -1px;
  width: 1px;
  height: 1px;
}
</style>
