
package les.control.web.command.impl;

import les.core.application.Result;
import les.domain.DomainEntity;

public class UpdateCommand extends AbstractCommand{

	
	public Result execute(DomainEntity entity) {
		
		return facade.update(entity);
	}

}
