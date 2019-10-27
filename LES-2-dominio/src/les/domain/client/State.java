package les.domain.client;

import les.domain.DomainEntity;

public class State extends DomainEntity {
	private String name;
	private String acronym;
	public State() {
		
	}
	public State(Integer id, String name) {
		super(id);
		this.name = name;
	}
	public State(Integer id) {
		super(id);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getAcronym() {
		return acronym;
	}
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
}
