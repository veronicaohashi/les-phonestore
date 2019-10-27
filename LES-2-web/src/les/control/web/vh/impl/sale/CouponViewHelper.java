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
import les.domain.sale.Coupon;
import les.domain.sale.CouponCategory;

public class CouponViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		String action = request.getParameter("action");		
		Coupon coupon = new Coupon();		
		String client_id = request.getParameter("client_id");
		String lactive = request.getParameter("lactive");
		String name = request.getParameter("txtName");
		String category_id = request.getParameter("txtCouponCategory");
		
		if(action.equals("LIST")) {
			if(client_id != null) {
				coupon.setClient(new Client(Integer.parseInt(client_id)));
			}		
			if(lactive != null) {
				coupon.setLactive(Boolean.parseBoolean(lactive));
			}	
		} else if(action.equals("CONSULT")) {
			if(category_id != null) {
				coupon.setCouponCategory(new CouponCategory(Integer.parseInt(category_id)));
			}	
			coupon.setName(name);
		}
		
		return coupon;		
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");	
		Client client = (Client)request.getSession().getAttribute("user");	
		String page = request.getParameter("page");	
		RequestDispatcher rd = null;
		
		List<String> headers = new ArrayList<String>();
		
		if (result.getMsg() == null) {			
			if (action.equals("LIST")) {
				request.setAttribute("coupons", result.getEntities());					

				if(client.getUser().getLevel() == 2) {
					headers.add("Código");
					headers.add("CPF");
					headers.add("Cliente");
					headers.add("Pedido");
					headers.add("Valor");
					headers.add("Validade");
					request.setAttribute("headers", headers);
					
					rd = request.getRequestDispatcher("AdminCoupons.jsp");						
				} else {							
					headers.add("Código");
					headers.add("Pedido");
					headers.add("Valor");
					headers.add("Validade");
					headers.add("Status");
					request.setAttribute("headers", headers);
					
					if(page != null) {
						if(page.equals("CART")) {
							rd = request.getRequestDispatcher("CartPaymentCoupons.jsp");	
						}
					} else {					
						rd = request.getRequestDispatcher("ClientCoupons.jsp");
					}
				}				
			} else if(action.equals("CONSULT")) {
					
				List<DomainEntity> references = result.getEntities();  

				String json = new Gson().toJson(references);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
											
			}		
		} else {
			request.setAttribute("response", result.getMsg());
			rd = request.getRequestDispatcher("Response.jsp");				
		}
		
		if(rd != null) {			
			rd.forward(request, response);		
		}
	}


	public void setView(Result result, ServletConfig config) {
	}

}
