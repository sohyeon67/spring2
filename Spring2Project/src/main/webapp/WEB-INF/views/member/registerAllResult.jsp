<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register All Result</title>
</head>
<body>
<!-- 
	[아래 결과처럼 출력해주세요.]
	
	유저ID			a001
	패스워드			1234
	이름				조현준
	E-MAIL			ddit@naver.com
	생년월일			2023년 11월 23일
	성별				남자
	개발자여부			개발자
	외국인여부			외국인
	국적				대한민국
	소유차량			소유차량 없음
	취미				운동 영화시청
	우편번호			45617
	주소				대전광역시 서구  오류동
	카드1(번호)		1451-1234-1234-1122
	카드1(유효년월)	2040년 09월 09일
	카드2(번호)		1234-1234-1234-1234
	카드2(유효년월)	2040년 09월 09일
	소개				반갑습니다
	
	** 조건
	- 선택한 성별에 따라 남자/여자/기타로 나타내주세요.
	- 개발자 여부 체크에 따라 개발자/일반으로 나타내주세요.
	- 외국인 여부 체크에 따라 외국인/내국인으로 나타내주세요.
	- 국적에 따라 대한민국/독일/호주/캐나다로 나타내주세요.
	- 소유차량 선택에 따라 소유차량 없음/JEEP/VOLVO/BMW/AUDI로 나타내주세요.
		선택에 따라 아래처럼 출력해주세요.
		> 소유차량을 선택하지 않았을 때, 소유차량 없음
		> 1개만 선택했을 때, JEEP
		> 1개 이상을 선택했을 때, JEEP VOLVO AUDI
	- 취미 선택에 따라 운동/영화시청/음악감상로 나타내주세요.
		선택 갯수에 따라 아래처럼 출력해주세요.
		> 취미를 선택하지 않았을 때, 취미 없음
		> 1개만 선택했을 때, 음악감상
		> 1개 이상을 선택했을 때, 운동 영화시청
 -->
<c:set value="${memInfo }" var="member"/>
<table border="1">
	<tr>
		<td>유저ID</td>
		<td>${member.userId }</td>
	</tr>
	<tr>
		<td>패스워드</td>
		<td>${member.password }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${member.userName }</td>
	</tr>
	<tr>
		<td>E-MAIL</td>
		<td>${member.email }</td>
	</tr>
	<tr>
		<td>생년월일</td>
		<td>${member.dateOfBirth }</td>
	</tr>
	<tr>
		<td>성별</td>
		<td>${member.gender }</td>
	</tr>
	<tr>
		<td>개발자여부</td>
		<td>${member.developer }</td>
	</tr>
	<tr>
		<td>외국인여부</td>
		<td>${member.foreigner }</td>
	</tr>
	<tr>
		<td>국적</td>
		<td>${member.nationality }</td>
	</tr>
	<tr>
		<td>소유차량</td>
		<td>${member.cars }</td>
	</tr>
	<tr>
		<td>취미</td>
		<td>${member.hobbys }</td>
	</tr>
	<tr>
		<td>우편번호</td>
		<td>${member.postCode }</td>
	</tr>
	<tr>
		<td>주소</td>
		<td>${member.location }</td>
	</tr>
	<tr>
		<td>카드1(번호)</td>
		<td>${member.cardNo1 }</td>
	</tr>
	<tr>
		<td>카드1(유효년월)</td>
		<td>${member.cardValidMonth1 }</td>
	</tr>
	<tr>
		<td>카드2(번호)</td>
		<td>${member.cardNo2 }</td>
	</tr>
	<tr>
		<td>카드2(유효년월)</td>
		<td>${member.cardValidMonth2 }</td>
	</tr>
	<tr>
		<td>소개</td>
		<td>${member.introduction }</td>
	</tr>
	
	

</table>

</body>
</html>