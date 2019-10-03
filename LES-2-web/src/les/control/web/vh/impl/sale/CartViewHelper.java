package les.control.web.vh.impl.sale;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import les.control.web.vh.IViewHelper;
import les.core.application.Result;
import les.domain.DomainEntity;
import les.domain.product.Reference;
import les.domain.sale.Cart;
import les.domain.sale.Orderi;

public class CartViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		String action = request.getParameter("action");		
		Cart cart = new Cart();
		Orderi item = new Orderi();

		String reference_id = request.getParameter("reference_id");
		String quantity = request.getParameter("cbQuantity");
		
		if(reference_id != null && ! reference_id.equals("")) {
			Reference reference = new Reference();
			reference.setId(Integer.parseInt(reference_id));
			item.setReference(reference);
		}
		
		if(quantity != null  && ! quantity.equals("")) {
			item.setQuantity(Integer.parseInt(quantity));
		} else {
			item.setQuantity(1);		
		}	
		
		if(action.equals("SAVE") || action.equals("DELETE")) {
			Cart cartSession = (Cart)request.getSession().getAttribute("cart");
			if(cartSession != null) {
				cart.setItems(cartSession.getItems());
			}
		}
		
		cart.setStorageItem(item);		
		
		return cart;		
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");	
		String isJson = request.getParameter("json");	
		RequestDispatcher rd = null;
		
		if (result.getMsg() == null) {
			Cart item = (Cart) result.getEntities().get(0);
			
			if (action.equals("SAVE")) {
				Cart cart = (Cart)request.getSession().getAttribute("cart");
				
				if(cart == null) {
					HttpSession session = request.getSession();
					session.setAttribute("cart", item);
				} else {
					request.getSession().setAttribute("cart", item);
				}
				
				if(isJson != null) {
					String json = new Gson().toJson(item);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json);
					
				} else {
					request.setAttribute("response", "Celular adicionado com sucesso!");
					rd = request.getRequestDispatcher("Cart.jsp");
				}
				
			} else if (action.equals("DELETE")) {
				request.getSession().setAttribute("cart", item);			
				request.setAttribute("response", "Celular removido com sucesso!");
				rd = request.getRequestDispatcher("Cart.jsp");
				
			} else if (action.equals("UPDATE")) {
				request.getSession().setAttribute("cart", item);
				request.setAttribute("resposta", "Celular alterado com sucesso!");
				rd = request.getRequestDispatcher("Cart.jsp");
			}				
		} else {
			request.setAttribute("response", result.getMsg());
			rd = request.getRequestDispatcher("Cart.jsp");				
		}
		
		if(rd != null) {			
			rd.forward(request, response);		
		}
	}


	public void setView(Result result, ServletConfig config) {
	}

}
