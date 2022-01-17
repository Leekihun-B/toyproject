package com.java.spring.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.spring.model.MsgTran;
import com.java.spring.service.MsgTranService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service	// --> import org.springframework.stereotype.Service;
public class MsgTranServiceImpl implements MsgTranService {
	
	/** MyBatis 세션 객체 주입 설정 */
	@Autowired
	SqlSession sqlSession;
	
	/**
	 * Msg_Tran 데이터 등록하기
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int addMsgTran(MsgTran input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.insert("MsgTranMapper.insertItem", input);
			
			if (result == 0) {
				throw new NullPointerException("result=0");
			}
		} catch(NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("저장된 데이터가 없습니다.");
		} catch(Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 저장에 실패했습니다.");
		}
		
		return result;
	}

}
