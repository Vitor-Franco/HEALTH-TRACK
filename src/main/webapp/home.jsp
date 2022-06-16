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

	<div class="container">
		<c:if test="${empty user}">
			<div class="row justify-content-center">
				<h1 class="text-center my-5 w-100">Bem Vindo ao HealthTrack</h1>
				<h2 class="text-center h5 w-100">Faça login para continuar</h2>

				<div class="col-12 d-flex justify-content-center text-center">
					<form class="form-inline my-2 my-lg-0" action="user" method="post">
						<input type="hidden" name="action" value="signin" /> <input
							class="form-control mr-sm-2" type="text" name="email"
							placeholder="E-mail" /> <input class="form-control mr-sm-2"
							type="password" name="senha" placeholder="Senha" />
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Entrar</button>
					</form>
				</div>
				<div class="col-12 text-center mt-3">
					<p class="navbar-text text-danger">${erro}</p>
					<p>
						Não possue conta? <a href="signup.jsp">Cadastre-se agora!</a>
					<p>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty user}">
			<h1 class="text-left mt-5 mb-2 h2">Bem Vindo, ${user.getNome()}.</h1>
			<p class="h5">Por onde gostaria de começar?</p>

			<div class="row m-0 pt-4">
				<div class="col-6">
					<p class="h5 w-100 mb-4">Atividade Física</p>

					<div class="d-flex">
						<div class="HOME__square_selection_item">
							<p>
								<a href="criarAtividadeFisica.jsp">Nova Atividade</a>
							</p>
						</div>
						<div class="HOME__square_selection_item">
							<p>
								<a href="atividade?acao=listar">Exibir atividades</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-6">
					<p class="h5 w-100 mb-4">Ingestão de Água</p>

					<div class="d-flex">
						<div class="HOME__square_selection_item">
							<p>
								<a href="criarIngestaoAgua.jsp">Nova ingestão de água</a>
							</p>
						</div>
						<div class="HOME__square_selection_item">
							<p>
								<a href="ingestao?acao=listar">Listar ingestão de água</a>
							</p>
						</div>
					</div>
				</div>
			</div>


			<div class="row m-0 pt-4 mt-3">
				<div class="col-6">
					<p class="h5 w-100 mb-4">Registro corporal</p>
					<div class="d-flex">

						<div class="HOME__square_selection_item">
							<p>
								<a href="criarRegistroCorporal.jsp">Novo registro corporal</a>
							</p>
						</div>
						<div class="HOME__square_selection_item">
							<p>
								<a href="registroCorporal?acao=listar">Listar registro
									corporal</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-6">
					<p class="h5 w-100 mb-4">Alimento</p>
					<div class="d-flex">

						<div class="HOME__square_selection_item">
							<p>
								<a href="criarAlimento.jsp">Novo alimento</a>
							</p>
						</div>
						<div class="HOME__square_selection_item">
							<p>
								<a href="alimento?acao=listar">Listar alimentos</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="row m-0 pt-4 mt-3">
				<div class="col-6">
					<p class="h5 w-100 mb-4">Refeição</p>
					<div class="d-flex">

						<div class="HOME__square_selection_item">
							<p>
								<a href="criarRefeicao.jsp">Nova refeição</a>
							</p>
						</div>
						<div class="HOME__square_selection_item">
							<p>
								<a href="refeicao?acao=listar">Listar refeições</a>
							</p>
						</div>
					</div>
				</div>
				
			</div>
		</c:if>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>