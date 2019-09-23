package les.domain.client;

import les.domain.DomainEntity;

public class ResidenceType extends DomainEntity {
	private String description;

	public ResidenceType() {
		
	}
	public ResidenceType(Integer id, String description) {
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
