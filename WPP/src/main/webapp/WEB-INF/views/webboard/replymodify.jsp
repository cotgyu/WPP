<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<br>댓글 수정
	<form name="form3" method="post" action="/webreply/update" method="${method}">
		<textarea name="replytext" id="replytext" rows="5" cols="82">${vo.replytext}</textarea>	
		<input type="hidden" name="bnum" id="bnum" value="${vo.bnum}"> 
		<input type="hidden" name="rnum" id="rnum" value="${vo.rnum}">
		<div>
			<button type="submit" class="btn btn-default">작성</button>
			<button type="button" class="btn btn-default" onClick="location.href='/webboard/view?bnum=${vo.bnum}'">취소</button>
		</div>
	</form>
</body>
</html>