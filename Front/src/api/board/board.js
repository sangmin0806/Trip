import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listArticle(param, success, fail) {
  local.get(`/article/list`, { params: param }).then(success).catch(fail);
}

function listArticle2(success, fail) {
  local.get(`/article/list/default`).then(success).catch(fail);
}

function detailArticle(articleno, success, fail) {
  local.get(`/article/view?articleno=${articleno}`).then(success).catch(fail);
}


function registArticle(article, success, fail) {
  console.log("boardjs article", article);
  local.post(`/article/write`, JSON.stringify(article)).then(success).catch(fail);
}

function getModifyArticle(articleno, success, fail) {
  local.get(`/article/modify?articleno=${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  local.put(`/article/modify`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  local.delete(`/article/delete?articleno=${articleno}`).then(success).catch(fail);
}

export {
  listArticle,
  listArticle2,
  detailArticle,
  registArticle,
  getModifyArticle,
  modifyArticle,
  deleteArticle,
};
