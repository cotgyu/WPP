<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>쪽지 보내기</title>
<%@ include file="../commons/_header.jspf"%>
<script>

//창 닫기 
$(document).ready(function() {
    $("#close").click(function() {
        window.open('about:blank', '_self').close();
    });
});
</script>
</head>
<body>
<h3>쪽지 보내기</h3><br>
<form id="messageform" action="/users/sendmessage" method="post">
	<h4>보내는 사람: ${senduser}</h4> <br>
	<h4>받는 사람 : ${receiver}</h4> <br>
	<input type="hidden" id="senduser" name="senduser" value="${senduser}">
	<input type="hidden" id="receiver" name="receiver" value="${receiver}" >
	<textarea id="content" name="content" style="width:399px; height: 200px;"></textarea><br>
	<button type="submit" class="btn btn-default">쪽지 전송</button>
	<button type="button" id="close"  class="btn btn-default">창 닫기</button>	
</form>
</body>
</html>