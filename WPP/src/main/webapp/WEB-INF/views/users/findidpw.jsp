<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cot :: ID/PW찾기</title>
<%@ include file="../commons/_header.jspf" %>
</head>
<body>
<%@ include file="../commons/_top.jspf" %>
<div class="center">
	<div class="boardpadding">
		<br><br><br>
		<h3>[아이디 찾기]</h3>
		<h3>회원가입시 입력한 이메일 주소를 입력해주세요!</h3>
		<h4>2번째 글자를 제외한 아이디를 알려드립니다.</h4>
		<br>
		<form id="idform" action="/users/findid" method="post">
			<input type="text" id="user_name" name="user_name" placeholder="이름" style="width: 15%;"><br>
			<input type="text" id="user_email" name="user_email" placeholder="E-MAIL" style="width: 15%;">
			<button type="submit" class="btn btn-default">ID 찾기</button>
		</form>
		<br>
		<br>
		<h3>[비밀번호 찾기]</h3>
		<h3>회원가입시 입력한 아이디와 이메일 주소를 입력해주세요!</h3>
		<h4>이메일로 새로운 비밀번호를 보내드립니다.</h4>
		<br>
		<form id="pwform" action="/users/findpw" method="post">
			<input type="text" id="user_id" name="user_id" placeholder="ID" style="width: 15%;"><br>
			<input type="text" id="user_email" name="user_email" placeholder="E-MAIL" style="width: 15%;">
		<button type="submit" class="btn btn-default">PASSWORD 찾기</button>
		</form>
	</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="../commons/_foot.jspf"%>
</div>
</body>
</html>