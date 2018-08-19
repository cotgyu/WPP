<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Cot ������</title>
<script>
	//�������� �̵� (�˻�����, Ű���� )
    function list(page){
        location.href="${path}/board/list?curPage="+page+"&searchOption-${map.searchOption}"+"&keyword=${map.keyword}";
    }
</script>
<%@ include file="./commons/_header.jspf" %>
</head>
<body>
<%@ include file="./commons/_top.jspf"%>
<div class="center">
  	<!-- Page Content �α�Խ��� ǥ��-->
	<a name="services"></a>
	<!-- �ֽŰԽù� ���� -->
     <div class="content-section-b">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6" >             
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">�ֽ� �Խù�</h2><br>  
                </div>
                
                <div class="col-lg-5 col-sm-pull-6 col-sm-6">
               	�ֽ� ��
                	<table border="2" align="center" style= "background-color: white" width="100%" >
						<c:forEach var="row3" items="${map.recentlist}">
						<tr>
							<td height="30">&nbsp;
							<!-- �Խ��� ������ ���� �з��Ͽ� �̵� -->
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
							<!-- �� �Խ��� ���� ��� -->&emsp;
							<c:choose>
							<c:when test="${row3.boardtag == 'web'}">
								<a href="/webboard/list" style="color: blue;">WEB�Խ���</a>
							</c:when>	
							
							<c:when test="${row3.boardtag == 'free'}">
								<a href="/freeboard/list" style="color: blue;">�����Խ���</a>
							</c:when>
							
							<c:when test="${row3.boardtag == 'gallery'}">
								<a href="/gallery/list" style="color: blue;">����������</a>
							</c:when>
							
							<c:when test="${row3.boardtag == 'notice'}">
								<a href="/notice/list" style="color: blue;">��������</a>
							</c:when>
								<c:when test="${row3.boardtag == 'qna'}">
							<a href="/qna/list" style="color: blue;">�����Խ���</a>
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
    <!-- /�ֽŰԽù� ����-->
	
	<!-- ���Խ��� ���� -->
    <div class="content-section-a">
		<div class="container">
	            <div class="row">
	                <div class="col-lg-5 col-sm-6">
	                    <hr class="section-heading-spacer">
	                    <div class="clearfix"></div>
	                    <h2 class="section-heading">�� ���� ������ �����ؿ�!</h2><br>
	                    <p class="lead">�ڽ��� ������ ������ �÷� ������ �����غ����?</p>               
	                </div>
	                <div class="col-lg-5 col-lg-offset-2 col-sm-6">
	                  	�α� �Խù�
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
    <!-- /���Խ��� ���� -->

	<!-- ������ ���� -->
    <div class="content-section-b">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-9" >             
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">������ �÷��ּ���!</h2><br>
                    <p class="lead">�ڽ��� ���ɺо� ������ �����غ����?</p>
                </div>
                
                <div class="col-lg-6 col-sm-pull-7 col-sm-18">
	                &emsp;&emsp;&emsp;&nbsp;�α�Խù�
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
    <!-- /������ ���� -->

	<!-- �����Խ��� ���� -->
    <div class="content-section-a">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">�����Ӱ� �ǰ��� ������!</h2><br>
                    <p class="lead">�����Ӱ� �ǰ��� ���������? </p> 
                </div>
                
                <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                	�α�Խù�
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
    <!-- /������ ���� -->
    
    <!-- ���� �Խ��� ���� -->
     <div class="content-section-b">
        <div class="container">
            <div class="row">
                <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6" >             
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">������ ���ּ���!</h2><br>
                    <p class="lead">�ñ��� ������ �������� �����ּ���! </p>
                </div>
                
                <div class="col-lg-5 col-sm-pull-6 col-sm-6">
               		�α�Խù�
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
    <!-- /�����Խ��� ���� --> 
 <%@ include file="../views/commons/_foot.jspf"%>
</div>
</body>
</html>