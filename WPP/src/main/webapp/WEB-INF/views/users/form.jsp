<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cot :: 회원가입</title>
<%@ include file="../commons/_header.jspf" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>

<script type="text/javascript">
//아이디 중복 체크
function checkId(){
	  var prmId = $('#userId').val();
	  //공백일시 경고창
	  if($("#userId").val() == ''){
		  alert('ID를 입력해주세요.'); 
	  	  return;
	  	  }
	  $.ajax({
	     type : 'POST',  
	     data:"prmId="+ prmId,
	     dataType : 'text',
	     url : '/users/checkId.do',  
	     success : function(rData, textStatus, xhr) {
	      var chkRst = rData;
	      //컨트롤러의 checkId에서 디비에 회원가입하고자하는 아이디를 조회하여 같은 아이디가있는 만큼 카운트한다. 결과가 0인 경우는 중복되는 아이디가 없다는 소리니깐 사용가능 , 아닐 시 중복으로 불가
	      if(chkRst == 0){
	       alert("사용 가능 합니다.");
	       $("#idChk").val('Y');
	      }
	      else{
	       alert("아이디중복 입니다.");
		   $("#userId").val('');
	       $("#idChk").val('N');
	     }
	     },
	     error : function(xhr, status, e) {  
	      alert(e);
	     }
	  });  
	 }
//아이디 체크를 해야 회원가입을 할 수 있음.
function join(){
	  var frm = document.companyForm; 
	  if($("#idChk").val() == 'N'){
		  alert('ID체크를 해주세요.');
	  return;
	  }
	  else{
		$("#form1").submit(); 
	  }
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
					<h3>회원가입</h3>
				</div>
				
				<c:choose>
				<c:when test="${empty user.userId}">
					<c:set var="method" value="post" />
				</c:when>
				<c:otherwise>
					<c:set var="method" value="post" />
				</c:otherwise>
				</c:choose>
				
				<form:form id="form1" modelAttribute="user" cssClass="form-horizontal" action="/users/create" method="${method}">
					<div class="control-group">
						<label class="control-label" for="userId">사용자 아이디[4~12영어로 입력해주세요.]</label>
						<!-- idChk 기본값은 N 중복체크를 해야 Y로 바꿀 수 있음 -->
						<input type="hidden" id="idChk" value="N" />	
						
						<div class="controls">				
							<form:input path="userId" id="userId"/><input type="button" class="btn btn-default" value="중복 체크" onclick="javascript:checkId();" />
							<br>
							<form:errors path="userId" cssClass="error" style="color:red"/>							
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="password">비밀번호[4~12문자로 입력해주세요.]</label>
						<div class="controls">
							<form:password path="password" />
							<br>
							<form:errors path="password" cssClass="error" style="color:red"/>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="name">이름</label>
						<div class="controls">
							<form:input path="name"/>
							<br>
							<form:errors path="name" cssClass="error" style="color:red"/>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="email">이메일[이메일 형식으로 입력해주세요.]</label>
						<div class="controls">
							<form:input path="email"/>
							<br>
							<form:errors path="email" cssClass="error" style="color:red"/>
						</div>
					</div>
					
					<div class="control-group">
						<div class="controls">
							<button type="button" class="btn btn-default" onclick="javascript:join();">회원가입</button>
						</div>
					</div>				
				</form:form> 
			</div>
		</div>
	</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<%@ include file="../commons/_foot.jspf"%>
</div>
</body>
</html>