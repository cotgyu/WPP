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
					<h3>내 정보</h3>
				</div>
				
				<c:choose>
				<c:when test="${empty user.userId}">
					<c:set var="method" value="post" />
				</c:when>
				<c:otherwise>
					<c:set var="method" value="put" />
				</c:otherwise>
				</c:choose>
					
				<div class="control-group">
					<label class="control-label" for="profileimg">프로필 사진</label>			
					<img src="/resources/profile/${user.profileimg}" width="100" height="100">							
					<form id=form action="/users/imgmodify" method="post" enctype="multipart/form-data">							
						<input type="file" name="file">			         				        
						<input type="submit" value="프로필 바꾸기" class="btn btn-default">				          
					</form>	
				</div>
				<!-- 유저 정보를 모델 형태로 전달받음 -->
				<form:form modelAttribute="user" cssClass="form-horizontal" action="/users" method="${method}">
					<div class="control-group">
						<label class="control-label" for="userId">사용자 아이디</label>
						<div class="controls">
							<c:choose>
							<c:when test="${empty user.userId}">
								<form:input path="userId"/>
								<form:errors path="userId" cssClass="error" />
							</c:when>
							<c:otherwise>
								${user.userId}
								<form:hidden path="userId"/>
							</c:otherwise>
							</c:choose>
						</div>
					</div>			
						
					<div class="control-group">
						<label class="control-label" for="name">이름</label>
						<div class="controls">
							${user.name}
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="email">이메일</label>
						<div class="controls">
							${user.email}
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="indate">가입일</label>
						<div class="controls">					
							<fmt:formatDate value="${user.joindate}" pattern="yyyy-MM-dd" />
						</div>
					</div>
					
					<div class="control-group">
						<div class="controls">
							<input type="button" value="정보 수정" class="btn btn-default" onClick="location.href='/users/myinfo/infomodify'">
							<input type="button" value="회원 탈퇴" class="btn btn-default" onClick="location.href='/users/unregisterform'">
						</div>
					</div>				
				</form:form> 
			</div>
		</div>
	</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="../commons/_foot.jspf"%>
</div>
</body>
</html>