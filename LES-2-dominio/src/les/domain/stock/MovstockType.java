package les.domain.stock;

import les.domain.DomainEntity;

public class MovstockType extends DomainEntity{
	private String description;
	
	public MovstockType() {
		
	}
	public MovstockType(Integer id) {
		super(id);
	}
	public MovstockType(Integer id, String description) {
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
