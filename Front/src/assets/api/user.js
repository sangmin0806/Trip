import { onMounted } from 'vue';
import { localAxios } from '@/util/http-commons';
const local = localAxios();
const root = 'http://localhost:8080';
let isLoggedIn = false;
let wrapper;
let modal;
onMounted(() => {
  // 이 부분은 컴포넌트가 마운트된 후에 실행됩니다.
  wrapper = document.querySelector('.wrapper');
  modal = document.querySelector('.modal-content');
  const loginLink = document.querySelector('.login-link');
  const registerLink = document.querySelector('.register-link');
  const btnLogin = document.querySelector('.btnLogin-popup');
  const btnLogout = document.querySelector('.btnLogout-popup');
  const iconClose = document.querySelector('.icon-close');

  // 폼이동
  registerLink.addEventListener('click', () => {
    deleteInput();
    wrapper.style.height = '600px';
    modal.classList.add('active'); // active 설정 (회원가입 폼으로)
  });

  loginLink.addEventListener('click', () => {
    deleteInput();
    wrapper.style.height = '440px';
    modal.classList.remove('active'); // active 설정 (login 폼으로)
  });

  // 로그인 폼 띄우기
  btnLogin.addEventListener('click', () => {
    showModal();
  });

  iconClose.addEventListener('click', () => {
    wrapper.classList.remove('active-popup');
  });

  //로그아웃 버튼 눌렀을때
  btnLogout.addEventListener('click', () => {
    isLoggedIn = false;
    updateMenu();
  });
});
// 메뉴 업데이트 함수
function updateMenu() {
  var myCourseLink = document.querySelector('.navigation a:nth-of-type(4)');
  var myPageLink = document.querySelector('.navigation a:nth-of-type(5)');
  if (!isLoggedIn) {
    // 로그인되어 안되어있을 때
    document.querySelector('.btnLogin-popup').style.display = 'block';
    document.querySelector('.btnLogout-popup').style.display = 'none'; // 로그아웃 버튼 숨기기
    myCourseLink.style.display = 'none'; // "내 코스" 링크 숨기기
    myPageLink.style.display = 'none'; // "마이페이지" 링크 숨기기
  } else {
    document.querySelector('.btnLogin-popup').style.display = 'none'; // 로그인 버튼 숨기기
    document.querySelector('.btnLogout-popup').style.display = 'block';
    myCourseLink.style.display = 'block'; // "내 코스" 링크 보이기
    myPageLink.style.display = 'block'; // "마이페이지" 링크 보이기
  }
}

function showModal() {
  console.log(wrapper);
  document.getElementById('Modal').style.display = 'block';
  wrapper.classList.add('active-popup');
  deleteInput();
}

function deleteInput() {
  wrapper.querySelectorAll('input').forEach((input) => {
    //폼이동시 이전 입력값 사라짐
    input.value = '';
  });
}
async function loginFormSubmit(user, success, fail) {
  // 필수 입력값 확인
  if (!user.userId || !user.userPwd) {
    alert('이메일과 비밀번호를 모두 입력해주세요.');
    return;
  }
  local.post(`user/login`, user).then(success).catch(fail);
}
async function registerFormSubmit(user, success, fail) {
  // 필수 입력값 확인
  if (!user.userId || !user.userPwd) {
    alert('이메일과 비밀번호를 모두 입력해주세요.');
    return;
  }
  local.post(`user/join`, user).then(success).catch(fail);
}

async function logoutSubmit(success, fail) {
  local.get(`user/logout`).then(success).catch(fail);
}
// 회원가입 폼 보내기

export { loginFormSubmit, showModal, registerFormSubmit, logoutSubmit };
