package les.control.web.vh.impl.product;

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
import les.domain.product.Phone;
import les.domain.product.Reference;

public class ReferenceViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		Reference reference = new Reference();

		String id = request.getParameter("id");
		String phone_id = request.getParameter("phone_id");
		
		if(id != null) {
			reference.setId(Integer.parseInt(id));
		} 	
		
		if(phone_id != null) {
			Phone phone = new Phone();
			phone.setId(Integer.parseInt(phone_id));
			reference.setPhone(phone);
		}
					
		return reference;
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");
		
		RequestDispatcher rd = null;
		if (result.getMsg() == null) {
			if (action.equals("DELETE")) {
				result.setMsg("Referência excluída com sucesso!");
				request.setAttribute("response", result.getMsg());
				rd = request.getRequestDispatcher("/Phones?action=LIST");
				
			} else if (action.equals("CONSULT")) {
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
		config.getServletContext().setAttribute("brandResult", result);		
	}

}
