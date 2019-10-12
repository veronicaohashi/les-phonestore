package les.control.web.vh.impl.client;

import java.io.IOException;

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
import les.domain.client.User;
import les.domain.sale.Cart;

public class ClientViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		Client client = new Client();
		User user = new User();
		String action = request.getParameter("action");
		
		String firstname = request.getParameter("txtFirstName");
		String lastname = request.getParameter("txtLastName");
		String cpf = request.getParameter("txtCpf");
		String birthday = request.getParameter("txtBirthday");
		String gender = request.getParameter("cbGender");
		String phone = request.getParameter("txtPhone");
		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
		String password_confirmation = request.getParameter("txtPasswordConfirmation");
		String level = request.getParameter("txtLevel");
		String user_id = request.getParameter("user_id");
		String id = request.getParameter("id");
		String lactive = request.getParameter("lactive");
		
		if(user_id != null && ! user_id.equals("")) {
			user.setId(Integer.parseInt(user_id));
		}
		
		if(id != null) {
			client.setId(Integer.parseInt(id));
		}
		
		if(action.equals("SAVE") || action.equals("UPDATE")) {
			client.setFirstname(firstname);
			client.setLastname(lastname);
			client.setCpf(cpf);
			client.setBirthday(birthday);
			client.setGender(gender);
			client.setPhone(phone);
			if(lactive == null)
				client.setLactive(true);
			else
				client.setLactive(Boolean.parseBoolean(lactive));
			if(email != null) {
				user.setEmail(email);
				user.setPassword(password);
				user.setPasswordConfirmation(password_confirmation);
				if(level != null)
					user.setLevel(Integer.parseInt(level));
			}
		}

		client.setUser(user);
		return client;
		 
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");
		String page = request.getParameter("page");	
		
		RequestDispatcher rd = null;
		
		if (result.getMsg() == null) {			
			if (action.equals("SAVE")) {
				HttpSession session = request.getSession();				
				session.setAttribute("user", result.getEntities().get(0));			

				result.setMsg("Cliente cadastrado com sucesso!");
				request.setAttribute("response", result.getMsg());
				rd = request.getRequestDispatcher("index.jsp");		
				
			} else if(action.equals("CONSULT")) {
				HttpSession session = request.getSession();				
				session.setAttribute("user", result.getEntities().get(0));	

				Cart cart = (Cart)request.getSession().getAttribute("cart");
				
				if(cart != null) {
					rd = request.getRequestDispatcher("/Addresses?action=CONSULT&lmain=true&client_id=" + result.getEntities().get(0).getId() + "&lentrega=true&page=CART");
				} else {
					rd = request.getRequestDispatcher("/Phones?action=LIST");
				}
			} else if(action.equals("UPDATE")) {

				if(page != null) {
					if(page.equals("INACTIVATION")) {	
						request.getSession().invalidate();
						rd = request.getRequestDispatcher("index.jsp");
					}
				} else {
					result.setMsg("Cliente alterado com sucesso!");
					request.setAttribute("response", result.getMsg());
					rd = request.getRequestDispatcher("/Clients?action=CONSULT&user_id="+result.getEntities().get(0).getId()+"");
				}
			}
		} else {
			if (action.equals("SAVE")) {
				request.setAttribute("response", result.getMsg());
				rd = request.getRequestDispatcher("ClientForm.jsp");
			}
			if (action.equals("UPDATE")) {
				request.setAttribute("response", result.getMsg());
				rd = request.getRequestDispatcher("ClientFormUpdate.jsp");	
			}
		}
	
		rd.forward(request, response);	
	}


	public void setView(Result result, ServletConfig config) {	
	}

}
