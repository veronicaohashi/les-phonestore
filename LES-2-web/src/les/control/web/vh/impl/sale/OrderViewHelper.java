package les.control.web.vh.impl.sale;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import les.domain.client.Client;
import les.domain.sale.Cart;
import les.domain.sale.Order;
import les.domain.sale.OrderAddress;
import les.domain.sale.Payment;
import les.domain.sale.Status;

public class OrderViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		String action = request.getParameter("action");		
		Order order = new Order();
		OrderAddress orderAddress;
		Payment payment;
		Cart cart;
		Client client;
		Status status = new Status();
		HttpSession session = request.getSession();

		String[] lupdate = request.getParameterValues("lupdate");
		String status_id = request.getParameter("status_id");
		String client_id = request.getParameter("client_id");
		String id = request.getParameter("id");
		
		if(status_id != null) {
			status.setId(Integer.parseInt(status_id));
			order.setStatus(status);			
		}
		
		if(action.equals("SAVE")) {
			orderAddress = (OrderAddress) session.getAttribute("address");
			payment = (Payment) session.getAttribute("payment");
			cart = (Cart) session.getAttribute("cart");
			client = (Client) session.getAttribute("user");

			Date date = new Date();
			String orderDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			
			order = new Order(orderAddress, payment, client, cart.getItems(), cart.getPrice(), cart.getQuantity(), orderDate);
		} else if(action.equals("UPDATE")) {
			for(String i : lupdate) {
				order.addIds(Integer.parseInt(i));
			}
		} else if(action.equals("CONSULT")) {
			if(id != null) {
				order.setId(Integer.parseInt(id));
			}		
		} else if(action.equals("LIST")) {
			if(client_id != null) {
				client = new Client();
				client.setId(Integer.parseInt(client_id));
				order.setClient(client);
			}		
		}
				
		return order;		
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");
		Client client = (Client)request.getSession().getAttribute("user");	
		RequestDispatcher rd = null;
		
		List<String> headers = new ArrayList<String>();
		
		if (result.getMsg() == null) {			
			if (action.equals("LIST")) {
				request.setAttribute("orders", result.getEntities());					

				if(client.getUser().getLevel() == 2) {
					headers.add("Código");
					headers.add("CPF");
					headers.add("Cliente");
					headers.add("Valor Total");
					headers.add("Quantidade Total");
					request.setAttribute("headers", headers);
					
					rd = request.getRequestDispatcher("AdminOrders.jsp");						
				} else {		
					headers.add("Código");
					headers.add("Valor Total");
					headers.add("Quantidade Total");
					headers.add("Status");
					request.setAttribute("headers", headers);
					
					rd = request.getRequestDispatcher("ClientOrders.jsp");
				}				
			} else if(action.equals("SAVE")) {
				request.getSession().removeAttribute("cart");
				request.setAttribute("response", "Pedido salvo com sucesso");
				rd = request.getRequestDispatcher("Orders?action=LIST&client_id="+client.getId());	
				
			} else if(action.equals("CONSULT")) {
				if(client.getUser().getLevel() == 2) {
					request.setAttribute("order", result.getEntities().get(0));
					rd = request.getRequestDispatcher("AdminOrderDetails.jsp");	
				} else {
					request.setAttribute("order", result.getEntities().get(0));
					rd = request.getRequestDispatcher("ClientOrderDetails.jsp");	
				}
			
			} else if(action.equals("UPDATE")) {
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
