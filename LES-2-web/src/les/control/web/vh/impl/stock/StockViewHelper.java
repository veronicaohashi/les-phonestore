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
import les.domain.stock.Stock;

public class StockViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		Stock stock = new Stock();
		
		return stock;
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");
		RequestDispatcher rd = null;

		List<String> headers = new ArrayList<String>();
		headers.add("REF");
		headers.add("Celular");
		headers.add("Cor");
		headers.add("Capacidade");
		headers.add("Quantidade");
		
		if (result.getMsg() == null) {
			if (action.equals("LIST")) {			
				
				request.setAttribute("headers", headers);
				request.setAttribute("stockItems", result.getEntities());	
				rd = request.getRequestDispatcher("Stock.jsp");	
			}
		} else {
			request.setAttribute("response", result.getMsg());
			rd = request.getRequestDispatcher("Response.jsp");
		}
		
		rd.forward(request, response);	
	}


	public void setView(Result result, ServletConfig config) {		
	}

}
