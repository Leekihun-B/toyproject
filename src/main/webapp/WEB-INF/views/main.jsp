<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	HttpSession session = request.getSession(false);

	//로그아웃 시 뒤로가기 차단 코드
	if("HTTP/1.1".equals(request.getProtocol())) {
		response.setHeader ("Cache-Control", "no-cache, no-store, must-revalidate");
	} else {
		response.setHeader ("Pragma", "no-cache");
	}
	response.setDateHeader ("Expires", 0);
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>홈페이지</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    
    <%-- Ajax 사용 --%>
    <%--Google CDN 서버로부터 jQuery 참조 --%>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <%-- Handlebar CDN 참조 --%>
    <script src="//cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.4.2/handlebars.min.js"></script>
    <%-- jQuery Ajax Setup --%>
    <script src="${pageContext.request.contextPath}/assets/plugins/ajax/ajax_helper.js"></script>
	
	<style type="text/css">
	
	*{
		color : white;
		background: black;
	}
	
	</style>
	
</head>

<body>
    
    <p>${serverTime }</p>
    <c:if test="${loginInfo.user_id == null}">
    <form id="login_form" method="post" action="login_ok.do">
    	<label for="user_id">아이디 : </label>
    	<input type="text" name="user_id" id="user_id" />
    	<label for="user_pw">패스워드 : </label>
    	<input type="password" name="user_pw" id="user_pw" />
    	<button type="button" onclick="login()">로그인</button>
    </form>
    </c:if>
    
    
   	<c:if test="${loginInfo.user_id != null}">
   		<form id="logoutform" method="post" action="logout_ok.do" >
    		<br />
    		<button type="button" onclick="logout()">로그아웃</button>
    		<br />
    	</form>
   		<p>
   		<%= session.getAttribute("user_id") %>님 환영합니다.
   		</p>
		<ul>
			<li><a href="${pageContext.request.contextPath}/board.do">게시판</a></li>
			<br />
			<li><a href="${pageContext.request.contextPath}/chatbot.do">챗봇</a></li>
		</ul>
	</c:if>
    	
    <!-- <a href="${pageContext.request.contextPath }/main_frame.do">메인프레임</a> -->
    
    <%-- form submit하는 코드 --%>
    <script type="text/javascript">
        function login(){
			<%-- 폼영역 --%>
			var loginform = document.querySelector("#login_form")
			<%-- 전송 --%>
			loginform.submit();
        }
        function logout(){
			<%-- 폼영역 --%>
			var logoutform = document.querySelector("#logoutform")
			<%-- 전송 --%>
			logoutform.submit();
        }
    </script>
    
    <%-- login key event --%>
    <script type="text/javascript">
 	$('#user_id').focus(function(e){
 		$(document).keypress(function(e){ 
   			if ( e.keyCode == 13 ) { 
       			login();
       		}
   		}); 
 	});
 	$('#user_pw').focus(function(e){
 		$(document).keypress(function(e){ 
   			if ( e.keyCode == 13 ) { 
       			login();
       		}
   		}); 
 	});
   	

    </script>
</body>

</html>

