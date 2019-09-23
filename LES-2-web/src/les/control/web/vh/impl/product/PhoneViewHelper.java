package les.control.web.vh.impl.product;

import java.io.IOException;
import java.util.ArrayList;
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
import les.domain.client.Client;
import les.domain.product.Brand;
import les.domain.product.Capacity;
import les.domain.product.Color;
import les.domain.product.ConnectionType;
import les.domain.product.InactivationCategory;
import les.domain.product.Phone;
import les.domain.product.PricingGroup;
import les.domain.product.Processor;
import les.domain.product.Reference;
import les.domain.product.SO;

public class PhoneViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		Phone phone = new Phone();
		String action = request.getParameter("action");
		
		String id_phone = request.getParameter("id");
		String model = request.getParameter("txtModel");
		String id_brand = request.getParameter("cbBrand");
		String id_so = request.getParameter("cbSO");
		String id_processor = request.getParameter("cbProcessor");
		String expandability = request.getParameter("txtExpandability");
		String id_pricing_group = request.getParameter("cbPricingGroup");
		String screenSize = request.getParameter("txtScreenSize");
		String screenResol = request.getParameter("txtScreenResol");
		String rCameraResol = request.getParameter("txtRcameraResol");
		String fCameraResol = request.getParameter("txtFcameraResol");
		String camcorderResol = request.getParameter("txtCamcorderResol");
		String chips = request.getParameter("txtChips");
		String height = request.getParameter("txtHeight");
		String width = request.getParameter("txtWidth");
		String depth = request.getParameter("txtDepth");
		String weight = request.getParameter("txtWeight");
		String connection[] = request.getParameterValues("cbConnection");
		String ram = request.getParameter("txtRAM");
		String packageContent = request.getParameter("txtPackage");
		String note = request.getParameter("txtNote");
		String id_inactivationCategory = request.getParameter("cbInactivationCategory");
		String costPrice = request.getParameter("txtCostPrice");
		String salePrice = request.getParameter("txtSalePrice");
		
		if ( action.equals("CONSULT") || action.equals("UPDATE") || action.equals("DELETE") ) {
			phone.setId(Integer.parseInt(id_phone));
			
			if(id_inactivationCategory != null) {
				InactivationCategory i = new InactivationCategory();
				i.setId(Integer.parseInt(id_inactivationCategory));
				phone.setInactivationCategory(i);
			}
		}
		
		if ( action.equals("UPDATE")) {
			if(! salePrice.equals("")) {
				phone.setSalePrice(Double.parseDouble(salePrice));			
			}
			if(! costPrice.equals("")) {
				phone.setCostPrice(Double.parseDouble(costPrice));			
			}
		}

		if ( action.equals("SAVE") || action.equals("UPDATE")) {	
			phone.setModel(model);
			phone.setPackageContent(packageContent);
			phone.setNote(note);

			if(! expandability.equals("")) {
				phone.setExpandability(Integer.parseInt(expandability));
			}
			if(! screenSize.equals("")) {
				phone.setScreenSize(Double.parseDouble(screenSize));
			}
			if(! screenResol.equals("")) {
				phone.setScreenResol(Double.parseDouble(screenResol));
			}
			if(! rCameraResol.equals("")) {
				phone.setRcameraResol(Double.parseDouble(rCameraResol));
			}
			if(! fCameraResol.equals("")) {
				phone.setFcameraResol(Double.parseDouble(fCameraResol));
			}
			if(! camcorderResol.equals("")) {
				phone.setCamcorderResol(Double.parseDouble(camcorderResol));
			}
			if(! height.equals("")) {
				phone.setHeight(Double.parseDouble(height));
			}
			if(! width.equals("")) {
				phone.setWidth(Double.parseDouble(width));
			}
			if(! depth.equals("")) {
				phone.setDepth(Double.parseDouble(depth));
			}
			if(! weight.equals("")) {
				phone.setWeight(Double.parseDouble(weight));
			}
			if(! ram.equals("")) {
				phone.setRamMemory(Integer.parseInt(ram));
			}
			if(id_brand != null) {
				Brand brand = new Brand();
				brand.setId(Integer.parseInt(id_brand));
				phone.setBrand(brand);				
			}
			if(id_so != null) {
				SO so = new SO();
				so.setId(Integer.parseInt(id_so));
				phone.setSo(so);				
			}
			if(id_processor != null) {
				Processor processor = new Processor();
				processor.setId(Integer.parseInt(id_processor));
				phone.setProcessor(processor);				
			}		
			if(id_pricing_group != null) {
				PricingGroup pricingGroup = new PricingGroup();
				pricingGroup.setId(Integer.parseInt(id_pricing_group));
				phone.setPricingGroup(pricingGroup);				
			}
			if(! chips.equals("")) {
				phone.setChip(Integer.parseInt(chips));		
			}
			if(connection != null) {
				List<ConnectionType> connections = new ArrayList<>();
				for(String id : connection){
					ConnectionType c = new ConnectionType();
					c.setId(Integer.parseInt(id));
					connections.add(c);
				}

				phone.setConnectionType(connections);
			}
			
			Reference ref = new Reference();
			List<Reference> references = new ArrayList<Reference>();
			int i = 0;
			
			if(request.getParameter("cbColor"+i) != null) {
				do {
					String id_color = request.getParameter("cbColor"+i);
					String[] ids_capacity = request.getParameterValues("cbCapacity"+i);
					
					Color color = new Color();
					color.setId(Integer.parseInt(id_color));
					
					List<Capacity> capacities = new ArrayList<>();
					for(String id : ids_capacity){
						Capacity capacity = new Capacity();
						capacity.setId(Integer.parseInt(id));
						capacities.add(capacity);
					}
					i++;			

					ref.setColorCapacity(color, capacities);
					references.add(ref);					
					
				} while(request.getParameterValues("cbColor"+i) != null && request.getParameterValues("cbCapacity"+i) != null);
			}
			phone.setReference(references);		
		}
		
		return phone;	
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {		
		String action = request.getParameter("action");
		String isJson = request.getParameter("json");
		
		Client client = (Client)request.getSession().getAttribute("user");
		
		RequestDispatcher rd = null;
		
		List<String> headers = new ArrayList<String>();
		headers.add("Código");
		headers.add("Modelo");
		headers.add("Marca");
		headers.add("Status");
		
		if (result.getMsg() == null) {
			if (action.equals("LIST")) {
				if(isJson != null) {
					List<DomainEntity> phones = result.getEntities();  

					String json = new Gson().toJson(phones);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json);	
					
				} else {
					request.setAttribute("headers", headers);
					request.setAttribute("phones", result.getEntities());	
					
					if(client != null && client.getUser().getLevel() == 2) {
						rd = request.getRequestDispatcher("Phones.jsp");						
					} else {
						
						rd = request.getRequestDispatcher("IndexPhone.jsp");
					}					
				}			
			} else if (action.equals("SAVE")) {
				result.setMsg("Celular cadastrado com sucesso!");
				request.setAttribute("response", result.getMsg());
				rd = request.getRequestDispatcher("/Phones?action=LIST");
				
			} else if (action.equals("CONSULT")) {
				request.setAttribute("phone", result.getEntities().get(0));
				
				if(client != null && client.getUser().getLevel() == 2) {
					rd = request.getRequestDispatcher("PhoneFormUpdate.jsp");						
				} else {
					rd = request.getRequestDispatcher("PhoneDetails.jsp");
				}
				
			} else if (action.equals("UPDATE")) {
				result.setMsg("Celular alterado com sucesso!");
				request.setAttribute("response", result.getMsg());
				rd = request.getRequestDispatcher("/Phones?action=LIST");
				
			} else if (action.equals("DELETE")) {
				result.setMsg("Celular inativado com sucesso!");
				request.setAttribute("response", result.getMsg());
				rd = request.getRequestDispatcher("/Phones?action=LIST");
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
