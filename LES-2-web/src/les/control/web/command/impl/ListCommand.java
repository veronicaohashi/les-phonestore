
package les.control.web.command.impl;

import les.core.application.Result;
import les.domain.DomainEntity;


public class ListCommand extends AbstractCommand{
	
	public Result execute(DomainEntity entity) {		
		return facade.list(entity);
	}
}
