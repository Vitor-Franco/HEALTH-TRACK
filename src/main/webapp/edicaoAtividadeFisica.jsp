<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar atividade física</title>
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
			<h1 class="text-center mt-5 w-100">Editar atividade física</h1>
			<p class="text-center mb-5 w-100">Aqui você pode editar a sua atividade física.</p>

			<form class="w-100 col-12" action="atividade" method="post">
				<input type="hidden" value="editar" name="acao">
				<input type="hidden" value="${atividade.id}" name="codigo">
				<div class="form-group">
					<label for="tipoAtividade">Tipo de atividade</label>
					<input type="text" name="tipoAtividade" id="tipoAtividade" class="form-control" value="${atividade.tipoAtividade}" >
				</div>
				<div class="form-group">
					<label for="nomeAtividade">Nome da atividade</label>
					<input type="text" name="nomeAtividade" id="nomeAtividade" class="form-control" value="${atividade.nome}" >
				</div>
				<div class="form-group">
					<label for="calorias">Calorias</label>
					<input type="text" name="calorias" id="calorias" class="form-control" value="${atividade.calorias}" >
				</div>
				<div class="form-group">
					<label for="descricao">Descrição</label>
					<input type="text" name="descricaoAtividade" id="descricao" class="form-control" value="${atividade.descricao}" >
				</div>
				<div class="form-group">
					<label for="dataAtividade">Data da atividade</label>
					<input type="text" name="dataAtividade" id="dataAtividade" class="form-control" 
						value='<fmt:formatDate value="${atividade.data.time}" pattern="dd/MM/yyyy"/>'>
				</div>		
				<div class="form-group">
					<label for="dataDuracao">Data da duração</label>
					<input type="text" name="dataDuracao" id="dataDuracao" class="form-control" 
						value='<fmt:formatDate value="${atividade.duracao.time}" pattern="dd/MM/yyyy"/>'>
				</div>				
				<input type="submit" value="Salvar" class="btn btn-primary">
				<a href="atividade?acao=listar" class="btn btn-danger">Cancelar</a>
			</form>
		</div>
	</div>

	<%@ include file="footer.jsp"%>	
</body>
</html>