<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Criar alimento</title>
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
			<h1 class="text-center mt-5 h4 w-100">Crie um alimento</h1>

			<div class="col-12 d-flex justify-content-center text-center">
				<form class="w-100 form-inline my-2 my-lg-0" action="alimento"
					method="post">
					<input type="hidden" name="action" value="create" />
					<div class="row m-0 w-100">
						<div class="col-6 p-2 mb-4">
							<input class="form-control w-100" type="text" name="nomeAlimento"
								placeholder="Nome" />
						</div>
						<div class="col-6 p-2 mb-4">
							<input class="form-control w-100" type="number" name="calorias"
								placeholder="calorias" />
						</div>
					</div>
					<div class="row m-0 w-100">
						<div class="col-6 p-2 mb-4">
							<input class="form-control w-100" type="text"
								name="macroNutrientes" placeholder="Macro Nutrientes" />
						</div>
					</div>

					<button class="btn btn-outline-success my-4 my-sm-0 w-100"
						type="submit">Salvar</button>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>
</html>