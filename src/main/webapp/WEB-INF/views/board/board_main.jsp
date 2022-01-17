<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/logincheckheader.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>게시판</title>
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
	
	table, td {
		padding-left : 20px;
		padding-right : 20px;
		border-collapse: collapse;
		border : 1px solid white;
		text-align : center;
	}
	
	</style>
	
</head>

<body>
	<div>
		<h1>게시판 시작</h1>
	    <p>${serverTime }</p>
	    <form id="logoutform" method="post" action="logout_ok.do" >
	   		<button type="button" onclick="logout()">로그아웃</button>
	    </form>
	</div>
    <div>
    	<h2>본문</h2>
    	<div class="table">
    		<table>
    			<thead>
    				<tr>
    					<td>No</td>
    					<td style="min-width: 400px;">제목</td>
    					<td>아이디</td>
    					<td>등록일</td>
    					<td>수정/삭제</td>
    				</tr>
    			</thead>
    			<tbody>
    				<c:choose>
    					<%-- 조회결과가 없는 경우 --%>
    					<c:when test="${output == null || fn:length(output) == 0}">
    						<tr>
    							<td colspan="5" align="center">조회결과가 없습니다.</td>
    						</tr>
    					</c:when>
    					<%-- 조회결과가 있는 경우 --%>
    					<c:otherwise>
    						<%-- 조회 결과에 따라 반복 처리 --%>
				    		<c:forEach var="item" items="${output }" varStatus="status">
				    			<tr>
				    				<td>${item.id }</td>
									<td>${item.title }</td>
									<td>${item.user_id }</td>
									<td>${item.insert_date }</td>
									<td><button type="button" onclick="board_update(${status.count})">수정</button><button type="button" onclick="board_delete(${status.count})">삭제</button></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
	                    
			<div class="list">
			<%-- 페이지 번호 구현 --%>
				<%-- 이전 그룹에 대한 링크 --%>
				<c:choose>
					<%-- 이전 그룹으로 이동 가능하다면? --%>
					<c:when test="${pageData.prevPage > 0 }">
						<%-- 이동할 URL 생성 --%>
						<c:url value="/board.do" var="prevPageUrl">
							<c:param name="page" value="${pageData.prevPage }" />
						</c:url>
						<a href="${prevPageUrl }">&lt;</a>
					</c:when>
					<c:otherwise>
						&lt;
					</c:otherwise>
				</c:choose>
						    
				
				<%-- 페이지 번호 (시작 페이지 부터 끝 페이지까지 반복) --%>
				<c:forEach var="i" begin="${pageData.startPage }" end="${pageData.endPage }" varStatus="status">
					<%-- 이동할 URL 생성 --%>
					<c:url value="/board.do" var="pageUrl">
						<c:param name="page" value="${i }" />
					</c:url>
				    	
					<%-- 페이지 번호 출력 --%>
					<c:choose>
						<%-- 현재 머물고 있는 페이지 번호를 출력할 경우 링크 적용 안함 --%>
						<c:when test="${pageData.nowPage == i }">
							${i }
						</c:when>
						<%-- 나머지 페이지의 경우 링크 적용함 --%>
						<c:otherwise>
							<a href="${pageUrl }">${i }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
						    
				<%-- 다음 그룹에 대한 링크 --%>
				<c:choose>
					<%-- 다음 그룹으로 이동 가능하다면? --%>
					<c:when test="${pageData.nextPage > 0 }">
						<%-- 이동할 URL 생성 --%>
						<c:url value="/board.do" var="nextPageUrl">
							<c:param name="page" value="${pageData.nextPage }" />
						</c:url>
						<a href="${nextPageUrl }">&gt;</a>
					</c:when>
					<c:otherwise>
						&gt;
					</c:otherwise>
				</c:choose>
			</div>
		</div>
    </div>
    
    
    
    <%-- form submit하는 코드 --%>
    <script type="text/javascript">
        function logout(){
			<%-- 폼영역 --%>
			var logoutform = document.querySelector("#logoutform")
			<%-- 전송 --%>
			logoutform.submit();
        }
    </script>
    
    <%-- 수정, 삭제 버튼 --%>
    <script type="text/javascript">
        function board_update(var a){
			var downloadpwd = prompt('다운로드 패스워드를 입력해 주세요.', '')
			$("#downloadpwd1").val(downloadpwd)
			<%-- 확인을 눌렀다면 실행 --%>
			if(downloadpwd != null){
				<%-- 폼영역 --%>
				var downform = document.querySelector("#downform1")
				<%-- 전송 --%>
				downform.submit();
			}
        }
        
        function board_delete(var a){
			var downloadpwd = prompt('다운로드 패스워드를 입력해 주세요.', '')
			$("#downloadpwd1").val(downloadpwd)
			<%-- 확인을 눌렀다면 실행 --%>
			if(downloadpwd != null){
				<%-- 폼영역 --%>
				var downform = document.querySelector("#downform1")
				<%-- 전송 --%>
				downform.submit();
			}
        }
	</script>
</body>

</html> 