<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Criar ingestão de água</title>
	<%@ include file="header.jsp"%>
</head>

<body>
	<%@ include file="menu.jsp"%>

	<c:if test="${empty user}"><% 
		String redirectURL = "home.jsp";
	    response.sendRedirect(redirectURL);
	%></c:if>

	<div class="container">
		<div class="row justify-content-center">
			<h1 class="text-center mt-5 w-100">Criar ingestão de água</h1>
			<p class="text-center mb-5 w-100">Aqui você pode registrar uma ingestão de água.</p>

			<c:if test="${not empty msg }">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<c:if test="${not empty erro }">
				<div class="alert alert-danger">${erro}</div>
			</c:if>

			<div class="col-12 d-flex justify-content-center text-center">
				<form class="form-inline my-2 my-lg-0" action="ingestao" method="POST">
					<input type="hidden" name="acao" value="cadastrar" />
					<div class="row m-0 w-100 mb-3">
						<div class="col-12 p-0">
							<input 
								class="form-control mr-sm-2 w-100" 
								type="text" 
								name="quantidadeAgua"
								placeholder="Quantidade de água consumida em ML."
							/>
						</div>
						<div class="col-12 mt-2 p-0">
							<input 
								class="form-control mr-sm-2 w-100" 
								type="date" 
								name="data"
								placeholder="Data do consumo"
							/>
						</div>						
					</div>

					<button class="btn btn-outline-success mt-2 my-sm-0 w-100" type="submit">Cadastrar</button>

				</form>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html> 