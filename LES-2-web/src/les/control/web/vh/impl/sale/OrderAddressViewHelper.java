package les.control.web.vh.impl.sale;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
import les.domain.client.City;
import les.domain.client.Client;
import les.domain.client.ResidenceType;
import les.domain.sale.Cart;
import les.domain.sale.Freight;
import les.domain.sale.OrderAddress;

public class OrderAddressViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String action = request.getParameter("action");	
		Address address = new Address();
		OrderAddress orderAddress = new OrderAddress();
		Client client = new Client();
		HttpSession session = request.getSession();
		
		String name = request.getParameter("txtName");
		String residenceType = request.getParameter("cbResidenceType");
		String postalCode = request.getParameter("txtPostalCode");
		String street = request.getParameter("txtStreet");
		String number = request.getParameter("txtNumber");
		String complement = request.getParameter("txtComplement");
		String district = request.getParameter("txtDistrict");
		String city = request.getParameter("txtCity");
		String observation = request.getParameter("txtObservation");
		String[] linsert = request.getParameterValues("linsert");
		String freight_id = request.getParameter("cbFreight");

		String id = request.getParameter("id");

		if(action.equals("CONSULT")) {
			if(id != null) {
				address.setId(Integer.parseInt(id));
			}
			if(freight_id != null) {
				orderAddress.setFreight(new Freight(Integer.parseInt(freight_id)));
			}
		}
		if(action.equals("SAVE")){
			address.setName(name);
			address.setPostalCode(postalCode);
			address.setStreet(street);
			address.setNumber(number);
			address.setComplement(complement);
			address.setDistrict(district);
			address.setObservation(observation);
			if(freight_id != null) {
				orderAddress.setFreight(new Freight(Integer.parseInt(freight_id)));
			}
			if(residenceType != null) {
				ResidenceType rt = new ResidenceType();
				rt.setId(Integer.parseInt(residenceType));
				address.setResidenceType(rt);
			}
			if(city != null) {
				City c = new City();
				c.setName(city);
				address.setCity(c);
			}
			if(linsert != null && linsert.length > 0) {
				client = (Client) session.getAttribute("user");
				address.setClient(client);
			}
		}

		orderAddress.setAddress(address);		
		return orderAddress;
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		Client client = (Client)request.getSession().getAttribute("user");
		Cart cart = (Cart)request.getSession().getAttribute("cart");

		RequestDispatcher rd = null;		
		
		if (result.getMsg() == null) {
			OrderAddress address = (OrderAddress) result.getEntities().get(0);
			session.setAttribute("address", address);	
			cart.setFreightPrice(address.getFreight().getPrice());		
			cart.setPrice(cart.getPrice() + cart.getFreightPrice());
			request.getSession().setAttribute("cart", cart);
			
			if(action.equals("SAVE")) {
				rd = request.getRequestDispatcher("/CreditCards?action=CONSULT&lmain=true&page=CART&client_id=" + client.getId());
				
			} else if (action.equals("CONSULT")) {
				rd = request.getRequestDispatcher("/CreditCards?action=CONSULT&lmain=true&page=CART&client_id=" + client.getId());					
			}
		} else {
			request.setAttribute("response", result.getMsg());
			
			if(action.equals("SAVE")) {
				rd = request.getRequestDispatcher("CartAddressForm.jsp");				
			} else if (action.equals("CONSULT")) {
				rd = request.getRequestDispatcher("/Addresses?action=CONSULT&lmain=true&client_id=" + client.getId() + "&lentrega=true&page=CART");			
			}
		}
		
		rd.forward(request, response);	
	}


	public void setView(Result result, ServletConfig config) {
	}

}
