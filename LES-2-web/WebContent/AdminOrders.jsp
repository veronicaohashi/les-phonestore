<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
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
			<form action="Orders" method="get">
		        <div class="row">	        
		         	<div class="col-4">		         	
						<div class="form-group">		  		
					    	<label for="status_id">Status</label>
					    	<select class='form-control' id='status_id' name='status_id'>
						    	<%
						  			Result status = (Result)getServletContext().getAttribute("statusResult");
				 					List<DomainEntity> statusList = status.getEntities();     
						    		String status_id = request.getParameter("status_id");
						    			
						    		int index = 1;			  			  			     	
			  						for(DomainEntity s : statusList){
			  							if(index <= 4){
			  								if(s.getId() == Integer.parseInt(status_id))
			  									out.println("<option value='" + ((Status)s).getId() + "' selected>" + ((Status)s).getDescription() +"</option>");
			  								else
			  									out.println("<option value='" + ((Status)s).getId() + "'>" + ((Status)s).getDescription() +"</option>");
			  							}
			 								index++;
			  						}
			  						out.println("</select>");
						  		%>
					    	</select>
					  	</div>		        				  	
		         	</div>    
		         	<div class="col-1">	         	
						<input type="hidden" name="action" id="action" value="LIST" />
						<input style="margin-top: 35px;" class="btn btn-sm btn-outline-secondary" type="submit" value="Filtrar" />
		         	</div> 	 	 
	         	</div>     
         	</form> 
			<form action="Orders" method="post">
	       		<table class="table">	       		
			        <thead>
			        <%
						List<String> headers = (List<String>) request.getAttribute("headers");
						List<Order> orders = (List<Order>) request.getAttribute("orders");
			
						out.println("<tr>");
						if(Integer.parseInt(status_id) != 4)
							out.println("<th></th>");
						for (String header : headers) {
							out.println("<th>" + header + "</th>");
						}
						out.println("<th>Ações</th>");
						out.println("</tr>");
						out.println("</thead>");
			
						out.println("<tbody>");
						for (Order order : orders){
							out.println("<tr>");
							if(Integer.parseInt(status_id) != 4)
								out.println("<td><input style='margin:0px !important' class='form-check-input' type='checkbox' name='lupdate' value=" + order.getId() + "></td>");	
							out.println("<td>" + order.getId() +"</td>");
							out.println("<td>" + order.getClient().getCpf() +"</td>");
							out.println("<td>" + order.getClient().getFirstname() +"</td>");
							out.println("<td>" + LocalDate.parse(order.getOrderDate()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +"</td>");
							out.println("<td>R$ " + order.getPrice() +"</td>");
							out.println("<td>" + order.getQuantity() +"</td>");
							out.println("<td><a href='Orders?action=CONSULT&id=" + order.getId() + "' class='btn btn-sm btn-outline-secondary mr-2'>" +
							"<i class='material-icons'>visibility</i></a></td>");
						
							out.println("</tr>");
						}
						out.println("</tbody>");
			
					%>
				</table>	
				<% if(Integer.parseInt(status_id) != 4){ %>
					<div class="row">
						<div class="col-2 offset-md-10">
							<input type="hidden" name="status_id" id="status_id" value="<%= status_id %>" />
							<input type="hidden" name="action" id="action" value="UPDATE" />
							<input class="btn btn-primary btn-block" type="submit" value="Alterar Status" />
		       			</div>
	      			</div>
      			<%} %>	 
			</form>                  
	    </div>
    </div>
</div>    
<script>
$("#response").show();
setTimeout(function() { $("#response").hide(); }, 5000);
</script>
