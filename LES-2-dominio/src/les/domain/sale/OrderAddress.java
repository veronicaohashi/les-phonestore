package les.domain.sale;

import les.domain.DomainEntity;
import les.domain.client.Address;

public class OrderAddress extends DomainEntity {
	private Address address;
	private Freight freight;
	
	public OrderAddress() {
		
	}
	public OrderAddress(Integer id) {
		super(id);
	}
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	public Freight getFreight() {
		return freight;
	}
	public void setFreight(Freight freight) {
		this.freight = freight;
	}
}