const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const btnPopup = document.querySelector('.btnLogin-popup');
const iconClose = document.querySelector('.icon-close');

// 폼이동
registerLink.addEventListener('click', (event) => {
    wrapper.querySelectorAll('input').forEach((input) => {
        //폼이동시 이전 입력값 사라짐
        input.value = '';
    });

    wrapper.classList.add('active'); // active 설정 (회원가입 폼으로)
});
loginLink.addEventListener('click', (event) => {
    wrapper.querySelectorAll('input').forEach((input) => {
        //폼이동시 이전 입력값 사라짐
        input.value = '';
    });

    wrapper.classList.remove('active'); // active 설정 (login 폼으로)
});

// 로그인 폼 띄우기
btnPopup.addEventListener('click', (event) => {
    wrapper.classList.add('active-popup');
});

iconClose.addEventListener('click', (event) => {
    wrapper.classList.remove('active-popup');
});
