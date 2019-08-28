package les.control.web.vh.impl.product;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les.control.web.vh.IViewHelper;
import les.core.application.Result;
import les.domain.DomainEntity;
import les.domain.product.Processor;

public class ProcessorViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		return new Processor();
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {	
	}


	public void setView(Result result, ServletConfig config) {
		config.getServletContext().setAttribute("processorResult", result);		
	}

}
