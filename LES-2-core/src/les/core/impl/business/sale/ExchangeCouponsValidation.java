package les.core.impl.business.sale;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.sale.CouponDAO;
import les.core.impl.dao.sale.OrderiDAO;
import les.domain.DomainEntity;
import les.domain.sale.Coupon;
import les.domain.sale.CouponCategory;
import les.domain.sale.Orderi;

public class ExchangeCouponsValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Orderi){
			Orderi orderi = (Orderi)entity;			
			IDAO dao = new OrderiDAO();
			IDAO couponDAO = new CouponDAO();
			
			if (!orderi.getOrderiIds().isEmpty() && orderi.getStatus().getId() == 8) {
				try {
					List<DomainEntity> items = dao.consult(orderi);	
					
					for(DomainEntity o : items) {
						Coupon coupon = new Coupon();		

						coupon.setValidity(LocalDateTime.now().plusDays(60).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
						coupon.setLactive(true);
						coupon.setCouponCategory(new CouponCategory(1));
						coupon.setValue(((Orderi)o).getPrice() * ((Orderi)o).getQuantity());
						coupon.setOrder(((Orderi)o).getOrder());
						coupon.setClient(((Orderi)o).getOrder().getClient());
						
						String name = "TR" + ((Orderi)o).getOrder().getId() + ((Orderi)o).getOrder().getClient().getId() + Math.random();

						coupon.setName(name);								
						couponDAO.save(coupon);						
							
													
					}									
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}		
		}		
		return null;
	}
}

