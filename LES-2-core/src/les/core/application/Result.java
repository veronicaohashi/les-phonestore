
package les.core.application;

import java.util.List;

import les.domain.DomainEntity;

public class Result extends DomainEntity {

	private String msg;
	private List<DomainEntity> entities;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<DomainEntity> getEntities() {
		return entities;
	}

	public void setEntities(List<DomainEntity> entities) {
		this.entities = entities;
	}
	
	
	
	
}
