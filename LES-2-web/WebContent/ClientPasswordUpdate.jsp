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
				<h3 class="text-center">
					Alterar Cliente:
					<%=client.getId()%></h3>
			</div>
			<form action="Clients" method="post">
				<input type="hidden" name="id" value="<%= client.getId() %>">
				<div class="row">
					<div class="col-6">
						<div class="form-group">
							<label for="txtPassword">Senha</label> <input
								class="form-control" name="txtPassword" type="text">
						</div>
					</div>
					<div class="col-6">
						<div class="form-group ">
							<label for="txtPasswordConfirmation">Confirmação de Senha</label> <input
								class="form-control" name="txtPasswordConfirmation" type="text">
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