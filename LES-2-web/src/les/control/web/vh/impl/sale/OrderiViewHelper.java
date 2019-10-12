package les.control.web.vh.impl.sale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import les.domain.sale.Order;
import les.domain.sale.Orderi;
import les.domain.sale.Status;

public class OrderiViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		String action = request.getParameter("action");		
		Orderi orderi = new Orderi();
		
		String status_id = request.getParameter("status_id");
		String delivery_date = request.getParameter("delivery_date");
		String id = request.getParameter("id");
		String exchange_category_id = request.getParameter("cbExchangeCategory");
		String[] lupdate = request.getParameterValues("lupdate");
		
		if(action.equals("UPDATE")) {
			if(status_id != null) {
				orderi.setStatus(new Status(Integer.parseInt(status_id)));				
			}
			
			if(exchange_category_id != null) {
				orderi.setExchangeCategory(new ExchangeCategory(Integer.parseInt(exchange_category_id)));				
			}
			
			if(delivery_date != null) {
				orderi.setOrder(new Order(delivery_date));
			}
			if(id != null) {
				orderi.setId(Integer.parseInt(id));				
			}
			
			if(lupdate != null) {
				for(String i : lupdate) {
					orderi.addIds(Integer.parseInt(i));
				}			
			}
		} else if(action.equals("LIST")) {
			if(status_id != null) {
				orderi.setStatus(new Status(Integer.parseInt(status_id)));					
			}			
		}				
		return orderi;		
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");
		Client client = (Client)request.getSession().getAttribute("user");	
		RequestDispatcher rd = null;
		List<String> headers = new ArrayList<String>();
				
		if (result.getMsg() == null) {			
			if(action.equals("UPDATE")) {
				if(client.getUser().getLevel() == 2) {
					request.setAttribute("response", "Status alterado com sucesso");
					rd = request.getRequestDispatcher("Orderi?action=LIST&status_id=1");	
				} else {
					request.setAttribute("response", "Status alterado com sucesso");
					rd = request.getRequestDispatcher("Orders?action=LIST&client_id=" + client.getId());	
				}					
			} else if(action.equals("LIST")) {
				request.setAttribute("orderi", result.getEntities());					

				if(client.getUser().getLevel() == 2) {
					headers.add("Pedido");
					headers.add("REF");
					headers.add("Valor");
					headers.add("Quantidade");
					headers.add("Justificativa");
					request.setAttribute("headers", headers);
					rd = request.getRequestDispatcher("AdminExchanges.jsp");						
				} else {		
					headers.add("Código");
					headers.add("Valor Total");
					headers.add("Quantidade Total");
					headers.add("Status");
					request.setAttribute("headers", headers);
					rd = request.getRequestDispatcher("ClientOrders.jsp");
				}
			}
		} else {
			request.setAttribute("response", result.getMsg());
			
			if(action.equals("UPDATE")) {
				if(client.getUser().getLevel() == 2) {
					rd = request.getRequestDispatcher("Orders?action=LIST&status_id=1");	
				} else {
					rd = request.getRequestDispatcher("Orders?action=LIST&client_id=" + client.getId());	
				}					
			} else {
				rd = request.getRequestDispatcher("index.jsp");						
			}		
		}
		
		rd.forward(request, response);		
	
	}


	public void setView(Result result, ServletConfig config) {
	}

}
