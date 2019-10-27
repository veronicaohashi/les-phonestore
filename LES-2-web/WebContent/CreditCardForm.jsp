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
	<form action="CreditCards" method="post">
		<div class="row">
       		<div class="col-md-12">
           		<div class="card card-body">
               		<h3 class="text-center mb-4">Novo cartão de crédito</h3>
					<div class="row">
						<div class="col-12">
							<div class="row">
								<div class="col-7">
									<div class="form-group">
										<label for="txtNumber">Número</label> <input type="text"
											class="form-control" id="txtNumber" name="txtNumber">
									</div>
								</div>
								<div class="col-5">
									<div class="form-group">
										<label for="txtFlag">Bandeira</label> <input type="text"
											class="form-control" id="txtFlag" name="txtFlag" readonly>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-4">
									<div class="form-group">
										<label for="txtMonth">Mês de validade</label> <input type="text"
											class="form-control" id="txtMonth" name="txtMonth">
									</div>
								</div>
								<div class="col-4">
									<div class="form-group">
										<label for="txtYear">Ano de validade</label> <input type="text"
											class="form-control" id="txtYear" name="txtYear">
									</div>
								</div>
								<div class="col-4">
									<div class="form-group">
										<label for="txtCode">Código</label> <input type="text"
											class="form-control" id="txtCode" name="txtCode">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-7">
									<div class="form-group">
										<label for="txtName">Nome do titular</label> <input
											type="text" class="form-control" id="txtName" name="txtName">
									</div>
								</div>
								<div class="col-5">
									<div class="form-group">
										<label for="txtCpf">CPF do titular</label> <input type="text"
											class="form-control" id="txtCpf" name="txtCpf">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-12">							
      						<span class="text-center mb-4">* O cartão cadastrado será configurado como cartão principal</span>
						</div>
					</div>
					<%
						Client client = (Client) session.getAttribute("user");
						out.println("<input type='hidden' name='lmain' id='lmain' value='false' />");
 						out.println("<input type='hidden' name='client_id' id='client_id' value='" + client.getId() + "' />");
					%>
					
					<div class="row">
						<div class="col-2 offset-md-8">
							<a href="CreditCards?action=LIST&client_id=<%= client.getId() %>"
							class="btn btn-primary btn-block">Cancelar</a>
						</div>
						<div class="col-2">
							<input type="hidden" name="action" id="action" value="SAVE" />
		        			<input class="btn btn-primary btn-block" type="submit" value="Cadastrar"/>
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
	$('#txtNumber').on("input",function(){
		var cardnumber = this.value.replace(/[^0-9]+/g, '');
		console.log(this.value)
			var cards = {
				Visa : /^4[0-9]{12}(?:[0-9]{3})/,                                                                   
				Mastercard : /^5[1-5][0-9]{14}/,																	         
				Diners : /^3(?:0[0-5]|[68][0-9])[0-9]{11}/,                                                           
				Amex : /^3[47][0-9]{13}/,																		     
				Discover : /^6(?:011|5[0-9]{2})[0-9]{12}/,															     
				Hipercard : /^(606282\d{10}(\d{3})?)|(3841\d{15})/,												         	
				Elo : /^((((636368)|(438935)|(504175)|(451416)|(636297))\d{0,10})|((5067)|(4576)|(4011))\d{0,12})/,
				Jcb : /^(?:2131|1800|35\d{3})\d{11}/,        													    
				Aura : /^(5078\d{2})(\d{2})(\d{11})$/      														 
			};

        for (var flag in cards) {
	        if(cards[flag].test(cardnumber)) {
	        	$('#txtFlag').val(flag);
	        	break;
	        } else {
	        	$('#txtFlag').val('');
	        }
        }      
	});
})
	$("#response").show();
	setTimeout(function() {
		$("#response").hide();
	}, 5000);
</script>