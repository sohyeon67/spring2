<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>6) type 속성을 date로 지정하여 날짜를 출력</h4>
	date default : <fmt:formatDate value="${now }" type="date" dateStyle="default"/><br/>
	date short : <fmt:formatDate value="${now }" type="date" dateStyle="short"/><br/>
	date medium : <fmt:formatDate value="${now }" type="date" dateStyle="medium"/><br/>
	date long : <fmt:formatDate value="${now }" type="date" dateStyle="long"/><br/>
	date full : <fmt:formatDate value="${now }" type="date" dateStyle="full"/><br/>
	
	<h4>7) type 속성을 time로 지정하여 날짜를 출력</h4>
	time default : <fmt:formatDate value="${now }" type="time" timeStyle="default"/><br/>
	time short : <fmt:formatDate value="${now }" type="time" timeStyle="short"/><br/>
	time medium : <fmt:formatDate value="${now }" type="time" timeStyle="medium"/><br/>
	time long : <fmt:formatDate value="${now }" type="time" timeStyle="long"/><br/>
	time full : <fmt:formatDate value="${now }" type="time" timeStyle="full"/><br/>
	
	<h4>8) type 속성을 both로 지정하여 날짜를 출력</h4>
	both default : <fmt:formatDate value="${now }" type="both" dateStyle="default" timeStyle="default"/><br/>
	both short : <fmt:formatDate value="${now }" type="both" dateStyle="short" timeStyle="short"/><br/>
	both medium : <fmt:formatDate value="${now }" type="both" dateStyle="medium" timeStyle="medium"/><br/>
	both long : <fmt:formatDate value="${now }" type="both" dateStyle="long" timeStyle="long"/><br/>
	both full : <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/><br/>
</body>
</html>