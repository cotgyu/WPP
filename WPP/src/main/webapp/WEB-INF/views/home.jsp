<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Cot 페이지</title>
<script>
	//페이지로 이동 (검색조건, 키워드 )
    function list(page){
        location.href="${path}/board/list?curPage="+page+"&searchOption-${map.searchOption}"+"&keyword=${map.keyword}";
    }
</script>
<%@ include file="./commons/_header.jspf" %>
</head>
<body>
<%@ include file="./commons/_top.jspf"%>
<div class="center">
  	<!-- Page Content 인기게시판 표시-->
	<a name="services"></a>
	<!-- 최신게시물 영역 -->
     <div class="content-section-b">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6" >             
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">최신 게시물</h2><br>  
                </div>
                
                <div class="col-lg-5 col-sm-pull-6 col-sm-6">
               	최신 글
                	<table border="2" align="center" style= "background-color: white" width="100%" >
						<c:forEach var="row3" items="${map.recentlist}">
						<tr>
							<td height="30">&nbsp;
							<!-- 게시판 종류에 따라 분류하여 이동 -->
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
							
							</a>&emsp;
							<fmt:formatDate value="${row3.date}" pattern="yyyy-MM-dd a HH:mm" />
							<!-- 글 게시판 종류 출력 -->&emsp;
							<c:choose>
							<c:when test="${row3.boardtag == 'web'}">
								<a href="/webboard/list" style="color: blue;">WEB게시판</a>
							</c:when>	
							
							<c:when test="${row3.boardtag == 'free'}">
								<a href="/freeboard/list" style="color: blue;">자유게시판</a>
							</c:when>
							
							<c:when test="${row3.boardtag == 'gallery'}">
								<a href="/gallery/list" style="color: blue;">사진갤러리</a>
							</c:when>
							
							<c:when test="${row3.boardtag == 'notice'}">
								<a href="/notice/list" style="color: blue;">공지사항</a>
							</c:when>
								<c:when test="${row3.boardtag == 'qna'}">
							<a href="/qna/list" style="color: blue;">질문게시판</a>
							</c:when>
							</c:choose>
							</td>
						</tr>
						</c:forEach>
					</table>             
                </div>
            </div>
        </div>
    </div>
    <!-- /최신게시물 영역-->
	
	<!-- 웹게시판 영역 -->
    <div class="content-section-a">
		<div class="container">
	            <div class="row">
	                <div class="col-lg-5 col-sm-6">
	                    <hr class="section-heading-spacer">
	                    <div class="clearfix"></div>
	                    <h2 class="section-heading">웹 관련 정보를 공유해요!</h2><br>
	                    <p class="lead">자신이 공부한 내용을 올려 사람들과 공유해볼까요?</p>               
	                </div>
	                <div class="col-lg-5 col-lg-offset-2 col-sm-6">
	                  	인기 게시물
						<table border="2" align="center" width="100%" style= "background-color: white">					
							<c:forEach var="row" items="${map.poplist}">
							<tr>					
								<td height="30">&nbsp;<a href="${path}/webboard/view?bnum=${row.bnum}">${row.title}
							    <c:if test="${row.recnt > 0}">
							    	<span style="color: red;">(${row.recnt})</span>
							    </c:if>	
								</a>&emsp;
								<fmt:formatDate value="${row.date}" pattern="yyyy-MM-dd a HH:mm"/>
								</td>
							</tr>
							</c:forEach>
						</table>			
	                </div>
	            </div>
	        </div>
    </div>
    <!-- /웹게시판 영역 -->

	<!-- 갤러리 영역 -->
    <div class="content-section-b">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-9" >             
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">사진을 올려주세요!</h2><br>
                    <p class="lead">자신의 관심분야 사진을 공유해볼까요?</p>
                </div>
                
                <div class="col-lg-6 col-sm-pull-7 col-sm-18">
	                &emsp;&emsp;&emsp;&nbsp;인기게시물
	                <ul>
						<c:forEach items="${map.popImglist}" var="map">						
							<li>						
								<a href="/gallery/list"><img src="/resources/uploads/${map.imgfile}" width="110" height="170"></a>					
							</li>
						</c:forEach>
					</ul>               
                </div>
            </div>
        </div>
    </div>
    <!-- /갤러리 영역 -->

	<!-- 자유게시판 영역 -->
    <div class="content-section-a">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">자유롭게 의견을 나눠요!</h2><br>
                    <p class="lead">자유롭게 의견을 나눠볼까요? </p> 
                </div>
                
                <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                	인기게시물
                    <table border="2" align="center" style= "background-color: white" width="100%" >
						<c:forEach var="row2" items="${map.popFlist}">
							<tr>
								<td height="30">&nbsp;<a href="${path}/freeboard/view?bnum=${row2.bnum}">${row2.title}
							    <c:if test="${row2.recnt > 0}">
							    <span style="color: red;">(${row2.recnt})</span>
							    </c:if>	
								</a>&emsp;
								<fmt:formatDate value="${row2.date}" pattern="yyyy-MM-dd a HH:mm" />
								</td>
							</tr>
						</c:forEach>
					</table>
                </div>
            </div>
        </div>
    </div>
    <!-- /갤러리 영역 -->
    
    <!-- 질문 게시판 영역 -->
     <div class="content-section-b">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6" >             
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">질문을 해주세요!</h2><br>
                    <p class="lead">궁금한 사항을 질문으로 남겨주세요! </p>
                </div>
                
                <div class="col-lg-5 col-sm-pull-6 col-sm-6">
               		인기게시물
                    <table border="2" align="center" style= "background-color: white" width="100%" >
						<c:forEach var="row2" items="${map.popQnalist}">
						<tr>
							<td height="30">&nbsp;<a href="${path}/qna/view?bnum=${row2.bnum}">${row2.title}
								<c:if test="${row2.recnt > 0}">
						        	<span style="color: red;">(${row2.recnt})</span>
						        </c:if>	
								</a>&emsp;
								<fmt:formatDate value="${row2.date}" pattern="yyyy-MM-dd a HH:mm" />
							</td>
						</tr>
						</c:forEach>
					</table>       
                </div>
            </div>
        </div>
    </div>
    <!-- /질문게시판 영역 --> 
 <%@ include file="../views/commons/_foot.jspf"%>
</div>
</body>
</html>