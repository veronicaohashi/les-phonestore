package les.control.web.vh.impl.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les.control.web.vh.IViewHelper;
import les.core.application.Result;
import les.domain.DomainEntity;
import les.domain.client.Client;
import les.domain.client.CreditCard;

public class CreditCardViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		String action = request.getParameter("action");				
		CreditCard card = new CreditCard();

		String number = request.getParameter("txtNumber");
		String month = request.getParameter("txtMonth");
		String year = request.getParameter("txtYear");
		String code = request.getParameter("txtCode");
		String name = request.getParameter("txtName");
		String cpf = request.getParameter("txtCpf");
		String flag = request.getParameter("txtFlag");
		String lmain = request.getParameter("lmain");
		String client_id = request.getParameter("client_id");
		
		String id = request.getParameter("id");		
				
		if(client_id != null) {
			Client client = new Client();
			client.setId(Integer.parseInt(client_id));
			card.setClient(client);
		}
		
		if(id != null) {
			card.setId(Integer.parseInt(id));
		}
		
		if(lmain != null) {
			card.setLmain(Boolean.parseBoolean(lmain));
		}
		
		if(action.equals("SAVE")) {
			card.setNumber(number);
			card.setCardholderName(name);
			card.setCardholderCpf(cpf);
			card.setFlag(flag);
			if(month != null && ! month.equals("")) {
				card.setMonth(Integer.parseInt(month));
			}
			if(year != null && ! month.equals("")) {
				card.setYear(Integer.parseInt(year));
			}
			if(code != null && ! month.equals("")) {
				card.setCode(Integer.parseInt(code));
			}
		}
		
		return card;
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		String action = request.getParameter("action");
		RequestDispatcher rd = null;
		
		List<String> headers = new ArrayList<String>();
		headers.add("Número");
		headers.add("Código");
		headers.add("Bandeira");
		headers.add("Mês");
		headers.add("Ano");
		headers.add("Nome do titular");
		headers.add("CPF do titular");
		headers.add("Ação");
		
		if (result.getMsg() == null) {
			String page = request.getParameter("page");		
			
			if (action.equals("CONSULT")) {
				if(page != null) {
					if(page.equals("CART")) {		
						request.setAttribute("card", result.getEntities().get(0));
						rd = request.getRequestDispatcher("CartPayment.jsp");
						
					} else if (page.equals("RESUME")) {
						HttpSession session = request.getSession();
						session.setAttribute("card", result.getEntities().get(0));							
						rd = request.getRequestDispatcher("Resume.jsp");
					}
				} else {
					rd = request.getRequestDispatcher("index.jsp");
					
				}				
			} else if (action.equals("SAVE")) {		
				// salvar e redirecionar para o painel administrativo
			} else if(action.equals("LIST")) {
				request.setAttribute("headers", headers);
				request.setAttribute("cards", result.getEntities());
				
				if(page != null) {
					if(page.equals("CART")) {
						rd = request.getRequestDispatcher("CartPaymentList.jsp");						
					} else if(page.equals("2CARDS")) {
						rd = request.getRequestDispatcher("CartPaymentCards.jsp");					
					}
				} else {
					rd = request.getRequestDispatcher("CreditCards.jsp");
				}				
			}	
		} else {
			if (action.equals("SAVE")) {
				// salvar e redirecionar para o painel administrativo
			}
		}
		
		rd.forward(request, response);	
	}


	public void setView(Result result, ServletConfig config) {
	}

}
