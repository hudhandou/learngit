<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${notyInfo != null }">
<script type="text/javascript">
	
	notyInfo = ${notyInfo};
	noty(notyInfo);
</script>
</c:if>