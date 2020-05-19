<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- bootstrap 썼을 때 스마트폰용(반응형 웹 ) -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta charset="UTF-8">
<%--bootstrap CSS 코드 --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<!-- navbar -->
<jsp:include page="../commons/global_nav.jsp"></jsp:include>
<br><br>
<div class="container mt-5">
<form action="login_process.do" method="post">
	<div class="row">
		 <div class="col-3"></div>	<!-- 왼쪽 여백 -->
		 <div class="col">
		 	<div class="row text-center">	<!-- 로고 삽입 -->
		 		<div class="col">
		 			<img src="${pageContext.request.contextPath}/resources/login.jpg">
		 		</div>
		 	</div>
		 	<br>
		 	<div class="row">	<!-- ID -->
		 		<div class="col-1">
		 			ID
		 		</div>
		 		<div class="col">
		 			<input type="text" name="member_id" class="form-control form-control-sm" placeholder="id@email.com" onfocus="this.placeholder=''" onblur="this.placeholder='id@email.com'">
		 		</div>
		 	</div>
		 	<div class="row mt-1">	<!-- PW -->
		 		<div class="col-1">
		 			PW
		 		</div>
		 		<div class="col">
		 			<input type="password" name="member_pw" class="form-control form-control-sm" placeholder="password" onfocus="this.placeholder=''" onblur="this.placeholder='id@email.com'">
		 		</div>
		 	</div>
		 	<div class="row mt-3">	<!-- 로그인 버튼 -->
		 		<div class="col">
		 			<input type="submit" value="로그인" class="btn btn-success btn-block">
		 		</div>
		 	</div>
		 	<div class="row mt-1">	<!-- 회원가입 버튼 -->
		 		<div class="col">
		 			<a href="join_member_page.do" class="btn btn-outline-secondary btn-block">회원가입</a>
		 		</div>
		 	</div>
	    </div>
	    <div class="col-3"></div>	<!-- 오른쪽 여백 -->
  	</div>
</form>
</div>
<%--bootstrap JS 코드 --%>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>