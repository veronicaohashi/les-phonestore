package les.control.web.vh.impl.stock;

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
import les.domain.product.Reference;
import les.domain.stock.Movstock;

public class MovstockViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		Movstock movstock = new Movstock();
		String action = request.getParameter("action");

		String reference_id = request.getParameter("reference_id");
		
		if(action.equals("LIST")) {
			if( reference_id != null ) {
				Reference reference = new Reference();			
				reference.setId(Integer.parseInt(reference_id));
				movstock.setReference(reference);
			}	
			
		}	
		return movstock;
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");
		RequestDispatcher rd = null;

		List<String> headers = new ArrayList<String>();
		headers.add("Preço");
		headers.add("Quantidade");
		headers.add("Fornedecor");
		headers.add("Data");
		headers.add("Tp. Movimentação");
		headers.add("Origem");
		
		if (result.getMsg() == null) {
			if (action.equals("LIST")) {
				request.setAttribute("headers", headers);
				request.setAttribute("movstock", result.getEntities());	
				rd = request.getRequestDispatcher("Movstock.jsp");	
				
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
