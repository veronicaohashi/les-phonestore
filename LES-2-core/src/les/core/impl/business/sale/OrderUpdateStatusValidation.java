package les.core.impl.business.sale;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.sale.Order;
import les.domain.sale.Status;

public class OrderUpdateStatusValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Order) {
			Order order = (Order)entity;
			
			Status status = new Status();
			status.setId(order.getStatus().getId() + 1);
			order.setStatus(status);
			
		}
		return null;
	}
}
