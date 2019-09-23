package les.core.impl.business.client;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.client.User;

public class RequiredFieldUserValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof User){
			User user = (User)entity;
			
			String password = user.getPassword();
			String email = user.getEmail();
			
			StringBuilder msg = new StringBuilder();
			
			if(email.equals("") ) 
				msg.append("O email deve ser preenchido!");
			
			if(password.equals("") ) 	
				msg.append("A senha deve ser preenchida!");
			
			if(msg.length() > 0)
				return msg.toString();
		}		
		return null;
	}

}
