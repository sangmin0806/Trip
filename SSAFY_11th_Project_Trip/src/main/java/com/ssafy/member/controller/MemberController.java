package com.ssafy.member.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class MemberController {
	
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
//	@Hidden
//	@Operation(hidden = true)
	@GetMapping("/{userid}")
	@ResponseBody
	public String idCheck(@PathVariable("userid") String userId) throws Exception {
		log.debug("idCheck userid : {}", userId);
		int cnt = memberService.idCheck(userId);
		return cnt + "";
	}
	
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody MemberDto memberDto) {
		System.out.println(memberDto);
		log.debug("memberDto info : {}", memberDto);
		try {
			memberService.joinMember(memberDto);
			if (memberDto != null) {
	            // 회원가입 성공 시
	            return ResponseEntity.ok().build(); // 성공 응답 (HTTP 상태 코드 200)
	        } else {
	            // 회원가입 실패 시
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호 확인 후 다시 로그인하세요!"); // 실패 응답 (HTTP 상태 코드 401)
	        }
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 중 문제 발생!!!"); // 서버 오류 응답 (HTTP 상태 코드 500)
		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> map, @RequestParam(name = "saveid", required = false) String saveid, HttpSession session) {
		log.debug("login map : {}", map);
		log.debug(saveid);
		try {
			MemberDto memberDto = memberService.loginMember(map);
			if (memberDto != null) {
	            // 로그인 성공 시
				session.setAttribute("userId", memberDto.getUserId());
	            session.setAttribute("isLoggedIn", true); // 세션에 로그인 상태 저장
	            return ResponseEntity.ok().build(); // 성공 응답 (HTTP 상태 코드 200)
	        } else {
	            // 로그인 실패 시
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호 확인 후 다시 로그인하세요!"); // 실패 응답 (HTTP 상태 코드 401)
	        }
		} catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 중 문제 발생!!!"); // 서버 오류 응답 (HTTP 상태 코드 500)
	    }
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/list")
	public String list() {
		return "redirect:/assets/list.html";
	}
	
}
