<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar ingestão de água</title>
<%@ include file="header.jsp"%>
</head>

<body>
	<%@ include file="menu.jsp"%>

	<c:if test="${empty user}">
		<%
		String redirectURL = "home.jsp";
		response.sendRedirect(redirectURL);
		%>
	</c:if>

	<div class="container">
		<div class="row justify-content-center">
			<h1 class="text-center mt-5 w-100">Editar ingestão de água</h1>
			<p class="text-center mb-5 w-100">Aqui você pode editar a ingestão de água.</p>

			<form class="w-100 col-12" action="ingestao" method="post">
				<input type="hidden" value="editar" name="acao">
				<input type="hidden" value="${ingestao.id}" name="codigo">
				<div class="form-group">
					<label for="quantidadeAgua">Quantidade de água</label>
					<input type="text" name="quantidadeAgua" id="quantidadeAgua" class="form-control" value="${ingestao.quantidadeAgua}" >
				</div>
				<div class="form-group">
					<label for="id-data">Data</label>
					<input type="text" name="data" id="id-data" class="form-control" 
						value='<fmt:formatDate value="${ingestao.data.time}" pattern="dd/MM/yyyy"/>'>
				</div>				
				<input type="submit" value="Salvar" class="btn btn-primary">
				<a href="ingestao?acao=listar" class="btn btn-danger">Cancelar</a>
			</form>
		</div>
	</div>

	<%@ include file="footer.jsp"%>	
</body>
</html>