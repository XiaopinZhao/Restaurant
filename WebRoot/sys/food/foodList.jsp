﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="${pageContext.request.contextPath }/sys/text/javascript" src="style/js/jquery.js"></script>
<script type="${pageContext.request.contextPath }/sys/text/javascript" src="style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath }/sys/style/images/title_arrow.gif"/> 菜品列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>
	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="/wirelessplatform/food.html" method="get">
			<input type="hidden" name="method" value="search">
			<input type="text" name="keyword" title="请输入菜品名称">
			<input type="submit" value="搜索">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>菜编号</td>
				<td>菜名</td>
				<td>所属菜系</td>
				<td>价格</td>
                <td>会员价格</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
			<c:if test="${not empty requestScope.foodList }">
				<c:forEach var="item" items="${requestScope.foodList }">
						<tr class="TableDetail1">
							<td>${item.id }&nbsp;</td>
							<td>${item.foodName }&nbsp;</td>
							<td>
								<c:forEach var="it" items="${requestScope.types }">
									<c:if test="${it.id==item.foodType_id }">
										${it.typeName }
									</c:if>
								</c:forEach>
							</td>
							<td>${item.price }&nbsp;</td>
			                <td>${item.mprice }&nbsp;</td>
							<td>
								<a href="${pageContext.request.contextPath }/FoodServlet?method=preUpdate&function=update&id=${item.id}"  class="FunctionButton">更新</a>				
								<a href="${pageContext.request.contextPath }/FoodServlet?method=deleteFood&id=${item.id}" onClick="return delConfirm();"class="FunctionButton">删除</a>				
							</td>
						</tr>
				</c:forEach>
       	 	</c:if>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath }/FoodServlet?method=preAdd">添加</a></div>
    </div> 
</div>
</body>
</html>
