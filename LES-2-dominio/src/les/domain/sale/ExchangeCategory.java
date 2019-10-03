package les.domain.sale;

import les.domain.DomainEntity;

public class ExchangeCategory extends DomainEntity {
	private String description;

	public ExchangeCategory() {
		
	}
	public ExchangeCategory(Integer id) {
		super(id);
	}
	public ExchangeCategory(Integer id, String description) {
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
