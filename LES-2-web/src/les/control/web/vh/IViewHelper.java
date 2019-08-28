
package les.control.web.vh;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les.core.application.Result;
import les.domain.DomainEntity;

public interface IViewHelper {

	public DomainEntity getEntity(HttpServletRequest request);
	
	public void setView(Result resultado, 
			HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException;
	
	public void setView(Result resultado, ServletConfig config);

}
