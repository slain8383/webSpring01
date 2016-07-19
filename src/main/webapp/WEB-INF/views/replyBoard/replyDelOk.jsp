<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${cnt>0}">
	<script>
		alert("게시글 삭제성공!");
		location.href="/webApp/replyBoard/replyList";
	</script>
</c:if>
<c:if test="${cnt<=0}">
	<script>
		alert("게시글 삭제실패!ㅋㅋ");
		history.back();
	</script>
</c:if>