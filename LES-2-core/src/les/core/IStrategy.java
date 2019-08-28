package les.core;

import les.domain.DomainEntity;

public interface IStrategy 
{
	public String process(DomainEntity entity);	
}
