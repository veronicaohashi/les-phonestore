package les.core.impl.business.client;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.client.CreditCard;

public class RequiredFieldCreditCardValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof CreditCard){
			CreditCard card = (CreditCard)entity;
			
			String number = card.getNumber();
			Integer month = card.getMonth();
			Integer year = card.getYear();
			Integer code = card.getCode();
			String flag = card.getFlag();
			String name = card.getCardholderName();
			String cpf = card.getCardholderCpf();
			
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
		}		
		return null;
	}

}
