package les.core.impl.business.sale;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.sale.Orderi;

public class OrderiExchangeCategoryValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Orderi){
			Orderi orderi = (Orderi)entity;
			
			if(orderi.getExchangeCategory() == null && orderi.getStatus().getId() == 5) {
				return "A troca deve estar associada a uma justificativa";
			}
		}
		
		return null;
	}
}
