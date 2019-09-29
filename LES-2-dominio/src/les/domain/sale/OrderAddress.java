package les.domain.sale;

import les.domain.DomainEntity;
import les.domain.client.Address;

public class OrderAddress extends DomainEntity {
	private Address address;
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
}