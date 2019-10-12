package les.domain.sale;

import les.domain.DomainEntity;
import les.domain.client.Client;

public class Coupon extends DomainEntity {
	private String name;
	private Double value;
	private String validity;
	private Boolean lactive;
	private CouponCategory couponCategory;
	private Order order;
	private Client client;
	
	public Coupon() {
	}
	public Coupon(Integer id) {
		super(id);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public Boolean getLactive() {
		return lactive;
	}
	public void setLactive(Boolean lactive) {
		this.lactive = lactive;
	}
	public CouponCategory getCouponCategory() {
		return couponCategory;
	}
	public void setCouponCategory(CouponCategory couponCategory) {
		this.couponCategory = couponCategory;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}
