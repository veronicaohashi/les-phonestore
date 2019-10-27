
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
       		<table class="table">	       		
		        <thead>
		        <%
					List<String> headers = (List<String>) request.getAttribute("headers");
					List<Coupon> coupons = (List<Coupon>) request.getAttribute("coupons");
					
		
					out.println("<tr>");
					for (String header : headers) {
						out.println("<th>" + header + "</th>");
					}
					out.println("</tr>");
					out.println("</thead>");
		
					out.println("<tbody>");
					for (Coupon c : coupons){
						out.println("<tr>");
						out.println("<td>" + c.getName() +"</td>");
						out.println("<td>" + c.getOrder().getId() +"</td>");
						out.println("<td>R$ " + c.getValue() +"</td>");
						out.println("<td>" + c.getValidity() +"</td>");	
						if(c.getLactive())
							out.println("<td>ATIVO</td>");
						else
							out.println("<td>INATIVO</td>");			
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
