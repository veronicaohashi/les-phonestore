<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="Master.jsp"%>	
<form action="Usuarios" method="post">
	<div class="container-fluid py-3">
    	<div class="row">
        	<div class="col-md-6 mx-auto">
            	<div class="card card-body">
                	<h3 class="text-center mb-4">Login</h3>
		      		<fieldset> 
		      			<div class="row">
							<div class="col-12">
		              			<div class="form-group">
		                      		<input class="form-control" placeholder="E-mail" name="txtEmail" type="email">
		                  		</div>
		                 	</div>
		                </div>  
		      			<div class="row">
							<div class="col-12">
		              			<div class="form-group">
		                      		<input class="form-control" placeholder="Senha" name="txtSenha" type="password">
		                  		</div>
		                 	</div>
		                </div>                 
			        	<input class="btn btn-lg btn-primary btn-block" type="submit" name="acao" value="ENTRAR"/>
					</fieldset>
               	</div>
           	</div>
		</div>
	</div>
</form> 
