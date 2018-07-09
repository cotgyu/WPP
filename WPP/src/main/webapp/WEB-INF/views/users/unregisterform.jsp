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
				정말로 탈퇴하시겠습니까??<br>
				탈퇴시 복구 불가능합니다.<br><br>	
				가입시 등록한 비밀번호를 입력해주세요.
				<form id="unreg" action="/users/unregister" method="post">
					<input type="password" id="user_password" name="user_password" placeholder="PASSWORD" style="width: 15%;"><br>
					<button type="submit" class="btn btn-default">회원 탈퇴</button>
				</form>		
			</div>
		</div>
	</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="../commons/_foot.jspf"%>
</div>
</body>
</html>