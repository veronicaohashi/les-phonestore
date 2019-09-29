package les.core.impl.business.client;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.client.CreditCard;
import les.domain.sale.Payment;
import les.domain.sale.PaymentData;

public class CreditCardRequiredFieldValidation implements IStrategy {

	public String process(DomainEntity entity) {		
		String number = "";
		Integer month = 0;
		Integer year = 0;
		Integer code = 0;
		String flag = "";
		String name = "";
		String cpf = "";
		
		if(entity instanceof CreditCard){
			CreditCard card = (CreditCard)entity;			
			number = card.getNumber();
			month = card.getMonth();
			year = card.getYear();
			code = card.getCode();
			flag = card.getFlag();
			name = card.getCardholderName();
			cpf = card.getCardholderCpf();
		} else if(entity instanceof Payment) {
			Payment payment = (Payment)entity;
			PaymentData paymentData = payment.getPaymentDatas().get(0);
			number = paymentData.getCard().getNumber();
			month = paymentData.getCard().getMonth();
			year = paymentData.getCard().getYear();
			code = paymentData.getCard().getCode();
			flag = paymentData.getCard().getFlag();
			name = paymentData.getCard().getCardholderName();
			cpf = paymentData.getCard().getCardholderCpf();
		}	

		StringBuilder msg = new StringBuilder();
		if(number.equals("") ) 
			msg.append("O número deve ser preenchido! ");
		if(number.length() > 16 || number.length() < 16)
			msg.append("Número inválido! O número deve conter 16 dígitos ");
		if(flag.equals(""))
			msg.append("Número inválido! O número contém erros e não foi associado com uma bandeira! ");
		if(code == null)
			msg.append("O código deve ser preenchido! ");
		if(code != null) {
			if(code.toString().length() > 3 || code.toString().length() < 3)
				msg.append("Código inválido! O código deve conter 3 dígitos! ");
		}
		if(year == null)
			msg.append("O ano deve ser preenchido! ");
		if(year != null) {
			if(year < 2019)
				msg.append("Ano inválido! O ano não deve se menor que o ano atual! ");				
		}
		if(month == null)
			msg.append("O mẽs deve ser preenchido! ");
		if(month != null) {
			if(month < 8)
				msg.append("Mês inválido! O mês não deve se menor que o mês atual! ");
		}
		if(name.equals(""))
			msg.append("O nome deve ser preenchido!");
		if(cpf.equals(""))
			msg.append("O cpf deve ser preenchido! ");
		if(cpf.length() > 11 || cpf.length() < 11 )
			msg.append("Cpf inválido! O cpf deve conter 11 dígitos! ");
		
		if(msg.length() > 0)
			return msg.toString();
		else
			return null;
	}

}
