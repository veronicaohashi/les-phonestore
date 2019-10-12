<%@page import="les.domain.sale.Coupon"%>
<%@page import="les.domain.sale.OrderCoupons"%>
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
		OrderCoupons coupons = (OrderCoupons) session.getAttribute("coupons");
		
	%>
	<div id="alerts"></div>
	<form action="Payments" method="post">
		<div class="row">
			<div class="col-md-8 mx-auto">
           		<div class="card card-body">
               		<h3 class="text-center mb-4">Forma de pagamento</h3>
			    	<div style="display: flex;align-items: center;justify-content:center;" class="btn-toolbar mb-2 mb-md-0">
			      		<div class="btn-group mr-2">
					        <a class="btn btn-sm btn-outline-secondary" href="CreditCards?action=LIST&page=CART&client_id=<%= client.getId() %>">Trocar Cartão</a>
				      	</div>
			      		<div class="btn-group mr-2">
					        <a class="btn btn-sm btn-outline-secondary" href='CartPaymentForm.jsp'>Cadastrar Cartão</a>
				      	</div>
			      		<div class="btn-group mr-2">
					        <a class="btn btn-sm btn-outline-secondary" href='Coupons?action=LIST&page=CART&client_id=<%= client.getId() %>&lactive=true'>Inserir Cupom</a>
				      	</div>
			      		<div class="btn-group mr-2">
					        <a class="btn btn-sm btn-outline-secondary" onclick="validatePrice()">Pagar com dois cartões</a>
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
			    		out.println("<input type='text' name='price' id='price' value='" + cart.getPrice() + "' />");
			    		out.println("<input type='text' name='client' id='client' value='" + client.getId() + "' />");
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
   					<% 
   					if(coupons != null) {
					%>
	                  	<div class="row">
							<div class="col-12">
						  		<span class="text-center mb-4">* Você está utilizando <%= coupons.getCoupons().size() %> 
						  		cupom(ns)! Valor total de desconto: R$<%= cart.getTotalDiscountPrice() %></span>
					       	</div>
				       	</div>			       	
		       		<% 	
		       			int i = 0;
		       			for(Coupon c : coupons.getCoupons()){
				    		out.println("<input type='text' name='txtCouponValue"+i+"' id='txtCouponValue"+i+"' value='"+c.getValue()+"'/>");
		       				i++;
		       			}
					} 
					%>
					<div class="row">
						<div class="col-2 offset-md-10">
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
	let minPrice = 100;
	
	if(price < 100)
		minPrice = price;
	
	for(var i = 1; (price / i).toFixed(2) >= minPrice; i++){
		options=options+"<option value='"+ i +"'>"+ i + " x de R$ "+ (price / i).toFixed(2) +"</option>";
	}
	
	$("#cbInstallmentQuantity").append(options);	
	$('#cbInstallmentQuantity').on("change", function() {
		$("#txtInstallmentPrice").val((price/this.value).toFixed(2));
	})
});

function validatePrice(){
	let price = document.getElementById("price").value;	
	if(price < 100){
		$("#response").hide();
	    $('#alerts').append("<div class='alert alert-primary' role='alert' id='response'>" +
		"Não é possível utilizar dois cartões em pedidos com valor menor que R$100.0</div>");
		setTimeout(function() { $("#response").hide(); }, 5000);
	} else {
		let client = document.getElementById("client").value;	
		 window.location.replace("CreditCards?action=LIST&page=2CARDS&client_id="+client);
	}
		
}
</script>