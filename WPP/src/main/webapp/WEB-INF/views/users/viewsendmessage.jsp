<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cot :: 쪽지함</title>
<%@ include file="../commons/_header.jspf" %>
<script>
//메세지 보내기
function openMessage(writer){  
    window.open("/users/formmessage?writer="+writer, "메시지 보내기", "width=400, height=500,resizable=yes" );  
}  
</script>
</head>
<body>
<%@ include file="../commons/_top.jspf" %>
<div class="center">
	<div class="boardpadding">
	<br><br><br>
	<h3><a href="/users/message" style="color:gray">받은 쪽지함</a>  보낸 쪽지함</h3>
		<h4 style="color:grey">보낸 사람 아이디를 클릭하여 답장을 보낼 수 있습니다.</h4>

		<table border="2" width="75%" height="50%" style= "background-color: white">
			<tr align="center">
				<td width="8%">받는 사람</td>
				<td width="45%">내용</td>
				<td width="10%">날짜</td>
			</tr>
			
			<c:forEach var="row" items="${message}">
			<tr align="center">
				<td>
				<c:if test="${not empty sessionScope.userId}">
				<c:if test="${sessionScope.userId ne row.receiver }">
				<a href="#" onClick="javascript_:openMessage('${row.receiver}');" style="color:black;"></c:if></c:if>${row.receiver}</a></td>
				<td align="left">&nbsp; ${row.content}</td>
				<td><fmt:formatDate value="${row.date}" pattern="yyyy-MM-dd a HH:mm" /></td>
			</tr>
			</c:forEach>
		</table>
	</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="../commons/_foot.jspf"%>	
</div>
</body>
</html>