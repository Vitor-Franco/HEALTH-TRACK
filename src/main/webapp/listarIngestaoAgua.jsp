<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar ingest?es de ?gua</title>
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
			<h1 class="text-center mt-5 w-100">Ingest?es de ?gua</h1>
			<p class="text-center mb-5 w-100">Aqui voc? pode visualizar cada
				ingest?o de ?gua.</p>

			<c:if test="${not empty msg }">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<c:if test="${not empty erro }">
				<div class="alert alert-danger">${erro}</div>
			</c:if>

			<table class="table table-striped">
				<tr>
					<th>Data</th>
					<th>Quantidade de ?gua</th>
					<th></th>
				</tr>
				<c:forEach items="${ingestoes}" var="i">
					<tr>
						<td><fmt:formatDate value="${i.data}" pattern="dd/MM/yyyy" /></td>
						<td>${i.quantidadeAgua}</td>						
						<td>
							<c:url value="ingestao" var="link">
								<c:param name="acao" value="abrir-form-edicao" />
								<c:param name="codigo" value="${i.id}" />
							</c:url> 
							<a href="${link}" class="btn btn-primary btn-xs">Editar</a>
							<button 
								type="button" 
								class="btn btn-danger btn-xs"
								data-toggle="modal" 
								data-target="#excluirModal"
								onclick="getElementById('codigoExcluir').value = ${i.id}"
							>
								Excluir
							</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

	<!-- Modal -->
	<div class="modal fade" id="excluirModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Confirma??o</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Deseja realmente excluir a ingest?o?</div>
				<div class="modal-footer">
					<form action="ingestao" method="post">
						<input type="hidden" name="acao" value="excluir"> <input
							type="hidden" name="codigo" id="codigoExcluir">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Cancelar</button>
						<button type="submit" class="btn btn-danger">Excluir</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>