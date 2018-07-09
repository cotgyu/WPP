<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
function showReplyModify(rnum) {
	$.ajax({
		type : "get",
		url : "${path}/noticereply/detail/" + rnum,
		success : function(result) {
			$("#modifyReply").html(result);
			// 태그.css("속성", "값")
			$("#modifyReply").css("visibility", "visible");
		}
	})
}

function showReplyComment(rnum) {
	$.ajax({
		type : "get",
		url : "${path}/noticereply/commentwrite/" + rnum,
		success : function(result) {
			$("#ReplyComment").html(result);
			// 태그.css("속성", "값")
			$("#ReplyComment").css("visibility", "visible");
		}
	})
}

function openMessage(writer){  
    window.open("/users/formmessage?writer="+writer, "메시지 보내기", "width=400, height=500,resizable=yes" );  
}  

</script>
</head>
<body>
<div style="width:73%;" >
	<div id="modifyReply"></div>    
	<div id="ReplyComment"></div>
	
        <c:forEach var="row" items="${list}">
            <div style= "margin-left: <c:out value="${30*row.reindent}"/>px; border:1px solid; background-color:white">
        	  	<input type="image" src="\resources\images\re.png" width="40" height="18" >
        	  	<c:if test="${not empty sessionScope.userId}">
        	  	<c:if test="${sessionScope.userId ne row.replyer}">		
					<a href="#" onClick="javascript_:openMessage('${row.replyer}');" style="color:black;">
				</c:if></c:if>
                ${row.replyer}</a> &emsp; <fmt:formatDate value="${row.date}" pattern="yyyy-MM-dd a HH:mm" />
                <br>
         		&emsp;
                ${row.replytext}<br>
              	<c:if test="${sessionScope.userId != null}">
                <c:if test="${sessionScope.userId == row.replyer}">
                    <button type="button" class="btn btn-default" onClick="location.href='/noticereply/delete?rnum=${row.rnum}&bnum=${row.bnum}'">삭제 </button>
                    <input type="button" class="btn btn-default" id="btnModify" value="수정" onclick="showReplyModify('${row.rnum}')"> 
                </c:if>
					<input type="button" class="btn btn-default" id="btnComment" value="댓글" onclick="showReplyComment('${row.rnum}')">
                </c:if>
            </div>
        </c:forEach>
</div>
</body>
</html>
