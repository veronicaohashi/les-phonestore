<%@page import="les.domain.product.Phone"%>
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
  				<h3 class="text-center">Celulares</h3>
		        <a class="btn btn-sm btn-outline-secondary admin-button" href='PhoneForm.jsp'>Novo Celular</a>
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
       		<table class="table">
		        <thead>
		        <%
					List<String> headers = (List<String>) request.getAttribute("headers");
					List<Phone> phones = (List<Phone>) request.getAttribute("phones");
		
					out.println("<tr>");
					for (String header : headers) {
						out.println("<th>" + header + "</th>");
					}
					out.println("<th>Ações</th>");
					out.println("</tr>");
					out.println("</thead>");
		
					out.println("<tbody>");
					for (Phone phone : phones){
						out.println("<tr>");
						out.println("<td  name='txtId'>" + phone.getId() +"</td>");
						out.println("<td>" + phone.getModel() +"</td>");
						out.println("<td>" + phone.getBrand().getDescription() +"</td>");
						if(phone.getLactive()){
							out.println("<td>ATIVO</td>");
							out.println("<td><a href='Phones?action=CONSULT&id=" + phone.getId() + "' class='btn btn-sm btn-outline-secondary mr-2'><i class='material-icons'>edit</i></a>"
									+"<a href='InactivationPhoneForm.jsp?phone_id=" + phone.getId() + "' class='btn btn-sm btn-outline-secondary'><i class='material-icons'>delete</i></a></td>");
						} else {
							out.println("<td>INATIVO</td>");			
							out.println("<td><a href='ActivationPhoneForm.jsp?phone_id=" + phone.getId() + "' class='btn btn-sm btn-outline-secondary mr-2'>ATIVAR</a></td>");
						}
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