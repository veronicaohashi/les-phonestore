<%@page import="les.domain.stock.Supplier"%>
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
    	<h1 class="h2">Nova Entrada</h1>      		   
	</div>
	<form action="Entry" method="post">
		<% out.println("<input type='hidden' name='txtReference' id='txtReference' />"); %>
	
		<div class="row">
			<div class="col-4">			
		  		<div class="form-group">		  		
			    	<label for="cbSupplier">Fornecedor</label>
			  		<%
		  				Result suppliers = (Result)getServletContext().getAttribute("supplierResult");
 						List<DomainEntity> supplierList = suppliers.getEntities();        					
	  			  			     	
						out.println("<select class='form-control' id='cbSupplier' name='cbSupplier'>");
						out.println("<option value='' disabled selected>Selecione</option>");
						for(DomainEntity s : supplierList){
							out.println("<option value='" + ((Supplier)s).getId() + "'>" + ((Supplier)s).getName() +"</option>");
						}
						out.println("</select>");
		  		%>
			  	</div>
			</div>
			<div class="col-3">
		  		<div class="form-group">
				    <label for="txtDate">Data</label>
				    <input type="date" class="form-control" id="txtDate" name="txtDate">
			  	</div>
           	</div>
         	<div class="col-4">
				<div class="form-group">		  		
			    	<label for="cbPhones">Celulares</label>
					<select id="cbPhones" class='form-control' name="cbPhones">
						<option value='' disabled selected>Selecione</option>							
					</select>
			  	</div>
         	</div>
		</div>
		<div class="row">
			<div class="offset-3 col-6" >
				<table class="table">
		        	<thead class="thead-dark">
			    		<tr>
			    			<td style="width:20%">REF</td>
			    			<td>Cor</td>
			    			<td>Capacidade</td>
			    			<td></td>
			    			<td></td>
			    		</tr>
			    	</thead>
	    			<tbody id="references">    	
					    <%
					
					      
						%>
		    		</tbody>
		  		</table>			     
			</div>
		</div>	
		
		<input type="submit" name="action" value="SAVE" class="btn btn-sm btn-outline-secondary"/>
	</form>
</main>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$.get("Phones?action=LIST&json=true").done(function(data){
	    let options="";
		$.each(data, function(key, value){
			options=options+"<option value='"+value.id+"'>"+value.model+"</option>";
		})

		$("#cbPhones").append(options);
	});  
	

	$('#cbPhones').on("change", function(){
		$.get("References?action=CONSULT&phone_id=" + this.value).done(function(data){
			
		    let options="";
			let count = 0;
			console.log(data)
			
			$.each(data, function(key, value){
				options=options+"<tr>"
				options=options+"<td>" + value.name + "</td>"
				options=options+"<td>" + value.color.description + "</td>"
				options=options+"<td>" + value.capacity.description + "</td>"
				options=options+"<td><input type='text' class='form-control' id='txtQuantity"+count+"' name='txtQuantity"+count+"' placeholder='Quantidade'></td>"
				options=options+"<td><input type='text' class='form-control' id='txtPrice"+count+"' name='txtPrice"+count+"' placeholder='Valor'></td>"
				options=options+"<td><input type='hidden' class='form-control' id='txtReference"+count+"' name='txtReference"+count+"' value="+value.id+" ></td>"
				options=options+"</tr>"
				count ++;

				//options=options+"<option value='"+value.id+"'>"+value.nome+"</option>";
				//$('<option>').val(value.id).text(value.nome).appendTo($("#cbDepartamento"))
			})				
			
			$("#txtReference").val(data.length);
			$("#references").empty();
			$("#references").append(options);
		})
	})
});
</script>