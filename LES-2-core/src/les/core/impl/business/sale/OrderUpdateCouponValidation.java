package les.core.impl.business.sale;

import java.sql.SQLException;
import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.sale.CouponDAO;
import les.domain.DomainEntity;
import les.domain.sale.Coupon;
import les.domain.sale.Order;

public class OrderUpdateCouponValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Order) {
			Order order = (Order)entity;
			IDAO couponDAO = new CouponDAO();
			
			if(order.getOrderCoupons() != null) {
				for(Coupon c : order.getOrderCoupons().getCoupons()) {
					try {
						if(c.getCouponCategory().getId() == 1) {
							c.setLactive(false);
							couponDAO.update(c);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
}
