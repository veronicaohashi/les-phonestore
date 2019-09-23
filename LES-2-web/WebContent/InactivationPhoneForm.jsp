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

<style>.card{border:none !important;}</style>
<%@ include file="Master.jsp"%>
<div class="row">
	<div class="col-md-12">	
  		<div class="card card-body">
       		<h3 class="text-center mb-4">Inativação do Celular: <%= request.getParameter("phone_id") %></h3>
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
				
				<div class="row">
					<div class="col-2 offset-md-8">
						<a href="Phones?action=LIST"
						class="btn btn-primary btn-block">Cancelar</a>
					</div>
					<div class="col-2">				
						<input type="hidden" name="action" id="action" value="DELETE" />
						<input class="btn btn-primary btn-block" type="submit" value="Inativar" />
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</main>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
</script>