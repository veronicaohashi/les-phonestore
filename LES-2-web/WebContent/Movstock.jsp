<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="les.domain.stock.Movstock"%>
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

	List<Movstock> movstock = (List<Movstock>) request.getAttribute("movstock");
 %>
<div class="row">
	<div class="col-md-12">	
  		<div class="card card-body">  		
  			<div class="admin-title">
  				<h3 class="text-center">Movimentações no Estoque: <%= movstock.get(0).getReference().getName() %> </h3>
	        </div>	  
       		<table class="table">	       		
		        <thead>
		        <%
					List<String> headers = (List<String>) request.getAttribute("headers");
		
					out.println("<tr>");
					for (String header : headers) {
						out.println("<th>" + header + "</th>");
					}
					out.println("</tr>");
					out.println("</thead>");
		
					out.println("<tbody>");
					for (Movstock m : movstock){
						out.println("<tr>");
						out.println("<td>R$" + m.getPrice() +"</td>");
						out.println("<td>" + m.getQuantity() +"</td>");
						out.println("<td>" + m.getSupplier().getName() +"</td>");
						out.println("<td>" + LocalDate.parse(m.getDate()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +"</td>");
						out.println("<td>" + m.getMovstockType().getDescription() +"</td>");
						out.println("<td>" + m.getOrigin() +"</td>");						
						out.println("</tr>");
					}
					out.println("</tbody>");
		
				%>
			</table>
	    </div>
    </div>
</div>            
<script>
$("#response").show();
setTimeout(function() { $("#response").hide(); }, 5000);
</script>
