package les.control.web.vh.impl.client;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import les.control.web.vh.IViewHelper;
import les.core.application.Result;
import les.domain.DomainEntity;
import les.domain.client.State;

public class StateViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		State state = new State();
		String action = request.getParameter("action");

		String acronym = request.getParameter("acronym");		
		if(action.equals("LIST")){
			state.setAcronym(acronym);
		}
		
		return state;
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");

		if (result.getMsg() == null) {				
			if(action.equals("LIST")) {	
				State state = (State) result.getEntities().get(0);
				String json = new Gson().toJson(state);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);				
			}
		}
	}


	public void setView(Result result, ServletConfig config) {	
	}

}
