<%@page import="les.domain.product.InactivationCategory"%>
<%@page import="les.domain.product.Phone"%>
<%@page import="les.domain.product.SO"%>
<%@page import="les.domain.product.ConnectionType"%>
<%@page import="les.domain.product.Processor"%>
<%@page import="les.domain.product.Capacity"%>
<%@page import="les.domain.product.Color"%>
<%@page import="les.domain.product.Brand"%>
<%@page import="les.domain.product.PricingGroup"%>
<%@page import="les.domain.DomainEntity"%>
<%@page import="java.util.List"%>
<%@page import="les.core.application.Result"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="Master.jsp"%>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    	<h1 class="h2">Inativação do Celular: <%= request.getParameter("phone_id") %></h1>      		   
	</div>
	<form action="Phones" method="post">
	<input type="hidden" name="id" value="<%=request.getParameter("phone_id")%>" /> 
       	<div class="col-4">
			<div class="form-group">		  		
		    	<label for="cbInactivationCategory">Justificativa</label>
		  		<%
		  			Result categories = (Result)getServletContext().getAttribute("inactivationCategoryResult");
 					List<DomainEntity> categoriesList = categories.getEntities();        					
	  			  			     	
					out.println("<select class='form-control' id='cbInactivationCategory' name='cbInactivationCategory'>");
					out.println("<option value='' disabled selected>Selecione</option>");
					for(DomainEntity c : categoriesList){
						out.println("<option value='" + ((InactivationCategory)c).getId() + "'>" + ((InactivationCategory)c).getDescription() +"</option>");
					}
					out.println("</select>");
		  		%>
		  	</div>
       	</div>
		
		<input type="submit" name="action" value="DELETE" class="btn btn-sm btn-outline-secondary"/>
	</form>
</main>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
</script>