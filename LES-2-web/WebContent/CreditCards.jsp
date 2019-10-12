<%@page import="les.domain.client.CreditCard"%>
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
  				<h3 class="text-center">Cartões de crédito</h3>
		        <a class="btn btn-sm btn-outline-secondary admin-button" href='AddressForm.jsp'>Novo cartão</a>
	        </div>
       		<table class="table">
		        <thead>
		        <%
					List<String> headers = (List<String>) request.getAttribute("headers");
					List<CreditCard> cards = (List<CreditCard>) request.getAttribute("cards");

					out.println("<tr>");
					for (String header : headers) {
						out.println("<th>" + header + "</th>");
					}
					out.println("</tr>");
					out.println("</thead>");
		
					out.println("<tbody>");
					for (CreditCard c : cards){
						out.println("<tr>");
						out.println("<td>" + c.getNumber() +"</td>");
						out.println("<td>" + c.getCode() +"</td>");
						out.println("<td>" + c.getFlag() +"</td>");
						out.println("<td>" + c.getMonth() +"</td>");
						out.println("<td>" + c.getYear() +"</td>");
						out.println("<td>" + c.getCardholderName() +"</td>");
						out.println("<td>" + c.getCardholderCpf() +"</td>");
						
						out.println("<td><a href='Phones?action=CONSULT&id=" + c.getId() + "' class='btn btn-sm btn-outline-secondary mr-2'><i class='material-icons'>edit</i></a>"
								+"<a href='InactivationPhoneForm.jsp?phone_id=" + c.getId() + "' class='btn btn-sm btn-outline-secondary'><i class='material-icons'>delete</i></a></td>");
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