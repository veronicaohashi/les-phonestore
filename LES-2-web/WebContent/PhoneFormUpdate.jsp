<%@page import="les.domain.product.Reference"%>
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
<%
	Phone phone  = (Phone) request.getAttribute("phone");
%>

<div class="row">
	<div class="col-md-12">	
  		<div class="card card-body">	
			<form action="Phones" method="post">   
			<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		    	<h3>Alterar Celular: <%= phone.getId() %></h3>  	
		   		<div class="btn-group mr-2">
					<div class="form-group">
					    <label for="txtCostPrice">Vl. Custo</label>
					    <input type="text" class="form-control" id="txtCostPrice" name="txtCostPrice" readonly value="<%=phone.getCostPrice()%>">
			  		</div>
					<div class="form-group">
					    <label for="txtSalePrice">Vl. Venda</label>
					    <input type="text" class="form-control" id="txtSalePrice" name="txtSalePrice" value="<%=phone.getSalePrice()%>">
			  		</div>
				</div>	   
			</div>
			<input type="hidden" name="id" value="<%=phone.getId()%>" /> 
				<div class="row">
					<div class="col-4">
		 				<div class="form-group">
						    <label for="txtModel">Modelo</label>
						    <input type="text" class="form-control" id="txtModel" name="txtModel" value="<%=phone.getModel()%>">
				  		</div>
		         	</div>
		         	<div class="col-4">
						<div class="form-group">		  		
					    	<label for="cbBrand">Marca</label>
					  		<%
					  			Result brands = (Result)getServletContext().getAttribute("brandResult");
			 					List<DomainEntity> brandList = brands.getEntities();        					
				  			  			     	
		 						out.println("<select class='form-control' id='cbBrand' name='cbBrand'>");
		  						out.println("<option value='' disabled selected>Selecione</option>");
		  						for(DomainEntity b : brandList){
		  							if(b.getId() == phone.getBrand().getId())
										out.println("<option value='" + phone.getBrand().getId()	 +"' selected>"+ phone.getBrand().getDescription()	 +"</option>");
		  							else
		  								out.println("<option value='" + ((Brand)b).getId() + "'>" + ((Brand)b).getDescription() +"</option>");
		  						}
		 
		  						out.println("</select>");
					  		%>
					  	</div>
		         	</div>
		       		<div class="col-4">
						<div class="form-group">		  		
					    	<label for="cbSO">Sistema Operacional</label>
					    	<%
					  			Result so = (Result)getServletContext().getAttribute("soResult");
			 					List<DomainEntity> soList = so.getEntities();          					
				  			  			     	
									out.println("<select class='form-control' id='cbSO' name='cbSO'>");
			 						out.println("<option value='' disabled selected>Selecione</option>");
			 						for(DomainEntity p : soList){
			  							if(p.getId() == phone.getSo().getId())
											out.println("<option value='" + phone.getSo().getId()	 +"' selected>"+ phone.getSo().getDescription()	 +"</option>");
			  							else
			 								out.println("<option value='" + ((SO)p).getId() + "'>" + ((SO)p).getDescription() +"</option>");
			 						}
			 						out.println("</select>");
					  		%>
					  	</div>
		         	</div> 
		       	</div>
		       	<div class="row">
		       		<div class="col-4">
						<div class="form-group">		  		
					    	<label for="cbProcessor">Processador</label>
					    	<%
					  			Result processor = (Result)getServletContext().getAttribute("processorResult");
			 					List<DomainEntity> processorList = processor.getEntities();          					
				  			  			     	
									out.println("<select class='form-control' id='cbProcessor' name='cbProcessor'>");
			 						out.println("<option value='' disabled selected>Selecione</option>");
			 						for(DomainEntity p : processorList){
			  							if(p.getId() == phone.getProcessor().getId())
											out.println("<option value='" + phone.getProcessor().getId()	 +"' selected>"+ phone.getProcessor().getDescription()	 +"</option>");
			  							else
			 								out.println("<option value='" + ((Processor)p).getId() + "'>" + ((Processor)p).getDescription() +"</option>");
			 						}
			 						out.println("</select>");
					  		%>
					  	</div>
			       	</div>
			       	<div class="col-4">
						<div class="form-group">		  		
					    	<label for="txtExpandability">Capacidade de Expansão</label>
						    <input type="text" class="form-control" id="txtExpandability" name="txtExpandability" value="<%=phone.getExpandability()%>">
					  	</div>
			       	</div>
					<div class="col-4">
				  		<div class="form-group">		  		
					    	<label for="cbPricingGroup">G. Precificação</label>
					  		<%
					  			Result groups = (Result)getServletContext().getAttribute("pricingGroupResult");
			 					List<DomainEntity> groupList = groups.getEntities();
					  			  			        
								out.println("<select class='form-control' id='cbPricingGroup' name='cbPricingGroup'>");
								out.println("<option value='' disabled selected>Selecione</option>");
								for(DomainEntity gp : groupList){
		  							if(gp.getId() == phone.getPricingGroup().getId())
										out.println("<option value='" + phone.getPricingGroup().getId()	 +"' selected>"+ phone.getPricingGroup().getDescription()	 +"</option>");
		  							else
										out.println("<option value='" + ((PricingGroup)gp).getId() + "'>" +  ((PricingGroup)gp).getDescription()  +"</option>");
								}
								out.println("</select>");
					  		%>
					  	</div>
			       	</div>
		       	</div>
				<div class="row">		
					<div class="col-2">
						<div class="form-group">
						    <label for="txtScreenSize">Tam. Tela</label>
						    <input type="text" class="form-control" id="txtScreenSize" name="txtScreenSize" value="<%=phone.getScreenSize()%>">
				  		</div>
			       	</div>
					<div class="col-2">
						<div class="form-group">
						    <label for="txtScreenResol">Resol. Tela</label>
						    <input type="text" class="form-control" id="txtScreenResol" name="txtScreenResol"  value="<%=phone.getScreenResol()%>">
				  		</div>
			       	</div>
					<div class="col-2">
						<div class="form-group">
						    <label for="txtRcameraResol">Resol. Câm. Traseira</label>
						    <input type="text" class="form-control" id="txtRcameraResol" name="txtRcameraResol" value="<%=phone.getRcameraResol()%>">
				  		</div>
			       	</div>
					<div class="col-2">
						<div class="form-group">
						    <label for="txtFcameraResol">Resol. Câm. Frontal</label>
						    <input type="text" class="form-control" id="txtFcameraResol" name="txtFcameraResol" value="<%=phone.getFcameraResol()%>">
				  		</div>
			       	</div>
					<div class="col-2">
		 				<div class="form-group">
						    <label for="txtCamcorderResol">Resol. Filmadora</label>
						    <input type="text" class="form-control" id="txtCamcorderResol" name="txtCamcorderResol" value="<%=phone.getCamcorderResol()%>">
				  		</div>
		         	</div>
					<div class="col-2">
		 				<div class="form-group">
						    <label for="txtChips">Qtd. Chips</label>
						    <input type="text" class="form-control" id="txtChips" name="txtChips" value="<%=phone.getChip()%>">
				  		</div>
		         	</div>
				</div>
				<div class="row">		
					<div class="col-3">
						<div class="form-group">
						    <label for="txtHeight">Altura</label>
						    <input type="text" class="form-control" id="txtHeight" name="txtHeight" value="<%=phone.getHeight()%>">
				  		</div>
			       	</div>
					<div class="col-3">
						<div class="form-group">
						    <label for="txtWidth">Largura</label>
						    <input type="text" class="form-control" id="txtWidth" name="txtWidth" value="<%=phone.getWidth()%>">
				  		</div>
			       	</div>
					<div class="col-3">
						<div class="form-group">
						    <label for="txtDepth">Profundidade</label>
						    <input type="text" class="form-control" id="txtDepth" name="txtDepth" value="<%=phone.getDepth()%>">
				  		</div>
			       	</div>
					<div class="col-3">
						<div class="form-group">
						    <label for="txtWeight">Peso</label>
						    <input type="text" class="form-control" id="txtWeight" name="txtWeight" value="<%=phone.getWeight()%>">
				  		</div>
			       	</div>
		       	</div>
				<div class="row">	
		       		<div class="col-2">
						<div class="form-group">		  		
					    	<label for="cbConnection">Conectividade</label>			    	
					  		<%
					  			Result connections = (Result)getServletContext().getAttribute("connectionTypeResult");
			 					List<DomainEntity> connectionList = connections.getEntities();
					  			  			        
								out.println("<select multiple class='form-control' id='cbConnection' name='cbConnection'>");
								out.println("<option value='' disabled selected>Selecione</option>");
								for(DomainEntity c : connectionList){		
									out.println("<option value='" + ((ConnectionType)c).getId() + "'>" +  ((ConnectionType)c).getDescription()  +"</option>");
								}
								out.println("</select>");
					  		%>
					  	</div>
		         	</div>
					<div class="col-2">
		 				<div class="form-group">
						    <label for="txtRAM">Memória RAM</label>
						    <input type="text" class="form-control" id="txtRAM" name="txtRAM" value="<%=phone.getRamMemory()%>">
				  		</div>
		         	</div>
					<div class="col-8">
		 				<div class="form-group">
						    <label for="txtPackage">Conteúdo da embalagem</label>
						    <input type="text" class="form-control" id="txtPackage" name="txtPackage" value="<%=phone.getPackageContent()%>">
				  		</div>
		         	</div>
		       	</div>
		     	<div class="row">
		       		<div class="col-12">
						<div class="form-group">  
					    	<label for="txtNote">Observação</label>   
							<textarea id=txtNote name="txtNote" class="form-control"><%=phone.getNote()%></textarea>
						</div>
					</div>  
				</div>
				<%
				
					Result color = (Result)getServletContext().getAttribute("colorResult");
					List<DomainEntity> colorList = color.getEntities();   
							
					Result capacities = (Result)getServletContext().getAttribute("capacityResult");
					List<DomainEntity> capacitiesList = capacities.getEntities();
				%> 
					   	
				<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		    		<h5>Referências</h5>
		      		<div class="btn-group mr-2">
						<a class="btn btn-sm btn-outline-secondary" id="referencia">ADICIONAR</a>
			      	</div>
				</div>		
				<div class="row">
					<div class="offset-3 col-6">
						<table class="table">
				        	<thead class="thead-dark">
					    		<tr>
					    			<td>REF</td>
					    			<td>Cor</td>
					    			<td>Capacidade</td>
					    			<td></td>
					    		</tr>
					    	</thead>
			    			<tbody>    	
							    <%
							
							      for(Reference ref : phone.getReference()){
									out.println("<tr>");
										out.println("<td>" + ref.getName() + "</td>");
										out.println("<td>" + ref.getColor().getDescription() + "</td>");
										out.println("<td>" + ref.getCapacity().getDescription() + "</td>");    
										out.println("<td><a href='References?action=DELETE&id="   + ref.getId() + "&phone_id="   + phone.getId() + "' class='btn btn-danger'><i class='material-icons'>delete</i></a></td>");
									out.println("</tr>");
								}
								%>
				    		</tbody>
				  		</table>			     
					</div>
				</div>	
				<div id="referencias"> 
				</div>
				
				
				<div class="row">
					<div class="col-2 offset-md-8">
						<a href="Phones?action=LIST"
						class="btn btn-primary btn-block">Cancelar</a>
					</div>
					<div class="col-2">				
						<input type="hidden" name="action" id="action" value="UPDATE" />
						<input class="btn btn-primary btn-block" type="submit" value="Salvar" />
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#cbCategoria').on("change", function(){
			$.get("Departamentos?acao=CONSULTAR&fk_categoria=" + this.value).done(function(data){
			    let options="";

				$.each(data, function(key, value){
					options=options+"<option value='"+value.id+"'>"+value.nome+"</option>";
				})				

				$("#cbDepartamento").empty();
				$("#cbDepartamento").append(options);
			})
		})
     	
	});
	var count = -1;
	$('#referencia').click(function(){
		count ++;
		$('#referencias').append('<div class="row">'
				+'<div class="col-4">'
				+'<label for="cbColor' + count +'" >Cor</label>'
				+'<select class="form-control" id="cbColor' + count +'" name="cbColor' + count +'">'
				+'<option value="" disabled selected>Selecione</option>'
				+'</select>'
				+'</div>'
				+'<div class="col-4">'
				+'<label for="cbCapacity' + count +'" >Capacidade</label>'
				+'<select multiple class="form-control" id="cbCapacity' + count +'" name="cbCapacity' + count +'">'
				+'<option value="" disabled selected>Selecione</option>'
				+'</select>'
				+'</div></div>');	
		
		$.get("Colors?action=LIST").done(function(data){
			if(data != null){
		     	let options="";

				$.each(data, function(key, value){
					options=options+"<option value='"+value.id+"'>"+value.description+"</option>";
				})
				
				$('#cbColor' + (count)).empty();
				$('#cbColor' + (count)).append(options);			  	
				
				$.get("Capacities?action=LIST").done(function(data){
			     	let tam_options="";
			     	
					$.each(data, function(key, value){
						tam_options=tam_options+"<option value='"+value.id+"'>"+value.description+"</option>";
					})
					
					$('#cbCapacity' + (count)).empty();
					$('#cbCapacity' + (count)).append(tam_options);					
				})				
			}			
		})	
	})
</script>