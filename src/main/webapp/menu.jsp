<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container">
		<a class="navbar-brand" href="home.jsp">HealthTrack</a>

		<c:if test="${not empty user}">
			<span class="navbar-text"> 
				${user.getNome()} 
				<a href="login" class="btn btn-outline-danger ml-2 my-2 my-sm-0">Sair</a>
			</span>
		</c:if>
	</div>
</nav>