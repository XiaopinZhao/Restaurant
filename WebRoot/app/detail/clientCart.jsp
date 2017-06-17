<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/detail/style/css/index.css" />
	<script type="text/javascript">
		/** // 删除菜品项
		function removeSorder(node) {
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.html?method=removeSorder&gid="+gid;
		}
		
		// 修改菜品项数量
		function alterSorder(node) {
			var snumber = node.value;
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.html?method=alterSorder&gid="+gid+"&snumber="+snumber;
		}
		*/
		// 下单
		function genernateOrder() {
			var food_id= document.getElementById("xiaopin").val();
			var count=document.getElementById("clientCount").val();
			
			window.location.href = "${pageContext.request.contextPath }/OrderServlet?method=setOrder&food_id=food_id}&food_count=count";
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
				 		<td align="center" width="20%">操作</td>
				 	</tr>
				 		<c:if test="${not empty sessionScope.foods }">
				 		<c:forEach var="food" items="${sessionScope.foods }">
				 			
									
								<tr height="60">
									<td align="center" width="20%">${food.key.foodName }</td>
									 <td align="center" width="20%">￥${food.key.price }</td>
									 <td align="center" width="20%">
										<input type="text" value="${food.value }" size="3" lang="3"  id="clientCount" onblur="alterSorder(this)" name="foodCount"/>
									 </td>
									 <td align="center" width="20%">${food.key.price }</td>
									 <td align="center" width="20%">
									 <a class="btn_next"   lang="3" href="${pageContext.request.contextPath}/OrderServlet?method=removeOrder&food_id=${food.key.id}" >
										删除
									 </td>
								</tr>
							 
								<tr>
									<td colspan="6" align="right">总计:
										<span style="font-size:36px;">&yen;&nbsp;${food.key.price*food.value}</span>
										<label	id="counter" style="font-size:36px"></label>
									</td>
								</tr>
								<tr>
									<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
										<input type="hidden" name="bId" value="">
										<a class="btn_next"  style="float:right" href="${pageContext.request.contextPath }/OrderServlet?method=setOrder&food_id=${food.key.id }&food_count=${food.value}">下单</a>
										<!-- <input type="button" value="下单" class="btn_next" onClick="javascript:window.location.href=window.location.href = ''${pageContext.request.contextPath }/OrderServlet?method=setOrder&food_id=${food.key.id }&food_count=${food.value}'" /> -->
									</td>
								</tr>
								
							</c:forEach>
						</c:if>
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
