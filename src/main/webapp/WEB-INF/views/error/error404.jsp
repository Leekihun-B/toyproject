<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page isErrorPage = "true" %>
<html>
<head>
    <!-- 저장시에 사용된 인코딩(파일의 저장형식) 값을 웹 브라우저에게 알려준다. - ANSI(euc-kr), UTF-8 -->
    <meta chaset="utf-8" />
    <!-- IE의 호환성 보기 모드 금지 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 스마트 장치에서의 해상도 균일화 처리 -->
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">

    <link rel="stylesheet" type="text/css" href="khome.css" />
    <!-- 브라우저에 표시될 문서 제목 -->
    <title>Error Page</title>

    <!-- 스타일시트 -->
    <style type="text/css">
        .k_text{
            width: 1500px;
            height: 400px;
            margin: auto;
            margin-top: 300px;
            text-align: center;
            font-size: 50px;
        }

        .text1 {
            font-size: 60px;
            color: rgb(28, 115, 255);
        }

        .text2 {
            line-height: 100px;
            font-size: 35px;
        }
    </style>

</head>

<body>
    <!-- s: 컨텐츠  -->
    <div class="k_text">
        <span class="text1">찾을 수 없는 페이지 입니다.</span><br />
        <span class="text2">이용에 불편을 드려서 죄송합니다.</span>
    </div>
</body>
</html>