<%@page import="les.domain.product.Phone"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="Master.jsp"%>
<head>	
	<title>CELULARES</title>
   	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    
    <style>
		.container {
		    max-width: -webkit-fill-available !important;
		}
		
		.product-grid3{font-family:Roboto,sans-serif;text-align:center;position:relative;z-index:1}
		.product-grid3:before{content:"";height:81%;width:100%;background:#fff;border:1px solid rgba(0,0,0,.1);opacity:0;position:absolute;top:0;left:0;z-index:-1;transition:all .5s ease 0s}
		.product-grid3:hover:before{opacity:1;height:100%}
		.product-grid3 .product-image3{position:relative}
		.product-grid3 .product-image3 a{display:block}
		.product-grid3 .product-image3 img{width:100%;height:auto}
		.product-grid3 .pic-1{opacity:1;transition:all .5s ease-out 0s}
		.product-grid3:hover .pic-1{opacity:0}
		.product-grid3 .pic-2{position:absolute;top:0;left:0;opacity:0;transition:all .5s ease-out 0s}
		.product-grid3:hover .pic-2{opacity:1}
		.product-grid3 .social{width:120px;padding:0;margin:0 auto;list-style:none;opacity:0;position:absolute;right:0;left:0;bottom:-23px;transform:scale(0);transition:all .3s ease 0s}
		.product-grid3:hover .social{opacity:1;transform:scale(1)}
		.product-grid3:hover .product-discount-label,.product-grid3:hover .product-new-label,.product-grid3:hover .title{opacity:0}
		.product-grid3 .social li{display:inline-block}
		.product-grid3 .social li a{color:#e67e22;background:#fff;font-size:18px;line-height:50px;width:50px;height:50px;border:1px solid rgba(0,0,0,.1);border-radius:50%;margin:0 2px;display:flex;transition:all .3s ease 0s;text-decoration: none;}
		.product-grid3 .social li a i{margin:auto}
		.product-grid3 .social li a:hover{background:#e67e22;color:#fff}
		.product-grid3 .product-discount-label,.product-grid3 .product-new-label{background-color:#e67e22;color:#fff;font-size:17px;padding:2px 10px;position:absolute;right:10px;top:10px;transition:all .3s}
		.product-grid3 .product-content{z-index:-1;padding:15px;text-align:left}
		.product-grid3 .title{font-size:14px;text-transform:capitalize;margin:0 0 7px;transition:all .3s ease 0s}
		.product-grid3 .title a{color:#414141}
		.product-grid3 .price{color:#000;font-size:16px;letter-spacing:1px;font-weight:600;margin-right:2px;display:inline-block}
		.product-grid3 .price span{color:#909090;font-size:14px;font-weight:500;letter-spacing:0;text-decoration:line-through;text-align:left;display:inline-block;margin-top:-2px}
		.product-grid3 .rating{padding:0;margin:-22px 0 0;list-style:none;text-align:right;display:block}
		.product-grid3 .rating li{color:#ffd200;font-size:13px;display:inline-block}
		.product-grid3 .rating li.disable{color:#dcdcdc}
		@media only screen and (max-width:1200px){.product-grid3 .rating{margin:0}
		}
		@media only screen and (max-width:990px){.product-grid3{margin-bottom:30px}
		.product-grid3 .rating{margin:-22px 0 0}
		}
		@media only screen and (max-width:359px){.product-grid3 .rating{margin:0}
		}

	</style>

</head>
<div class="col-12">
	<% 
		if(request.getAttribute("response") != null){
			out.println("<div class='alert alert-primary' role='alert' id='response'>");
			out.println(request.getAttribute("response") + "</div>");
		}
	 %>
	<div class="container">
	    <div class="row">
	        <div class="col-12 col-sm-3">
	            <div class="card bg-light mb-3">
	                <div class="card-header text-white text-uppercase" style="background-color:#e67e22"><i class="fa fa-list"></i> Modelos</div>
	                <ul class="list-group category_block">
	                	<%
        					List<Phone> phones = (List<Phone>) request.getAttribute("phones");
							for (Phone p : phones){		
								if(p.getSalePrice() > 0 && p.getLactive()){
	                	%>
	                    <li class="list-group-item"><a href="category.html"><%= p.getModel() %></a></li>  	
					   	<%
								}
							}
					   	
					   	%>  	
	                </ul>
	            </div>
	        </div>
	        <div class="col">
	            <div class="row">
	        
	               	<%	                	        			
						for (Phone p : phones){		
							if(p.getSalePrice() > 0 && p.getLactive()){
					%>
	                <div class="col-12 col-md-6 col-lg-4">
			            <div class="product-grid3">
			                <div class="product-image3">
			                    <a href="#">
			                        <img class="pic-1" src="https://dummyimage.com/800x800/55595c/fff">
			                        <img class="pic-2" src="https://dummyimage.com/800x800/55595c/fff">
			                    </a>
			                    <ul class="social">			                    
			                        <% out.println("<li><a href='Phones?action=CONSULT&id=" + p.getId() +"'><i class='fa fa-eye'></i></a></li>"); %>
			                        <li><a href="Cart.jsp"><i class="fa fa-shopping-cart"></i></a></li>
			                    </ul>
			                    <span class="product-new-label">Novo</span>
			                </div>
			                <div class="product-content">
			                    <h3 class="title"><a href="#"><%= p.getModel() %></a></h3>
			                    <div class="price">R$ <%= p.getSalePrice() %></div>
			                    <ul class="rating">
			                        <li class="fa fa-star"></li>
			                        <li class="fa fa-star"></li>
			                        <li class="fa fa-star"></li>
			                        <li class="fa fa-star disable"></li>
			                        <li class="fa fa-star disable"></li>
			                    </ul>
			                </div>
			            </div>
			        </div> 	
				   	<%
							}
						}
				   	
				   	%>  	
			        </div>       	                    
	            </div>
	        </div>
	    </div>
	</div>	
 	<script src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
	<script>
		$("#response").show();
		setTimeout(function() { $("#response").hide(); }, 5000);
	</script>
</div>