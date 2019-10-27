package les.domain.sale;

import les.domain.DomainEntity;
import les.domain.client.State;

public class Freight extends DomainEntity {
	private State state;
	private String description;
	private Double price;
	public Freight() {
		
	}
	public Freight(Integer id) {
		super(id);
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
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
