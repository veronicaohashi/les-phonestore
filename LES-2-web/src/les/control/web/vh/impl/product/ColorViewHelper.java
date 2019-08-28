package les.control.web.vh.impl.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import les.control.web.vh.IViewHelper;
import les.core.application.Result;
import les.domain.DomainEntity;
import les.domain.product.Color;

public class ColorViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		return new Color();
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		String action = request.getParameter("action");
		
		if (result.getMsg() == null) {
			if (action.equals("LIST")) {		
				List<DomainEntity> colors = result.getEntities();  							
				
				String json = new Gson().toJson(colors);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} 			
		} 
	}


	public void setView(Result result, ServletConfig config) {
		config.getServletContext().setAttribute("colorResult", result);		
	}

}
