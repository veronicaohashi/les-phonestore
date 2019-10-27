package les.control.web.vh.impl.sale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		String exchange_categories_id = request.getParameter("exchange_categories_id");

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
			if(exchange_categories_id != null) {
				orderi.setExchangeCategory(new ExchangeCategory(1));
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
				List<DomainEntity> orderi = result.getEntities();
				String page = request.getParameter("page");							

				if(client.getUser().getLevel() == 2) {
					headers.add("Pedido");
					headers.add("REF");
					headers.add("Valor");
					headers.add("Quantidade");
					headers.add("Justificativa");
					request.setAttribute("headers", headers);
					if(page != null) {
						if(page.equals("ANALYSIS")) {
							String json = new Gson().toJson(orderi);
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write(json);}
					} else {
						request.setAttribute("orderi", result.getEntities());
						rd = request.getRequestDispatcher("AdminExchanges.jsp");						

					}
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

		if(rd != null) {
			rd.forward(request, response);		
		}
	
	}


	public void setView(Result result, ServletConfig config) {
	}

}
