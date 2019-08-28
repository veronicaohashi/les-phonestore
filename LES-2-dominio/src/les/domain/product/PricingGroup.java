package les.domain.product;

import les.domain.DomainEntity;

public class PricingGroup extends DomainEntity {
	private String description;
	private double percentage;
	
	public PricingGroup() {
		
	}

	public PricingGroup(Integer id, String description, double percentage) {
		super(id);
		this.description = description;
		this.percentage = percentage;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}	
}
