<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cot :: 내 정보</title>
<%@ include file="../commons/_header.jspf" %>
</head>
<body>
<%@ include file="../commons/_top.jspf" %>
<div class="center">
	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<h3>회원탈퇴</h3>
				</div>
				비밀번호를 다시 확인해주세요.<br><br>
				<button type="button" onclick="location.href='/'" class="btn btn-default">홈</button>
				<button type="button" onclick="location.href='/users/unregisterform'" class="btn btn-default">회원탈퇴</button>
			</div>
		</div>
	</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="../commons/_foot.jspf"%>
</div>
</body>
</html>