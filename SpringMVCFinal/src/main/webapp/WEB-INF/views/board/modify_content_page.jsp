<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%--bootstrap CSS 코드 --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<!-- navbar -->
<jsp:include page="../commons/global_nav.jsp"></jsp:include>

<form action="${pageContext.request.contextPath }/board/write_content_process.do" method="post">
<div class="row">
	<div class="col-4"></div>	<!-- 왼쪽 여백 -->
	<div class="col mt-5">	<!-- 가운데 여백 -->
		<div class="row">
			<div class="col text-center">	<!-- 글 수정 -->
				<h1>글 수정</h1>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col">	<!-- 작성자 -->
				작성자:
			</div>
			<div class="col-10 text-left">	<!-- 닉네임 -->
				${boardContent.memberVo.member_nick }
			</div>
		</div>
		<div class="row mt-3">
			<div class="col">	<!-- 제목 -->
				제목:
			</div>
			<div class="col-10 text-left">	<!-- 제목 박스 -->
				<input type="text" name="board_title" value="${boardContent.boardVo.board_title }" class="form-control">
			</div>
		</div>
		<div class="row">
			<div class="col">	<!-- 내용 -->
					내용:
			</div>
		</div>
		<div class="row">
			<div class="col">	<!-- 내용 박스 -->
				<textarea class="form-control" rows="15" cols="40" name="board_content">${boardContent.boardVo.board_content }</textarea>
			</div>
		</div>
		<div class="row mt-1">
			<div class="col text-right">	<!-- 수정, 목록 버튼 -->
				<input type="submit" value="수정" class="btn btn-success">
				<input type="hidden" name="board_no" value="${boardContent.boardVo.board_no }">
				<a href="${pageContext.request.contextPath }/board/main_page.do"  class="btn btn-success">목록</a>
			</div>
		</div>
	</div>
	<div class="col-4"></div>	<!-- 오른쪽 여백 -->
</div>
</form>

<%--bootstrap JS 코드 --%>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
