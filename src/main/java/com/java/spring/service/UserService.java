package com.java.spring.service;

import java.util.List;

import com.java.spring.model.User;

public interface UserService {
	/**
	 * User 데이터 상세 조회
	 * @param User	조회할 데이터의 일련번호를 담고 있는 Beans
	 * @return	조회된 데이터가 저장된 Beans
	 * @throws Eception
	 */
	public User getUserItem(User input) throws Exception;
	
	/**
	 * User 데이터 목록 조회
	 * @return 조회 결과에 대한 컬렉션
	 * @throws Exception
	 */
	public List<User> getUserList(User input) throws Exception;
	
	/**
	 * User 데이터가 저장되어 있는 갯수 조회
	 * @return int
	 * @throws Exception
	 */
	public int getUserCount(User input) throws Exception;
	
	/**
	 * User 데이터 등록하기
	 * @return int
	 * @throws Exception
	 */
	public int addUser(User input) throws Exception;
	
	/**
	 * User 데이터 수정하기
	 * @param User 수정할 정보를 담고 있는 Beans
	 * @return int
	 * @throws Exception
	 */
	public int editUser(User input) throws Exception;
	
	/**
	 * User 데이터 삭제하기
	 * @param User 삭제할 데이터의 일련번호를 담고 있는 Beans
	 * @return int
	 * @throws Exception
	 */
	public int deleteUser(User input) throws Exception;
}
