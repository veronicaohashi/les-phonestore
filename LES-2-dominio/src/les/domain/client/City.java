package les.domain.client;

import les.domain.DomainEntity;

public class City extends DomainEntity {
	public String name;
	public State state;
	public City() {
		
	}
	public City(String name) {
		this.name = name;
	}
	public City(Integer id, String name, State state) {
		super(id);
		this.name = name;
		this.state = state;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
}
