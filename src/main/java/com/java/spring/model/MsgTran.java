package com.java.spring.model;

import lombok.Data;

// 메시지 SMS 전송을 위한 POJO 클래스
@Data
public class MsgTran {
	private int Msg_id;								// 메시지의 고유번호, 자동 증가하는 값으로 Msg_Tran 의 Primary Key가 된다.
	private String Phone_No;						// 수신번호
	private String Callback_No = "023305133";		// 발신번호 (oneshot2 주식회사에 등록된 번호 [고정])
	private int Status;								// 상태값(발송요청:0, 전송중:1, 결과대기:2)
	private int Msg_Type;							// 메시지 타입(4:SMS, 6:LMS,MMS, 7:알림톡, 8:친구톡)
	private String Send_Time;						// 전송시간
	private String Save_Time;						// 저장시간
	private String Message;							// 발송 메시지(SMS:90바이트, LMS,MMS:2000바이트, 카카오:1000자)
}
