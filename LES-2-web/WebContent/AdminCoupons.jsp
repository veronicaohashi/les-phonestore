
<%@page import="les.domain.sale.Coupon"%>
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
  				<h3 class="text-center">Cupons</h3>
	        </div>	  
			<form action="Phones" method="get">
		        <div class="row">	        
		         	<div class="col-4">
						<div class="form-group">		  		
					    	<label for="lactive">Status</label>
					    	<select class='form-control' id='lactive' name='lactive'>
					    		<option value='true' selected>Ativo</option>
					    		<option value='false'>Inativo</option>
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
						List<Coupon> coupons = (List<Coupon>) request.getAttribute("coupons");
						
			
						out.println("<tr>");
						for (String header : headers) {
							out.println("<th>" + header + "</th>");
						}
						out.println("<th>Ações</th>");
						out.println("</tr>");
						out.println("</thead>");
			
						out.println("<tbody>");
						for (Coupon c : coupons){
							out.println("<tr>");
							out.println("<td>" + c.getId() +"</td>");
							out.println("<td>" + c.getClient().getCpf() +"</td>");
							out.println("<td>" + c.getClient().getFirstname() +"</td>");
							out.println("<td>" + c.getOrder().getId() +"</td>");
							out.println("<td>R$ " + c.getValue() +"</td>");
							out.println("<td>" + c.getValidity() +"</td>");
							out.println("<td><a href='Orders?action=CONSULT&id=" + c.getId() + "' class='btn btn-sm btn-outline-secondary mr-2'>" +
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
