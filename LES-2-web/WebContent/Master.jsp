<%@page import="les.domain.client.Client"%>
<%@page import="les.domain.client.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<!-- Required meta tags -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
     	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
   		<title>CELULARES</title>
	    <style>
	    hr {
	    	height: 1px;
		}
		.btn-primary{
	    	background-color: #e67e22 !important;
	    	border-color: #e67e22;
		}
		
		html{
			font-size:18px;
		}	
		
		.navbar-brand {
		   padding-top: .75rem;
		   padding-bottom: .75rem;
		   font-size: 1rem;
		   background-color: rgba(0, 0, 0, .25);
		   box-shadow: inset -1px 0 0 rgba(0, 0, 0, .25);
		}
		
		.navbar .form-control {
		   padding: .75rem 1rem;
		   border-width: 0;
		   border-radius: 0;
		}	
		
		.form-control-dark {
		   color: #fff;
		   background-color: rgba(255, 255, 255, .1);
		   border-color: rgba(255, 255, 255, .1);
		}
		
		.sidebar {
		   position: fixed;
		   top: 0;
		   bottom: 0;
		   left: 0;
		   z-index: 100;
		   padding: 48px 0 0;
		   box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
		}
		
		.sidebar-sticky {
		   position: -webkit-sticky;
		   position: sticky;
		}
		
		.sidebar-sticky {
		   position: relative;
		   top: 0;
		   height: calc(100vh - 48px);
		   padding-top: .5rem;
		   overflow-x: hidden;
		   overflow-y: auto;
		}
		
		.sidebar .nav-link.active {
		   color: #007bff;
		}
		
		.sidebar .nav-link {
		   font-weight: 500;
		   color: #333;
		}
		.sidebar-heading {
		   font-size: .75rem;
		   text-transform: uppercase;
		}
		
		@media (min-width: 768px){
		  [role="main"] {
		      padding-top: 48px;
		  }
		}
		.sidebar .nav-link:hover .feather, .sidebar .nav-link.active .feather {
		   color: inherit;
		}
		
		.sidebar .nav-link .feather {
		   margin-right: 4px;
		   color: #999;
		}
		
		.feather {
		   vertical-align: text-bottom;
		}
		
		.btn-outline-primary {
		    color: #e67e22 !important;
		    border-color: #e67e22;
		}
		
		.btn-primary:hover {
		    border-color: #e67e22 !important;
		}
		
		.btn-outline-primary:hover {		
    		color: #fff !important;
		    background-color: #e67e22;
		    border-color: #e67e22 !important;
		}

		.btn-outline-primary:not(:disabled):not(.disabled).active, .btn-outline-primary:not(:disabled):not(.disabled):active, .show>.btn-outline-primary.dropdown-toggle {
		    color: #fff;
		    background-color: #fff !important;
		    border-color: #fff !importante;
		}
		
		.left {
		    text-align: left;
		}
		.right {
		    text-align: right;
		    padding-right: 15px;
		}
		.left, .right {
		    display: inline-block;
		    width: 50%;
		}
		div.admin-title{
			display: flex;
			justify-content: center;
			position: relative;
		}
		a.admin-button{
			position: absolute; 
			right: 0;
		}
	  </style>
	</head>
	<body>
	
		<% 		
			if(session.getAttribute("user") != null) {
				Client client = (Client) session.getAttribute("user");
				
				if(client.getUser().getLevel() == 1) {	
		%>
	  	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
	    	<a class="my-0 mr-md-auto font-weight-normal" href="index.jsp">CELULARES</a>
	    	<a class="my-0 mr-md-auto font-weight-normal" href="ClientFormUpdate.jsp">Perfil</a>
	    	<a class="my-0 mr-md-auto font-weight-normal" href="CreditCards?action=LIST&client_id=<%= client.getId() %>">Cartões de Crédito</a>	    	
	    	<a class="my-0 mr-md-auto font-weight-normal" href="Addresses?action=LIST&client_id=<%=client.getId()%>">Endereços</a>	
	    	<a class="my-0 mr-md-auto font-weight-normal" href="Orders?action=LIST&client_id=<%= client.getId() %>">Meus Pedidos</a>
	    	<a class="my-0 mr-md-auto font-weight-normal" href="Coupons?action=LIST&client_id=<%= client.getId() %>">Meus Cupons</a>
  	    	<a class="my-0 mr-md-auto font-weight-normal" href="Clients?action=UPDATE&id=<%= client.getId() %>&lactive=false&page=INACTIVATION">Excluir Conta</a>	    	
      		<div class="btn-group mr-2">
				<span>Bem vindo(a), <%= client.getFirstname()%></span>
			</div>
      		<div class="btn-group mr-2">
				<a class="btn btn-outline-primary" href="Cart.jsp">Carrinho</a>
			</div>
			<a class="btn btn-outline-primary" href="Logout">Sair</a>
		</div>
  		<% } else if (client.getUser().getLevel() == 2) { %>
  		
	  	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
	    	<a class="my-0 mr-md-auto font-weight-normal" href="ClientFormUpdate.jsp">Perfil</a>
	    	<a class="my-0 mr-md-auto font-weight-normal" href="Phones?action=LIST">Celulares</a>	    	
	    	<a class="my-0 mr-md-auto font-weight-normal" href="Stocks?action=LIST">Estoque</a>	
	    	<a class="my-0 mr-md-auto font-weight-normal" href="Orders?action=LIST&status_id=1">Pedidos</a>
	    	<a class="my-0 mr-md-auto font-weight-normal" href="Orderi?action=LIST&status_id=5">Trocas</a>
	    	<a class="my-0 mr-md-auto font-weight-normal" href="Coupons?action=LIST">Cupons</a>
	    	
      		<div class="btn-group mr-2">
				<span>Bem vindo(a), <%= client.getFirstname()%></span>
			</div>
      		<div class="btn-group mr-2">
				<a class="btn btn-outline-primary" href="Cart.jsp">Carrinho</a>
			</div>
			<a class="btn btn-outline-primary" href="Logout">Sair</a>
		</div>
		
  		
		<% } 
		}else { %>
	  	
	  	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
	    	<a class="my-0 mr-md-auto font-weight-normal" href="index.jsp">CELULARES</a>
      		<div class="btn-group mr-2">
				<a class="btn btn-outline-primary" href="Cart.jsp">Carrinho</a>
			</div>
			<a class="btn btn-outline-primary" href="Login.jsp">Entrar</a>
		</div>
		<% } %>	
	</body>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>