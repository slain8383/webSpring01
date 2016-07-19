<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/webApp/js_css/style.css" rel="stylesheet"/>
</head>
<body>
<h1>답변글 목록</h1>
<ul>
	<li>번호</li>
	<li>제목</li>
	<li>작성자</li>
	<li>조회수</li>
	<li>등록일</li>
</ul>
<c:set var="recordNum" value="${startNumber+1}"/>
<c:forEach var="vo" items="${list}">
<ul>
	<li>${recordNum=recordNum-1}</li>  <!-- 1씩 감소하게 -->
	<li>
		<c:if test="${vo.lev>0}">
			<c:forEach var="space" begin="0" end="${vo.lev}">
				&nbsp;&nbsp;
			</c:forEach>
			☞
		</c:if>
		<a href="/webApp/replyBoard/View?num=${vo.num}">${vo.subject}</a>
	</li>
	<li>${vo.username }</li>
	<li>${vo.hit }</li>
	<li>${vo.writedate }</li>
</ul>
</c:forEach>
<hr/>
<a href="/webApp/replyBoard/write">글쓰기</a>
<a href="/webApp/">홈</a>
</body>
</html>