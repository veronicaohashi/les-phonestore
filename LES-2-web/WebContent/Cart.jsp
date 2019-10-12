<%@page import="les.domain.client.Client"%>
<%@page import="les.domain.sale.Orderi"%>
<%@page import="les.domain.sale.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
.container{max-width: -webkit-fill-available !important;} 
.title{font-size: 22px; text-transform: capitalize; margin: 0 0 0px; transition: all .3s ease 0s;}
.text{color: #414141; font-size: 14px }
.price{color: #000; font-size: 20px; letter-spacing: 1px; font-weight: 600; margin-right: 2px; display: inline-block;}
.social{padding-inline-start: 0px !important;}
.social li{display:inline-block}
.social li a{color:#ff0018;background:#fff;font-size:18px;margin:0 35px;display:block}
.total{-ms-flex-align: center; align-items: center; display: -ms-flexbox; font-size: 16px; height: 48px; padding: 0 16px;}
</style>
<%@ include file="Master.jsp"%>
<div class="container">
	<%
		if(request.getAttribute("response") != null){
		out.println("<div class='alert alert-primary' role='alert' id='response'>");
		out.println(request.getAttribute("response") + "</div>");
			}
	%>
	<%
		Cart cart = (Cart) session.getAttribute("cart"); 
			if(cart == null || cart.getItems().size() == 0){
		out.println("<div class='alert alert-primary' role='alert' id='resposta'>" +
					"Você não possui itens no carrinho");
			} else {
	%>
	<div class="row">
    	<div class="col-8">
		<%
			int i = 0;
			for(Orderi item : cart.getItems()){
		%>
			<div class="row">
		    	<div class="col-12">
		  			<div class="card">
				  		<div class="card-body">
				  			<div class="row">
				  				<div class="col-2">
		                 			<img class="img-fluid" src="https://dummyimage.com/800x800/55595c/fff" />
				  				</div>
				  				<div class="col-10">		  					
				                    <ul class="social">
		                				<li><h3 class="title"><%= item.getReference().getPhone().getModel() %></h3></li>
		                				<li><a href="Cart?action=DELETE&reference_id=<%= item.getReference().getId() %>"><i class="fa fa-trash"></i></a></li>   
		               				</ul> 
						  			<div class="row">
						  				<div class="col-3">
						  					<p class="text"><b>REF: </b><%= item.getReference().getName() %></p>
					                        <p class="text"><b>Capacidade: </b><%= item.getReference().getCapacity().getDescription() %></p>
					                        <p class="text"><b>Cor: </b><%= item.getReference().getColor().getDescription() %></p>  	
					                        
						  				</div>
						  				<div class="col-3 offset-col-3">		  					
						  					<div class="form-group">		
										    	<label for="cbQuantity<%= i %>" >Quantidade</label>								  							         					
												<select class='form-control' id="cbQuantity<%= i %>" name="cbQuantity<%= i %>" onchange="calculatePrice(<%= i %>, this.value)">
													<option value='' selected><%= item.getQuantity() %></option>
												</select>					      
										  	</div>
						  				</div>
						  				<div class="col-3">		  					
						  					<div class="form-group">		  		
										    	<label for="cbPreco_<%= i %>">Preço Unitário</label>								  							         					
												<select class='form-control' id='cbPreco_<%= i %>' name='cbPreco_<%= i %>' disabled onchange="calculateSubtotal(this.value)">
													<option value='<%= item.getReference().getPhone().getSalePrice() %>' selected><%= item.getReference().getPhone().getSalePrice() %></option>
												</select>					      
										  	</div>
						  				</div>
						  			</div>
					  			</div>
				  			</div>
					  	</div>
					</div>
					
					<% 
						out.println("<input type='hidden' name='quantity"+i+"' id='quantity"+i+"' value='" + item.getQuantity() + "' />");
						out.println("<input type='hidden' name='reference_id_"+i+"' id='reference_id_"+i+"' value='" + item.getReference().getId() + "' />");
						i++;
					%>
					</div>
		        </div>
        <% } %>
        </div>
			
			<% 
				out.println("<input type='hidden' name='totalItems' id='totalItems' value='" + cart.getItems().size() + "' />");
			%>
        <div class="col-4">	  			
            <div class="row" style="padding-bottom: 20px;">
				<div class="left"><b>Total Itens: </b></div>
				<div class="right"><input style='border-width:0px !important;text-align: right;' name='txtQuantity' id='txtQuantity' value='<%= cart.getQuantity() %>'/></div>
			</div>				
            <div class="row" style="padding-bottom: 20px;">
				<div class="left"><b>Subtotal: </b></div>
				<div class="right"><input style='border-width:0px !important;text-align: right;' name='price' id='price' value='<%= cart.getTotalItemsPrice() %>'/></div>
			</div>	
			<hr>
            <div class="row" style="padding-bottom: 50px;">
				<div class="left"><b>Total: </b></div>
				<div class="right"><input style='border-width:0px !important;text-align: right;' name='totalPrice' id='totalPrice' value='<%= cart.getPrice() %>'/></div>
			</div>
			<% if(session.getAttribute("user") != null) {
				Client client = (Client) session.getAttribute("user"); %>
				<a href="Addresses?action=CONSULT&lmain=true&page=CART&client_id=<%= client.getId() %>" class="btn btn-primary btn-lg btn-block text-uppercase">FINALIZAR</a>
				
			<% } else { %>
				<a href="Login.jsp" class="btn btn-primary btn-lg btn-block text-uppercase">FINALIZAR</a>
			<% }%>
			<a href="index.jsp" class="btn btn-primary btn-lg btn-block text-uppercase">CONTINUAR COMPRANDO</a>						
        </div>
    </div>
    
	
    <% } %>
    

 </div>
 <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
 
<script>
var count = 0;
$(document).ready(function() {
	jQuery.ajaxSetup({async:false});
		
	let totalItems = document.getElementById("totalItems");
	let reference_id = [];
		
	if(totalItems != null) {
		for(var i = 0; i < totalItems.value; i++){
			reference_id = document.getElementById("reference_id_" + i).value;
			getAvaiableQuantity(reference_id);
		}
	}
	
	frete()
})

function getAvaiableQuantity(id){
	if(id != null){		
	 	
		$.get("Stocks?action=CONSULT&reference_id=" + id).done(function(data){
			let selected = document.getElementById("quantity"+count).value;
			var quantity = + selected;
			
		 	let options="";
			if(data.length > 0){
				data.filter((item) => { 
					if(item.avaiable > 0) quantity += + item.avaiable; 
				})
			} else {
				console.log("erro")
			}

			for(var i = 1; i <= quantity; i++){
				if(i != selected)
				options=options+"<option value='"+ i +"'>"+i+"</option>";
			}
			$("#cbQuantity" + (count)).append(options);					
			count++;
		})	
	}
}

function calculatePrice(i, quantity){

	let reference_id = document.getElementById("reference_id_" + i).value;
	let previous_quantity = document.getElementById("quantity" + i).value;
	
	$.post("Cart?action=SAVE&json=true", { reference_id : reference_id, cbQuantity : quantity  - previous_quantity})
	.done(function( data ) {
    	$("#price").val(data.price);
    	$("#totalPrice").val(data.price);
    	$("#txtQuantity").val(data.quantity);
 	});
}

function calculateSubtotal(){
	var sum = 0;
	for(var i = 0; i < qtd_itens.value; i++){
		var select = document.getElementById("cbPreco_" + i);
		var preco = select.options[0].value;
		
		sum += preco;
	}
}
$("#response").show();
setTimeout(function() { $("#response").hide(); }, 5000);     
</script>