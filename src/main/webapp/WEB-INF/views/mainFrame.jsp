<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <title>홈페이지</title>
    
    <style type="text/css">
    	* {
    		margin : 0px;
    		padding : 0px;
    	}
    
    	.header {
    		width: 100%;
    		height: 100px;
    		background-color: red;
    	}
    	
    	.body {
    		width: 100%;
    		height: auto;
    		background-color: pink;
    	}
    </style>
</head>

<body>
    <div class="header">
    header
    </div>
    <div class="body">
    body
    <br />
    footer
    </div>
</body>

</html>