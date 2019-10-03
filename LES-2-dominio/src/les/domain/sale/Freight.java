package les.domain.sale;

import les.domain.DomainEntity;

public class Freight extends DomainEntity {
	private String description;
	private Double price;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
