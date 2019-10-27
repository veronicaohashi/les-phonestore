package les.core.impl.business.sale;
import java.util.ArrayList;
import java.util.List;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.sale.Coupon;
import les.domain.sale.Order;

public class OrderCouponValueValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Order) {
			Order order = (Order)entity;

			if(order.getOrderCoupons() != null) {
				int discount = 0;
				List<Coupon> new_order_coupons = new ArrayList<Coupon>();
				int count = 0;
				for(Coupon c : order.getOrderCoupons().getCoupons()) {
					if(c.getCouponCategory().getId() == 2)
						count++;
				}
				
				if(count == order.getOrderCoupons().getCoupons().size()) {
					order.setPrice(0.0);
				} else {
				
					for(Coupon c : order.getOrderCoupons().getCoupons()) {
						
						discount += c.getValue();		
						new_order_coupons.add(c);			
	
						if(discount > order.getTotalItemsPrice()) {						
							order.setTotalDiscountPrice(discount);
							order.setPrice(order.getTotalItemsPrice() - discount);
							order.getOrderCoupons().setCoupons(new_order_coupons);
							break;
						}
					}
				}
				
				
			}
		}
		return null;
	}
}
