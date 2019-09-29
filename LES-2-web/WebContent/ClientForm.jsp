<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="Master.jsp"%>	
<% 
	if(request.getAttribute("response") != null){
		out.println("<div class='alert alert-primary' role='alert' id='response'>");
		out.println(request.getAttribute("response") + "</div>");
	}
 %>
<form action="Clients" method="post">
	<div class="container-fluid py-3">
    	<div class="row">
        	<div class="col-md-6 mx-auto">
            	<div class="card card-body">
                	<h3 class="text-center mb-4">Novo Cliente</h3>
                   	<fieldset> 
                   	<div class="row">
                   		<div class="col-12">
                   			<h5 class="text-center mb-4">Dados Pessoais</h5>
       						<input name="txtLevel" type="hidden" value="1">
	                   		<div class="row">
	    						<div class="col-12">
			                    	<div class="form-group">
			                            <input class="form-control" placeholder="Nome" name="txtFirstName" type="text">
			                        </div>
		                        </div>
	                        </div>                  
	                   		<div class="row">
	    						<div class="col-12">
			                    	<div class="form-group ">
			                            <input class="form-control" placeholder="Sobrenome" name="txtLastName" type="text">
			                        </div>
		                        </div>
	                        </div>
	                        <div class="row">
	    						<div class="col-6">
			                        <div class="form-group">
			                            <input class="form-control" placeholder="CPF" name="txtCpf" type="text">
			                        </div>
		                        </div>
	    						<div class="col-6">
			                        <div class="form-group">
			                            <input class="form-control" placeholder="Dt. Nascimento" name="txtBirthday" type="date">
			                        </div>
		                        </div>
	                        </div>
	                        <div class="row">
	    						<div class="col-6">
			                        <div class="form-group">
			                            <select class="form-control" name="cbGender">
			                                <option selected disabled>Gênero</option>
										<option value='F'>Feminino</option>
										<option value='M'>Masculino</option>
			                            </select>
			                        </div>
		                        </div>
	                        	<div class="col-6">
			                        <div class="form-group">
			                            <input class="form-control" placeholder="Celular" name="txtPhone" type="text">
			                        </div>
		                        </div>
	                        </div>
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
			                            <input class="form-control" placeholder="Senha" name="txtPassword" type="text">
			                        </div>
		                        </div>
	    						<div class="col-12">
			                        <div class="form-group">
			                            <input class="form-control" placeholder="Confirmação de Senha" name="txtPasswordConfirmation" type="text">
			                        </div>
		                        </div>
	                        </div>
                        </div>
                		
                		<input type="hidden" name="action" id="action" value="SAVE" />
        				<input type="submit" value="CADASTRAR" class="btn btn-save btn-lg btn-primary btn-block"/>
                   	</div>                        
                    </fieldset>
				</div>
			</div>
		</div>
	</div>
</form>
<script>
	$("#response").show();
	setTimeout(function() { $("#response").hide(); }, 5000);
</script>