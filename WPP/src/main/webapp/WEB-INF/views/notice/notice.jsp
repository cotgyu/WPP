<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cot :: 공지사항</title>

<%@ include file="../commons/_header.jspf"%>
<script>
    function list(page){
        location.href="${path}/notice/list?curPage="+page+"&searchOption-${map.searchOption}"+"&keyword=${map.keyword}";
    }

	function search(){
		document.formId.method = "post"     
        document.formId.submit();
		}

	function openMessage(writer){  
	    window.open("/users/formmessage?writer="+writer, "메시지 보내기", "width=400, height=500,resizable=yes" );  
	}  
    
</script>
</head>
<body>
<%@ include file="../commons/_top.jspf"%>
<br>
<div class="center">
	<div class="boardpadding">
		<br>
		<h3>공지사항</h3>
	</div>
		<br><br><br><br>
	<div class="searchMenu">
		 <form name="form1" method="post" action="${path}/notice/list">
	        <select name="searchOption">   
	            <option value="all" <c:out value="${map.searchOption == 'all'?'selected':''}"/> >제목+이름+제목</option>
	            <option value="writer" <c:out value="${map.searchOption == 'writer'?'selected':''}"/> >이름</option>
	            <option value="content" <c:out value="${map.searchOption == 'content'?'selected':''}"/> >내용</option>
	            <option value="title" <c:out value="${map.searchOption == 'title'?'selected':''}"/> >제목</option>
	        </select>
	        <input name="keyword" value="${map.keyword}">
	        <input type="image" src="\resources\images\search2.png" onClick="javascript_:search();" width="40" height="18" >
	    </form>
	     ${map.count}개의 게시물이 있습니다.
		<c:if test="${sessionScope.userId =='관리자'}">
			<button type="button" class="btn btn-default" onClick="location.href='write'">글쓰기</button>
		</c:if>
	</div>
		<div class="boardpadding">
			<table border="2" width="75%"  style= "background-color: white">
				<tr height="40" align="center">
					<td width="30">번호</td>
					<td width="600">제목</td>
					<td width="120">작성자</td>
					<td width="160">작성일</td>
					<td width="60">조회수</td>
				</tr>
				
				<c:forEach var="row" items="${map.list}">
				<tr height="40" align="center">
					<td >${row.bnum}</td>
					
					<td align="left">&nbsp;<a href="${path}/notice/view?bnum=${row.bnum}">${row.title}
		                   <c:if test="${row.recnt > 0}">
		                   <span style="color: red;">(${row.recnt})</span>
		                   </c:if></a>
					</td>						
					<td>
						<c:if test="${not empty sessionScope.userId}">
						<c:if test="${sessionScope.userId ne row.writer}">
						<a href="#" onClick="javascript_:openMessage('${row.writer}');" style="color:black;">
						</c:if></c:if>
						${row.writer}</a>
					</td>
					<td>
						<fmt:formatDate value="${row.date}" pattern="yyyy-MM-dd a HH:mm" />
					</td>
					<td>${row.hit}</td>
				</tr>
				</c:forEach>
				
				<tr tr height="30">
		            <td colspan="6">
		            	<c:if test="${map.boardPage.curPage > 1}">
                    	<a href="javascript:list('1')">[처음]</a>
                		</c:if>
                 
	                	<c:if test="${map.boardPage.curBlock > 1}">
	                    	<a href="javascript:list('${map.boardPage.prevPage}')">[이전]...</a>
	                	</c:if>
		            		
		                <c:forEach var="num" begin="${map.boardPage.blockBegin}" end="${map.boardPage.blockEnd}"> 
		                    <c:choose>
		                        <c:when test="${num == map.boardPage.curPage}">
		                            <span style="color: red">${num}</span>&nbsp;
		                        </c:when>
		                        <c:otherwise>
		                            <a href="javascript:list('${num}')">${num}</a>&nbsp;
		                        </c:otherwise>
		                    </c:choose>
		                </c:forEach>
		                
		                <c:if test="${map.boardPage.curBlock < map.boardPage.totBlock}">
	                   		<a href="javascript:list('${map.boardPage.nextPage}')">...[다음]</a>
	              		</c:if> 
	
		                <c:if test="${map.boardPage.curPage < map.boardPage.totPage}">
		                    <a href="javascript:list('${map.boardPage.totPage}')">[끝]</a>
		                </c:if>
		            </td>
		        </tr>
			</table>
	</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="../commons/_foot.jspf"%>
</div>
</body>
</html>