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
import les.domain.sale.Cart;
import les.domain.sale.Coupon;
import les.domain.sale.CouponCategory;
import les.domain.sale.OrderCoupons;

public class OrderCouponsViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		String action = request.getParameter("action");		
		Coupon coupon = new Coupon();
		OrderCoupons orderCoupons = new OrderCoupons();
		
		String[] lcoupons = request.getParameterValues("lcoupons");
		String name = request.getParameter("txtName");
		String category_id = request.getParameter("txtCouponCategory");

		List<Coupon> coupons = new ArrayList<Coupon>();
		
		if(action.equals("CONSULT")) {
			if(! name.equals("")) {
				coupon.setName(name);			
				if(category_id != null) {
					coupon.setCouponCategory(new CouponCategory(Integer.parseInt(category_id)));
				}	
				coupons.add(coupon);				
			}
			
			if(lcoupons != null) {				
				for(String c : lcoupons) {
					coupons.add(new Coupon(Integer.parseInt(c)));
				}			
			}	
		}

		orderCoupons.setCoupons(coupons);
		return orderCoupons;		
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");	
		Client client = (Client)request.getSession().getAttribute("user");	
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		String page = request.getParameter("page");	
		RequestDispatcher rd = null;
				
		if (result.getMsg() == null) {			
			 if(action.equals("CONSULT")) {
				if(page != null) {
					if(page.equals("PAYMENT")) {
						OrderCoupons orderCoupons = (OrderCoupons) result.getEntities().get(0);
						if(orderCoupons.getCoupons().size() > 0 ) {	
							request.getSession().setAttribute("coupons", orderCoupons);	
							request.getSession().removeAttribute("payment");
								
							double total = 0;
							for(Coupon c : orderCoupons.getCoupons()) {
								total += c.getValue();
							}
							cart.setTotalDiscountPrice(total);
							cart.setPrice(cart.getTotalItemsPrice() - total);
							request.getSession().setAttribute("cart", cart);
							
							if(cart.getPrice() > 0) {							
								rd = request.getRequestDispatcher("/CreditCards?action=CONSULT&lmain=true&page=CART&client_id="+ client.getId());
							} else {
								rd = request.getRequestDispatcher("Resume.jsp");								
							}											
						} else {
							cart.setTotalDiscountPrice(0.0);
							cart.setPrice(cart.getTotalItemsPrice());
							request.getSession().setAttribute("cart", cart);
							
							request.getSession().removeAttribute("coupons");
							rd = request.getRequestDispatcher("/CreditCards?action=CONSULT&lmain=true&page=CART&client_id="+ client.getId());
						}
					}
				} 						
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
