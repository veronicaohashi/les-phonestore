package les.core.impl.business.client;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.client.CreditCardDAO;
import les.domain.DomainEntity;
import les.domain.client.CreditCard;

public class CreditCardUniqueMainValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof CreditCard){
			CreditCard creditCard = (CreditCard)entity;
			IDAO dao = new CreditCardDAO();
			
			if(creditCard.getClient() != null) {
				try {
					List<DomainEntity> cards = dao.consult(new CreditCard(creditCard.getClient(), creditCard.getLmain()));
					if(cards.size() > 0){
						if(cards.get(0).getId() != creditCard.getId() && creditCard.getLmain()) {
							CreditCard c = (CreditCard)cards.get(0);
							c.setLmain(false);
							
						} else if(cards.get(0).getId() == creditCard.getId() && ! creditCard.getLmain()) {
							return "Você deve possuir apenas um cartão principal!";					
						}
					} else {
						creditCard.setLmain(true);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}
		
		return null;
	}

}
