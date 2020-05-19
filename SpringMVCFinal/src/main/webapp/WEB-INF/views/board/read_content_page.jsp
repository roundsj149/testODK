<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%--bootstrap CSS 코드 --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script>
	

	function refreshReplyList() {
		var boardNo = ${boardContent.boardVo.board_no };
		
		var xmlhttp = new XMLHttpRequest();
		
		xmlhttp.onreadystatechange = function () {
			
			if(xmlhttp.readyState==4 && xmlhttp.status == 200) {
				var resultData = JSON.parse(xmlhttp.responseText);	// 문자열을 파싱해서 javascript object로 전달해줌
				// refresh를 위해 박스 안의 내용 모두 삭제하고 다시 보여줌
				// replyBox.innerHTML = ""; -> 이렇게 해도 되는데 멋없어서 for문으로 지워봄
				var replyBox = document.getElementById("reply_box");	// 박스 태그 가져옴
				var length = replyBox.childNodes.length;
				
				for(var i=0; i< length; i++) {
					replyBox.removeChild(replyBox.childNodes[0]);
				}
				
				// 자바스크립트로 동적 UI 꾸미기
				for(var data of resultData) {	// data: hashMap형태
					
					var boxRow1 = document.createElement("div");
					boxRow1.setAttribute("class","row");	// 속성 설정
					var boxRow1_col1 = document.createElement("div");
					boxRow1_col1.setAttribute("class","col-8");
					var boxNick = document.createElement("div");
					boxNick.setAttribute("class","alert alert-secondary");
					
					boxNick.innerText = data.memberVo.member_nick;
					
					boxRow1_col1.appendChild(boxNick);
					boxRow1.appendChild(boxRow1_col1);
					
					// 날짜
					var boxRow1_col2 = document.createElement("div");
					boxRow1_col2.setAttribute("class","col");
					
					var boxWriteDate = document.createElement("div");
					boxWriteDate.setAttribute("class","alert alert-secondary");
					
					// 날짜: millisecond에서 년 월 일로 바꾸기
					var milliseconds = data.replyVo.reply_writedate;
					var date = new Date(milliseconds);
					
					boxWriteDate.innerText = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
					
					boxRow1_col2.appendChild(boxWriteDate);
					boxRow1.appendChild(boxRow1_col2);
					
					// 댓글 내용
					var boxRow2 = document.createElement("div");
					boxRow2.setAttribute("class","row");
					
					var boxRow2_col = document.createElement("div");
					boxRow2_col.setAttribute("class","col");
					
					var boxReplyContent = document.createElement("div");
					boxReplyContent.setAttribute("class","alert alert-secondary");
					
					boxReplyContent.innerText = data.replyVo.reply_content;
					
					boxRow2_col.appendChild(boxReplyContent);
					boxRow2.appendChild(boxRow2_col);
					
					replyBox.appendChild(boxRow1);
					replyBox.appendChild(boxRow2);
				}
			}
		};
		
		xmlhttp.open("get", "./get_reply_list.do?board_no="+boardNo, true);	// select는 get방식으로 하는 것이 일반적
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlhttp.send();
	}

	function writeReply() {
		var boardNo = ${boardContent.boardVo.board_no };
		var replyContent = document.getElementById("reply_content").value;
		var replyBox = document.getElementById("reply_content");
		
		var xmlhttp = new XMLHttpRequest();
		
		xmlhttp.onreadystatechange = function () {
			if(xmlhttp.readyState==4 && xmlhttp.status == 200) {
				refreshReplyList();
				replyBox.value="";
				replyBox.focus();
			}
		};
		xmlhttp.open("post", "./write_reply_process.do", true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlhttp.send("board_no="+boardNo+"&reply_content="+replyContent);	// DB 연동을 위해 이름을 컬럼명과 같게! 
	}

</script>
</head>
<body onload="refreshReplyList()">	<!-- 시작 시 자동으로 로드하기 -->
<div class="container">
	<div class="row">
		<div class="col">	<!-- 글 내용 -->
			작성자: ${boardContent.memberVo.member_nick }<br><br>
			조회수: ${boardContent.boardVo.board_readcount }<br><br>
			제목: ${boardContent.boardVo.board_title }<br><br>
			내용:<br>
			${boardContent.boardVo.board_content }<br>
		</div>
	</div>
	<div class="row">
		<div id="reply_box" class="col">	<!-- 댓글 리스트 -->
			<div class="row" style="margin:0px">	<!-- 댓글(반복되는 부분) -->
				<div class="col-8" style="padding:0px">	<!-- 댓글 작성자 닉네임 -->
					<div class="alert alert-secondary">닉네임</div>
				</div>	
				<div class="col" style="padding:0px">
					<div class="alert alert-secondary">날짜</div>
				</div>	<!-- 댓글 작성 날짜 -->
			</div>
			<div class="row" style="margin:0px">	<!-- 댓글 내용 -->
				<div class="col" style="padding:0px">
					<div class="alert alert-secondary">댓글 내용</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">	<!-- 댓글 -->
		<div class="col">
			댓글
		</div>
		<div class="col-8">
			<textarea id="reply_content" class="form-control"></textarea>
		</div>
		<div class="col">
			<input type="button" class="btn btn-primary" value="작성" onclick="writeReply()">
		</div>
	</div>
	<c:forEach var="fileVo" items="${boardContent.uploadFileVoList }">
		<img src="/upload/${fileVo.file_link_path }"><br>
	</c:forEach>
	<c:forEach var="fileVo" items="${boardContent.uploadFileVoList }" varStatus="status">
		<!-- <a href="/upload/${fileVo.file_link_path }" target="_blank"> file: ${status.count}</a>  
		<a href="/upload/${fileVo.file_link_path }" onclick="window.open(this.href, '_blanck', 'width=600, height=400, left=830, top=120'); return false">file: ${status.count}</a>
		-->
		<a href="${pageContext.request.contextPath }/board/file_download_process.do?filePath=/upload/${fileVo.file_link_path }">File: ${status.count}</a>
	</c:forEach>
	<br>
	<c:if test="${!empty sessionUser && sessionUser.member_no == boardContent.memberVo.member_no }">
		<a class="btn btn-primary" href="${pageContext.request.contextPath }/board/modify_content_page.do?board_no=${boardContent.boardVo.board_no }">수정</a>
		<a class="btn btn-primary" href="${pageContext.request.contextPath }/board/delete_content_process.do?board_no=${boardContent.boardVo.board_no }">삭제</a>
	</c:if>
	<a class="btn btn-primary" href="${pageContext.request.contextPath }/board/main_page.do">목록</a>
</div>
	<%--bootstrap JS 코드 --%>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>