package com.java.spring.model;

import lombok.Data;

@Data
public class User {
	private int id;						// 일련번호
	private String user_id;				// 아이디
	private String user_pw;				// 패스워드
	private String user_name;			// 회원이름
	private String user_nicname;		// 닉네임
	private String is_out;				// 탈퇴여부(Y/N)
	private String reg_date;			// 가입일시
	private String last_date;			// 탈퇴일시
	private String login_date;			// 마지막 로그인 일시
}
