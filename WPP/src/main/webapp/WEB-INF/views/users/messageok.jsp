<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>쪽지 보내기</title>
<%@ include file="../commons/_header.jspf"%>
<script>
//창닫기
$(document).ready(function() {
    $("#close").click(function() {
        window.open('about:blank', '_self').close();
    });
});
</script>
</head>
<body>
	<h3>쪽지 전송이 성공하였습니다.</h3><br>
	<button type="button" id="close" class="btn btn-default">창 닫기</button>
</body>
</html>