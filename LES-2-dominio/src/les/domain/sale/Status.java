package les.domain.sale;

import les.domain.DomainEntity;

public class Status extends DomainEntity {
	private String description;

	public Status() {
		
	}
	public Status(Integer id) {
		super(id);
	}
	public Status(Integer id, String description) {
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
