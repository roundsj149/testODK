<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="#">Home</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNavDropdown">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.request.contextPath }/board/main_page.do">Freeboard
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Shopping
					Mall</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Auction</a></li>
		</ul>
	</div>
	<div class="col">
		<c:choose>
			<c:when test="${!empty sessionUser }">
				<ul class="navbar-nav" style="list-style: none">
					<li class="nav-item dropdown ml-auto"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">
							${sessionUser.member_nick } 님 </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item"
								href="${pageContext.request.contextPath }/member/logout_process.do">Logout</a>
							<a class="dropdown-item" href="#">My Page</a> <a
								class="dropdown-item" href="#">Sign Out</a>
						</div></li>
					<%-- 브라우저에서 절대경로는 호스트 주소 다음(ex. http://localhost:8181/)이므로 logout 처리 시 경로 문제됨(logout은 member폴더!) --%>
					<%-- 1) ../: 현재 폴더의 상위 폴더 - 상대경로--%>
					<%-- 2) /freeboard/: 현재 폴더의 상위 폴더 - 절대경로(링크 쪽은 대부분 절대경로 사용!!! forwarding이나 redirect는 상대경로로 해도 됨) -> 패키지명 바꾸면 바뀔 수 있으므로 하드코딩x--%>
					<%-- 3) contextPath --%>
					<li class="mr-5"></li>
					<!-- 없으면 dropdown-menu 선택 시 가로 스크롤바 생김 -->
				</ul>
			</c:when>
			<c:otherwise>
				<ul class="navbar-nav">
					<li class="nav-item ml-auto">
						<a class="nav-link" style="text-align: right" href="${pageContext.request.contextPath }/member/login_page.do">Login</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" style="text-align: right" href="${pageContext.request.contextPath }/member/join_member_page.do">Sign In</a>
					</li>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>
</nav>
