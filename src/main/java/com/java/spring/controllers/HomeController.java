package com.java.spring.controllers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.java.spring.controllers.HomeController;
import com.java.spring.helper.PageData;
import com.java.spring.helper.RegexHelper;
import com.java.spring.helper.UploadItem;
import com.java.spring.helper.WebHelper;
import com.java.spring.model.Board;
import com.java.spring.model.User;
import com.java.spring.service.BoardService;
import com.java.spring.service.UserService;

import clojure.asm.Attribute;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class HomeController {
	
	/** WebHelper 객체 주입 */
	// --> import org.springframework.beans.factory.annotation.Autowired;
	// --> import study.spring.springhelper.helper.WebHelper;
	// 페이지간에 이동을 할 수 있게 해주는 helper
	@Autowired
	WebHelper webHelper;
	
	/** RegexHelper 객체 주입 */
	// --> import study.spring.springhelper.helper.RegexHelper;
	// 형식검사를 하게 해주는 Helper
	@Autowired
	RegexHelper regexHelper;
	
	/** 객체 주입 --> 자동할당 */
	// --> import org.springframework.beans.factory.annotation.Autowired;
	@Autowired
	UserService userService;
	
	/** 객체 주입 --> 자동할당 */
	// --> import org.springframework.beans.factory.annotation.Autowired;
	@Autowired
	BoardService boardService;
	
	/** "/프로젝트이름" 에 해당하는 ContextPath 변수 주입 */
	// --> import org.springframework.beans.factory.annotation.Value;
	@Value("#{servletContext.contextPath}")
	String contextPath;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * IP 확인 후 location
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "index";
	}
	
	/**
	 * 로그인 페이지
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView login(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		// 세션이 있다면 자동 로그인
		if(session.getAttribute("user_id") != null) {
			System.out.println("세션 유무 확인 후 로그인");
			System.out.println(session.getAttribute("user_id"));
			System.out.println(session.getAttribute("user_pw"));
			
			// 사용자 계정 정보 객체
			User input = new User();
			input.setUser_id((String)session.getAttribute("user_id"));
			input.setUser_pw((String)session.getAttribute("user_pw"));
			
			// 사용자 데이터를 담을 객체
			User output = null;
			
			try {
				output = userService.getUserItem(input);
			} catch (Exception e) {
				return webHelper.redirect(null, e.getLocalizedMessage());
			}
			
			// 로그인 사용자 정보
			User loginInfo = new User();
			loginInfo = output;
			
			model.addAttribute("loginInfo", loginInfo);
		}
		
		
		return new ModelAndView("main");
	}
	
	/**
	 * 로그인 action 페이지
	 */
	@RequestMapping(value = "/login_ok.do", method = RequestMethod.POST)
	public ModelAndView login(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required=false) String user_id,
			@RequestParam(required=false) String user_pw) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		// 유효성 검사
		if(user_id.equals("")) 	{ return webHelper.redirect(null, "아이디를 입력해주세요."); }
		if(user_pw.equals("")) 	{ return webHelper.redirect(null, "패스워드를 입력해주세요."); }
		
		// 세션을 생성한다.
		HttpSession session = request.getSession();
		
		// 사용자 계정 정보 객체
		User input = new User();
		input.setUser_id(user_id);
		input.setUser_pw(user_pw);
		
		// 사용자 데이터를 담을 객체
		User output = null;
		
		try {
			output = userService.getUserItem(input);
		} catch (Exception e) {
			return webHelper.redirect(null, "아이디와 패스워드를 확인해 주세요.");
		}
		
		// 세션에 값 저장
		session.setAttribute("user_id", user_id);
		session.setAttribute("user_pw", user_pw);
		session.setMaxInactiveInterval(30*60); // 60분동안 세션을 유지하고 싶다면, 60 * 60으로 설정 (초단위)
		
		// 로그인 사용자 정보
		User loginInfo = new User();
		loginInfo = output;
		
		/** 3) View 처리 */
		model.addAttribute("output", output);
		model.addAttribute("loginInfo", loginInfo);
		
		return new ModelAndView("redirect:/login.do");
	}
	
	/**
	 * 로그아웃 실행 페이지
	 */
	@RequestMapping(value = "/logout_ok.do", method = RequestMethod.POST)
	public ModelAndView logout(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response,
			// 페이지 구현에서 사용할 현재 페이지 번호
			@RequestParam(value="page", defaultValue="1") int nowPage) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		// 세션 삭제
		request.getSession().invalidate();
		request.getSession(true);
		
		return new ModelAndView("redirect:/login.do");
	}
	
	/**
	 * 게시판 페이지
	 */
	@RequestMapping(value="/board.do", method = RequestMethod.GET)
	public ModelAndView board(Locale locale, Model model,
			// 페이지 구현에서 사용할 현재 페이지 번호
			@RequestParam(value="page", defaultValue="1") int nowPage) {
		
		/** 현재시각 데이터 */
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		/** 1) 페이지 구현에 필요한 변수값 생성 */
		int totalCount = 0;			// 전체 게시글 수
		int listCount = 10;			// 한 페이지당 표시할 목록 수
		int pageCount = 10;			// 한 그룹당 표시할 페이지 번호 수
		
		// --> import study.spring.trspring.helper.PageData;
		PageData pageData = null;			// 페이지 번호를 계산한 결과가 저장될 객체
		
		/** 2) 데이터 조회하기 */
		// 검색어를 담을 데이터
		Board input = new Board();
		
		List<Board> output = null;		    		// 전체 조회결과가 저장될 객체

		// 실습사례 코드번호를 select해서 뿌려줄지 아니면 입력을 받는 형식으로 할지 확인============================================
		
		try {
			
			// 전체 게시글 수 조회
			totalCount = boardService.getBoardCount(input);
			// 페이지 번호 계산 --> 계산결과가 로그로 출력될 것이다.
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);
			
			// SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
			Board.setOffset(pageData.getOffset());
			Board.setListCount(pageData.getListCount());
			
			// 데이터 조회하기
			output = boardService.getBoardList(input);			// 전체 데이터

		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		/** 3) View 처리 */
		model.addAttribute("output", output);
		
		model.addAttribute("pageData", pageData);					// 페이지 번호 계산 결과 객체
		
		return new ModelAndView("board/board_main");
	}
	
	/**
	 * 게시판 입력 페이지
	 */
	@RequestMapping(value="/boardInsert.do", method = RequestMethod.GET)
	public ModelAndView boardInsert(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return new ModelAndView("board/board_insert");
	}
	
	/**
	 * 게시판 수정 페이지
	 */
	@RequestMapping(value="/boardUpdate.do", method = RequestMethod.GET)
	public ModelAndView boardUpdate(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return new ModelAndView("board/board_update");
	}
	
	/**
	 * 챗봇 페이지 (아직)
	 */
	@RequestMapping(value="/chatbot.do", method = RequestMethod.GET)
	public ModelAndView chatbot(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return new ModelAndView("chatbot/chatbot");
	}
	
	/**
	 * 메인프레임 페이지 (아직)
	 */
	@RequestMapping(value = "/main_frame.do", method = RequestMethod.GET)
	public String mainframe(Locale locale, Model model) {
		return "mainFrame";
	}
	
}
