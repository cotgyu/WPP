<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cot :: WEB게시판</title>
<%@ include file="../commons/_header.jspf"%>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		listReply(); 
	});

	function listReply() {
		$.ajax({
			type : "get",
			url : "${path}/webreply/list.do?bnum=${dto.bnum}",
			success : function(result) {
				$("#listReply").html(result);
			}
		});
	}

	function openMessage(writer){  
	    window.open("/users/formmessage?writer="+writer, "메시지 보내기", "width=400, height=500,resizable=yes" );  
	}  
</script>
</head>
<body>
<%@ include file="../commons/_top.jspf"%>	
<div class="center">
	<div class="boardpadding">
		<h3>WEB게시판</h3><br>
		<table border="1" width="73%" style= "background-color: white">
			<h3>${dto.title}</h3>
			<tr>
				<td>
					<div style="display: inline;">
						&nbsp;<img src="/resources/profile/${profileimg}" width="50" height="40">
						<c:if test="${not empty sessionScope.userId}">	
						<c:if test="${sessionScope.userId ne dto.writer  }">		
						<a href="#" onClick="javascript_:openMessage('${dto.writer}');" style="color:black;">
						</c:if></c:if>
						${dto.writer}</a>
					</div>
					<div style="float: right; display: inline;" >
						작성일자 : <fmt:formatDate value="${dto.date}" pattern="yyyy-MM-dd a HH:mm" /> &emsp; 조회수 : ${dto.hit}
					</div>
				</td>
			</tr>
			
			<tr>
			<td>
				<div>
					${dto.content}
				</div>
			</td>
			</tr>
			
		</table>
				<input type="hidden" name="bnum" value="${dto.bnum}">
				<c:if test="${sessionScope.userId == dto.writer}">				
					<button type="button" class="btn btn-default" onClick="location.href='updatedetail/${dto.bnum}'">수정</button>
					<button type="button" class="btn btn-default" onClick="location.href='delete?bnum=${dto.bnum}'">삭제</button>
				</c:if>
				<button type="button" class="btn btn-default" onClick="location.href='/webboard/list'">목록</button>		
	</div><br><br>	
	
	<div class="boardpadding">			
		<form name="form2" method="post" action="/webreply/insert.do" method="${method}">
			<div style="width: 15%;">
				<br>
			
				<c:if test="${sessionScope.userId != null}">
					${sessionScope.userId}
					<textarea rows="5" cols="82" name="replytext" placeholder="댓글을 작성해주세요"></textarea>
					<input type="hidden" name="bnum" value="${dto.bnum}">
					<br>
					<button type="submit" class="btn btn-default">댓글 작성</button>
				</c:if>
			</div>
		</form>
		<br>
		<div id="listReply"></div>	
	</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="../commons/_foot.jspf"%>
</div>
</body>
</html>