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
	<form action="CreditCards" method="post">
		<div class="row">
			<div class="col-md-8 mx-auto">
           		<div class="card card-body">
               		<h3 class="text-center mb-4">Cartões de cŕedito</h3>
                  	<fieldset> 
                  		<table class="table">
					        <thead>
					        <%
								List<String> headers = (List<String>) request.getAttribute("headers");
								List<CreditCard> cards = (List<CreditCard>) request.getAttribute("cards");
								Client client = (Client) session.getAttribute("user");
					        
								out.println("<tr>");
								for (String header : headers) {
									out.println("<th>" + header + "</th>");
								}
								out.println("</tr>");
								out.println("</thead>");
				
								out.println("<tbody>");
								
								for(CreditCard c : cards){
									out.println("<tr>");
									out.println("<td>" + c.getNumber() +"</td>");
									out.println("<td>" + c.getCode() +"</td>");
									out.println("<td>" + c.getFlag() +"</td>");
									out.println("<td>" + c.getMonth() +"</td>");
									out.println("<td>" + c.getYear() +"</td>");
									out.println("<td>" + c.getCardholderName() +"</td>");
									out.println("<td>" + c.getCardholderCpf() +"</td>");
									out.println("<td><a class='btn btn-sm btn-outline-primary'" +
											"href='CreditCards?action=CONSULT&page=CART&&id=" + c.getId() + "'>Selecionar</a></td>");
									out.println("</tr>");	        
								}
								out.println("</tbody>");				
							%>
				    	</table>    
						<div class="row">
							<div class="col-2 offset-md-10">
								<a href="CreditCards?action=CONSULT&client_id=<%=client.getId()%>&page=CART"
									class="btn btn-primary btn-block">Cancelar</a>
							</div>
						</div>	         	
              		</fieldset>
           		</div>
       		</div>
   		</div>
	</form>
 </div>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>