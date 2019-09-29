<%@page import="les.core.application.Result"%>
<%@page import="les.domain.DomainEntity"%>
<%@page import="les.domain.sale.Cart"%>
<%@page import="les.domain.client.CreditCard"%>
<%@page import="java.util.List"%>
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
	Cart cart = (Cart) session.getAttribute("cart");
	out.println("<input type='hidden' name='price' id='price' value='" + cart.getPrice() + "' />");

	if(request.getAttribute("response") != null){
		out.println("<div class='alert alert-primary' role='alert' id='response'>");
		out.println(request.getAttribute("response") + "</div>");
	}	
%>		
<div class="container-fluid py-3">
	<form action="Payments" method="post">
		<div class="row">
			<div class="col-md-12 mx-auto">
				<div class="card card-body">
					<h3 class="text-center mb-4">Pagamento com 2 cartões de
						crédito</h3>
					<div class="row">
						<div class="col-6">
							<h5 class="text-center mb-4">1º Cartão</h5>
							<div class="row">
								<div class="col-12">
									<div class="form-group">
										<label for="id">Cartões</label>
										<%
											List<CreditCard> cards = (List<CreditCard>) request.getAttribute("cards");

											out.println("<select class='form-control' id='id' name='id'>");
											out.println("<option value='' disabled selected>Selecione</option>");
											for (DomainEntity c : cards) {
												out.println("<option value='" + ((CreditCard) c).getId() + "'>" + ((CreditCard) c).getNumber()
														+ "</option>");
											}
											out.println("</select>");
										%>
									</div>
								</div>
								<div class="col-12">
									<div class="form-group">
										<label for="txtValue">Valor</label> <input type="text"
											class="form-control" id="txtValue" name="txtValue">
									</div>
								</div>
								<div class="col-12">
									<div class="form-group">
										<label for="cbInstallmentQuantity">Parcelas</label> <select
											class='form-control' id="cbInstallmentQuantity"
											name="cbInstallmentQuantity" disabled>
											<option disabled selected>Selecione</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-6">
							<h5 class="text-center mb-4">2º Cartão</h5>
							<div class="row">
								<div class="col-12">
									<div class="form-group">
										<label for="id1">Cartões</label> <select class="form-control"
											id="id1" name="id1" disabled>
										</select>
									</div>
								</div>
								<div class="col-12">
									<div class="form-group">
										<label for="txtValue1">Valor</label> <input type="text"
											class="form-control" id="txtValue1" name="txtValue1" readonly>
									</div>
								</div>
								<div class="col-12">
									<div class="form-group">
										<label for="cbInstallmentQuantity1">Parcelas</label> <select
											class='form-control' id="cbInstallmentQuantity1"
											name="cbInstallmentQuantity1">
											<option disabled selected>Selecione</option>
										</select>
									</div>
								</div>
							</div>
						</div>

						<%
							Client client = (Client) session.getAttribute("user");
							out.println("<input type='hidden' name='client_id' id='client_id' value='" + client.getId() + "' />");
							out.println("<input type='hidden' name='txtInstallmentPrice' id='txtInstallmentPrice' />");
							out.println("<input type='hidden' name='txtInstallmentPrice1' id='txtInstallmentPrice1' />");
						%>
						<div class="col-2 offset-md-8">
							<a
								href="CreditCards?action=CONSULT&client_id=<%=client.getId()%>&page=CART"
								class="btn btn-primary btn-block">Cancelar</a>
						</div>
						<div class="col-2">				                   		
							<input type="hidden" name="page" id="page" value="2CARDS" /> 						
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
	$(document)
			.ready(
					function() {
						let price = document.getElementById("price").value;

						$('#id')
								.on(
										"change",
										function() {
											let firstCard = document
													.getElementById("id");
											let options = "";

											for (let i = 1; i < firstCard.options.length; i++) {
												if (firstCard.options[i].value != this.value)
													options = options
															+ "<option value='"+firstCard.options[i].value+"'>"
															+ firstCard.options[i].text
															+ "</option>";
											}

											$("#id1").prop('disabled', false);
											$("#id1").find("option").remove();
											$("#id1").empty();
											$("#id1").append(options);
										});

						$('#txtValue')
								.on(
										"input",
										function() {
											let options = "<option disabled selected>Selecione</option>";

											let price = document
													.getElementById("price").value;
											for (var i = 1; (this.value / i)
													.toFixed(2) > 100; i++) {
												options = options
														+ "<option value='"+ i +"'>"
														+ i
														+ " x de R$ "
														+ (this.value / i)
																.toFixed(2)
														+ "</option>";
											}

											$("#cbInstallmentQuantity").prop(
													'disabled', false);
											$("#cbInstallmentQuantity").empty();
											$("#cbInstallmentQuantity").append(
													options);

											let secondValue = price
													- this.value;
											$("#txtValue1").val(secondValue);

											let options2 = "<option disabled selected>Selecione</option>";
											for (var i = 1; (secondValue / i)
													.toFixed(2) > 100; i++) {
												options2 = options2
														+ "<option value='"+ i +"'>"
														+ i
														+ " x de R$ "
														+ (secondValue / i)
																.toFixed(2)
														+ "</option>";
											}

											$("#cbInstallmentQuantity1").prop(
													'disabled', false);
											$("#cbInstallmentQuantity1")
													.empty();
											$("#cbInstallmentQuantity1")
													.append(options2);
										});

						$("#cbInstallmentQuantity").on(
								"change",
								function() {
									let value = document
											.getElementById("txtValue").value;
									$("#txtInstallmentPrice").val(
											(value / this.value).toFixed(2));
								})

						$("#cbInstallmentQuantity1").on(
								"change",
								function() {
									let value1 = document
											.getElementById("txtValue1").value;
									$("#txtInstallmentPrice1").val(
											(value1 / this.value).toFixed(2));
								})
					});
</script>