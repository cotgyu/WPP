<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cot :: 정보 수정</title>
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
					<h3>정보 수정</h3>
				</div>
				<!-- 유저 정보를 모델형태로 전달받음 -->
				<form:form modelAttribute="user" cssClass="form-horizontal" action="/users/modify" method="post">
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
						<label class="control-label" for="password">비밀번호</label>
						<div class="controls">
							<form:password path="password" />
							<form:errors path="password" cssClass="error" />
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="name">이름</label>
						<div class="controls">
							<form:input path="name"/>
							<form:errors path="name" cssClass="error" />
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="email">이메일</label>
						<div class="controls">
							<form:input path="email"/>
							<form:errors path="email" cssClass="error" />
						</div>
					</div>		
						
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-default">수정</button>
						</div>
					</div>				
				</form:form> 
			</div>
		</div>
	</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="../commons/_foot.jspf"%>
</div>
</body>
</html>