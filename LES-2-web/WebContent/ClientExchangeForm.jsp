<%@page import="les.domain.sale.ExchangeCategory"%>
<%@page import="les.core.application.Result"%>
<%@page import="les.domain.DomainEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>.card{border:none !important;}</style>
<%@ include file="Master.jsp"%>
<div class="row">
	<div class="col-md-12">	
  		<div class="card card-body">
       		<h3 class="text-center mb-4">Troca do Item: <%=request.getParameter("id")%></h3>
			<form action="Orderi" method="post">
				<input type="hidden" name="id" value="<%=request.getParameter("id")%>" /> 
				<input type="hidden" name="status_id" value="<%=request.getParameter("status_id")%>" /> 
				<input type="hidden" name="delivery_date" value="<%=request.getParameter("delivery_date")%>" /> 
		       	<div class="col-4">
					<div class="form-group">		  		
				    	<label for="cbExchangeCategory">Justificativa</label>
				  		<%
				  			Result categories = (Result)getServletContext().getAttribute("exchangeCategoryResult");
		 					List<DomainEntity> categoriesList = categories.getEntities();        					
  			  			  			     	
  							out.println("<select class='form-control' id='cbExchangeCategory' name='cbExchangeCategory'>");
  							out.println("<option value='' disabled selected>Selecione</option>");
  							for(DomainEntity e : categoriesList){
  								out.println("<option value='" + ((ExchangeCategory)e).getId() + "'>" + ((ExchangeCategory)e).getDescription() +"</option>");
  							}
  							out.println("</select>");
				  		%>
				  	</div>
		       	</div>
				
				<div class="row">
					<div class="col-2 offset-md-8">
						<a href="Phones?action=LIST"
						class="btn btn-primary btn-block">Cancelar</a>
					</div>
					<div class="col-2">				
						<input type="hidden" name="action" id="action" value="UPDATE" />
						<input class="btn btn-primary btn-block" type="submit" value="Solicitar Troca" />
						
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
