package les.core.impl.business.sale;

import java.time.LocalDate;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.sale.Orderi;

public class OrderiExchangeDateValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Orderi) {
			Orderi orderi = (Orderi)entity;
			if(orderi.getStatus().getId() == 5) {
				LocalDate currentDate = LocalDate.now();
				LocalDate maximumDate =  LocalDate.parse(orderi.getOrder().getDeliveryDate()).plusDays(7);
	
				if(currentDate.isAfter(maximumDate))
					return "A troca não pode ser solicitada pois o prazo máximo foi atingido!";
			}
		}
		return null;
	}
}
