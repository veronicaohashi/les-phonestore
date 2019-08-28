package les.domain.product;

import les.domain.DomainEntity;

public class InactivationCategory extends DomainEntity {
	private String description;
		
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
