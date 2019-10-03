package les.core.impl.business.sale;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.sale.Orderi;
import les.domain.sale.Status;

public class OrderiUpdateStatusValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Orderi) {
			Orderi orderi = (Orderi)entity;
			
			Status status = new Status();
			status.setId(orderi.getStatus().getId() + 1);
			orderi.setStatus(status);
		}
		return null;
	}
}
