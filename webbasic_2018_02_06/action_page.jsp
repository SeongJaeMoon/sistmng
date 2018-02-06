<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 주의) 현재 페이지를 단독 실행하지 않는다. --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SIST_쌍용교육센터</title>
</head>
<body>

<p>FirstName:<%=request.getParameter("firstname")%></p>
<p>LastName:<%=request.getParameter("lastname")%></p>

</body>
</html>