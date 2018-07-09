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
    function list(page){
        location.href="${path}/admin/replylist?curPage="+page+"&searchOption-${map.searchOption}"+"&keyword=${map.keyword}";
    }

    function search(){
		document.formId.method = "post"     
        document.formId.submit();
		}

</script>
</head>
<body>
<!-- 관리자모드_모든댓글 보기 -->
	<%@ include file="../commons/_top.jspf" %>
	<div class="center">
	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="page-header">
					<h3>관리자</h3>
				</div>
				<!-- 게시물 볼건지 댓글 볼건지 선택  -->
				<a href="/admin/list" style="color: black">게시물</a>&nbsp;  
				<a href="/admin/replylist" style="color: black">댓글</a>
				<a href="/admin/userlist" style="color: black">사용자</a>
				<div class="searchMenu5">
					 <form name="form1" method="post" action="${path}/admin/replylist">
				        <select name="searchOption">
				            <!-- 검색조건을 검색처리후 결과화면에 보여주기위해  c:out 출력태그 사용, 삼항연산자 -->
				            <option value="all" <c:out value="${map.searchOption == 'all'?'selected':''}"/> >작성자+내용</option>
				            <option value="replyer" <c:out value="${map.searchOption == 'replyer'?'selected':''}"/> >작성자</option>
				            <option value="replytext" <c:out value="${map.searchOption == 'replytext'?'selected':''}"/> >내용</option>
				           
				        </select>
				        <input name="keyword" value="${map.keyword}">
				        <input type="image" src="\resources\images\search2.png" onClick="javascript_:search();" width="40" height="18" >
				    </form>
				    
				     ${map.countreply}개의 댓글이 있습니다.
					
				</div>
				
				
				    <!-- 댓글 테이블  -->
				<table border="2" align="center" style= "background-color: white" width="100%" >
				<tr align="center">
					<td width="8%">댓글종류</td>
					<td width="5%">글번호</td>
					<td width="8%">작성자</td>
					<td>내용</td>
					<td width="15%">시간</td>
					<td width="6%">댓글 변경</td>
				</tr>
				<c:forEach var="row3" items="${map.list}">
				<tr>
					<td>
					<!-- 글종류  -->
					<c:choose>
					<c:when test="${row3.replytag == 'web'}">
					WEB게시판
					</c:when>	
					<c:when test="${row3.replytag == 'free'}">
					자유게시판
					</c:when>
					<c:when test="${row3.replytag == 'gallery'}">
					사진갤러리
					</c:when>
					<c:when test="${row3.replytag == 'notice'}">
					공지사항
					</c:when>
					<c:when test="${row3.replytag == 'qna'}">
					질문게시판
					</c:when>
					</c:choose>
					</td>
					
					<!-- 글번호 ,작성자  -->
					<td>${row3.rnum}</td>
					<td>${row3.replyer}</td>
					
					<td height="30">&nbsp;
					<!-- 원본 글 이동 -->
					<c:choose>
					<c:when test="${row3.replytag == 'web'}">
					<a href="${path}/webboard/view?bnum=${row3.bnum}">${row3.replytext}
					</c:when>
					
					<c:when test="${row3.replytag == 'free'}">
					<a href="${path}/freeboard/view?bnum=${row3.bnum}">${row3.replytext}
					</c:when>
				
					<c:when test="${row3.replytag == 'notice'}">
					<a href="${path}/notice/view?bnum=${row3.bnum}">${row3.replytext}
					</c:when>
					
					<c:when test="${row3.replytag == 'qna'}">
					<a href="${path}/qna/view?bnum=${row3.bnum}">${row3.replytext}
					</c:when>
					</c:choose>
					</td>
					
					<td>
					</a>&emsp;<fmt:formatDate value="${row3.date}" pattern="yyyy-MM-dd a HH:mm" />
					</td>
					
					<td>
					<c:choose>
					<c:when test="${row3.replytag == 'qna'}">
					<button type="button"  onclick="location.href='/qnareply/delete?rnum=${row3.rnum}&bnum=${row3.bnum}'" class="btn btn-default">댓글 삭제</button>
					</c:when>
					
					<c:when test="${row3.replytag == 'web'}">
					<button type="button"  onclick="location.href='/webreply/delete?rnum=${row3.rnum}&bnum=${row3.bnum}'" class="btn btn-default">댓글 삭제</button>
					</c:when>
					
					<c:when test="${row3.replytag == 'free'}">
					<button type="button"  onclick="location.href='/freereply/delete?rnum=${row3.rnum}&bnum=${row3.bnum}'" class="btn btn-default">댓글 삭제</button>
					</c:when>
					
					<c:when test="${row3.replytag == 'notice'}">
					<button type="button"  onclick="location.href='/noticereply/delete?rnum=${row3.rnum}&bnum=${row3.bnum}'" class="btn btn-default">댓글 삭제</button>
					</c:when>	
					</c:choose>
					</td>
					
				</tr>
				</c:forEach>	 
		<!-- 페이지 부분 -->
			<tr height="30">
            <td colspan="6">
            	<!-- 처음 페이지로-->
                <c:if test="${map.boardPage.curPage > 1}">
                    <a href="javascript:list('1')">[처음]</a>
                </c:if>
                
	            <!-- 이전 블록으로 이동  -->
	            <c:if test="${map.boardPage.curBlock > 1}">
	                <a href="javascript:list('${map.boardPage.prevPage}')">[이전]...</a>
	            </c:if>	
            
                <!-- 페이지 표시 -->
                <c:forEach var="num" begin="${map.boardPage.blockBegin}" end="${map.boardPage.blockEnd}">
                    <!-- 현재페이지에는 링크 제거 -->
                    <c:choose>
                        <c:when test="${num == map.boardPage.curPage}">
                            <span style="color: red">${num}</span>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:list('${num}')">${num}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                
                <!-- 다음 블록으로 이동  -->
	            <c:if test="${map.boardPage.curBlock < map.boardPage.totBlock}">
	                <a href="javascript:list('${map.boardPage.nextPage}')">...[다음]</a>
	            </c:if> 
                
	        	<!--마지막 페이지로 이동 -->
                <c:if test="${map.boardPage.curPage <= map.boardPage.totPage}">
                    <a href="javascript:list('${map.boardPage.totPage}')">[끝]</a>
                </c:if>
            </td>
        </tr>
		</table>
				
			</div>
		</div>
	</div>
	<!-- 바닥부분 -->
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<%@ include file="../commons/_foot.jspf"%>
	</div>
</body>
</html>