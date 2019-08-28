<%@page import="les.domain.stock.Stock"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="Master.jsp"%>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<% 
		if(request.getAttribute("response") != null){
			out.println("<div class='alert alert-primary' role='alert' id='response'>");
			out.println(request.getAttribute("response") + "</div>");
		}
	 %>
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    	<h1 class="h2">Estoque</h1>
    	<div class="btn-toolbar mb-2 mb-md-0">
      		<div class="btn-group mr-2">
		        <a class="btn btn-sm btn-outline-secondary" href='EntryForm.jsp'>Nova Entrada</a>
	      	</div>
	    </div>
	</div>
	<table class="table">
        <thead class="thead-dark">
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
 	<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
	<script>
		$("#response").show();
		setTimeout(function() { $("#response").hide(); }, 5000);
	</script>
</main>