<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cot :: 자유 게시판</title>

<%@ include file="../commons/_header.jspf"%>
<script>
    //페이지로 이동 (검색조건, 키워드 )
    function list(page){
        location.href="${path}/freeboard/list?curPage="+page+"&searchOption-${map.searchOption}"+"&keyword=${map.keyword}";
    }
    //키워드 검색
	function search(){
		document.formId.method = "post"     
        document.formId.submit();
		}
	//쪽지 창 띄우기
	function openMessage(writer){  
	    window.open("/users/formmessage?writer="+writer, "메시지 보내기", "width=400, height=500,resizable=yes" );  
	}  
</script>
</head>
<body>
<%@ include file="../commons/_top.jspf"%>
<br>
<div class="center">
	<!-- 인기 게시판  -->
	<div class="popboard">
		<table border="2"  width="75%"  height="30" style= "background-color: white">
		<h3>[자유]인기게시판</h3>	
			<c:forEach var="row2" items="${map.poplist}" varStatus="status">
				<td width="300" height="40">&nbsp;<a href="${path}/freeboard/view?bnum=${row2.bnum}">${status.count}.&nbsp;&nbsp;${row2.title}
				<!-- ** 댓글이 있으면 게시글 이름 옆에 출력하기 -->
		        <c:if test="${row2.recnt > 0}">
		        <span style="color: red;">(${row2.recnt})</span>
		        </c:if></a>
		        </td>
			</c:forEach>
		</table>
	</div>
	<br><br><br>
	
	<!-- 검색메뉴 -->
	<div class="searchMenu">
		 <form name="form1" method="post" action="${path}/freeboard/list">
	     	<select name="searchOption">
	            <!-- 검색조건을 검색처리후 결과화면에 보여주기위해  c:out 출력태그 사용, 삼항연산자 -->
	            <option value="all" <c:out value="${map.searchOption == 'all'?'selected':''}"/> >제목+이름+내용</option>
	            <option value="writer" <c:out value="${map.searchOption == 'writer'?'selected':''}"/> >이름</option>
	            <option value="content" <c:out value="${map.searchOption == 'content'?'selected':''}"/> >내용</option>
	            <option value="title" <c:out value="${map.searchOption == 'title'?'selected':''}"/> >제목</option>
	        </select>
	    <input name="keyword" value="${map.keyword}">
	    <input type="image" src="\resources\images\search2.png" onClick="javascript_:search();" width="40" height="18" >
	    </form>
    ${map.count}개의 게시물이 있습니다.
        
		<c:if test="${not empty sessionScope.userId}">
		<button type="button" class="btn btn-default" onClick="location.href='write'">글쓰기</button>
		</c:if>
	</div>
	
	<!-- 게시물 테이블 -->
	<div class="boardpadding">
		<table border="2" width="75%"  style= "background-color: white">
			<tr height="40" align="center">
				<td width="30">번호</td>
				<td width="30">태그</td>
				<td width="600">제목</td>
				<td width="120">작성자</td>
				<td width="160">작성일</td>
				<td width="60">조회수</td>
			</tr>
			<c:forEach var="row" items="${map.list}">
				<tr height="40" align="center">
					<td >${row.bnum}</td>
					<td>${row.tag}</td>
				
					<td align="left">&nbsp;<a href="${path}/freeboard/view?bnum=${row.bnum}">${row.title}
						<!-- 댓글 수 표시-->
	                    <c:if test="${row.recnt > 0}">
	                    <span style="color: red;">(${row.recnt})</span>
	                    </c:if>	
					</a>
					</td>
						
					<td>
					<!-- 세션아이디값과 비교하여 메세지 링크 생성 -->
					<c:if test="${not empty sessionScope.userId}">
					<c:if test="${sessionScope.userId ne row.writer  }">
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
			 <!-- 페이지 부분 -->
			 <tr tr height="30">
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
	                    <!-- 현재 페이지는 링크 제거 -->
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
		                         
	                <!-- 마지막 페이지로 이동 -->
	                <c:if test="${map.boardPage.curPage < map.boardPage.totPage}">
	                    <a href="javascript:list('${map.boardPage.totPage}')">[끝]</a>
	                </c:if>
	            </td>
	        </tr>
		</table>
	</div>
	<br><br><br><br><br><br><br><br><br>
	<%@ include file="../commons/_foot.jspf"%>
	</div>
</body>
</html>