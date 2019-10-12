package les.core.impl.business.sale;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.client.CreditCard;
import les.domain.sale.Payment;
import les.domain.sale.PaymentData;

public class PaymentRequiredFieldValidation implements IStrategy {

	public String process(DomainEntity entity) {	
		if(entity instanceof Payment) {
			Payment payment = (Payment)entity;	
			StringBuilder msg = new StringBuilder();
			
			if(payment.getPaymentDatas().size() == 0) {
				msg.append("Selecione o número de parcelas!");

			} else {
				for(PaymentData paymentData : payment.getPaymentDatas()) {	
					Double price = paymentData.getPrice();
					Integer quantity = paymentData.getQuantity();		
					CreditCard card = paymentData.getCard();
		
					if(quantity == null || price == null)
						msg.append("Selecione o número de parcelas!");					
					if(card.getId() == null && card.getNumber().equals(""))
						msg.append("Selecione um cartão!");				
				}
			}
				
			if(msg.length() > 0)
				return msg.toString();
		}
			return null;
	}

}
