const wrapper = document.querySelector('.wrapper');
const modal = document.querySelector('.modal-content');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const btnLogin = document.querySelector('.btnLogin-popup');
const btnLogout = document.querySelector('.btnLogout-popup');
const iconClose = document.querySelector('.icon-close');
const root = 'http://localhost:8080';
 var isLoggedIn = false;
// 폼이동
registerLink.addEventListener('click', (event) => {
    deleteInput();
    wrapper.style.height = '600px';
    modal.classList.add('active'); // active 설정 (회원가입 폼으로)
});
loginLink.addEventListener('click', (event) => {
    deleteInput();
    wrapper.style.height = '440px';
    modal.classList.remove('active'); // active 설정 (login 폼으로)
});


// 로그인 폼 띄우기
btnLogin.addEventListener('click', (event) => {
	showModal();
	deleteInput()
    wrapper.classList.add('active-popup');
});

iconClose.addEventListener('click', (event) => {
    wrapper.classList.remove('active-popup');
});

//로그아웃 버튼 눌렀을때
btnLogout.addEventListener('click', (event) => {
   	isLoggedIn = false;
   	updateMenu();
});

//로그인 폼 보내기
document.querySelector('#loginBtn').addEventListener('click', async () => {
    // 로그인 정보 수집
    let userId = document.querySelector("#userId").value;
    let userPwd = document.querySelector("#userPwd").value;
    let saveid = document.querySelector("#saveid").checked;


    // 필수 입력값 확인
    if (!userId || !userPwd) {
        alert("이메일과 비밀번호를 모두 입력해주세요.");
        return;
    }

    // fetch 요청 설정
    let config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ "userId": userId, "userPwd": userPwd }),
    };

    try {
        const response = await fetch(`${root}/user/login?saveid=${saveid}`, config);
        if (response.ok) {
            // 로그인 성공
        deleteInput()
         wrapper.classList.remove('active-popup');
         isLoggedIn = true;
   		updateMenu();
        } else {
            // 로그인 실패
            alert("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
    } catch (error) {
        // 네트워크 오류 등으로 인한 요청 실패
        console.error('Fetch error:', error);
    }
});
//회원가입 폼 보내기
document.querySelector('#registerBtn').addEventListener('click', async () => {
    // 로그인 정보 수집
    let userId = document.querySelector("#userId2").value;
    let userPwd = document.querySelector("#userPwd2").value;
    let userName = document.querySelector("#userName").value;
    let email = document.querySelector("#email").value;
    console.log(userId);
    console.log(userPwd);

    // fetch 요청 설정
    let config = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ "userId": userId, "userPwd": userPwd,"userName": userName,"email": email }),
    };

    try {
        const response = await fetch(`${root}/user/join`, config);
        if (response.ok) {
			alert("회원가입 성공.");
            // 회원가입 성공
        deleteInput();
         wrapper.style.height = '440px';
        modal.classList.remove('active');
         wrapper.classList.remove('active-popup');
        } else {
            // 회원가입 실패
            alert("회원가입 실패.");
        }
    } catch (error) {
        // 네트워크 오류 등으로 인한 요청 실패
        console.error('Fetch error:', error);
    }
});


window.onload = function() {
    updateMenu(); // 메뉴 업데이트
};

function showModal() {
    document.getElementById('Modal').style.display = 'block';
}
// 메뉴 업데이트 함수
function updateMenu() {
   
  	var myCourseLink = document.querySelector(".navigation a:nth-of-type(4)");
    var myPageLink = document.querySelector(".navigation a:nth-of-type(5)");
    if (!isLoggedIn) {
        // 로그인되어 안되어있을 때
        document.querySelector(".btnLogin-popup").style.display = "block";
        document.querySelector(".btnLogout-popup").style.display = "none"; // 로그아웃 버튼 숨기기
 
        myCourseLink.style.display = "none"; // "내 코스" 링크 숨기기
        myPageLink.style.display = "none"; // "마이페이지" 링크 숨기기
    }else{
		 document.querySelector(".btnLogin-popup").style.display = "none"; // 로그인 버튼 숨기기
		 document.querySelector(".btnLogout-popup").style.display = "block";
		 myCourseLink.style.display = "block"; // "내 코스" 링크 보이기
         myPageLink.style.display = "block"; // "마이페이지" 링크 보이기
	}
}
function deleteInput(){
		wrapper.querySelectorAll('input').forEach((input) => {
        	//폼이동시 이전 입력값 사라짐
        	input.value = '';
    	});
}