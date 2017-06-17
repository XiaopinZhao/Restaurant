﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/detail/style/css/index.css" />
	<script type="text/javascript">
		// 通知服务员结账
		function callPay(node) {
			
			window.location.href = "${pageContext.request.contextPath }/app/detail/jiezhang.jsp";
		}
	</script>
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 餐车div -->
			<div id="count">
				<table align="center" width="100%">
					<tr height="40">
				 		<td align="center" width="20%">菜名</td>
				 		<td align="center" width="20%">单价</td>
				 		<td align="center" width="20%">数量</td>
				 		<td align="center" width="20%">小计</td>
				 	</tr>
					
					<c:if test="${not empty requestScope.AllMenu }">
						<c:forEach var="food" items="${requestScope.AllMenu }">
								<tr height="60">
								<!-- <input type="hidden" id="xiaopin" value="${food.key.id }"/> -->
							 		<td align="center" width="20%">${food.foodName }</td>
							 		<td align="center" width="20%">￥${food.price }</td>
							 		<td align="center" width="20%">${food.foodCount }</td>
							 		<td align="center" width="20%">${food.price*food.foodCount }</td>
						 		</tr>
						 </c:forEach>
					</c:if>
					<tr>
						<td colspan="6" align="right">总计:
							<span style="font-size:36px;">&yen;</span>
							<label="counter" style="font-size:36px">${requestScope.totalPrice }</label>
						</td>
					</tr>
						
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
							<input type="button" value="结账" class="btn_next" lang="" onclick="callPay(this)" />
						</td>
					</tr>
				</table>
			</div>
		</div>

		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top">
				<ul>
					<li class="dish_num"></li>
					<li>
						<a href="${pageContext.request.contextPath }/OrderServlet?method=getMenuList">
							<img src="${pageContext.request.contextPath }/app/detail/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>

			<div id="dish_2">
				<ul>
					
					<c:if test="${not empty requestScope.foodTypeList }">
						<c:forEach var="item" items="${requestScope.foodTypeList }">
							<li>
								<a href="${pageContext.request.contextPath }/IndexServlet?method=getMenu&foodtype=${item.id}">${item.typeName }</a>
							</li>
						</c:forEach>
					</c:if>
						
					
				</ul>
			</div>
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<form action="${pageContext.request.contextPath }/IndexServlet?method=getMenu" method="post">
					<table width="166px">
						<tr>
							<td>
								<input type="text" id="dish_name" name="foodName" class="select_value" /> 
								<input type="hidden" value="selectFood" name="method">
							</td>
						</tr>
						<tr>
							<td><input type="submit" id="sub" value="" /></td>
						</tr>
						<tr>
							<td>
								<a href="${pageContext.request.contextPath }/IndexServlet?method=getMenu">
									<img src="${pageContext.request.contextPath }/app/detail/style/images/look.gif" />
								</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>
