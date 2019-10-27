package les.control.web.vh.impl.sale;

import java.io.IOException;
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
import les.domain.client.State;
import les.domain.sale.Freight;

public class FreightViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		String action = request.getParameter("action");				
		Freight freight = new Freight();

		String state_id = request.getParameter("state_id");	
		
		if(action.equals("LIST")) {
			if(state_id != null) {
				freight.setState(new State(Integer.parseInt(state_id)));
			}
		}		
		return freight;
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		String action = request.getParameter("action");

		RequestDispatcher rd = null;		
		
		if (result.getMsg() == null) {
			if (action.equals("LIST")) {
				List<DomainEntity> freight = result.getEntities();
				
				String json = new Gson().toJson(freight);
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
