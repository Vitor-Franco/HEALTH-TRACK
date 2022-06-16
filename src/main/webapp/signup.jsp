<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<%@ include file="header.jsp"%>
</head>

<body>
	<%@ include file="menu.jsp"%>

	<c:if test="${not empty user}">
		<%
		String redirectURL = "home.jsp";
		response.sendRedirect(redirectURL);
		%>
	</c:if>

	<div class="container">
		<div class="row justify-content-center">
			<h1 class="text-center my-5 w-100">Bem Vindo ao HealthTrack</h1>
			<h2 class="text-center h5 w-100">Crie sua conta!</h2>

			<div class="col-12 d-flex justify-content-center text-center">
				<form class="form-inline my-2 my-lg-0" action="user" method="post">
					<input type="hidden" name="action" value="signup" />
					<div class="row m-0 w-100">
						<div class="col-12 pl-0">
							<input class="form-control mr-sm-2 w-100" type="text" name="nome"
								placeholder="Nome" />
						</div>
					</div>
					<div class="row m-0 w-100 mt-2">
						<div class="col-6 pl-0">
							<input class="form-control mr-sm-2 w-100" type="string"
								name="altura" placeholder="Altura" />
						</div>
						<div class="col-6 pr-0">
							<select class="form-select" name="sexo">
								<option value="F">Feminino</option>
								<option value="M">Masculino</option>
							</select>
						</div>
					</div>
					<div class="row m-0 w-100 mt-2">
						<div class="col-6 pl-0">
							<input class="form-control mr-sm-2 w-100" type="number"
								name="idade" placeholder="Idade" />
						</div>
						<div class="col-6 pr-0">
							<input type="date" name="dtNascimento" placeholder="dd/MM/yyyy"
								class="form-control mr-sm-2 w-100" />
						</div>
					</div>
					<div class="row m-0 w-100 my-2">
						<div class="col-6 pl-0">
							<input class="form-control mr-sm-2 w-100" type="email"
								name="email" placeholder="E-mail" />
						</div>
						<div class="col-6 pr-0">
							<input class="form-control mr-sm-2 w-100" type="password"
								name="senha" placeholder="Senha" />
						</div>
					</div>
					<button class="btn btn-outline-success my-2 my-sm-0 w-100"
						type="submit">Cadastrar</button>

				</form>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>