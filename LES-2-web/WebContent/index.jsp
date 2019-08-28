<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="Master.jsp"%>
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">	
	<% 
		if(request.getAttribute("resposta") != null){
			out.println("<div class='alert alert-primary' role='alert' id='resposta'>");
			out.println(request.getAttribute("resposta") + "</div>");
		}
	 %>
	
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    	<h1 class="h2">Início</h1>
    	
	</div>
	
</main>		
<%
	//}
%>
<script>
$("#resposta").show();
setTimeout(function() { $("#resposta").hide(); }, 5000);     
</script>
