package les.control.web.vh.impl.sale;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les.control.web.vh.IViewHelper;
import les.core.application.Result;
import les.domain.DomainEntity;
import les.domain.client.Client;
import les.domain.sale.ExchangeCategory;
import les.domain.sale.Orderi;
import les.domain.sale.Status;

public class OrderiViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		String action = request.getParameter("action");		
		Orderi orderi = new Orderi();
		
		String status_id = request.getParameter("status_id");
		String id = request.getParameter("id");
		String exchange_category_id = request.getParameter("cbExchangeCategory");
		
		if(action.equals("UPDATE")) {
			if(status_id != null) {
				Status status = new Status();
				status.setId(Integer.parseInt(status_id));
				orderi.setStatus(status);				
			}
			
			if(exchange_category_id != null) {
				ExchangeCategory exchangeCategory = new ExchangeCategory();
				exchangeCategory.setId(Integer.parseInt(exchange_category_id));
				orderi.setExchangeCategory(exchangeCategory);				
			}
			
			if(id != null) {
				orderi.setId(Integer.parseInt(id));				
			}
		}
				
		return orderi;		
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");
		Client client = (Client)request.getSession().getAttribute("user");	
		RequestDispatcher rd = null;
				
		if (result.getMsg() == null) {			
			if(action.equals("UPDATE")) {
				if(client.getUser().getLevel() == 2) {
					request.setAttribute("response", "Status alterado com sucesso");
					rd = request.getRequestDispatcher("Orders?action=LIST&status_id=1");	
				} else {
					request.setAttribute("order", result.getEntities().get(0));
					rd = request.getRequestDispatcher("Orders?action=LIST&client_id=" + client.getId());	
				}
					
			}
		} else {
			request.setAttribute("response", result.getMsg());
			rd = request.getRequestDispatcher("index.jsp");				
		}
		
		rd.forward(request, response);		
	
	}


	public void setView(Result result, ServletConfig config) {
	}

}
