<%@page import="les.domain.sale.Coupon"%>
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
               				<li><h3 class='title'>Forma de pagamento</h3></li>
              				</ul> 
       					 <% if(order.getPayment() != null){ %>
				  			<div class="row">
				  			<%
				  				int count = 1;
				  				for(PaymentData p : order.getPayment().getPaymentDatas()){
               				%>
			  					<div class="col-4" style="padding: 15px;">
		  							<p class="text"><b><%= count %>º Cartão</b></p>                				
				  					<p class="text"><b><%=p.getCard().getNumber()%></b></p>
				  					<p class="text"><b>Parcelas: <%= p.getQuantity() %> x R$<%= p.getPrice() %></b></p>
				  				</div>
				  				
		  					<% 		count++; 
		  						} 
	  						%>
			  				</div>
        				<% } %>
        				
      						<% //if(order.getOrderCoupons() != null){
     								out.println("<div class='row'>");
      							for(Coupon c : order.getOrderCoupons().getCoupons()){
      								out.println("<div class='col-4' style='padding: 15px;'>");
      								if(c.getCouponCategory().getId() == 1)     								
                  						out.println("<p class='text'><b>Cupom de Troca</b></p>");
                 						else                   										
                  						out.println("<p class='text'><b>Cupom de Desconto</b></p>");
            						%>			
			  					<p class="text"><b><%= c.getName()%></b>: R$<%= c.getValue() %></p>
				  		<%
				  				out.println("</div>"); 
      							}
			  				out.println("</div>"); 
      						//}
				  		%>
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
									x R$<%=item.getPrice()%></p>
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
		
		<div class="row">
			<div class="col-2 offset-md-10">
				<a href="Orders?action=LIST&status_id=1"
					class="btn btn-primary btn-block">Voltar</a>
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
