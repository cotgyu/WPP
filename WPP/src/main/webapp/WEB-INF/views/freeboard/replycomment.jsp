<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<br><코멘트><br>
	${sessionScope.userId}
	<form name="form4" method="post" action="/freereply/comment" method="${method}">
		<textarea name="replytext" id="replytext" rows="5" cols="82" ></textarea>
		<input type="hidden" name="regroup" id="regroup" value="${vo.regroup}"> 
		<input type="hidden" name="restep" id="restep" value="${vo.restep}">
		<input type="hidden" name="reindent" id="reindent" value="${vo.reindent}">
		<input type="hidden" name="bnum" id="bnum" value="${vo.bnum}"> 
		<input type="hidden" name="rnum" id="rnum" value="${vo.rnum}">
		
		<br>		
		<button type="submit" class="btn btn-default">작성</button>
		<button type="button" class="btn btn-default" onClick="location.href='/freeboard/view?bnum=${vo.bnum}'">취소</button>		
	</form>
</body>
</html>