<%@page import="les.domain.stock.Stock"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  				<h3 class="text-center">Estoque</h3>
		        <a class="btn btn-sm btn-outline-secondary admin-button" href='EntryForm.jsp'>Nova Entrada</a>
	        </div>
       		<table class="table">
		        <thead>
			        <%
						List<String> headers = (List<String>) request.getAttribute("headers");
						List<Stock> stockItems = (List<Stock>) request.getAttribute("stockItems");
			
						out.println("<tr>");
						for (String header : headers) {
							out.println("<th>" + header + "</th>");
						}
						out.println("</tr>");
						out.println("</thead>");
			
						out.println("<tbody>");
						for (Stock item : stockItems){
							out.println("<tr>");
							out.println("<td>" + item.getReference().getName() +"</td>");
							out.println("<td>" + item.getReference().getPhone().getModel() +"</td>");
							out.println("<td>" + item.getReference().getColor().getDescription() +"</td>");
							out.println("<td>" + item.getReference().getCapacity().getDescription() +"</td>");
							out.println("<td>" + item.getQuantity() +"</td>");
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
