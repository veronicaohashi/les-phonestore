package les.control.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les.control.web.command.ICommand;
import les.control.web.command.impl.UpdateCommand;
import les.control.web.vh.IViewHelper;
import les.control.web.vh.impl.product.BrandViewHelper;
import les.control.web.vh.impl.product.CapacityViewHelper;
import les.control.web.vh.impl.product.ColorViewHelper;
import les.control.web.vh.impl.product.ConnectionTypeViewHelper;
import les.control.web.vh.impl.product.InactivationCategoryViewHelper;
import les.control.web.vh.impl.product.PhoneViewHelper;
import les.control.web.vh.impl.product.PricingGroupViewHelper;
import les.control.web.vh.impl.product.ProcessorViewHelper;
import les.control.web.vh.impl.product.ReferenceViewHelper;
import les.control.web.vh.impl.product.SOViewHelper;
import les.control.web.vh.impl.stock.EntryViewHelper;
import les.control.web.vh.impl.stock.StockViewHelper;
import les.control.web.vh.impl.stock.SupplierViewHelper;
import les.core.application.Result;
import les.control.web.command.impl.ConsultCommand;
import les.control.web.command.impl.DeleteCommand;
import les.control.web.command.impl.SaveCommand;
import les.domain.DomainEntity;
import les.domain.product.PricingGroup;

public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;
	private static String uri = null;
	private static HttpServletRequest request;
	private static String action = null;

	private static IViewHelper vh;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		// Parametros definidos como <init-param> no web.xml
		Enumeration<String> params = config.getInitParameterNames();

		while (params.hasMoreElements()) {
			String param = params.nextElement();
			uri = config.getInitParameter(param);
			Result result = doProcess();

			vh.setView(result, config);
		}	
	}
	
    public Servlet() {

    	commands = new HashMap<String, ICommand>();
    	commands.put("LIST", new ConsultCommand());
    	commands.put("SAVE", new SaveCommand());
    	commands.put("DELETE", new DeleteCommand());
    	commands.put("CONSULT", new ConsultCommand());
    	commands.put("UPDATE", new UpdateCommand());
    	
    	vhs = new HashMap<String, IViewHelper>();
    	
//		PRODUCT
    	vhs.put("/LES-web/Phones", new PhoneViewHelper());    
    	vhs.put("/LES-web/Colors", new ColorViewHelper());    
    	vhs.put("/LES-web/Capacities", new CapacityViewHelper());    
    	vhs.put("/LES-web/References", new ReferenceViewHelper());    
    	vhs.put("/LES-web/Stocks", new StockViewHelper());      
    	vhs.put("/LES-web/Entry", new EntryViewHelper());     

    	vhs.put("/LES-web/ConsultBrand", new BrandViewHelper());
    	vhs.put("/LES-web/ConsultColor", new ColorViewHelper());
    	vhs.put("/LES-web/ConsultCapacity", new CapacityViewHelper());
    	vhs.put("/LES-web/ConsultPricingGroup", new PricingGroupViewHelper());
    	vhs.put("/LES-web/ConsultProcessor", new ProcessorViewHelper());
    	vhs.put("/LES-web/ConsultConnectionType", new ConnectionTypeViewHelper());
    	vhs.put("/LES-web/ConsultSO", new SOViewHelper());
    	vhs.put("/LES-web/ConsultSupplier", new SupplierViewHelper());
    	vhs.put("/LES-web/ConsultInactivationCategory", new InactivationCategoryViewHelper());
    }      
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    		IOException {
    	doProcessRequest(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcessRequest(request, response);
	}
		
	protected void doProcessRequest(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		uri = request.getRequestURI();
		this.request = request;
		action = request.getParameter("action");
		Result result = doProcess();
		vh.setView(result, request, response);
	
	}
	
	protected Result doProcess() throws ServletException {

		vh = vhs.get(uri);
		DomainEntity entity = vh.getEntity(request);

		if (request == null) {
			action = "CONSULT";
		} else {
			action = request.getParameter("action");
		}

		if(action != null) {
			ICommand command = commands.get(action);
			Result result = command.execute(entity);
			return result;
		}		
		return null;
	}
}
