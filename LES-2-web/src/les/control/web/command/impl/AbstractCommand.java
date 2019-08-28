
package les.control.web.command.impl;

import les.control.web.command.ICommand;
import les.core.IFacade;
import les.core.impl.control.Facade;

public abstract class AbstractCommand implements ICommand {

	protected IFacade facade = new Facade();

}
