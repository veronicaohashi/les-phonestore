<%@page import="les.domain.product.Reference"%>
<%@page import="les.domain.product.Phone"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
	<style>
		.container{max-width: -webkit-fill-available !important;} 
		.card{border: none !important }
		.title{text-transform: capitalize; margin: 0 0 0px; transition: all .3s ease 0s;}
		.text{color: #414141; font-size: 16px}
		.ref{color: #000; font-size: 16px; letter-spacing: 1px;}
		.price{margin-top: 45px;margin-right: 2px;color: #000;font-size: 26px;letter-spacing: 1px;font-weight: 600;display: inline-block;}
		.tab-content{padding-top: 20px;}
	</style>
</head>
<%@ include file="Master.jsp"%>
<% Phone phone = (Phone) request.getAttribute("phone"); %>
<div class="container">
    <div class="row">
        <div class="col-12 col-lg-6">
            <div class="card mb-3">
                <div class="card-body">                    
                 <img class="img-fluid" src="https://dummyimage.com/600x600/55595c/fff" />
               </div>
            </div>
        </div>
        <div class="col-12 col-lg-6">
            <div class="card mb-3">
                <div class="card-body">
                	<h3 class="title"><%= phone.getModel() %></h3>
					<% out.println("<input class='ref' style='border-width:0px !important' name='reference_name' id='reference_name' />"); %>
                	<br>
                    <p class="price">R$ <%= phone.getSalePrice() %></p>
                    <form action="Cart" method="POST">
    					<div class="row">
	                    	<div class="col-6">
		                        <div class="form-group">
		                            <label for="cbCapacity">Capacidade</label>
		                            <select class="custom-select" id="cbCapacity" name="cbCapacity">
		                            </select>
		                        </div>
	                        </div>
	                    	<div class="col-6">
		                        <div class="form-group">
		                            <label for="cbColor">Cor</label>
		                            <select class="custom-select" id="cbColor" name="cbColor" disabled>
		                                <option selected>Selecione</option>
		                                <%
		        							for(Reference r : phone.getReference()){
		        								out.println("<option value='" + r.getId() +"'>"+ r.getColor().getDescription() +"</option>");
		        							}
		                                %>
		                            </select>
		                        </div>                    	
	                    	</div>
	                    </div>
   						<% out.println("<input type='hidden' name='reference_id' id='reference_id' />"); %>
						<% out.println("<input type='hidden' name='id' id='id' value='" + phone.getId() + "' />"); %>
						<% out.println("<input type='hidden' name='txtModel' id='txtModel' value='" + phone.getModel() + "' />"); %>
        				
	                    <input type="hidden" name="action" id="action" value="SAVE" />
                   		<input type ="submit" class="btn btn-primary btn-lg btn-block text-uppercase" id="btEntrar" value="COMPRAR"/>
                    </form>
                    <br/>
					<ul class="nav nav-tabs" id="myTab" role="tablist">
					  <li class="nav-item">
					    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Descrição</a>
					  </li>
					  <li class="nav-item">
					    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#perfil" role="tab" aria-controls="profile">Detalhes</a>
					  </li>
					</ul>
					
					<div class="tab-content">
					  <div class="tab-pane active" id="home" role="tabpanel" aria-labelledby="home-tab">
					  	<p class="text"><%= phone.getPackageContent() %></p>
					  </div>
					  <div class="tab-pane" id="perfil" role="tabpanel" aria-labelledby="profile-tab">
					  	<div class="row">
					  		<div class="col-4">
		                        <p class="text"><b>Modelo: </b><%= phone.getModel() %></p>
		                        <p class="text"><b>Marca: </b><%= phone.getBrand().getDescription() %></p>
		                        <p class="text"><b>Versão do S.O: </b><%= phone.getSo().getDescription() %></p>
		                        <p class="text"><b>Processador: </b><%= phone.getProcessor().getDescription() %></p>
					  		</div>
					  		<div class="col-4">
		                        <p class="text"><b>Tam. Tela (PX): </b><%= phone.getScreenSize() %></p>
		                        <p class="text"><b>Resol. Tela: </b><%= phone.getScreenResol() %></p>
		                        <p class="text"><b>Resol. Câmera Frontal: </b><%= phone.getFcameraResol() %></p>
		                        <p class="text"><b>Resol. Câmera Traseira: </b><%= phone.getRcameraResol() %></p>
					  		</div>
					  		<div class="col-4">
		                        <p class="text"><b>Resol. Filmadora: </b><%= phone.getCamcorderResol() %></p>
		                        <p class="text"><b>Dimensões: </b><%= phone.getHeight() %> x <%= phone.getWidth() %> x <%= phone.getDepth() %></p>
		                        <p class="text"><b>Peso Aprox. (g): </b><%= phone.getWeight() %></p>
					  		</div>
					  	</div>
					  </div>
					</div>                 
                </div>
            </div>
        </div>
    </div>
 </div>
 <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
 
<script>
	$(document).ready(function() {	
		var references;
		var stock_refs = [];
		var capacity_id;
		var phone_id = document.getElementById("id");
		    
		$.get("References?action=CONSULT&fk_calcado=" + phone_id.value).done(function(data){	
			references = data
			let capacities = []
			let result = []
						
			$.get("Stocks?action=CONSULT").done(function(data){	
				references.filter((item) => {
					data.filter((stock) => {
						if(stock.reference.id == item.id && stock.avaiable > 0){
							capacities.push(item.capacity)
							stock_refs.push(item);
						}
					})
				}) 
								
				result = capacities.reduce((unique, o) => {
				  if (! unique.find((obj) => obj.id === o.id)) unique.push(o);
			      return unique;
			  	},[]);
				
			 	let options="<option selected>Selecione</option>";

				$.each(result, function(key, value){
					options=options+"<option value='"+value.id+"'>"+value.description+"</option>";
				})				

				$("#cbCapacity").empty();
				$("#cbCapacity").append(options);
			})
		})		
			
	    $('#cbCapacity').on("change", function(){
	    	let colors = [];
	    	capacity_id = this.value;
	    	console.log(stock_refs)
			stock_refs.filter(function(item){
	    		if(item.capacity.id == capacity_id){
	    			colors.push(item.color);
	    		}
			})
			
			result = colors.reduce((unique, o) => {
			  if (! unique.find((obj) => obj.id === o.id)) unique.push(o);
		      return unique;
		  	},[]);
	    	
	    	$("#cbColor").prop('disabled', false);	
			$("#cbColor").find("option").remove();

		 	let options="<option selected>Selecione</option>";

	    	$.each(colors, function(key, value){
				options=options+"<option value='"+value.id+"'>"+value.description+" </option>";
			});	    	
	    	
	    	$("#cbColor").empty();
			$("#cbColor").append(options);
		})	
		
		$('#cbColor').on("change", function(){
	    	let color_id = this.value;
	    	let reference_id;
		    let reference_name;	
    	 	references.filter(function(item){
    			if(item.capacity.id == capacity_id && item.color.id == color_id){
    				reference_id = item.id;
    				reference_name = item.name;
	    		}
	    	})
	    	$("#reference_id").val(reference_id);	
	    	$("#reference_name").val(reference_name);			    	
		})	
	})
</script>