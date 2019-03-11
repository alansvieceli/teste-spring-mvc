<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Produtos</title>
</head>
<body>
	<form action="/teste-spring-mvc/produtos" method="post">
		<div>
			<label>Título</label> <input type="text" name="titulo">
		</div>
		<div>
			<label>Descrição</label>
			<textarea " row="10" cols="20" name="descricao"></textarea>
		</div>
		<div>
			<label>Páginas</label> <input type="text" name="paginas">
		</div>
		<button type="submit">Cadastrar</button>
	</form>
</body>
</html>