package les.domain.product;

import les.domain.DomainEntity;

public class Processor extends DomainEntity {
	private String description;

	public Processor() {
		
	}
	
	public Processor(Integer id, String description) {
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
