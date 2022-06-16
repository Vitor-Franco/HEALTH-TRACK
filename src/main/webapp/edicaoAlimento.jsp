<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edite um registro corporal</title>
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
			<h1 class="text-center my-5 h4 w-100">Edite um registro corporal
			</h1>

			<div class="col-12 d-flex justify-content-center text-center">
				<form class="form-inline my-2 my-lg-0" action="registroCorporal"
					method="post">
					<input type="hidden" name="action" value="edit" /> <input
						type="hidden" value="${registro.id}" name="codigo">
					<div class="row m-0 w-100">
						<div class="col-12 p-0 mb-4">
							<input class="form-control w-100" type="text" name="peso"
								placeholder="Peso" value="${registro.peso}" />
						</div>
					</div>
					<input type="submit" value="Salvar" class="btn btn-primary mr-2">
					<a href="registroCorporal?acao=listar" class="btn btn-danger">Cancelar</a>

				</form>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>
