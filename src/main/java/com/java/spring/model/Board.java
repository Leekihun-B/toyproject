package com.java.spring.model;

import lombok.Data;

@Data
public class Board {
	private int id;					// 일련번호
	private String title;			// 게시판 제목
	private String content;			// 게시판 내용
	private String filename;		// 업로드 파일명
	private String insert_date;		// 등록일시
	private String update_date;		// 수정일시
	private String last_date;		// 삭제일시
	private String is_out;			// 삭제여부(Y/N)
	
	private String user_id;			// 사용자 아이디 (user 테이블 pk)
	
	/** 페이지 구현이 필요한 경우 아래 속성들을 추가한다. (static) */
	private static int offset;		// LIMIT 절에서 사용할 검색 시작 위치
	private static int listCount;	// LIMIT 절에서 사용하는 검색할 데이터의 수
	
	public static int getOffset() {
		return offset;
	}
	public static void setOffset(int offset) {
		Board.offset = offset;
	}
	public static int getListCount() {
		return listCount;
	}
	public static void setListCount(int listCount) {
		Board.listCount = listCount;
	}
}
