package les.domain.sale;

import les.domain.DomainEntity;

public class CouponCategory extends DomainEntity {
	private String description;

	public CouponCategory() {
		
	}
	public CouponCategory(Integer id) {
		super(id);
	}
	public CouponCategory(Integer id, String description) {
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
