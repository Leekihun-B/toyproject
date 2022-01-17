package com.java.spring.helper;

import lombok.Data;

@Data
public class UploadItem {
	private String fieldName;		// <input type="file">의 name속성
	private String orginName;		// 원본 파일 이름
	private String contentType;		// 파일의 형식
	private long fileSize;			// 파일의 용량
	private String filePath;		// 서버상의 파일경로
	private String thumbnailPath;	// 썸네일 이미지 경로
	private String fileUrl;			// 서버상의 원본 이미지 URL
	private String thumbnailUrl;	// 썸네일 이미지 URL
	private String fileName;		// 현재시각 + 카운트값 + 확장자 경로에 저장될 이름
}
