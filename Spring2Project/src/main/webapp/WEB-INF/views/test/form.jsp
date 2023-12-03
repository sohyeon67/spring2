<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.108.0">
<title>DDIT BOARD LIST</title>
<link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/album/">
<link href="${pageContext.request.contextPath }/resources/assets/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	height: 3rem;
	background-color: rgba(0, 0, 0, .1);
	border: solid rgba(0, 0, 0, .15);
	border-width: 1px 0;
	box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em
		rgba(0, 0, 0, .15);
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh;
}

.bi {
	vertical-align: -.125em;
	fill: currentColor;
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;
}
</style>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<c:if test="${not empty message }">
<script type="text/javascript">
alert("${message}");
<c:remove var="message" scope="request"/>
<c:remove var="message" scope="session"/>
</script>
</c:if>
<c:set value="등록" var="name"/>
<c:if test="${status eq 'u' }">
	<c:set value="수정" var="name"/>
</c:if>
<body>
	<header>
		<div class="collapse bg-dark" id="navbarHeader">
		</div>
		<div class="navbar navbar-dark bg-dark shadow-sm">
			<div class="container"></div>
		</div>
	</header>
	<main>
		<section class="py-1 text-center container">
			<div class="row py-lg-4">
				<div class="col-lg-6 col-md-8 mx-auto">
					<h1 class="fw-light">DDIT ${name }</h1>
				</div>
			</div>
		</section>
		<section class="py-1 text-center container">
			<form class="album py-1 bg-light" action="/board/tag/form.do" method="post" id="dditboard">
				<!-- 수정 시 필요한 게시글 번호 -->
				<c:if test="${status eq 'u' }">
					<input type="hidden" name="boNo" value="${tagBoardVO.boNo }"/>
				</c:if>
				<div class="">
					<div class="container">
						<div class="card-body">
							<div class="input-group input-group-lg">
								<span class="input-group-text" id="inputGroup-sizing-lg">제목</span>
								<input type="text" id="boTitle" class="form-control" name="boTitle" value="${tagBoardVO.boTitle }"/>
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-text" id="inputGroup-sizing-lg">내용</span>
								<textarea class="form-control" aria-label="With textarea" rows="12" id="boContent" name="boContent">${tagBoardVO.boContent }</textarea>
							</div>
							<div class="input-group input-group-lg">
								<span class="input-group-text" id="inputGroup-sizing-lg">태그</span>
								<input type="text" class="form-control" name="tag" value="${tagBoardVO.tag }"/>
							</div>
						</div>
						<div class="card-footer" align="right">
							<button type="button" class="btn btn-primary" id="registerBtn">${name }</button>
							<c:if test="${status ne 'u' }">
								<button type="button" class="btn btn-info" id="listBtn">목록</button>
							</c:if>
							<c:if test="${status eq 'u' }">
								<button type="button" class="btn btn-info" id="cancelBtn">취소</button>
							</c:if>
						</div>
					</div>
				</div>
			</form>
		</section>
	</main>
	<script src="${pageContext.request.contextPath }/resources/assets/dist/js/bootstrap.bundle.min.js"></script>
</body>
<script type="text/javascript">
$(function() {
	var dditboard = $("#dditboard");
	var registerBtn = $("#registerBtn");
	var listBtn = $("#listBtn");
	var cancelBtn = $("#cancelBtn");
	
	registerBtn.on("click", function() {
		var title = $("#boTitle").val();
		var content = $("#boContent").val();
		
		if(title == null || title == "") {
			alert("제목을 입력해주세요!");
			$("#boTitle").focus();
			return false;
		}
		if(content == null || content == "") {
			alert("내용을 입력해주세요!");
			$("#boContent").focus();
			return false;
		}
		
		// input type="button"은 val(), button type="button"은 text()★★★★★
		if($(this).text() == "수정") {
			dditboard.attr("action", "/board/tag/update.do");
		}
		
		dditboard.submit();
	});
	
	listBtn.on("click", function() {
		location.href = "/board/tag/list.do";
	});
	
	cancelBtn.on("click", function() {
		location.href = "/board/tag/detail.do?boNo=${tagBoardVO.boNo}";
	});
});
</script>
</html>