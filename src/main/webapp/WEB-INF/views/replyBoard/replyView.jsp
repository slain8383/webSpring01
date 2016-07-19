<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>답변게시판 글 내용보기</title>
</head>
<body>
<h1>글 내용 보기</h1>
<ul>
	<li>작성자 : ${vo.username }</li>
	<li>제목 : ${vo.subject }</li>
	<li>글내용 : ${vo.content }</li>
	<li>조회수 : ${vo.hit }</li>
	<li>등록일 : ${vo.writedate }</li>
	<li>IP : ${vo.userip }</li>
</ul>
<hr/>
<a href="/webApp/">홈</a>
<a href="/webApp/replyBoard/replyList">리스트</a>
<a href="/webApp/replyBoard/replyEdit?num=${vo.num}">글수정</a>
<a href="/webApp/replyBoard/replyWrite?num=${vo.num}">답글쓰기</a>
<a href="/webApp/replyBoard/replyDel?num=${vo.num}">삭제</a>
</body>
</html>