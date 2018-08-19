<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cot :: 관리자 </title>

<%@ include file="../commons/_header.jspf" %>
<script>
    // **원하는 페이지로 이동시 검색조건, 키워드 값을 유지하기 위해 
    function adminuserlist(page){
        location.href="/admin/userlist?curPage="+page+"&searchOption=${map.searchOption}"+"&keyword=${map.keyword}";
    }

    function adminusersearch(){
		document.formId.method = "post"     
        document.formId.submit();
		}

</script>
</head>
<body>
	<%@ include file="../commons/_top.jspf" %>
	<div class="center">
	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<h3>관리자</h3>
				</div>
				<!-- 게시물 볼건지 댓글 볼건지 선택  -->
				<a href="/admin/list" style="color: black">게시물</a>&nbsp;  
				<a href="/admin/replylist" style="color: black">댓글</a>
				<a href="/admin/userlist" style="color: black">사용자</a>
				
				<!-- 검색부분 -->
				<div class="searchMenu5">
					 <form name="form1" method="post" action="${path}/admin/userlist">
				        <select name="searchOption">
				            <!-- 검색조건을 검색처리후 결과화면에 보여주기위해  c:out 출력태그 사용, 삼항연산자 -->
				            <option value="all" <c:out value="${map.searchOption == 'all'?'selected':''}"/> >이름+아이디</option>
				            <option value="name" <c:out value="${map.searchOption ==  'name'?'selected':''}"/> >이름</option>
				          
				            <option value="userid" <c:out value="${map.searchOption == 'userid'?'selected':''}"/> >아이디</option>
				           
				        </select>
				        <input name="keyword" value="${map.keyword}">
				        <input type="image" src="\resources\images\search2.png" onClick="javascript_:adminusersearch();" width="40" height="18" >
				    </form>
				    
				     ${map.countuser}명이 있습니다. 
					
				</div>
				
				<!-- 유저 테이블 -->
				<table border="2" align="center" style= "background-color: white" width="100%" >
				<tr align="center">
					<td width="15%">사용자 아이디</td>
					<td width="15%">사용자 이름</td>
					<td width="20%">사용자 이메일</td>
					<td width="30%">가입 날짜</td>
					<td width="3%">사용자 관리</td>
				</tr>
				
				<c:forEach var="row" items="${map.list}">
				<tr>
					<!-- 아이디 , 이름  -->
					<td align="center">${row.username}</td>
					<td align="center">${row.name}</td>	
					<td align="center">${row.email}</td>
					<td align="center">
					</a><fmt:formatDate value="${row.joindate}" pattern="yyyy-MM-dd a HH:mm" />
					</td>
					<td align="center">			
				
					<button type="submit"  onclick="location.href='/admin/userunregister/${row.username}'" class="btn btn-default">회원 탈퇴</button>					
					</td>
					
				</tr>
				</c:forEach>	 
			<tr height="30">
            	<td colspan="6">
            	<!-- 처음 페이지로-->
                <c:if test="${map.boardPage.curPage > 1}">
                    <a href="javascript:adminuserlist('1')">[처음]</a>
                </c:if>
                
	            <!-- 이전 블록으로 이동  -->
	            <c:if test="${map.boardPage.curBlock > 1}">
	                <a href="javascript:adminuserlist('${map.boardPage.prevPage}')">[이전]...</a>
	            </c:if>	
            	
            	<!-- 페이지 표시 -->
                <c:forEach var="num" begin="${map.boardPage.blockBegin}" end="${map.boardPage.blockEnd}">
                     <!-- 현재페이지에는 링크 제거 -->
                    <c:choose>
                        <c:when test="${num == map.boardPage.curPage}">
                            <span style="color: red">${num}</span>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:adminuserlist('${num}')">${num}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                
                <!-- 다음 블록으로 이동  -->
	            <c:if test="${map.boardPage.curBlock < map.boardPage.totBlock}">
	                <a href="javascript:adminuserlist('${map.boardPage.nextPage}')">...[다음]</a>
	            </c:if> 
                         
                <!-- 마지막 페이지 이동-->
                <c:if test="${map.boardPage.curPage <= map.boardPage.totPage}">
                    <a href="javascript:adminuserlist('${map.boardPage.totPage}')">[끝]</a>
                </c:if>
            </td>
        </tr>
		</table>
				
			</div>
		</div>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<%@ include file="../commons/_foot.jspf"%>
	</div>
</body>
</html>