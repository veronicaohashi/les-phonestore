package les.core.impl.business.sale;
import java.util.List;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.sale.Order;
import les.domain.sale.Orderi;
import les.domain.sale.Status;

public class OrderFirstStatusValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Order) {
			Order order = (Order)entity;

			order.setStatus(new Status(1));
			List<Orderi> items = order.getItems();
			
			for(Orderi o : items) {
				o.setStatus(new Status(1));
			}
			
		}
		return null;
	}
}
