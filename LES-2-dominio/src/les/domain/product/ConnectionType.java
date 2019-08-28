package les.domain.product;

import les.domain.DomainEntity;

public class ConnectionType extends DomainEntity {
	private String description;
	private Phone phone;
	public ConnectionType() {
		
	}
	public ConnectionType(int id, String description) {
		super(id);
		this.description = description;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}	
}
