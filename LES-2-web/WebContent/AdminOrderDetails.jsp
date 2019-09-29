<%@page import="les.domain.sale.PaymentData"%>
<%@page import="les.domain.sale.Orderi"%>
<%@page import="les.domain.sale.Order"%>
<%@page import="les.core.application.Result"%>
<%@page import="les.domain.sale.Status"%>
<%@page import="les.domain.DomainEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="Master.jsp"%>

<style>
.card {
	border: none !important;
}
</style>
<%
	if (request.getAttribute("response") != null) {
		out.println("<div class='alert alert-primary' role='alert' id='response'>");
		out.println(request.getAttribute("response") + "</div>");
	}
	
	Order order = (Order) request.getAttribute("order");
%>
<div class="row">
	<div class="col-8">
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-body">
						<ul class="social">
							<li><h3 class="title">Endereço de entrega</h3></li>
						</ul>
						<div class="row">
							<div class="col-10">
								<p class="text">
									<b><%= order.getOrderAddress().getAddress().getName()%></b>
								</p>
								<p class="text">
									<b>Logradouro: </b><%= order.getOrderAddress().getAddress().getStreet()%>,
									<%= order.getOrderAddress().getAddress().getNumber()%>,
									<%= order.getOrderAddress().getAddress().getDistrict()%>
									-
									<%= order.getOrderAddress().getAddress().getPostalCode()%></p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-body">
						<ul class="social">
							<li>
								<%
									if (order.getPayment().getPaymentDatas().size() == 1) {
										out.println("<h3 class='title'>Forma de pagamento (1 Cartão)</h3>");
									} else {
										out.println("<h3 class='title'>Forma de pagamento (2 Cartões)</h3>");
									}
								%>
							</li>
						</ul>
						<div class="row">
							<%
								int count = 1;
								for (PaymentData p : order.getPayment().getPaymentDatas()) {
							%>
							<div class="col-4">
								<p class="text">
									<b><%=count%>º Cartão</b>
								</p>
								<p class="text">
									<b><%=p.getCard().getNumber()%></b>
								</p>
								<p class="text">
									<b>Parcelas: <%=p.getQuantity()%> x <%=p.getPrice()%></b>
								</p>
							</div>

							<%
								count++;
								}
							%>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-body">
						<ul class="social">
							<li><h3 class="title">Produto(s)</h3></li>
						</ul>
						<%
							int i = 0;
							for (Orderi item : order.getItems()) {
						%>
						<div class="row">
							<div class="col-10">
								<p class="text">
									<b><%=item.getReference().getPhone().getModel()%></b>
								</p>
								<p class="text">
									<b>REF: </b><%=item.getReference().getName()%>
									- <b>Capacidade: </b><%=item.getReference().getCapacity().getDescription()%>
									- <b>Cor: </b><%=item.getReference().getColor().getDescription()%>
									-
									<%=item.getQuantity()%>
									x R$<%=item.getReference().getPhone().getSalePrice()%></p>
							</div>
						</div>
						<%
							i++;
							}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="card card-body">
			<div class="admin-title">
				<h3 class="text-center">Dadados do pedido</h3>
			</div>
			<table class="table">

				<%

					out.println("<thead>");
					out.println("<tr>");
					out.println("<th></th>");
					out.println("<th></th>");
					out.println("<th>Ações</th>");
					out.println("</tr>");
					out.println("</thead>");

					out.println("<tbody>");
					for (Orderi orderi : order.getItems()) {
						out.println("<tr>");
						out.println("<td>" + orderi.getId() + "</td>");
						out.println("<td>" + orderi.getReference().getName() + "</td>");
						out.println("<td>" + orderi.getReference().getPhone().getModel() + "</td>");
						out.println("<td>" + orderi.getReference().getCapacity().getDescription() + "</td>");
						out.println("<td>" + orderi.getReference().getColor().getDescription() + "</td>");

						out.println("</tr>");
					}
					out.println("</tbody>");
				%>
			</table>

			<div class="row">
				<div class="col-2 offset-md-10">
					<a href="Orders?action=LIST&status_id=1"
						class="btn btn-primary btn-block">Voltar</a>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$("#response").show();
	setTimeout(function() {
		$("#response").hide();
	}, 5000);
</script>
