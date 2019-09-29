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
import les.domain.product.Phone;
import les.domain.product.Reference;
import les.domain.stock.Entry;
import les.domain.stock.Entryi;
import les.domain.stock.Supplier;

public class EntryViewHelper  implements IViewHelper{
	public DomainEntity getEntity(HttpServletRequest request) {
		Entry entry = new Entry();
		String action = request.getParameter("action");
		
		String references = request.getParameter("txtReference");
		String date = request.getParameter("txtDate");
		String id_supplier = request.getParameter("cbSupplier");
		
		if ( action.equals("SAVE") || action.equals("UPDATE")) {	

			entry.setDate(date);
			
			if(id_supplier != null) {
				Supplier supplier = new Supplier();
				supplier.setId(Integer.parseInt(id_supplier));
				entry.setSupplier(supplier);
			}
			
			List<Entryi> items = new ArrayList<>();	
			int i = 0;
			if(! references.equals("")) {			
				do {
					String quantity = request.getParameter("txtQuantity"+i);
					String price = request.getParameter("txtPrice"+i);
					String id_reference = request.getParameter("txtReference"+i);
					String id_phone = request.getParameter("cbPhones");
					
					if(quantity == null || quantity.equals(""))
						quantity = "0";
					
					if(price == null || price.equals(""))
						price = "0";
					
					if(! quantity.equals("0") || ! price.equals("0")) {
									
						Entryi entryi = new Entryi();
						entryi.setPrice(Double.parseDouble(price));
						entryi.setQuantity(Integer.parseInt(quantity));
						
						Reference reference = new Reference();
						reference.setId(Integer.parseInt(id_reference));
						
						Phone phone = new Phone();
						phone.setId(Integer.parseInt(id_phone));
						reference.setPhone(phone);
						
						entryi.setReference(reference);
						
						items.add(entryi);	
					}
					
					i++;	
				
				} while( i < Integer.parseInt(references));
			}
			entry.setItems(items);
		}
		return entry;
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
		String action = request.getParameter("action");
		RequestDispatcher rd = null;
		
		if (result.getMsg() == null) {
			if (action.equals("SAVE")) {
				result.setMsg("Entrada realizada com sucesso!");
				request.setAttribute("resposta", result.getMsg());
				rd = request.getRequestDispatcher("/Stocks?action=LIST");
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
