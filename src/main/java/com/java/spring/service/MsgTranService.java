package com.java.spring.service;

import com.java.spring.model.MsgTran;

public interface MsgTranService {
	/**
	 * Msg_Tran 데이터 등록하기
	 * @return int
	 * @throws Exception
	 */
	public int addMsgTran(MsgTran input) throws Exception;
}
