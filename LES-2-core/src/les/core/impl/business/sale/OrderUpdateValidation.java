package les.core.impl.business.sale;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.sale.Order;

public class OrderUpdateValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Order) {
			Order order = (Order)entity;
			
			if(order.getOrderIds().isEmpty())
				return "Selecione um pedido para alterar o status";
		}
		return null;
	}
}
