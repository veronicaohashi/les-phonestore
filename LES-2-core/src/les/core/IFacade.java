package les.core;

import les.core.application.Result;
import les.domain.DomainEntity;

public interface IFacade {

	public Result save(DomainEntity entity);
	public Result update(DomainEntity entity);
	public Result delete(DomainEntity entity);
	public Result consult(DomainEntity entity);
	public Result list(DomainEntity entity);
	
	
}
