<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cot :: 사진 갤러리</title>

<%@ include file="../commons/_header.jspf"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>

<script type="text/javascript"> 
$(function() {
$("#save").click(function(){
	var imgname = $("#imgname").val();		
				
	if(imgname==""){
		alert("제목을 입력하세요");
	}else{
			
	 $("#form1").submit(); 
	 }
	 
});

});
</script>
</head>
<body>
	<%@ include file="../commons/_top.jspf"%>
<br>
<div class="center">
	<div class="ImageGallery-writebutton">
		<h3>이미지 등록</h3>
		<br><br>
		<form id=form1 action="${path}/gallery/insert" method="post" enctype="multipart/form-data">
		        <input type="file" name="file">
		               사진제목을 입력해주세요!<input type="text" name="imgname" id="imgname">
		        <br><br>
		        <input type="button" class="btn btn-default" id="save" value="업로드" /> 
		        <button type="button" class="btn btn-default" onClick="location.href='/gallery/list'">갤러리로</button>    
		</form>  
	 </div>
	 
 <br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br> <br><br><br><br><br><br><br><br><br> <br><br><br><br>
<%@ include file="../commons/_foot.jspf"%>
 </div>
</body>
</html>