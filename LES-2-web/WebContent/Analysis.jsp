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
			<div class="row">
		       	<div class="col-4">
					<canvas id="order" width="300" height="300"></canvas>
		       	</div>
		       	<div class="col-4">
		       		<canvas id="orderStatus" width="400" height="400"></canvas>
		       	</div>
		       	<div class="col-4">
		       		<canvas id="myChart" width="400" height="400"></canvas>
		       	</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script>
	var chartColors = {
	  red: 'rgb(255, 99, 132)',
	  orange: 'rgb(255, 159, 64)',
	  yellow: 'rgb(255, 205, 86)',
	  green: 'rgb(75, 192, 192)',
	  blue: 'rgb(54, 162, 235)',
	  purple: 'rgb(153, 102, 255)',
	  grey: 'rgb(231,233,237)'
	};	
	
	$.get("Orders?action=LIST&page=ANALYSIS").done((orders) => {	
		let ordersByMonth = [];
		let ordersByStatus = [];
		let exchangesByMonth = [];
		let exchangesByBug = [];
		
		Object.values(orders.filter((order, index) => {
 			let month = new Date(order.orderDate).getMonth();
 			ordersByMonth[month] = ordersByMonth[month] || 0;
 			ordersByMonth[month] += +order.quantity;
		}))
		
		for(let i = 0; i < 12; i++){
			if(ordersByMonth[i] == undefined) ordersByMonth[i] = 0
		}

		$.get("Orderi?action=LIST&exchange_categories_id=true&page=ANALYSIS").done((exchanges) => {	
			
			Object.values(exchanges.filter((exchange, index) => {
	 			if(exchange.exchangeCategory.id != 1){
		 			let month = new Date(exchange.order.orderDate).getMonth();
		 			exchangesByMonth[month] = exchangesByMonth[month] || 0;
	 				exchangesByMonth[month] += +exchange.quantity;
	 			}
			}))
			
			for(let i = 0; i < 12; i++){
				if(exchangesByMonth[i] == undefined) exchangesByMonth[i] = 0
			}	
						
			Object.values(exchanges.filter((exchange, index) => {
	 			if(exchange.exchangeCategory.id == 1){
		 			let month = new Date(exchange.order.orderDate).getMonth();
		 			exchangesByBug[month] = exchangesByBug[month] || 0;
		 			exchangesByBug[month] += +exchange.quantity;
	 			}
			}))
			
			for(let i = 0; i < 12; i++){
				if(exchangesByBug[i] == undefined) exchangesByBug[i] = 0
			}
				
			let MONTHS = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];
			let config = {
			  type: 'line',
			  data: {
			    labels: MONTHS,
			    datasets: [{
			    	label: "Itens Vendidos",
			      	fill: false,
				    backgroundColor: chartColors.red,
				    borderColor: chartColors.red,
				    data: ordersByMonth,
			    }, {
			      	label: "Itens Trocados",
			      	fill: false,
			      	backgroundColor: chartColors.blue,
			      	borderColor: chartColors.blue,
		   			data: exchangesByMonth,
			    }, {
			      	label: "Itens Trocados por Defeito",
			      	fill: false,
			      	backgroundColor: chartColors.yellow,
			      	borderColor: chartColors.yellow,
		   			data: exchangesByBug,
			    }]
			  },
			  options: {
			    title: {
			      display: true,
			      text: 'Movimentações de Venda'
			    },
			    scales: {
			      xAxes: [{
			        scaleLabel: {
			          display: true,
			          labelString: 'Mês'
			        }
			      }],
			      yAxes: [{
			        scaleLabel: {
			          display: true,
			          labelString: 'Quantidade'
			        }
			      }]
			    }
			  }
			};
		
	
			let ctx = document.getElementById("order").getContext("2d");
			window.myLine = new Chart(ctx, config);
						
			Object.values(orders.filter((order, index) => {				
				ordersByStatus[order.status.id - 1] = ordersByMonth[order.status.id - 1] || 0;
				ordersByStatus[order.status.id - 1] += +order.quantity;
			}))
			
			
			for(let i = 0; i < 4; i++){
				if(ordersByStatus[i] == undefined) ordersByStatus[i] = 0
			}

			ctx = document.getElementById('orderStatus').getContext('2d');
			let myChart = new Chart(ctx, {
			    type: 'bar',
			    data: {
			        labels: ['EM ABERTO', 'APROVADO', 'EM TRÂNSITO', 'ENTREGUE'],
			        datasets: [{
			            data: ordersByStatus,
			            backgroundColor: [
			                'rgba(255, 99, 132, 0.2)',
			                'rgba(54, 162, 235, 0.2)',
			                'rgba(255, 206, 86, 0.2)',
			                'rgba(75, 192, 192, 0.2)'
			            ],
			            borderColor: [
			                'rgba(255, 99, 132, 1)',
			                'rgba(54, 162, 235, 1)',
			                'rgba(255, 206, 86, 1)',
			                'rgba(75, 192, 192, 1)'
			            ],
			            borderWidth: 1
			        }]
			    },
			    options: {
			        legend: {
			            display: false
			        },
				    title: {
				      display: true,
				      text: 'Vendas por Status (2019)'
				    },
				    scales: {
				      xAxes: [{
				        scaleLabel: {
				          display: true,
				          labelString: 'Status'
				        }
				      }],
				      yAxes: [{
				        scaleLabel: {
				          display: true,
				          labelString: 'Quantidade'
				        }
				      }]
				    }
			    }
			});
		})
	})
</script>