<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Produtos</title>
</head>
<body>
	<h2>Lista de Produtos</h2>

	<div>
		<b>${sucesso}</b>
	</div>
	<p>
	<table>
		<tr>
			<td>Título</td>
			<td>Descrição</td>
			<td>Página</td>
		</tr>

		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td>${produto.titulo}</td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas}</td>
			</tr>
		</c:forEach>

	</table>
	</p>
</body>
</html>