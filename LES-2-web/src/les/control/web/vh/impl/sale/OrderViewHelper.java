package les.control.web.vh.impl.sale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les.control.web.vh.IViewHelper;
import les.core.application.Result;
import les.domain.DomainEntity;
import les.domain.client.Address;
import les.domain.client.Client;
import les.domain.sale.Cart;
import les.domain.sale.Order;
import les.domain.sale.Payment;
import les.domain.sale.Status;

public class OrderViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		String action = request.getParameter("action");		
		Order order = new Order();
		Address address;
		Payment payment;
		Cart cart;
		Client client;
		Status status = new Status();
		HttpSession session = request.getSession();

		String status_id = request.getParameter("status_id");
		if(status_id != null) {
			status.setId(Integer.parseInt(status_id));
			order.setStatus(status);			
		}
		
		if(action.equals("SAVE")) {
			address = (Address) session.getAttribute("address");
			payment = (Payment) session.getAttribute("payment");
			cart = (Cart) session.getAttribute("cart");
			client = (Client) session.getAttribute("user");
			
			order = new Order(address, payment, client, cart.getItems(), cart.getPrice(), cart.getQuantity());
		}
				
		return order;		
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");	
		RequestDispatcher rd = null;
		
		List<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("CPF");
		headers.add("Cliente");
		
		if (result.getMsg() == null) {			
			if (action.equals("LIST")) {
				request.setAttribute("headers", headers);
				request.setAttribute("orders", result.getEntities());					

				Client client = (Client)request.getSession().getAttribute("user");
				if(client.getUser().getLevel() == 2) {
					rd = request.getRequestDispatcher("AdminOrders.jsp");						
				} else {					
					rd = request.getRequestDispatcher("IndexPhone.jsp");
				}				
			} else if(action.equals("SAVE")) {
				request.setAttribute("response", "Pedido salvo com sucesso");
				rd = request.getRequestDispatcher("index.jsp");	
				
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
