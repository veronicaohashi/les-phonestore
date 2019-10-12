package les.core.impl.dao.sale;

import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.sale.Coupon;
import les.domain.sale.OrderCoupons;

public class OrderCouponsDAO extends AbstractJdbcDAO{
    
	public OrderCouponsDAO() {
		super("order_coupons", "id");	
	}

	@Override
	public void save(DomainEntity entity) {	
		// Não executa nada pois vai salvar depois de finalizar o pedido	
	}
	
	@Override
	public void update(DomainEntity entity) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void delete(DomainEntity entity) {	
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		OrderCoupons orderCoupons = (OrderCoupons)entity;
		CouponDAO couponDAO = new CouponDAO();
		List<DomainEntity> entities = new ArrayList<DomainEntity>();
		List<Coupon> newOrderCoupons = new ArrayList<Coupon>();
		
		for(Coupon o : orderCoupons.getCoupons()) {
			List<DomainEntity> coupons = couponDAO.consult(o);
			
			if(! coupons.isEmpty()) {
				Coupon coupon = (Coupon)coupons.get(0);
				o.setId(coupon.getId());
				o.setValue(coupon.getValue());
				o.setCouponCategory(coupon.getCouponCategory());
				o.setName(coupon.getName());
				newOrderCoupons.add(o);
			}
		}
		
		orderCoupons.setCoupons(newOrderCoupons);
		entities.add(orderCoupons);
		return entities;
		
	}

}
