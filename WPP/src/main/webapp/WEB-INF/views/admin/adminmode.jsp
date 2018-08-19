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
	//관지라 게시물 리스트, 검색
function adminlist(page){
    location.href="/admin/list?curPage="+page+"&searchOption-${map.searchOption}"+"&keyword=${map.keyword}";
}

function adminsearch(){
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
					 <form name="form1" method="post" action="${path}/admin/list">
				        <select name="searchOption">
				            <!-- 검색조건을 검색처리후 결과화면에 보여주기위해  c:out 출력태그 사용, 삼항연산자 -->
				            <option value="all" <c:out value="${map.searchOption == 'all'?'selected':''}"/> >이름+제목</option>
				            <option value="writer" <c:out value="${map.searchOption == 'writer'?'selected':''}"/> >이름</option>
				          
				            <option value="title" <c:out value="${map.searchOption == 'title'?'selected':''}"/> >제목</option>
				           
				        </select>
				        <input name="keyword" value="${map.keyword}">
				        <input type="image" src="\resources\images\search2.png" onClick="javascript_:adminsearch();" width="40" height="18" >
				    </form>
				    
				     ${map.count}개의 게시물이 있습니다.
					
				</div>
				
				<!-- 게시물 테이블 -->
				<table border="2" align="center" style= "background-color: white" width="100%" >
				<tr align="center">
					<td width="8%">글종류</td>
					<td width="5%">글번호</td>
					<td width="8%">작성자</td>
					<td>제목</td>
					<td width="15%">시간</td>
					<td width="12.6%">글 변경</td>
				</tr>
				
				<c:forEach var="row3" items="${map.list}">
				<tr>
					<td>
					<!-- 글종류  -->
					<c:choose>
					<c:when test="${row3.boardtag == 'web'}">
					WEB게시판
					</c:when>	
					<c:when test="${row3.boardtag == 'free'}">
					자유게시판
					</c:when>
					<c:when test="${row3.boardtag == 'gallery'}">
					사진갤러리
					</c:when>
					<c:when test="${row3.boardtag == 'notice'}">
					공지사항
					</c:when>
					<c:when test="${row3.boardtag == 'qna'}">
					질문게시판
					</c:when>
					</c:choose>
					</td>
					
					<!-- 글번호 ,작성자  -->
					<td>${row3.bnum}</td>
					<td>${row3.writer}</td>	
					<td height="30">&nbsp;
					<c:choose>
					<c:when test="${row3.boardtag == 'web'}">
					<a href="${path}/webboard/view?bnum=${row3.bnum}">${row3.title}
					</c:when>
					
					<c:when test="${row3.boardtag == 'free'}">
					<a href="${path}/freeboard/view?bnum=${row3.bnum}">${row3.title}
					</c:when>
					
					<c:when test="${row3.boardtag == 'gallery'}">
					<a href="/gallery/list">${row3.title}
					</c:when>
					
					<c:when test="${row3.boardtag == 'notice'}">
					<a href="${path}/notice/view?bnum=${row3.bnum}">${row3.title}
					</c:when>
					
					<c:when test="${row3.boardtag == 'qna'}">
					<a href="${path}/qna/view?bnum=${row3.bnum}">${row3.title}
					</c:when>
					</c:choose>
					</td>
					
					<td>
					</a>&emsp;<fmt:formatDate value="${row3.date}" pattern="yyyy-MM-dd a HH:mm" />
					</td>
					
					<td>
					<c:choose>
					<c:when test="${row3.boardtag == 'qna'}">
					<button type="button"  onclick="location.href='/qna/delete?bnum=${row3.bnum}'" class="btn btn-default">글 삭제</button>
					<button type="button"  onclick="location.href='/qna/updatedetail/${row3.bnum}'" class="btn btn-default">글 수정</button>
					</c:when>
					
					<c:when test="${row3.boardtag == 'web'}">
					<button type="button"  onclick="location.href='/webboard/delete?bnum=${row3.bnum}'" class="btn btn-default">글 삭제</button>
					<button type="button"  onclick="location.href='/webboard/updatedetail/${row3.bnum}'" class="btn btn-default">글 수정</button>
					</c:when>
					
					<c:when test="${row3.boardtag == 'free'}">
					<button type="button"  onclick="location.href='/freeboard/delete?bnum=${row3.bnum}'" class="btn btn-default">글 삭제</button>
					<button type="button"  onclick="location.href='/freeboard/updatedetail/${row3.bnum}'" class="btn btn-default">글 수정</button>
					</c:when>
					
					<c:when test="${row3.boardtag == 'notice'}">
					<button type="button"  onclick="location.href='/notice/delete?bnum=${row3.bnum}'" class="btn btn-default">글 삭제</button>
					<button type="button"  onclick="location.href='/notice/updatedetail/${row3.bnum}'" class="btn btn-default">글 수정</button>
					</c:when>
					
					<c:when test="${row3.boardtag == 'gallery'}">
					<button type="button"  onclick="location.href='/gallery/delete/${row3.bnum}'" class="btn btn-default">글 삭제</button>
					</c:when>
					</c:choose>
					</td>
				</tr>
				</c:forEach>	 
			<tr height="30">
            	<td colspan="6">
            	<!-- 처음 페이지로-->
                <c:if test="${map.boardPage.curPage > 1}">
                    <a href="javascript:adminlist('1')">[처음]</a>
                </c:if>
                
	            <!-- 이전 블록으로 이동  -->
	            <c:if test="${map.boardPage.curBlock > 1}">
	                <a href="javascript:adminlist('${map.boardPage.prevPage}')">[이전]...</a>
	            </c:if>	
            	
            	<!-- 페이지 표시 -->
                <c:forEach var="num" begin="${map.boardPage.blockBegin}" end="${map.boardPage.blockEnd}">
                     <!-- 현재페이지에는 링크 제거 -->
                    <c:choose>
                        <c:when test="${num == map.boardPage.curPage}">
                            <span style="color: red">${num}</span>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:adminlist('${num}')">${num}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                
                <!-- 다음 블록으로 이동  -->
	            <c:if test="${map.boardPage.curBlock < map.boardPage.totBlock}">
	                <a href="javascript:adminlist('${map.boardPage.nextPage}')">...[다음]</a>
	            </c:if> 
                         
                <!-- 마지막 페이지 이동-->
                <c:if test="${map.boardPage.curPage <= map.boardPage.totPage}">
                    <a href="javascript:adminlist('${map.boardPage.totPage}')">[끝]</a>
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