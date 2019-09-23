package les.control.web.vh.impl.client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les.control.web.vh.IViewHelper;
import les.core.application.Result;
import les.domain.DomainEntity;
import les.domain.client.User;

public class UserViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		User user = new User();
		
		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");		
		user.setEmail(email);
		user.setPassword(password);		
		
		return user;		 
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");
		RequestDispatcher rd = null;
		
		if(result == null) {
			request.getSession().invalidate();		
			rd = request.getRequestDispatcher("index.jsp");
			
		} else if (result.getMsg() == null) {
			if (action.equals("CONSULT")) {
				DomainEntity entity = result.getEntities().get(0);		
				rd = request.getRequestDispatcher("/Clients?action=CONSULT&user_id="+entity.getId()+"");		
			}
		} else {
			if (action.equals("CONSULT")) {
				request.setAttribute("response", result.getMsg());
				rd = request.getRequestDispatcher("Login.jsp");
			}
		}
	
		rd.forward(request, response);	
	}


	public void setView(Result result, ServletConfig config) {
	}

}
