<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
//검색
function search(){
	document.formId.method = "post"     
    document.formId.submit();
	}

//쪽지 창 띄우기
function openMessage(writer){  
    window.open("/users/formmessage?writer="+writer, "메시지 보내기", "width=400, height=500,resizable=yes" );  
}  
</script>
<style>
.Ilist{
padding-left: 200px;
}
.Ilist ul li {
  position:relative; 
  display:inline-block; 
  width:300px; 
  height:305px;
  margin:25px; 
  padding:5px;   
  box-shadow:0 0 5px rgba(0,0,0,.35);
}
.Ilist ul li div {
  font-size:130%;
  position:absolute;
  height:0; 
  width:300px;
  background:rgba(0,0,0,.20); 
  overflow:hidden;
  bottom:00px; 
  left:5px;
  padding: 0 px;
  line-height:30px;
  color:#fff;
  transition:height 1s;
}
.Ilist ul li:hover div {
  height:30px;
}

body{
background: #F3F3F3;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cot :: 사진 갤러리</title>

<%@ include file="../commons/_header.jspf"%>
<script>
//이미지 클릭시 원본으로 보여주기 (이미지 상세보기)
function imgclick(img){ 
	 img1= new Image(); 
	 img1.src=(img); 
	 imgControll(img); 
	} 
	  
function imgControll(img){ 
	 if((img1.width!=0)&&(img1.height!=0)){ 
	    viewImage(img); 
	  } 
	  else{ 
	     controller="imgControll('"+img+"')"; 
	     intervalID=setTimeout(controller,20); 
	  } 
	}
function viewImage(img){ 
	 W=img1.width; 
	 H=img1.height; 
	 O="width="+W+",height="+H+",scrollbars=yes"; 
	 imgWin=window.open("","",O); 
	 imgWin.document.write("<html><head><title>:: 이미지상세보기 ::</title></head>");
	 imgWin.document.write("<body topmargin=0 leftmargin=0>");
	 imgWin.document.write("<img src="+img+" onclick='self.close()' style='cursor:pointer;' title ='클릭하시면 창이 닫힙니다.'>");
	 imgWin.document.close();
	}


function list(page){
	 location.href="${path}/gallery/list?curPage="+page+"&searchOption-${map.searchOption}"+"&keyword=${map.keyword}";
	    }
</script>
</head>
<body>
<%@ include file="../commons/_top.jspf"%>
<div class="center"> 
<br><br>
	<div class="ImageGallery-writebutton">
		<h3>사진을 등록해주세요!!</h3>
		<h5> 카메라버튼을 통해 사진을 등록할 수 있습니다.</h5>
		<h5>클릭시 원본 크기로 볼 수 있습니다.</h5>
		<c:if test="${not empty sessionScope.userId}">
			<input type="image" src="\resources\images\upcamera.png" onClick="location.href='write'" width="110" height="50" >
		</c:if>
		<br><br><br>
		
		 <form name="form1" method="post" action="${path}/gallery/list">
	        <select name="searchOption">
	            <!-- 검색조건을 검색처리후 결과화면에 보여주기위해  c:out 출력태그 사용, 삼항연산자 -->
	            <option value="all" <c:out value="${map.searchOption == 'all'?'selected':''}"/> >제목+이름</option>
	            <option value="imgwriter" <c:out value="${map.searchOption == 'imgwriter'?'selected':''}"/> >이름</option>
	            <option value="imgname" <c:out value="${map.searchOption == 'imgname'?'selected':''}"/> >제목</option>
	        </select>
	        <input name="keyword" value="${map.keyword}">
	        <input type="image" src="\resources\images\search2.png" onClick="javascript_:search();" width="40" height="18" >
	    </form>
	  	${map.count}개의 게시물이 있습니다. 
	  	<button type="button" class="btn btn-default" onClick="location.href='/gallery/orderbyup'">추천 순</button>
  </div>
<br>   
	<div class="Ilist">
		<ul>
			<c:forEach items="${map.ilist}" var="map">
				
				<li><a href="javascript:imgclick('/resources/uploads/${map.imgfile}');"><img src="/resources/uploads/${map.imgfile}" width="300" height="300" ></a>
				<div>&nbsp;${map.imgname}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${map.imgdate}" pattern="yyyy-MM-dd a HH:mm" /></div>
				<c:if test="${empty sessionScope.userId}">
				<input type="image" src="\resources\images\up.png" width="20" height="20">
				</c:if>
				<!-- 회원만 추천할 수 있게 처리 -->
				<c:if test="${not empty sessionScope.userId}">
				<input type="image" src="\resources\images\up.png" onClick="location.href='/gallery/up/${map.imgid}'" width="20" height="20">
				</c:if>
				&nbsp;&nbsp;${map.imgup} &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				
				작성자: 
				<c:if test="${not empty sessionScope.userId}">
				<c:if test="${sessionScope.userId ne map.imgwriter}">
				<a href="#" onClick="javascript_:openMessage('${map.imgwriter}');" style="color:black;">
				</c:if></c:if>
				${map.imgwriter}</a>
				<c:if test="${sessionScope.userId == map.imgwriter}">
				<button type="button" class="btn btn-default" onClick="location.href='/gallery/delete/${map.imgid}'">삭제</button>
				</c:if>
				</li>
			</c:forEach>
		</ul>
		<div>       
					
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
	                    <a href="javascript:list('${map.boardPage.totPage}')">[끝] </a>   
	                </c:if>
	    </div>
      </div>     
<br><br><br><br><br><br><br><br><br>
<%@ include file="../commons/_foot.jspf"%>
</div>
</body>
</html>