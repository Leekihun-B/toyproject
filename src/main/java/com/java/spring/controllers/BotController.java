package com.java.spring.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.spring.helper.RegexHelper;
import com.java.spring.helper.WebHelper;
import com.java.spring.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@RestController
public class BotController {
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
	
	/** "/프로젝트이름" 에 해당하는 ContextPath 변수 주입 */
	// --> import org.springframework.beans.factory.annotation.Value;
	@Value("#{servletContext.contextPath}")
	String contextPath;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * chatbot 기본. 다시 수정해야함.
	 */
	@RequestMapping(value = "/chatbot_api.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		
		
		return "chatbot/chatbot";
	}
}
