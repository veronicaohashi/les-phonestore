package les.domain.product;

import les.domain.DomainEntity;

public class Capacity extends DomainEntity {
	private String description;

	public Capacity() {
		
	}
	
	public Capacity(Integer id, String description) {
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
