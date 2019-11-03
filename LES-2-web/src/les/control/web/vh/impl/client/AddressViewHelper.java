package les.control.web.vh.impl.client;

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
import les.domain.client.Address;
import les.domain.client.City;
import les.domain.client.Client;
import les.domain.client.ResidenceType;

public class AddressViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		Address address = new Address();
		String action = request.getParameter("action");
		Client client = new Client();

		String id = request.getParameter("id");
		String name = request.getParameter("txtName");
		String residenceType = request.getParameter("cbResidenceType");
		String postalCode = request.getParameter("txtPostalCode");
		String street = request.getParameter("txtStreet");
		String number = request.getParameter("txtNumber");
		String complement = request.getParameter("txtComplement");
		String district = request.getParameter("txtDistrict");
		String city = request.getParameter("txtCity");
		String observation = request.getParameter("txtObservation");
		String lmain = request.getParameter("lmain");		
		
		String client_id = request.getParameter("client_id");
		
		if(client_id != null) {
			client.setId(Integer.parseInt(client_id));
			address.setClient(client);
		}
		if(id != null) {
			address.setId(Integer.parseInt(id));
		}
		if(action.equals("SAVE")){
			address.setName(name);
			address.setPostalCode(postalCode);
			address.setStreet(street);
			address.setNumber(number);
			address.setComplement(complement);
			address.setDistrict(district);
			address.setObservation(observation);
			address.setLmain(Boolean.parseBoolean(lmain));
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
		}
		
		return address;
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");
		Client client = (Client)request.getSession().getAttribute("user");
		RequestDispatcher rd = null;
				
		List<String> headers = new ArrayList<String>();
		headers.add("Nome");
		headers.add("CEP");
		headers.add("Logradouro");
		headers.add("Número");
		headers.add("Complemento");
		headers.add("Bairro");
		headers.add("Cidade");
		headers.add("Estado");
		headers.add("Ação");

		if (result.getMsg() == null) {	
			String page = request.getParameter("page");		
			
			if(action.equals("CONSULT")) {	
				if(page != null) {
					if(page.equals("CART")) {	
						request.setAttribute("address", result.getEntities().get(0));
						rd = request.getRequestDispatcher("CartAddress.jsp");
						
					} else if(page.equals("")) {	
					}
				} else {
					rd = request.getRequestDispatcher("index.jsp");
				}		
				
			} else if(action.equals("LIST")) {
				request.setAttribute("headers", headers);
				request.setAttribute("addresses", result.getEntities());
				
				if(page != null) {
					if(page.equals("CART")) {
						rd = request.getRequestDispatcher("CartAddressList.jsp");					
					}
				} else {
					rd = request.getRequestDispatcher("Addresses.jsp");
				}		
				
			} else if (action.equals("SAVE")) {	
				result.setMsg("Endereço cadastrado com sucesso!");
				request.setAttribute("response", result.getMsg());
				rd = request.getRequestDispatcher("/Addresses?action=LIST&client_id="+client.getId());
					
			} else if (action.equals("DELETE")) {	
				result.setMsg("Endereço excluído com sucesso!");
				request.setAttribute("response", result.getMsg());
				rd = request.getRequestDispatcher("/Addresses?action=LIST&client_id="+client.getId());	
			}
		} else {
			request.setAttribute("response", result.getMsg());
			
			if (action.equals("DELETE") || action.equals("SAVE")) {	
				rd = request.getRequestDispatcher("/Addresses?action=LIST&client_id="+client.getId());	
			}
		}
	
		rd.forward(request, response);	
	}


	public void setView(Result result, ServletConfig config) {
		config.getServletContext().setAttribute("residenceTypeResult", result);		
	}

}
