<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	HttpSession session = request.getSession(false);
	
	// 로그인이 안되어 있으면 접근할 수 없음.
	if(session.getAttribute("user_id") == null){
		%>
		<script type="text/javascript">
			alert("해당 페이지는 로그인 후 접근 가능합니다.");
			location.href = "http://localhost:8080/hellospring/";
		</script>
		<%
	}
	
	//로그아웃 시 뒤로가기 차단 코드
	if("HTTP/1.1".equals(request.getProtocol())) {
		response.setHeader ("Cache-Control", "no-cache, no-store, must-revalidate");
	} else {
		response.setHeader ("Pragma", "no-cache");
	}
	response.setDateHeader ("Expires", 0);
%>