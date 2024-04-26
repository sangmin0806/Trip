package com.ssafy.member.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "MemberDto (회원정보)", description = "회원의 아이디, 비번, 이름을 가진 Domain Class")
public class MemberDto {

	@Schema(description = "회원아이디", requiredMode = Schema.RequiredMode.REQUIRED, example = "hissam")
	private String userId;
	@Schema(description = "회원이름", example = "하이쌤")
	private String userName;
	@Schema(description = "회원비밀번호")
	private String userPwd;
	@Schema(description = "이메일")
	private String email;
	@Schema(description = "가입일", defaultValue = "현재시간")
	private String joinDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "MemberDto [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", email="
				 +email + "="  + ", joinDate=" + joinDate + "]";
	}

}
