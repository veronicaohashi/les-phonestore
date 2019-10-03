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
.container{max-width: -webkit-fill-available !important;} 
.title{font-size: 22px; margin: 0 0 0px; transition: all .3s ease 0s}
.text{color: #777676; font-size: 18px; margin-bottom:0px}
.price{color: #000; font-size: 20px; letter-spacing: 1px; font-weight: 600; margin-right: 2px; display: inline-block;}
.social{padding-inline-start: 0px !important;}
.social li{display:inline-block}
.social li a{color:#ff0018;background:#fff;font-size:18px;margin:0 35px;display:block}
.total{-ms-flex-align: center; align-items: center; display: -ms-flexbox; font-size: 16px; height: 48px; padding: 0 16px;}
.card{border:none !important;}</style>
<%
	if (request.getAttribute("response") != null) {
		out.println("<div class='alert alert-primary' role='alert' id='response'>");
		out.println(request.getAttribute("response") + "</div>");
	}
	
	Order order = (Order) request.getAttribute("order");
%>
<div class="row">
	<div class="col-12">
		<div class="row">
			<div class="col-12">	
				<div class="card">
					<div class="card-body">
			  			<div class="admin-title">
			  				<h3 class="text-center">Dados do Pedido: <%= order.getId() %></h3>
				        </div>	  
			  			<div class="admin-title">
			  				<h5 class="text-center"><%= order.getStatus().getDescription() %></h5>
				        </div>	  
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
									- <%= order.getOrderAddress().getAddress().getCity().getName() %> / <%= order.getOrderAddress().getAddress().getCity().getState().getName() %>
									- <%= order.getOrderAddress().getAddress().getPostalCode()%></p>
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
		<form action="Orders" method="post">
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-body">
					
			       		<table class="table">	       		
					        <thead>
					        <%
					
								out.println("<tr>");
									out.println("<th>Modelo</th>");
									out.println("<th>REF</th>");
									out.println("<th>Capacidade</th>");
									out.println("<th>Cor</th>");
									out.println("<th>Preço</th>");
									out.println("<th>Quantidade</th>");
									out.println("<th>Status</th>");
									if(order.getStatus().getId() == 4)
										out.println("<th>Ações</th>");
								out.println("</tr>");
								out.println("</thead>");
					
								out.println("<tbody>");
								for (Orderi item : order.getItems()) {
									out.println("<tr>");
									out.println("<td>" + item.getReference().getPhone().getModel() +"</td>");
									out.println("<td>" + item.getReference().getName() +"</td>");
									out.println("<td>" + item.getReference().getCapacity().getDescription() +"</td>");
									out.println("<td>" + item.getReference().getColor().getDescription() +"</td>");
									out.println("<td>R$ " + item.getPrice() +"</td>");
									out.println("<td>" + item.getQuantity() +"</td>");
									out.println("<td>" + item.getStatus().getDescription() +"</td>");
									
									if(item.getStatus().getId() == 4)
										out.println("<td><a href='ClientExchangeForm.jsp?id=" + item.getId() + "&status_id=" + item.getStatus().getId() + "'" +
										"class='btn btn-sm btn-outline-secondary mr-2'>Solicitar Troca</a></td>");
								
									out.println("</tr>");
								}
								out.println("</tbody>");
					
							%>
						</table>	
					</div>
				</div>
			</div>
		</div>
			<input type="hidden" name="action" id="action" value="UPDATE" /> 
		</form>    
		<div class="row">
			<div class="col-2 offset-md-10">
				<% Client client = (Client) session.getAttribute("user"); %>
				<a href="Orders?action=LIST&client_id=<%= client.getId() %>" class="btn btn-primary btn-block">Voltar</a>
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
