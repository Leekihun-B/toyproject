package com.java.spring.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.spring.model.Board;
import com.java.spring.model.Department;
import com.java.spring.model.User;
import com.java.spring.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j		// --> import lombok.extern.slf4j.Slf4j;
@Service	// --> import org.springframework.stereotype.Service;
public class BoardServiceImpl implements BoardService{

	/** MyBatis 세션 객체 주입 설정 */
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public Board getBoardItem(Board input) throws Exception {
	Board result = null;
		
		try {
			result = sqlSession.selectOne("BoardMapper.selectItem", input);
			
			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch(NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch(Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		
		return result;
	}

	@Override
	public List<Board> getBoardList(Board input) throws Exception {
		List<Board> result = null;
		
		try {
			result = sqlSession.selectList("BoardMapper.selectList", input);
			
			if (result == null) {
				throw new NullPointerException("result=null");
			}
		} catch(NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("조회된 데이터가 없습니다.");
		} catch(Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		
		return result;
	}

	@Override
	public int getBoardCount(Board input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.selectOne("BoardMapper.selectCountAll", input);
		} catch(Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public int addBoard(Board input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.insert("BoardMapper.insertItem", input);
			
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

	@Override
	public int editBoard(Board input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.update("BoardMapper.updateItem", input);
			
			if (result == 0) {
				throw new NullPointerException("result=0");
			}
		} catch(NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("수정된 데이터가 없습니다.");
		} catch(Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 수정에 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public int deleteBoard(Board input) throws Exception {
		int result = 0;
		
		try {
			result = sqlSession.delete("BoardMapper.deleteItem", input);
			
			if (result == 0) {
				throw new NullPointerException("result=0");
			}
		} catch(NullPointerException e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("삭제된 데이터가 없습니다.");
		} catch(Exception e) {
			log.error(e.getLocalizedMessage());
			throw new Exception("데이터 삭제에 실패했습니다.");
		}
		
		return result;
	}
	
}
