<%@page import="java.util.List"%>
<%@page import="les.domain.client.Address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
.container {
	max-width: -webkit-fill-available !important;
}
</style>

<%@ include file="Master.jsp"%>
<%
	if(request.getAttribute("response") != null){
		out.println("<div class='alert alert-primary' role='alert' id='response'>");
		out.println(request.getAttribute("response") + "</div>");
	}	
%>
<div class="container-fluid py-3">
	<form action="OrderAddresses" method="post">
		<%
			Address address = (Address) request.getAttribute("address");
			Client client = (Client) session.getAttribute("user");
		%>
		<div class="row">
			<div class="col-md-8 mx-auto">
				<div class="card card-body">
					<h3 class="text-center mb-4">Endereço de entrega</h3>
					<div style="display: flex;align-items: center;justify-content:center;" class="btn-toolbar mb-2 mb-md-0">
						<div class="btn-group mr-2">
							<a class="btn btn-sm btn-outline-secondary"
								href="Addresses?action=LIST&client_id=<%=client.getId()%>&page=CART">Trocar
								Endereço</a>
						</div>
						<div class="btn-group mr-2">
							<a class="btn btn-sm btn-outline-secondary"
								href='CartAddressForm.jsp'>Cadastrar Endereço</a>
						</div>
					</div>
					<fieldset>
						<div class="row">
							<div class="col-12">
								<div class="row">
									<input name="id" type="hidden" value="<%=address.getId()%>">
									<div class="col-6">
										<div class="form-group">
											<label for="txtNome">Nome</label> <input type="text"
												class="form-control" id="txtNome" name="txtNome"
												value="<%=address.getName()%>" readonly>
										</div>
									</div>
									<div class="col-3">
										<div class="form-group">
											<label for="txtTpResidencia">Tp. Residência</label> <input
												type="text" class="form-control" id="txtTpResidencia"
												name="txtTpResidencia"
												value="<%=address.getResidenceType().getDescription()%>"
												readonly>
										</div>
									</div>
									<div class="col-3">
										<div class="form-group">
											<label for="txtCep">CEP</label> <input type="text"
												class="form-control" id="txtCep" name="txtCep"
												value="<%=address.getPostalCode()%>" readonly>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-4">
										<div class="form-group">
											<label for="txtLogradouro">Logradouro</label> <input
												type="text" class="form-control" id="txtLogradouro"
												name="txtLogradouro" value="<%=address.getStreet()%>"
												readonly>
										</div>
									</div>
									<div class="col-4">
										<div class="form-group">
											<label for="txtNumero">Número</label> <input type="text"
												class="form-control" id="txtNumero" name="txtNumero"
												value="<%=address.getNumber()%>" readonly>
										</div>
									</div>
									<div class="col-4">
										<div class="form-group">
											<label for="txtComplemento">Complemento</label> <input
												type="text" class="form-control" id="txtComplemento"
												name="txtComplemento" value="<%=address.getComplement()%>"
												readonly>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-4">
										<div class="form-group">
											<label for="txtBairro">Bairro</label> <input type="text"
												class="form-control" id="txtBairro" name="txtBairro"
												value="<%=address.getDistrict()%>" readonly>
										</div>
									</div>
									<div class="col-4">
										<div class="form-group">
											<label for="txtCidade">Cidade</label> <input type="text"
												class="form-control" id="txtCidade" name="txtCidade"
												value="<%=address.getCity().getName()%>" readonly>
										</div>
									</div>
									<div class="col-4">
										<div class="form-group">
											<label for="txtEstado">Estado</label> <input type="text"
												class="form-control" id="txtEstado" name="txtEstado"
												value="<%=address.getCity().getState().getName()%>"
												readonly>
										</div>
										<input type="hidden" name="state_id" id="state_id" value="<%= address.getCity().getState().getId() %>" /> 
									</div>
								</div>
								<div class="row">
									<div class="col-12">
										<div class="form-group">
											<label for="txtObservacao">Observação</label>
											<textarea id="txtObservacao" name="txtObservacao"
												class="form-control" readonly><%=address.getObservation()%></textarea>
										</div>
									</div>
								</div>
			                  	<div class="row">
			                    	<div class="col-md-6">
				                        <div class="form-group">
				                            <label for="cbFreight">Frete</label>
				                            <select class="custom-select" id="cbFreight" name="cbFreight">
				                            </select>
				                        </div>
			                        </div>
						       	</div>	
							</div>
						</div>
						<div class="row">
							<div class="col-2 offset-md-10">
								<input type="hidden" name="page" id="page" value="PAYMENT" /> 
								<input type="hidden" name="action" id="action" value="CONSULT" />
								<input class="btn btn-primary btn-block" type="submit" value="Próximo" />
							</div>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
	</form>
</div>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>
$(document).ready(function() {	
	$("#response").show();
	setTimeout(function() { $("#response").hide(); }, 5000);
	
	var state_id = document.getElementById("state_id");	    
	$.get("Freight?action=LIST&state_id=" + state_id.value).done(function(data){
		let options = "<option disabled selected>Selecione</option>";
	 	console.log(data)
		$.each(data, function(key, value){
			options=options+"<option value='"+value.id+"'>"+value.description+" - R$ "+ value.price +"</option>";
		})				
			
		$("#cbFreight").empty();
		$("#cbFreight").append(options);
		
	});	
});
</script>