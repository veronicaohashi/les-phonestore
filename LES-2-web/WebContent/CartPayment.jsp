<%@page import="les.domain.sale.Cart"%>
<%@page import="les.domain.client.CreditCard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
.container{max-width: -webkit-fill-available !important;} 
</style>

<%@ include file="Master.jsp"%>
<div class="container-fluid py-3">

	<%
		if(request.getAttribute("response") != null){
			out.println("<div class='alert alert-primary' role='alert' id='response'>");
			out.println(request.getAttribute("response") + "</div>");
		}	

	
		Client client = (Client) session.getAttribute("user");
		CreditCard card = (CreditCard) request.getAttribute("card");
		
	%>
	<form action="Payments" method="post">
		<div class="row">
			<div class="col-md-8 mx-auto">
           		<div class="card card-body">
               		<h3 class="text-center mb-4">Forma de pagamento</h3>
			    	<div class="btn-toolbar mb-2 mb-md-0">
			      		<div class="btn-group mr-2">
					        <a class="btn btn-sm btn-outline-secondary" href="CreditCards?action=LIST&page=CART&client_id=<%= client.getId() %>">Alterar Cartão</a>
				      	</div>
			      		<div class="btn-group mr-2">
					        <a class="btn btn-sm btn-outline-secondary" href='CartPaymentForm.jsp'>Cadastrar Cartão</a>
				      	</div>
			      		<div class="btn-group mr-2">
					        <a class="btn btn-sm btn-outline-secondary" href='CartPaymentForm.jsp'>Inserir Cupom</a>
				      	</div>
			      		<div class="btn-group mr-2">
					        <a class="btn btn-sm btn-outline-secondary" href="CreditCards?action=LIST&page=2CARDS&client_id=<%= client.getId() %>">Pagar com dois cartões</a>
				      	</div>
				    </div>
                  	<div class="row">
                  		<div class="col-12">
	                   		<div class="row">
								<div class="col-7">
							  		<div class="form-group">
									    <label for="txtNumber">Número</label>
									    <input type="text" class="form-control" id="txtNumber" name="txtNumber"
									    value="<%= card.getNumber() %>" readonly>
								  	</div>
						       	</div>
								<div class="col-5">
							  		<div class="form-group">
									    <label for="txtFlag">Bandeira</label>
									    <input type="text" class="form-control" id="txtFlag" name="txtFlag"
									    value="<%= card.getFlag() %>" readonly>
								  	</div>
						       	</div>
					       	</div>
							<div class="row">
								<div class="col-4">
							  		<div class="form-group">		  		
								    	<label for="txtMonth">Mês</label>
									    <input type="text" class="form-control" id="txtMonth" name="txtMonth"
									    value="<%= card.getMonth() %>" readonly>
								  	</div>
						       	</div>
								<div class="col-4">
							  		<div class="form-group">
									    <label for="txtYear">Ano</label>
									    <input type="text" class="form-control" id="txtYear" name="txtYear"
									    value="<%= card.getYear() %>" readonly>
								  	</div>
						       	</div>
								<div class="col-4">
							  		<div class="form-group">
									    <label for="txtCode">Código</label>
									    <input type="text" class="form-control" id="txtCode" name="txtCode"
									    value="<%= card.getCode() %>" readonly>
								  	</div>
						       	</div>
					       	</div>
							<div class="row">
								<div class="col-7">
							  		<div class="form-group">
									    <label for="txtName">Nome do titular</label>
									    <input type="text" class="form-control" id="txtName" name="txtName"
									    value="<%= card.getCardholderName() %>" readonly>
								  	</div>
						       	</div>
								<div class="col-5">
							  		<div class="form-group">
									    <label for="txtCpf">CPF do titular</label>
									    <input type="text" class="form-control" id="txtCpf" name="txtCpf"
									    value="<%= card.getCardholderCpf() %>" readonly>
								  	</div>
						       	</div>
						   	</div>   	
                   		</div>
                  		</div>
      					<%

      						Cart cart = (Cart) session.getAttribute("cart");			
				    		out.println("<input type='hidden' name='price' id='price' value='" + cart.getPrice() + "' />");
				    		out.println("<input type='hidden' name='txtInstallmentPrice' id='txtInstallmentPrice' />");

      					%>	
                  	<div class="row">
						<div class="col-12">
					  		<div class="form-group">						
						    	<label for="cbInstallmentQuantity" >Parcelas</label>								  							         					
								<select class='form-control' id="cbInstallmentQuantity" name="cbInstallmentQuantity">
								</select>	
							</div>
				       	</div>
			       	</div>
					<div class="row">
						<div class="col-2 offset-md-8">
							<a
								href="Payments?action=CONSULT&client_id=<%=client.getId()%>"
								class="btn btn-primary btn-block">Cancelar</a>
						</div>
						<div class="col-2">
							<input name="id" type="hidden" value="<%=card.getId()%>">
							<input type="hidden" name="action" id="action" value="CONSULT" />
							<input class="btn btn-primary btn-block" type="submit" value="Próximo" />
						</div>
					</div>		
				</div>	
       		</div>
   		</div>
	</form>
 </div>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>
$(document).ready(function() {

	let options = "<option disabled selected>Selecione</option>";
	let price = document.getElementById("price").value;
	for(var i = 1; (price / i).toFixed(2) > 100; i++){
		options=options+"<option value='"+ i +"'>"+ i + " x de R$ "+ (price / i).toFixed(2) +"</option>";
	}

	$("#cbInstallmentQuantity").append(options);
	

	$('#cbInstallmentQuantity').on("change", function() {
		$("#txtInstallmentPrice").val((price/this.value).toFixed(2));
	})
});
</script>