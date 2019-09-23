package les.core.impl.business.client;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.client.Client;

public class PasswordValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Client){
			Client client = (Client)entity;
			String password = client.getUser().getPassword();
			
			if(password != null) {
				if(password.equals(""))
					return "A senha deve ser preenchida!";
				else if(password.length() < 8 ) 
					return "A senha deve possuir no mínimo 8 caracteres!";
			}
			
		}		
		return null;
	}

}
