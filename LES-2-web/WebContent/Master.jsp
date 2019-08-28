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

		
   		<title>CELULARES</title>
     	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	    
	    <style>
		html{
			font-size:20px;
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
		
		.btn-outline-primary:hover {
		    color: #ffff;
		    background-color: #e67e22;
		    border-color: #e67e22;
		}

		.btn-outline-primary:not(:disabled):not(.disabled).active, .btn-outline-primary:not(:disabled):not(.disabled):active, .show>.btn-outline-primary.dropdown-toggle {
		    color: #fff;
		    background-color: #fff !important;
		    border-color: #fff !importante;
		}
	  </style>
	</head>
	<body>
		<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
	    	<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="index.jsp">CELULARES</a>
		    <input class="form-control form-control-dark w-100" type="text" placeholder="Pesquisar" aria-label="Search">
		    <ul class="navbar-nav px-3">
	      		<li class="nav-item text-nowrap">
		        	<a class="nav-link" href="Logout">Sair</a>
	      		</li>
	    	</ul>
		</nav>	 
  		<div class="container-fluid">
    		<div class="row">
      			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
        			<div class="sidebar-sticky">
          				<ul class="nav flex-column">
          				 <%
  	
							out.println("<li class='nav-item'>");
					    		out.println("<a class='nav-link active' href='#'>");
								out.println("<i class='material-icons feather feather-home'>person</i>");
					    		out.println("Perfil <span class='sr-only'>(current)</span>");
					    		out.println("</a>");
							out.println("</li>");
							out.println("<li class='nav-item'>");
					    		out.println("<a class='nav-link active' href='Phones?action=LIST'>");
								out.println("<i class='material-icons feather feather-home'>phone</i>");
					    		out.println("Celulares <span class='sr-only'>(current)</span>");
					    		out.println("</a>");
							out.println("</li>");
							out.println("<li class='nav-item'>");
					    		out.println("<a class='nav-link active' href='Stocks?action=LIST'>");
								out.println("<i class='material-icons feather feather-home'>inbox</i>");
					    		out.println("Estoque <span class='sr-only'>(current)</span>");
					    		out.println("</a>");
							out.println("</li>");
						%>
         				</ul>
       				</div>
     			</nav>      			
    		</div>
  		</div> 
	</body>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
 
</html>