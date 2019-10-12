package les.core.impl.business.sale;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.sale.Order;

public class OrderDeliveryDateValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Order) {
			Order order = (Order)entity;
			if(order.getStatus().getId() == 4)
				
				order.setDeliveryDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
		return null;
	}
}
