<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<c:url value="/" var="contextPath" />

<meta charset="UTF-8">
<title>Produtos</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${contextPath}/resources/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="${contextPath}/resources/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${s:mvcUrl('HC#index').build() }">Casa do Código</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${s:mvcUrl('PC#listar').build() }">Lista de
							Produtos</a></li>
					<li><a href="${s:mvcUrl('PC#form').build() }">Cadastro de
							Produtos</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</nav>


	<div class="container">
		<h2>Lista de Produtos</h2>
		<div>
			<b>${sucesso}</b>
		</div>
		<div>
			<b>${falha}</b>
		</div>
		<p>
		<table class="table table-striped table-hover">
			<tr>
				<th>Título</th>
				<th>Descrição</th>
				<th>Páginas</th>
			</tr>

			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td><a
						href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build() }">${produto.titulo}</a></td>
					<td>${produto.descricao}</td>
					<td>${produto.paginas}</td>
				</tr>
			</c:forEach>

		</table>
		</p>
	</div>
</body>
</html>