<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
.card {
	border: none !important;
}
</style>
<%@ include file="Master.jsp"%>
<%
	if (request.getAttribute("response") != null) {
		out.println("<div class='alert alert-primary' role='alert' id='response'>");
		out.println(request.getAttribute("response") + "</div>");
	}

	Client client = (Client) session.getAttribute("user");
%>
<div class="row">
	<div class="col-md-12">
		<div class="card card-body">
			<div class="admin-title">
				<h3 class="text-center">Alterar Cliente: <%=client.getId()%></h3>
		        <a class="btn btn-sm btn-outline-secondary admin-button" href='ClientPasswordUpdate.jsp'>Alterar Senha</a>
			</div>
			<form action="Clients" method="post">
				<input type="hidden" name="id" value="<%= client.getId() %>">
				<input type="hidden" name="user_id" value="<%= client.getUser().getId() %>">
				<div class="row">
					<div class="col-4">
						<div class="form-group">
							<label for="txtFirstName">Nome</label> <input
								class="form-control" name="txtFirstName" type="text"
								value=<%=client.getFirstname()%>>
						</div>
					</div>
					<div class="col-4">
						<div class="form-group ">
							<label for="txtLastName">Sobrenome</label> <input
								class="form-control" name="txtLastName" type="text"
								value=<%=client.getLastname()%>>
						</div>
					</div>
					<div class="col-4">
						<div class="form-group">
							<label for="txtLastName">E-mail</label> <input
								class="form-control" name="txtEmail" type="email"
								value=<%=client.getUser().getEmail()%>>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-3">
						<div class="form-group">
							<label for="txtLastName">CPF</label> <input class="form-control"
								name="txtCpf" type="text" value=<%=client.getCpf()%>>
						</div>
					</div>
					<div class="col-3">
						<div class="form-group">
							<label for="txtLastName">Dt. Nascimento</label> <input
								class="form-control" name="txtBirthday" type="date"
								value=<%=client.getBirthday()%>>
						</div>
					</div>
					<div class="col-3">
						<div class="form-group">
							<label for="txtLastName">Gênero</label> <select
								class="form-control" name="cbGender">
								<option selected disabled>Gênero</option>
								<%
									if (client.getGender().equals("F")) {
										out.println("<option value='F' selected>Feminino</option>");
										out.println("<option value='M'>Masculino</option>");
									} else {
										out.println("<option value='M'selected>Masculino</option>");
										out.println("<option value='F'>Feminino</option>");
									}
								%>
							</select>
						</div>
					</div>
					<div class="col-3">
						<div class="form-group">
							<label for="txtLastName">Celular</label> <input
								class="form-control" name="txtPhone" type="text"
								value=<%=client.getPhone()%>>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-2 offset-md-8">
						<a href="index.jsp" class="btn btn-primary btn-block">Cancelar</a>
					</div>
					<div class="col-2">
						<input type="hidden" name="action" id="action" value="UPDATE" />
						<input class="btn btn-primary btn-block" type="submit"
							value="Salvar" />
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script>
	$("#response").show();
	setTimeout(function() {
		$("#response").hide();
	}, 5000);
</script>