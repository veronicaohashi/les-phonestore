<%@page import="les.domain.product.ActivationCategory"%>
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
       		<h3 class="text-center mb-4">Ativação do Celular: <%= request.getParameter("phone_id") %></h3>
			<form action="Phones" method="post">
			<input type="hidden" name="id" value="<%=request.getParameter("phone_id")%>" /> 
		       	<div class="col-4">
					<div class="form-group">		  		
				    	<label for="cbActivationCategory">Justificativa</label>
				  		<%
				  			Result categories = (Result)getServletContext().getAttribute("activationCategoryResult");
		 					List<DomainEntity> categoriesList = categories.getEntities();    			    		
			  			  			     	
							out.println("<select class='form-control' id='cbActivationCategory' name='cbActivationCategory'>");
							out.println("<option value='' disabled selected>Selecione</option>");
							for(DomainEntity a : categoriesList){
								out.println("<option value='" + ((ActivationCategory)a).getId() + "'>" + ((ActivationCategory)a).getDescription() +"</option>");
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
					    <input type="hidden" id="lactive" name="lactive" value="true">		
						<input type="hidden" name="action" id="action" value="UPDATE" />
						<input class="btn btn-primary btn-block" type="submit" value="Ativar" />
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