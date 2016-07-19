<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${result>0}">
	<script>
		alert("수정완료!");
		location.href="/webApp/replyBoard/View?num=${num}";
	</script>
</c:if>
<c:if test="${result<=0}">
	<script>
		alert("수정실패!ㅋㅋ");
		history.back();
	</script>
</c:if>