<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar refeição</title>
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
			<h1 class="text-center mt-5 w-100">Editar refeição</h1>
			<p class="text-center mb-5 w-100">Aqui você pode editar a sua refeição.</p>

			<form class="w-100 col-12" action="refeicao" method="post">
				<input type="hidden" value="editar" name="acao">
				<input type="hidden" value="${refeicao.id}" name="codigo">
				<div class="form-group">
					<label for="descricaoRefeicao">Descrição da refeicao</label>
					<input type="text" name="descricaoRefeicao" id="descricaoRefeicao" class="form-control" value="${refeicao.descricaoRefeicao}" >
				</div>
				<div class="form-group">
					<label for="nomeRefeicao">Nome da refeicao</label>
					<input type="text" name="nomeRefeicao" id="nomeRefeicao" class="form-control" value="${refeicao.nomeRefeicao}" >
				</div>
				<div class="form-group">
					<label for="dataRefeicao">Data da refeicao</label>
					<input type="text" name="dataRefeicao" id="dataRefeicao" class="form-control" 
						value='<fmt:formatDate value="${refeicao.dtRefeicao.time}" pattern="dd/MM/yyyy" type="both" />'>
				</div>						
				<input type="submit" value="Salvar" class="btn btn-primary">
				<a href="refeicao?acao=listar" class="btn btn-danger">Cancelar</a>
			</form>
		</div>
	</div>

	<%@ include file="footer.jsp"%>	
</body>
</html>