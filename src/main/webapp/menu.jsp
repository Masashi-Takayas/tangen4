<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
<link href="css/commons.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<div id="app">

		<div class="header">
			<h1 class="site_logo">
				<a href="menu.html">商品管理システム</a>
			</h1>
			<div class="user">
				<p class="user_name">${name}さん、こんにちは</p>
				<form class="logout_form" action="logout.jsp" method="get">
					<button class="logout_btn" type="submit">
						<img src="images/ドアアイコン.png">ログアウト
					</button>
				</form>
			</div>
		</div>

		<hr>
		<c:choose>
			<c:when test="${check == true}">
				<div class="btn">
					<a class="basic_btn regist" href="insert.jsp">新規登録</a>
				</div>
			</c:when>
		</c:choose>
		<p>${insert}</p>
		<form method="get" action="Search" class="search_container">
			<input type="text" size="25" name="keyWord" placeholder="キーワード検索">
			<input type="submit" value="&#xf002">
		</form>

		<table>
			<div class="caption">
				<p>検索結果：${fn:length(list)}件</p>
			</div>

			<div class="order">
				<form method="get" action="Search">
					<select class="base-text" name="orderBy"
						onchange="this.form.submit()">
						<option selected>並び替え</option>
						<option value="product_id">商品ID</option>
						<option value="category_id">カテゴリ</option>
						<option value="price">単価：安い順</option>
						<option value="price DESC">単価：高い順</option>
						<option value="">登録日：古い順</option>
						<option value="">登録日：新しい順</option>
					</select>
				</form>
			</div>

			<thead>
				<tr>
					<th>商品ID</th>
					<th>商品名</th>
					<th>単価</th>
					<th>カテゴリ</th>
					<th>詳細</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${list}">
					<tr>
						<td>${p.product_id}</td>
						<td>${p.p_name}</td>
						<td>${p.price}</td>
						<td>${p.category.c_name}</td>
						<td><a class="detail_btn"
							href="Detail?product_id=${p.product_id}">詳細</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</body>
</html>
