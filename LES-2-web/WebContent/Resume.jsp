<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="les.domain.sale.Coupon"%>
<%@page import="les.domain.sale.OrderCoupons"%>
<%@page import="les.domain.sale.OrderAddress"%>
<%@page import="les.domain.sale.PaymentData"%>
<%@page import="les.domain.sale.Payment"%>
<%@page import="les.domain.client.CreditCard"%>
<%@page import="les.domain.sale.Orderi"%>
<%@page import="les.domain.client.Address"%>
<%@page import="les.domain.sale.Cart"%>
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
</style>
<div class="container">
	<%
		if(request.getAttribute("response") != null){
		out.println("<div class='alert alert-primary' role='alert' id='response'>");
		out.println(request.getAttribute("response") + "</div>");
			}
	%>
	<%
		Cart cart = (Cart) session.getAttribute("cart"); 
		OrderAddress orderAddress = (OrderAddress) session.getAttribute("address");
		CreditCard card = (CreditCard) session.getAttribute("card");
		Payment payment = (Payment) session.getAttribute("payment");
		OrderCoupons coupons = (OrderCoupons)session.getAttribute("coupons");
	%>
	<form action="Orders" method="post">
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
					  					<p class="text"><b><%= orderAddress.getAddress().getName()%></b></p>
					  					<p class="text"><b>Logradouro: </b><%= orderAddress.getAddress().getStreet()%>, <%= orderAddress.getAddress().getNumber()%>,
					  					 <%= orderAddress.getAddress().getDistrict()%> -
					  					 <%= orderAddress.getAddress().getPostalCode()%></p>
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
		       					 <% if(payment != null){ %>
						  			<div class="row">
						  			<%
						  				int count = 1;
						  				for(PaymentData p : payment.getPaymentDatas()){
		               				%>
					  					<div class="col-4" style="padding-bottom: 15px;">
				  							<p class="text"><b><%= count %>º Cartão</b></p>                				
						  					<p class="text"><b><%=p.getCard().getNumber()%></b></p>
						  					<p class="text"><b>Parcelas: <%= p.getQuantity() %> x R$<%= p.getPrice() %></b></p>
						  				</div>
						  				
				  					<% 		count++; 
				  						} 
			  						%>
					  				</div>
		        				<% } %>
		        				
	       						<% if(coupons != null){
       								out.println("<div class='row'>");
	       							for(Coupon c : coupons.getCoupons()){
	       								out.println("<div class='col-4'>");
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
	       						}
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
	 								for(Orderi item : cart.getItems()){
	 							%>
					  			<div class="row">
					  				<div class="col-10">
					  					<p class="text"><b><%= item.getReference().getPhone().getModel() %></b></p>
					  					<p class="text"><b>REF: </b><%= item.getReference().getName() %> -
				                        <b>Capacidade: </b><%= item.getReference().getCapacity().getDescription() %> -
				                        <b>Cor: </b><%= item.getReference().getColor().getDescription() %> -  	
				                        <%= item.getQuantity() %> x R$<%= item.getReference().getPhone().getSalePrice() %></p>  	
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
				
				<% 
					out.println("<input type='hidden' name='totalItems' id='totalItems' value='" + cart.getItems().size() + "' />");
				%>
	        <div class="col-4">	  			
	            <div class="row" style="padding-bottom: 20px;">
					<div class="left"><b>Total Itens: </b></div>
					<div class="right"><input style='border-width:0px !important;text-align: right;' name='txtQuantity' id='txtQuantity' value='<%= cart.getQuantity() %>'/></div>
				</div>				
	            <div class="row" style="padding-bottom: 20px;">
					<div class="left"><b>Subtotal: </b></div>
					<div class="right"><input style='border-width:0px !important;text-align: right;' name='price' id='price' value='<%= cart.getTotalItemsPrice() %>'/></div>
				</div>	
	            <div class="row" style="padding-bottom: 20px;">
					<div class="left"><b>Frete: </b></div>
					<div class="right"><input style='border-width:0px !important;text-align: right;' name='price' id='price' value='<%= cart.getFreightPrice() %>'/></div>
				</div>	
	            <div class="row" style="padding-bottom: 20px;">
					<div class="left"><b>Cupom: </b></div>
					<div class="right"><input style='border-width:0px !important;text-align: right;' name='price' id='price' value='<%= cart.getTotalDiscountPrice() %>'/></div>
				</div>	
				<hr>
	            <div class="row" style="padding-bottom: 50px;">
					<div class="left"><b>Total: </b></div>
					<div class="right"><%= cart.getPrice() %></div>
				</div>
				
           		<input type="hidden" name="action" id="action" value="SAVE"/>
           		<input class="btn btn-primary btn-lg btn-block text-uppercase" type="submit" value="Finalizar"/>			
	        </div>
	    </div>
	</form>
 </div>
<script>
$("#resposta").show();
setTimeout(function() { $("#resposta").hide(); }, 5000);     
</script>
