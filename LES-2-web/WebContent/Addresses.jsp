<%@page import="les.domain.client.Address"%>
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

	Client client = (Client) session.getAttribute("user");

 %>
<div class="row">
	<div class="col-md-12">	
  		<div class="card card-body">  		
  			<div class="admin-title">
  				<h3 class="text-center">Endereços</h3>
		        <a class="btn btn-sm btn-outline-secondary admin-button" href='AddressForm.jsp'>Novo endereço</a>
	        </div>
       		<table class="table">
		        <thead>
		        <%
					List<String> headers = (List<String>) request.getAttribute("headers");
					List<Address> addresses = (List<Address>) request.getAttribute("addresses");

					out.println("<tr>");
					for (String header : headers) {
						out.println("<th>" + header + "</th>");
					}
					out.println("</tr>");
					out.println("</thead>");
		
					out.println("<tbody>");
					for (Address a : addresses){
						out.println("<tr>");
						out.println("<td>" + a.getName() +"</td>");
						out.println("<td>" + a.getPostalCode() +"</td>");
						out.println("<td>" + a.getStreet() +"</td>");
						out.println("<td>" + a.getNumber() +"</td>");
						out.println("<td>" + a.getComplement() +"</td>");
						out.println("<td>" + a.getDistrict() +"</td>");
						out.println("<td>" + a.getCity().getName() +"</td>");
						out.println("<td>" + a.getCity().getState().getName() +"</td>");						
						out.println("<td><a href='Addresses?action=DELETE&id=" + a.getId() + "&client_id=" + client.getId() +"' class='btn btn-sm btn-outline-secondary'><i class='material-icons'>delete</i></a></td>");
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
</main>