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
				int discountExchange = 0;
				int discountPromotional = 0;
				
				List<Coupon> new_order_coupons = new ArrayList<Coupon>();

				for(Coupon c : order.getOrderCoupons().getCoupons()) {
					if(c.getCouponCategory().getId() == 2) {
						discountPromotional += c.getValue();		
						new_order_coupons.add(c);		
					}
				}
								
				for(Coupon c : order.getOrderCoupons().getCoupons()) {
					if(c.getCouponCategory().getId() == 1) {
						discountExchange += c.getValue();		
						new_order_coupons.add(c);	
					}
				}
				if(discountExchange > order.getTotalItemsPrice()) {		
					order.setPrice(order.getTotalItemsPrice() - discountExchange);	
				} else if(discountExchange + discountPromotional > order.getTotalItemsPrice()) {			
					order.setPrice(0.0);
				}
								
				order.setTotalDiscountPrice(discountExchange);
				order.getOrderCoupons().setCoupons(new_order_coupons);				
			}
		}
		return null;
	}
}
