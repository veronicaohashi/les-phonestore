package les.domain.product;

import les.domain.DomainEntity;

public class SO extends DomainEntity {
	private String description;
	
	public SO() {
		
	}
	
	public SO(Integer id, String description) {
		super(id);
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
