<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="les.domain.sale.OrderCoupons"%>
<%@page import="les.domain.sale.Coupon"%>
<%@page import="les.domain.sale.Cart"%>
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
			out.println("Cupom inválido</div>");
		}	
	
		OrderCoupons orderCoupons = (OrderCoupons) session.getAttribute("coupons");	
	%>
	<div id="alerts"></div>
	<form action="OrderCoupons" method="post">
		<div class="row">
			<div class="col-md-8 mx-auto">
           		<div class="card card-body">
               		<h3 class="text-center mb-4">Pagamento com cupons</h3>               		
					<div class="row">
						<div class="col-8">
							<h5 class="text-center mb-4">Cupons de Troca</h5>
							<div class="row">
								<div class="col-12">
			                  		<table class="table">
								        <thead>
								        <%
											List<String> headers = (List<String>) request.getAttribute("headers");
											List<Coupon> coupons = (List<Coupon>) request.getAttribute("coupons");
											Client client = (Client) session.getAttribute("user");

											out.println("<tr>");
											out.println("<th></th>");
											for (String header : headers) {
												out.println("<th>" + header + "</th>");
											}
											out.println("</tr>");
											out.println("</thead>");
							
											out.println("<tbody>");
											
											for(Coupon c : coupons){
												out.println("<tr>");
												if(orderCoupons != null){
													for(Coupon oc : orderCoupons.getCoupons()){
														if(oc.getId() == c.getId()){
															out.println("<td><input style='margin:0px !important' class='form-check-input' type='checkbox' name='lcoupons' value=" + c.getId() + " checked></td>");	
															break;
														} else{
															out.println("<td><input style='margin:0px !important' class='form-check-input' type='checkbox' name='lcoupons' value=" + c.getId() + "></td>");	
														}

													}
												} else {
													out.println("<td><input style='margin:0px !important' class='form-check-input' type='checkbox' name='lcoupons' value=" + c.getId() + "></td>");	

												}
												out.println("<td>" + c.getName() +"</td>");
												out.println("<td>" + c.getOrder().getId() +"</td>");
												out.println("<td>R$ " + c.getValue() +"</td>");
												out.println("<td>" + LocalDate.parse(c.getValidity()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +"</td>");						
												out.println("</tr>");	        
											}
											out.println("</tbody>");				
										%>
							    	</table>
						    	</div>
					    	</div>
				    	</div>    
						<div class="col-4">
							<h5 class="text-center mb-4">Cupom Promocional</h5>						
					        <div class="row">
								<div class="col-7 offset-md-2">
									<div class="form-group">
										<label for="txtName">Código do cupom</label> 
										<%
											boolean coupon = false;
											if(orderCoupons != null) {
												for(Coupon c : orderCoupons.getCoupons()){
													if(c.getCouponCategory().getId() == 2){
														out.println("<input type='text' class='form-control' id='txtName'"
														+ "name='txtName' value='"+ c.getName() +"'>");
														coupon = true;
													}
												}	
											}
											
											if(! coupon){
										%>
											<input type="text" class="form-control" id="txtName" name="txtName">
										<%
											}
										%>
										<input type="hidden" name="txtCouponCategory" id="txtCouponCategory" value="2" />	
									</div>
						    	</div>
					         	<div class="col-1">	         	
									<a style="margin-top: 35px;" class="btn btn-sm btn-outline-secondary" id="btnValidate">Validar</a>
					         	</div>  
				         	</div> 
					    	<div class="row">
 									<div class="col-7 offset-md-2">
									<input type="text" style='border-width:0px !important' class="form-control" id="txtDiscount" name="txtDiscount">
				    			</div>
						  	</div>	    
				    	</div> 
			    	</div>   			    	
					<div class="row">
						<div class="col-2 offset-md-8">
							<a href="CreditCards?action=CONSULT&client_id=<%=client.getId()%>&page=CART"
								class="btn btn-primary btn-block">Cancelar</a>
						</div>
						<div class="col-2">				                   	
							<input type="hidden" name="page" id="page" value="PAYMENT" />	
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
		var name = document.getElementById("txtName");		    
			
	    $('#btnValidate').on("click", function(){
			$.get("Coupons?action=CONSULT&txtName=" + name.value + "&txtCouponCategory=" + 2).done((data) => {
				$("#response").hide();
				
				if(data.length > 0){
				    $('#alerts').append("<div class='alert alert-primary' role='alert' id='response'>" +
		    		"Cupom válido! Desconto de R$" + data[0].value + "</div>");
					setTimeout(function() { $("#response").hide(); }, 5000);
										
				} else {					
				    $('#alerts').append("<div class='alert alert-primary' role='alert' id='response'>" +
		    		"Cupom inválido</div>");
					setTimeout(function() { $("#response").hide(); }, 5000);
					
				}
			});
	    });   	
	});
</script>
