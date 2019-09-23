package les.domain.client;

import les.domain.DomainEntity;

public class State extends DomainEntity {
	private String name;
	public State() {
		
	}
	public State(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
