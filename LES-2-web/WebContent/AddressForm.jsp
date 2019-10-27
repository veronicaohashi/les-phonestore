<%@page import="les.domain.client.ResidenceType"%>
<%@page import="les.domain.DomainEntity"%>
<%@page import="les.core.application.Result"%>
<%@page import="les.domain.client.Address"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="Master.jsp"%>
<style>.card{border:none !important;}</style>

<div class="container-fluid py-3">
	<%
		if(request.getAttribute("response") != null){
			out.println("<div class='alert alert-primary' role='alert' id='response'>");
			out.println(request.getAttribute("response") + "</div>");
		}	
	%>
	<form action="Addresses" method="post">
		<div class="row">
       		<div class="col-md-12">
           		<div class="card card-body">
               		<h3 class="text-center mb-4">Novo endereço de entrega</h3>
                  	<fieldset> 
	                  	<div class="row">
	                  		<div class="col-12">
		                   		<div class="row">
									<div class="col-6">
								  		<div class="form-group">
										    <label for="txtName">Nome</label>
										    <input type="text" class="form-control" id="txtName" name="txtName">
									  	</div>
							       	</div>
									<div class="col-3">
								  		<div class="form-group">		  		
									    	<label for="cbResidenceType">Tp. Residência</label>
									  		<%	

									  			Result residenceTypes = (Result)getServletContext().getAttribute("residenceTypeResult");
							   					List<DomainEntity> residenceTypesList = residenceTypes.getEntities();
										         					
												out.println("<select class='form-control' id='cbResidenceType' name='cbResidenceType'>");
												out.println("<option value='' disabled selected>Selecione</option>");
												for(DomainEntity r : residenceTypesList){
													out.println("<option value='" + ((ResidenceType)r).getId() + "'>" + ((ResidenceType)r).getDescription() +"</option>");
												}
												out.println("</select>");					      
											%>	
									  	</div>
							       	</div>
									<div class="col-3">
								  		<div class="form-group">
										    <label for="txtPostalCode">CEP</label>
										    <input type="text" class="form-control" id="txtPostalCode" name="txtPostalCode">
									  	</div>
							       	</div>
						       	</div>
								<div class="row">
									<div class="col-4">
								  		<div class="form-group">
										    <label for="txtStreet">Logradouro</label>
										    <input type="text" class="form-control" id="txtStreet" name="txtStreet" readonly>
									  	</div>
							       	</div>
									<div class="col-4">
								  		<div class="form-group">
										    <label for="txtNumber">Número</label>
										    <input type="text" class="form-control" id="txtNumber" name="txtNumber">
									  	</div>
							       	</div>
									<div class="col-4">
								  		<div class="form-group">
										    <label for="txtComplement">Complemento</label>
										    <input type="text" class="form-control" id="txtComplement" name="txtComplement">
									  	</div>
							       	</div>
							   	</div>   	
								<div class="row">
									<div class="col-4">
								  		<div class="form-group">
										    <label for="txtDistrict">Bairro</label>
										    <input type="text" class="form-control" id="txtDistrict" name="txtDistrict" readonly>
									  	</div>
							       	</div>
									<div class="col-4">
								  		<div class="form-group">
										    <label for="txtCity">Cidade</label>
										    <input type="text" class="form-control" id="txtCity" name="txtCity" readonly>
									  	</div>
							       	</div>
									<div class="col-4">
								  		<div class="form-group">
										    <label for="txtState">Estado</label>
										    <input type="text" class="form-control" id="txtState" name="txtState" readonly>
									  	</div>
							       	</div>
							   	</div>  
								<div class="row">
									<div class="col-12">
										<div class="form-group">  
									    	<label for="txtObservation">Observação</label>   
											<textarea id="txtObservation" name="txtObservation" class="form-control"></textarea>
										</div>
									</div>
								</div>	
								<div class="row">
									<div class="col-12">							
                   						<span class="text-center mb-4">* O primeiro endereço cadastrado será configurado como endereço principal</span>
									</div>
									<div class="col-12">							
                   						<span class="text-center mb-4">* O endereço cadastrado será configurado como endereço de entrega e cobrança</span>
									</div>
								</div>
								<% 
									Client client = (Client) session.getAttribute("user");

									out.println("<input type='hidden' name='lmain' id='lmain' value='false' />");
								%>
								
								<div class="row">
									<div class="col-2 offset-md-8">
										<a href="Addresses?action=LIST&client_id=<%=client.getId()%>"
										class="btn btn-primary btn-block">Cancelar</a>
									</div>
									<div class="col-2">
									    <input type="hidden" id="client_id" name="client_id" value="<%=client.getId()%>">
				                   		<input type="hidden" name="action" id="action" value="SAVE" />   
					        			<input class="btn btn-primary btn-block" type="submit" value="Cadastrar"/>
				        			</div>
			        			</div>	                   		
		        			</div>
                   		</div>					
              		</fieldset>
           		</div>
       		</div>
   		</div>
	</form>
 </div>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
    function clean() {
      $("#txtStreet").val("");
      $("#txtDistrict").val("");
      $("#txtCity").val("");
      $("#txtState").val("");
    }
    
    $("#txtPostalCode").blur(function() {
      	var cep = $(this).val().replace(/\D/g, '');
      	if (cep != "") {
	        var validacep = /^[0-9]{8}$/;
	        if(validacep.test(cep)) {
	          $("#txtStreet").val("...");
	          $("#txtDistrict").val("...");
	          $("#txtCity").val("...");
	          $("#txtState").val("...");

	          $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(data) {
	            if (!("erro" in data)) {
	                $("#txtStreet").val(data.logradouro);
	                $("#txtDistrict").val(data.bairro);
	                $("#txtCity").val(data.localidade);
	                $("#txtState").val(data.uf);
	            } 
	            else {
	              clean();
	              alert("CEP não encontrado.");
	            }
	          });
	        } else {
	        	clean();
	          	alert("Formato de CEP inválido.");
	        }
      	} else {
      		clean();
      	}
    });

    $("#response").show();
    setTimeout(function() { $("#response").hide(); }, 5000);    
    
  });
</script>