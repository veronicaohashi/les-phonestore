<%@page import="les.domain.product.Phone"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    	<h1 class="h2">Celulares</h1>
    	
    	<div class="btn-toolbar mb-2 mb-md-0">
      		<div class="btn-group mr-2">
		        <a class="btn btn-sm btn-outline-secondary" href='PhoneForm.jsp'>Novo Celular</a>
	      	</div>
	    </div>
	</div>
	
    <div class="row">
		<div class="col-1">
			<b>Filtro:</b>
        </div>
		<div class="col-4">
        	<div class="form-group">
        	
            	<select class="form-control" name="cbGenero">
               	<option selected disabled>Ações</option>
				<option value='A' selected>Ativo</option>
				<option value='I'>Inativo</option>
                </select>
             </div>
        </div>
    </div>
	<table class="table">
        <thead class="thead-dark">
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
				} else {
					out.println("<td>INATIVO</td>");			
				}
				out.println("<td><a href='Phones?action=CONSULT&id=" + phone.getId() + "' class='btn btn-warning btn-group mr-2'><i class='material-icons'>edit</i></a>"
				//+"<a href='Phones?action=DELETE&id=" + phone.getId() + "' class='btn btn-danger'><i class='material-icons'>delete</i></a>"
				+"<a href='InactivationPhoneForm.jsp?phone_id=" + phone.getId() + "' class='btn btn-danger'><i class='material-icons'>delete</i></a></td>");
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