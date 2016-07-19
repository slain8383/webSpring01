<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>답변게시판 글수정</h1>
<form method="post" action="/webApp/replyBoard/replyEditOk">
	<ul>
		<li>작성자 : <input type="text" name="username" value="${vo.username}"/></li>
		<li>제목 : <input type="text" name="subject" value="${vo.subject}"/></li>
		<li>글내용 : <textarea name="content">${vo.content}</textarea></li>
		<li><input type="submit" value="수정"/>
			<input type="hidden" name="num" value="${vo.num}">
		</li>
	</ul>
</form>
<a href="/webApp/">홈</a>
<a href="/webApp/replyBoard/replyList">리스트</a>
</body>
</html>