package les.domain.product;

import les.domain.DomainEntity;

public class Color extends DomainEntity {
	private String description;

	public Color() {
		
	}
	
	public Color(Integer id, String description) {
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
