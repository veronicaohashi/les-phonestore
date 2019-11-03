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
       		<h3 class="text-center mb-4">Gráficos de Análise</h3>
			<input type="hidden" name="id" value="<%=request.getParameter("phone_id")%>" /> 
			<form action="Orders" method="get">
		        <div class="row">	        
		         	<div class="col-2">
						<div class="form-group">		  		
					    	<label for="txtDateIni">Dt. Inicio</label>
						    <input type="date" class="form-control" id="txtDateIni" name="txtDateIni">
					  	</div>		        				  	
		         	</div>         
		         	<div class="col-2">
						<div class="form-group">		  		
					    	<label for="txtDateEnd">Dt. Fim</label>
						    <input type="date" class="form-control" id="txtDateEnd" name="txtDateEnd">
					  	</div>		        				  	
		         	</div>   
		         	<div class="col-1">	         	
						<input type="hidden" name="action" id="action" value="LIST" />
						<input type="hidden" name="page" id="page" value="ANALYSIS" />
						<input style="margin-top: 35px;" class="btn btn-sm btn-outline-secondary" 
						onclick="generateGraphic(document.getElementById('txtDateIni').value, document.getElementById('txtDateEnd').value)" value="Filtrar" />
		         	</div> 	 
	         	</div>     
         	</form>
	        <div class="row">	        
	         	<div class="col-12"> 
	         		<div id="container" ></div>
	         	</div> 	 
         	</div>   
		</div>
	</div>
</div>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script>

$(document).ready(() => {	

 	let dateIni = document.getElementById("txtDateIni");
	let firstDateOfYear = new Date(new Date().getFullYear(), 0, 1);	
	dateIni.value = firstDateOfYear.toISOString().slice(0,10);
	
	let dateEnd = document.getElementById("txtDateEnd");
	let lastDateOfYear = new Date(new Date().getFullYear(), 11, 31);	
	dateEnd.value = lastDateOfYear.toISOString().slice(0,10);
	
	generateGraphic(dateIni.value, dateEnd.value);
})


function generateGraphic(dateIni, dateEnd){	
	$.get("Orders?action=LIST&page=ANALYSIS&txtDateIni=" + dateIni + "&txtDateEnd=" + dateEnd).done((orders) => {
		let order_ids = orders.map(order => order.id);
		
		$.get("Orderi?action=LIST&exchange_categories_id=true&page=ANALYSIS&order_ids=" + order_ids).done((exchanges) => {	

			let months = calculateMonths(dateIni, dateEnd);
			let ordersByMonth = generateOrdersByMonth(orders, Object.assign({}, months));
			let exchangesByMonth = generateExchangesByMonth(exchanges, Object.assign({}, months));
		
			
			console.log(exchangesByMonth, ordersByMonth)
			
		
		
			Highcharts.chart('container', {
			    chart: { type: 'line' },
			    title: { text: 'Movimentações de Venda' },
			    xAxis: {
			        categories: months,
			        title: { text: 'Mês' }
			    },
			    yAxis: {
			        title: { text: 'Quantidade' }
			    },
			    plotOptions: {
			        line: {
			            dataLabels: { enabled: true },
			            enableMouseTracking: false
			        }
			    },
			    series: [{
			        name: 'Itens Vendidos',
			        data: ordersByMonth
			    }, {
			        name: 'Itens Trocado',
			        data: exchangesByMonth[0]
			    }, {
			        name: 'Itens Trocados por Defeito',
			        data: exchangesByMonth[1]
			    }]
			});
		});
	});
}

function calculateMonths(dateIni, dateEnd){
	let startYear = new Date(dateIni).getUTCFullYear();
	let endYear = new Date(dateEnd).getUTCFullYear();
  	let dates = [];
  	
  	for(let i = startYear; i <= endYear; i++) {  
  	    let endMonth = (i != endYear) ? 12 : new Date(dateEnd).getUTCMonth() + 1;
  	    let startMonth = (i == startYear) ? new Date(dateIni).getUTCMonth() + 1 : 1;
  	    
  		for(let j = startMonth; j <= endMonth; j++){
  			dates.push(j + "/" + i);
  		} 
  	}
	
  return dates;
}

function generateOrdersByMonth(orders, months){
	let ordersByMonth = Object.values(months);
	let newMonths = Object.values(months);
	
	Object.values(orders.filter((order, index) => {
		let month = new Date(order.orderDate).getMonth() + 1;
		let year = new Date(order.orderDate).getUTCFullYear();
		let date = month + "/" + year;		
		
		newMonths.forEach((ordersDate, index) => {
			if(ordersDate == date){
				if(typeof ordersByMonth[index] == "string"){
					ordersByMonth[index] = 0;
				}
				ordersByMonth[index] += +order.quantity;
			}
		})
	}));

	for(let i = 0; i < ordersByMonth.length; i++){
		if(typeof ordersByMonth[i] == "string") ordersByMonth[i] = 0
	}	
	
	return ordersByMonth;
}


function generateExchangesByMonth(exchanges, months){
	let exchangesByMonth = Object.values(months);
	let exchangesByBug = Object.values(months);
	let newMonths = Object.values(months);
	
	Object.values(exchanges.filter((exchange, index) => {
		let month = new Date(exchange.order.orderDate).getMonth() + 1;
		let year = new Date(exchange.order.orderDate).getUTCFullYear();
		let date = month + "/" + year;		
		
		newMonths.forEach((exchangesDate, index) => {
			if(exchangesDate == date){
				if(exchange.exchangeCategory.id == 1){	
					if(typeof exchangesByBug[index] == "string"){
						exchangesByBug[index] = 0;
					}
					exchangesByBug[index] += +exchange.quantity;
				} else {
					if(typeof exchangesByMonth[index] == "string"){
						exchangesByMonth[index] = 0;
					}
					exchangesByMonth[index] += +exchange.quantity;
					
				}
			}
		})
	}));

	for(let i = 0; i < exchangesByMonth.length; i++){
		if(typeof exchangesByMonth[i] == "string") exchangesByMonth[i] = 0
	}

	for(let i = 0; i < exchangesByBug.length; i++){
		if(typeof exchangesByBug[i] == "string") exchangesByBug[i] = 0
	}
	
	return [exchangesByMonth, exchangesByBug];
}
</script>