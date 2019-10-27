package les.core.impl.business.sale;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.sale.CouponDAO;
import les.domain.DomainEntity;
import les.domain.sale.Coupon;
import les.domain.sale.CouponCategory;
import les.domain.sale.Order;

public class OrderDifferenceCouponValidation implements IStrategy {
	
	public String process(DomainEntity entity) {
		if(entity instanceof Order) {
			Order order = (Order)entity;
			double price = -order.getPrice();
			if(order.getOrderCoupons() != null && order.getPrice() < 0) {
				for(Coupon c : order.getOrderCoupons().getCoupons()) {
					if(c.getCouponCategory().getId() == 2)
						price -= c.getValue();
				}
				
				if(price > 0) {
					Coupon coupon = new Coupon();
					String name = "TR" + order.getId() + order.getClient().getId() + Math.random();
	
					coupon.setClient(order.getClient());
					coupon.setCouponCategory(new CouponCategory(1));
					coupon.setLactive(true);
					coupon.setName(name);
					coupon.setOrder(order);
					coupon.setValidity(LocalDateTime.now().plusDays(60).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
					coupon.setValue(price);
					
					IDAO couponDAO = new CouponDAO();
					try {
						couponDAO.save(coupon);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} 
			}
		}
		return null;
	}
}
