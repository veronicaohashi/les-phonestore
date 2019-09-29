<%@page import="java.util.List"%>
<%@page import="les.domain.client.Address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
.container{max-width: -webkit-fill-available !important;} 
</style>

<%@ include file="Master.jsp"%>
<div class="container-fluid py-3">
	<form action="OrderAddresses" method="post">
		<div class="row">
       		<div class="col-md-12 mx-auto">
           		<div class="card card-body">
               		<h3 class="text-center mb-4">Endereços de entrega</h3>
                  	<fieldset> 
                  		<table class="table">
					        <thead>
					        <%
								List<String> headers = (List<String>) request.getAttribute("headers");
								List<Address> addresses = (List<Address>) request.getAttribute("addresses");
								Client client = (Client) session.getAttribute("user");
					        
								out.println("<tr>");
								for (String header : headers) {
									out.println("<th>" + header + "</th>");
								}
								out.println("</tr>");
								out.println("</thead>");
				
								out.println("<tbody>");
								
								for(Address a : addresses){
									out.println("<tr>");
									out.println("<td>" + a.getName() +"</td>");
									out.println("<td>" + a.getPostalCode() +"</td>");
									out.println("<td>" + a.getStreet() +"</td>");
									out.println("<td>" + a.getNumber() +"</td>");
									out.println("<td>" + a.getComplement() +"</td>");
									out.println("<td>" + a.getDistrict() +"</td>");
									out.println("<td>" + a.getCity().getName() +"</td>");
									out.println("<td>" + a.getCity().getState().getName() +"</td>");
									out.println("<td><a class='btn btn-sm btn-outline-primary'" +
											"href='Addresses?action=CONSULT&id=" + a.getId() + "&page=PAYMENT'>Selecionar</a></td>");
									out.println("</tr>");	        
								}
								out.println("</tbody>");				
							%>
				    	</table>    
						<div class="row">
							<div class="col-2 offset-md-10">
								<a
									href="Addresses?action=CONSULT&client_id=<%=client.getId()%>&page=CART"
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