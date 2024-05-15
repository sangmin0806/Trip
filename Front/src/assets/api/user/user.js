import { localAxios } from '@/util/http-commons';
const local = localAxios();

//로그인
async function loginFormSubmit(user, success, fail) {
    local.post(`user/login`, user).then(success).catch(fail);
}
//회원가입
async function registerFormSubmit(user, success, fail) {
    local.post(`user/join`, user).then(success).catch(fail);
}
//로그아웃
async function logoutSubmit(success, fail) {
    local.get(`user/logout`).then(success).catch(fail);
}

export { loginFormSubmit, registerFormSubmit, logoutSubmit };
