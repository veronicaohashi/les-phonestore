package les.control.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import les.control.web.vh.impl.client.AddressViewHelper;
import les.control.web.vh.impl.client.ClientViewHelper;
import les.control.web.vh.impl.client.CreditCardViewHelper;
import les.control.web.vh.impl.client.ResidenceTypeViewHelper;
import les.control.web.vh.impl.client.StateViewHelper;
import les.control.web.vh.impl.client.UserViewHelper;
import les.control.web.vh.impl.product.ActivationCategoryViewHelper;
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
import les.control.web.vh.impl.sale.CartViewHelper;
import les.control.web.vh.impl.sale.CouponViewHelper;
import les.control.web.vh.impl.sale.ExchangeCategoriesViewHelper;
import les.control.web.vh.impl.sale.FreightViewHelper;
import les.control.web.vh.impl.sale.OrderAddressViewHelper;
import les.control.web.vh.impl.sale.OrderCouponsViewHelper;
import les.control.web.vh.impl.sale.OrderViewHelper;
import les.control.web.vh.impl.sale.OrderiViewHelper;
import les.control.web.vh.impl.sale.PaymentViewHelper;
import les.control.web.vh.impl.sale.StatusViewHelper;
import les.control.web.vh.impl.stock.EntryViewHelper;
import les.control.web.vh.impl.stock.MovstockViewHelper;
import les.control.web.vh.impl.stock.StockViewHelper;
import les.control.web.vh.impl.stock.SupplierViewHelper;
import les.core.application.Result;
import les.control.web.command.impl.ConsultCommand;
import les.control.web.command.impl.DeleteCommand;
import les.control.web.command.impl.SaveCommand;
import les.domain.DomainEntity;

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
    	vhs.put("/LES-web/Cart", new CartViewHelper());     
    	vhs.put("/LES-web/Clients", new ClientViewHelper());     
    	vhs.put("/LES-web/Users", new UserViewHelper());       
    	vhs.put("/LES-web/Addresses", new AddressViewHelper());    
    	vhs.put("/LES-web/CreditCards", new CreditCardViewHelper());    
    	vhs.put("/LES-web/Logout", new UserViewHelper());       
    	vhs.put("/LES-web/Orders", new OrderViewHelper());      
    	vhs.put("/LES-web/Orderi", new OrderiViewHelper());      
    	vhs.put("/LES-web/Payments", new PaymentViewHelper());
    	vhs.put("/LES-web/OrderAddresses", new OrderAddressViewHelper());   
    	vhs.put("/LES-web/Movstock", new MovstockViewHelper());  
    	vhs.put("/LES-web/Coupons", new CouponViewHelper());    
    	vhs.put("/LES-web/OrderCoupons", new OrderCouponsViewHelper());    
    	vhs.put("/LES-web/Freight", new FreightViewHelper());         
    	vhs.put("/LES-web/States", new StateViewHelper());         
    	
    	vhs.put("/LES-web/ConsultBrand", new BrandViewHelper());
    	vhs.put("/LES-web/ConsultColor", new ColorViewHelper());
    	vhs.put("/LES-web/ConsultCapacity", new CapacityViewHelper());
    	vhs.put("/LES-web/ConsultPricingGroup", new PricingGroupViewHelper());
    	vhs.put("/LES-web/ConsultProcessor", new ProcessorViewHelper());
    	vhs.put("/LES-web/ConsultConnectionType", new ConnectionTypeViewHelper());
    	vhs.put("/LES-web/ConsultSO", new SOViewHelper());
    	vhs.put("/LES-web/ConsultSupplier", new SupplierViewHelper());
    	vhs.put("/LES-web/ConsultInactivationCategory", new InactivationCategoryViewHelper());
    	vhs.put("/LES-web/ConsultResidenceTypes", new ResidenceTypeViewHelper());
    	vhs.put("/LES-web/ConsultStatus", new StatusViewHelper());
    	vhs.put("/LES-web/ConsultActivationCategory", new ActivationCategoryViewHelper());
    	vhs.put("/LES-web/ConsultExchangeCategory", new ExchangeCategoriesViewHelper());
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

    	try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
