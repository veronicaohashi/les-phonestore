
package les.control.web.command;

import les.core.application.Result;
import les.domain.DomainEntity;

public interface ICommand {
	public Result execute(DomainEntity entity);
}
