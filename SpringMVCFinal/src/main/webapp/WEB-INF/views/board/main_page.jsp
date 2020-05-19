<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	<!-- 날짜 타입을 문자 타입으로 변환 -->
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
<!-- 로그인 성공/실패 화면 -->
<div class="container mt-5">
	<div class="row">
		<div class="col mb-3 text-center"><h1>게시판</h1></div>
	</div>
	<div class="row">
		<div class="col-2"></div>	<!-- 1/3 -->
		<div class="col">			<!-- 2/3 -->
			<form action="${pageContext.request.contextPath }/board/main_page.do" method="get">
				<div class="row my-3">	<!-- search -->
					<div class="col"></div>
					<div class="col-5">
						<input type="text" class="form-control" name="search_word">
					</div>
					<div class="col-2">
						<input type="submit" class="btn btn-success btn-block" value="검색">
					</div>
				</div>
			</form>
			<div class="row">		<!-- 테이블(게시글 보이기) -->
				<div class="col">	<!-- 게시판 - 테이블 스키마 -->
					<table class="table table-hover">
					<thead>
						<tr style="text-align:center">
							<th>글 번호</th><th>제목</th><th>작성자</th><th>조회수</th><th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="boardData" items="${dataList }">
							<tr style="text-align:center">
								<th scope="row">${boardData.boardVo.board_no }</th>
								<td><a href="${pageContext.request.contextPath }/board/read_content_page.do?board_no=${boardData.boardVo.board_no }">${boardData.boardVo.board_title }</a></td>
								<td>${boardData.memberVo.member_nick }</td>
								<td>${boardData.boardVo.board_readcount }</td>
								<!-- 날짜를 문자로 변환 -->
								<td><fmt:formatDate value="${boardData.boardVo.board_writedate }" pattern="yy.MM.dd  hh:mm:ss"/></td>
								
								<%-- 날짜를 문자로 변환: Date type -> String
								<%
									java.util.Map<String,Object> map = (java.util.Map<String,Object>)pageContext.getAttribute("boardData");
									com.ja.freeboard.vo.BoardVo boardVo = (com.ja.freeboard.vo.BoardVo)map.get("boardVo");
									java.util.Date date = boardVo.getBoard_writedate();
									
									java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat("yy.MM.dd");
									
									String printValue = sd.format(date);
									
									// printValue 출력
								%>
								--%>
							</tr>
						</c:forEach>
					</tbody>
					</table>
				</div>
			</div>
			<div class="row mt-3">	<!-- 페이징, 글쓰기 버튼 -->
			<div class="col-3"></div>
				<div class="col-6  mr-3">	<!-- 페이징 -->
					<nav aria-label="Page navigation example">
					  <ul class="pagination">
					  	<li class="page-item<c:if test="${beginPage-1 <=0 }"> disabled</c:if>">
					  		<a class="page-link" href="${pageContext.request.contextPath }/board/main_page.do?curr_page=${beginPage-1}&search_word=${param.search_word}">Previous</a>
					  	</li>
					  	<c:forEach var="i" begin="${beginPage }" end="${endPage }">
					  		<li class="page-item<c:if test="${curr_page==i}"> active</c:if>">
					  			<a class="page-link" href="${pageContext.request.contextPath }/board/main_page.do?curr_page=${i }&search_word=${param.search_word}">${i }</a>
					  		</li>
					  	</c:forEach>				  
					    <li class="page-item<c:if test="${endPage+1 >= (totalCount-1)/10+1 }"> disabled</c:if>">
					    	<a class="page-link" href="${pageContext.request.contextPath }/board/main_page.do?curr_page=${endPage+1}&search_word=${param.search_word}">Next</a>
					    </li>
					  </ul>
					</nav>
				</div>
				<div class="col mr-1" style="padding:0px">	<!-- 글쓰기 버튼 -->
					<c:if test="${!empty sessionUser }">
						<a class="btn btn-success btn-block" href="${pageContext.request.contextPath }/board/write_content_page.do">글쓰기</a>
					</c:if>
				</div>
				<div class="col mr-3" style="padding:0px">	<!--  버튼 -->
					<a class="btn btn-success btn-block" href="${pageContext.request.contextPath }/board/main_page.do">목록</a>
				</div>
			</div>
		</div>		
		<div class="col-2"></div>	<!-- 3/3 -->
	</div>
</div>
<%--bootstrap JS 코드 --%>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>