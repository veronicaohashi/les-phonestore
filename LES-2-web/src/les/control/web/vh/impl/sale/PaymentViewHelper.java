package les.control.web.vh.impl.sale;

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
import les.domain.sale.Payment;
import les.domain.sale.PaymentData;

public class PaymentViewHelper implements IViewHelper{

	public DomainEntity getEntity(HttpServletRequest request) {
		String action = request.getParameter("action");				
		CreditCard card = new CreditCard();
		Payment payment = new Payment();
		Client client = new Client();
		HttpSession session = request.getSession();

		String number = request.getParameter("txtNumber");
		String month = request.getParameter("txtMonth");
		String year = request.getParameter("txtYear");
		String code = request.getParameter("txtCode");
		String name = request.getParameter("txtName");
		String cpf = request.getParameter("txtCpf");
		String flag = request.getParameter("txtFlag");
		String[] linsert = request.getParameterValues("linsert");
		
		String id = request.getParameter("id");
		String quantity = request.getParameter("cbInstallmentQuantity");
		String price = request.getParameter("txtInstallmentPrice");
		
		String id1 = request.getParameter("id1");
		String quantity1 = request.getParameter("cbInstallmentQuantity1");
		String price1 = request.getParameter("txtInstallmentPrice1");
		
		List<PaymentData> dados = new ArrayList<PaymentData>();
		PaymentData dados1 = new PaymentData();
		PaymentData dados2 = new PaymentData();
		
			
		if(action.equals("SAVE")) {
			// Recuperando dados para cadastrar novo cartão
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
			if(linsert.length > 0) {
				client = (Client) session.getAttribute("client");
				card.setClient(client);
			}
			// Recuperando quantidade de parcelas e valor
			if(quantity != null && price != null) {
				dados1.setQuantity(Integer.parseInt(quantity));
				dados1.setPrice(Double.parseDouble(price));			
			}
			dados1.setCard(card);
			dados.add(dados1);
			payment.setPaymentDatas(dados);
		}
		
		if(action.equals("CONSULT")) {
			// Recuperando quantidade de parcelas, valor e id do cartão 1
			if(quantity != null && price != null) {
				dados1.setQuantity(Integer.parseInt(quantity));
				dados1.setPrice(Double.parseDouble(price));	
				dados1.setCard(new CreditCard(Integer.parseInt(id)));
				dados.add(dados1);
			}
			// Recuperando quantidade de parcelas, valor e id do cartão 2
			if(quantity1 != null && price1 != null) {
				dados2.setQuantity(Integer.parseInt(quantity1));
				dados2.setPrice(Double.parseDouble(price1));
				dados2.setCard(new CreditCard(Integer.parseInt(id1)));
				dados.add(dados2);
			}
		}
		
		payment.setPaymentDatas(dados);
		
		return payment;
	}	
	
	public void setView(Result result, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
				
		RequestDispatcher rd = null;		
		
		if (result.getMsg() == null) {
			if(action.equals("SAVE")) {
				Payment payment = (Payment) result.getEntities().get(0);
				session.setAttribute("payment", payment);

				rd = request.getRequestDispatcher("Resume.jsp");				
			} else if (action.equals("CONSULT")) {
				Payment payment = (Payment) result.getEntities().get(0);
				session.setAttribute("payment", payment);

				rd = request.getRequestDispatcher("Resume.jsp");					
			}
		}
		
		rd.forward(request, response);	
	}


	public void setView(Result result, ServletConfig config) {
	}

}
