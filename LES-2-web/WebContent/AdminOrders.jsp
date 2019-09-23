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
         	<div class="col-4">
				<div class="form-group">		  		
			    	<label for="cbStatus">Status</label>
			  		<%
			  			Result status = (Result)getServletContext().getAttribute("statusResult");
	 					List<DomainEntity> statusList = status.getEntities();        			
			    		int index = 1;
		  			  			     	
 						out.println("<select class='form-control' id='cbStatus' name='cbStatus'>");
  						out.println("<option value='' disabled selected>Selecione</option>");
  						for(DomainEntity s : statusList){
  							if(index <= 4)
  								out.println("<option value='" + ((Status)s).getId() + "'>" + ((Status)s).getDescription() +"</option>");
  							index++;
  						}
  						out.println("</select>");
			  		%>
			  	</div>
         	</div>      
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
						out.println("<td>" + order.getClient().getCpf() +"</td>");
						out.println("<td>" + order.getClient().getFirstname() +"</td>");
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
