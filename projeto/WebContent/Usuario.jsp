<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="metodos_validacao.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- scriptlets em java-->
<%
	
	String nome = (String )request.getAttribute("nome");
	String cpf = (String )request.getAttribute("cpf");
	String senha = (String )request.getAttribute("senha");
	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	<p>	Bem vindo <%= nome %>!</p><!-- atarves de codigo html -->
	<p>	Senha: <%= request.getAttribute("senha") %></p><!-- atarves de codigo html -->
	<p>	Cpf : <%= ValidarCPF.imprimeCPF(cpf) %></p><!-- atarves de codigo html -->
</body>
</html>