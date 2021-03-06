<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

	<div class="header">
		<h1 class="site_logo">
			<a href="menu.jsp">商品管理システム</a>
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

	<div class="update">
		<div class="form_body">
			<div class="img_block">
				<img src="images/マッキー.png" class="product_img"><br>
			</div>
			<form action="Delete" method="get">
				<fieldset class="label-130 product_block">
					<p class="error">${error}</p>
					<div>
						<label>商品ID</label> <input type="text" name="loginId"
							value="${findId.product_id}" readonly class="base-text">
					</div>
					<div>
						<label>商品名</label> <input type="text" name="userName"
							value="${findId.p_name}" readonly class="base-text">
					</div>
					<div>
						<label>単価</label> <input type="text" name="tel"
							value="${findId.price}" readonly class="base-text">
					</div>
					<div>
						<label>カテゴリ</label> <input type="text" name="roleName"
							value="${findId.category.c_name}" readonly class="base-text">
					</div>
					<div>
						<label>商品説明</label>
						<textarea name="description" readonly class="base-text"
							style="background-color: rgb(209, 209, 209);">
${findId.description}
            </textarea>
					</div>
				</fieldset>
				<div>
					<c:choose>
						<c:when test="${check == true}">
							<div class="btns">
								<input type="button" onclick="openModal()" value="${delete}"
									class="basic_btn"> <input type="button"
									onclick="location.href='Update'" value="${update}"
									class="basic_btn">
							</div>
						</c:when>
					</c:choose>
					<input type="button" onclick="location.href='./menu.jsp'"
						value="戻る" class="cancel_btn">


					<div id="modal">
						<p class="modal_message">削除しますか？</p>
						<div class="btns">
							<button type="submit" class="basic_btn">削除</button>
							<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>