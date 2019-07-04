<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Usuario</title>
</head>
<body>
	<p>Seja bem vindo!</p>
	<p>Por favor cadastre-se</p>
	
	<form action="LoginUsuarioServlet" method="post">
		Nome : <input type="text" name="nome" /><br> 
		Senha: <input type="text" name = "senha" /><br /> 
		Cpf  : <input type="text" name="cpf" /><br /> 
	
		<input type="submit" value="Entrar" />

	</form>
</body>
</html>