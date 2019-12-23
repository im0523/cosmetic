<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
p { text-align: center; font-size: 30px; font-weight: bold;}
.menu {width: 1000px; height: 50px; margin: 50px auto; }
.menu li { list-style: none; float: left; color: lightslategray; font-size: 15px; padding: 10px 27px; border: 1px solid #bdbdbd; margin: 0 4px;}
.item_box { width: 280px; height: 390px; float: left; margin: 20px;}
.thumbnail { width: 280px; height: 270px; margin: 0;}
.name { width: 280px; height: 40px; border-bottom: 1px solid #ececec; margin: 0; padding: 10px 0; line-height: 40px; }
.price { width: 280px; height: 60px; margin: 0; line-height: 0px;}
</style>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<form method="post" id="list">
<p>PRODUCT</p>
	<ul class="menu">
		<li>new</li>
		<li>best seller</li>
		<li>all</li>
		<li>face</li>
		<li>lips</li>
		<li>eye</li>
		<li>multi</li>
		<li>cleansing</li>
		<li>gifting</li>
		<li>acc</li>
	</ul>
	
<p style="font-size: 15px; margin: 0 0 0 1100px;"><a class="btn-fill" onclick="location='new.pd'">상품등록</a></p>
	
	<div class="content">
		<c:forEach items="${page.list }" var="vo">
			<a style="cursor: pointer;" onclick="go_detail(${vo.no})"><div class="item_box">
				<div class="thumbnail"><img src="${vo.imagepath }" class="thumbnail"/></div>
				<div class="name">${vo.name }</div>
				<div class="price"><h5>${vo.price }</h5></div>
			</div></a>
		</c:forEach>
	</div>
	
	<input type="hidden" name="no"/>
</form>
<script type="text/javascript">
function go_detail(no){
	$('[name=no]').val(no);
	$('#list').attr('action', 'detail.pd');
	$('#list').submit();
}
</script>
</body>
</html>