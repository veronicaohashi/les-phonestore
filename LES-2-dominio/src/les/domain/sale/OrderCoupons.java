package les.domain.sale;

import java.util.List;

import les.domain.DomainEntity;

public class OrderCoupons extends DomainEntity {
	private List<Coupon> coupons;

	public List<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
}
