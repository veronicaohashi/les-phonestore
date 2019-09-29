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
       		<h3 class="text-center mb-4">Novo Celular</h3>
			<form action="Phones" method="post">
				<div class="row">
					<div class="col-4">
		 				<div class="form-group">
						    <label for="txtModel">Modelo</label>
						    <input type="text" class="form-control" id="txtModel" name="txtModel">
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
			 							out.println("<option value='" + ((Processor)p).getId() + "'>" + ((Processor)p).getDescription() +"</option>");
			 						}
			 						out.println("</select>");
					  		%>
					  	</div>
			       	</div>
			       	<div class="col-4">
						<div class="form-group">		  		
					    	<label for="txtExpandability">Capacidade de Expansão</label>
						    <input type="text" class="form-control" id="txtExpandability" name="txtExpandability">
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
						    <input type="text" class="form-control" id="txtScreenSize" name="txtScreenSize">
				  		</div>
			       	</div>
					<div class="col-2">
						<div class="form-group">
						    <label for="txtScreenResol">Resol. Tela</label>
						    <input type="text" class="form-control" id="txtScreenResol" name="txtScreenResol">
				  		</div>
			       	</div>
					<div class="col-2">
						<div class="form-group">
						    <label for="txtRcameraResol">Resol. Câm. Traseira</label>
						    <input type="text" class="form-control" id="txtRcameraResol" name="txtRcameraResol">
				  		</div>
			       	</div>
					<div class="col-2">
						<div class="form-group">
						    <label for="txtFcameraResol">Resol. Câm. Frontal</label>
						    <input type="text" class="form-control" id="txtFcameraResol" name="txtFcameraResol">
				  		</div>
			       	</div>
					<div class="col-2">
		 				<div class="form-group">
						    <label for="txtCamcorderResol">Resol. Filmadora</label>
						    <input type="text" class="form-control" id="txtCamcorderResol" name="txtCamcorderResol">
				  		</div>
		         	</div>
					<div class="col-2">
		 				<div class="form-group">
						    <label for="txtChips">Qtd. Chips</label>
						    <input type="text" class="form-control" id="txtChips" name="txtChips">
				  		</div>
		         	</div>
				</div>
				<div class="row">		
					<div class="col-3">
						<div class="form-group">
						    <label for="txtHeight">Altura</label>
						    <input type="text" class="form-control" id="txtHeight" name="txtHeight">
				  		</div>
			       	</div>
					<div class="col-3">
						<div class="form-group">
						    <label for="txtWidth">Largura</label>
						    <input type="text" class="form-control" id="txtWidth" name="txtWidth">
				  		</div>
			       	</div>
					<div class="col-3">
						<div class="form-group">
						    <label for="txtDepth">Profundidade</label>
						    <input type="text" class="form-control" id="txtDepth" name="txtDepth">
				  		</div>
			       	</div>
					<div class="col-3">
						<div class="form-group">
						    <label for="txtWeight">Peso</label>
						    <input type="text" class="form-control" id="txtWeight" name="txtWeight">
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
						    <input type="text" class="form-control" id="txtRAM" name="txtRAM">
				  		</div>
		         	</div>
					<div class="col-8">
		 				<div class="form-group">
						    <label for="txtPackage">Conteúdo da embalagem</label>
						    <input type="text" class="form-control" id="txtPackage" name="txtPackage">
				  		</div>
		         	</div>
		       	</div>
		     	<div class="row">
		       		<div class="col-12">
						<div class="form-group">  
					    	<label for="txtNote">Observação</label>   
							<textarea id=txtNote name="txtNote" class="form-control"></textarea>
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
					<div class="col-4">
						<div class="form-group">		  		
					    	<label for="cbColor0">Cor</label>
							<%
							
								out.println("<select class='form-control' id='cbColor0' name='cbColor0'>");
								out.println("<option value='' disabled selected>Selecione</option>");
					    			for(DomainEntity c : colorList){
									out.println("<option value='" + ((Color)c).getId() + "'>" + ((Color)c).getDescription() +"</option>");
								}
								out.println("</select>");
							%>	
						</div>
					</div>   
					<div class="col-4">
						<div class="form-group">		  		
					    	<label for="cbCapacity0">Capacidade</label>
							<%
								out.println("<select multiple class='form-control' id='cbCapacity0' name='cbCapacity0'>");
								out.println("<option value='' disabled selected>Selecione</option>");
								for(DomainEntity c : capacitiesList){
									out.println("<option value='" + ((Capacity)c).getId() + "'>" + ((Capacity)c).getDescription() +"</option>");
								}
								out.println("</select>");
							%>	
						</div>
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
					
					    <input type="hidden" id="lactive" name="lactive" value="true">
						<input type="hidden" name="action" id="action" value="SAVE" />
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
	var count = 0
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