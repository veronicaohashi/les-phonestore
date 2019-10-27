<%@page import="les.domain.sale.Order"%>
<%@page import="les.core.application.Result"%>
<%@page import="les.domain.sale.Status"%>
<%@page import="les.domain.DomainEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="Master.jsp"%>

<style>.card{border:none !important;}</style>
<% 
	if(request.getAttribute("response") != null){
		out.println("<div class='alert alert-primary' role='alert' id='response'>");
		out.println(request.getAttribute("response") + "</div>");
	}
 %>
<div class="row">
	<div class="col-md-12">	
  		<div class="card card-body">  		
  			<div class="admin-title">
  				<h3 class="text-center">Pedidos</h3>
	        </div>	  
			<form action="Orders" method="post">
	       		<table class="table">	       		
			        <thead>
			        <%
						List<String> headers = (List<String>) request.getAttribute("headers");
						List<Order> orders = (List<Order>) request.getAttribute("orders");
			
						out.println("<tr>");
						for (String header : headers) {
							out.println("<th>" + header + "</th>");
						}
						out.println("<th>Ações</th>");
						out.println("</tr>");
						out.println("</thead>");
			
						out.println("<tbody>");
						for (Order order : orders){
							out.println("<tr>");
							out.println("<td>" + order.getId() +"</td>");
							out.println("<td>" + order.getOrderDate() +"</td>");
							out.println("<td>R$ " + order.getPrice() +"</td>");
							out.println("<td>" + order.getQuantity() +"</td>");
							out.println("<td>" + order.getStatus().getDescription() +"</td>");
							out.println("<td><a href='Orders?action=CONSULT&id=" + order.getId() + "' class='btn btn-sm btn-outline-secondary mr-2'>" +
							"<i class='material-icons'>visibility</i></a></td>");
						
							out.println("</tr>");
						}
						out.println("</tbody>");
			
					%>
				</table>	 
			</form>                  
	    </div>
    </div>
</div>    
<script>
$("#response").show();
setTimeout(function() { $("#response").hide(); }, 5000);
</script>
