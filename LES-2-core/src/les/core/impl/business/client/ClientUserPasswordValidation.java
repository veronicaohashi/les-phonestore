package les.core.impl.business.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.client.Client;

public class ClientUserPasswordValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Client){
			Client client = (Client)entity;
			String password = client.getUser().getPassword();
			String passwordConfirmation = client.getUser().getPasswordConfirmation();

			if(client.getLactive()) {
				Pattern p = Pattern.compile("[^A-Za-z0-9]");
				if(password != null) {
					Matcher m = p.matcher(password);
					
					if(password.equals(""))
						return "A senha deve ser preenchida!";
					else if(password.length() < 8 ) 
						return "A senha deve possuir no mínimo 8 caracteres!";
					else if(! m.find())
						return "A senha deve possuir no mínimo 1 caracter especial!";
					else if(password.equals(password.toLowerCase()))
						return "A senha deve possuir no mínimo 1 caracter maiusculo!";
					else if(! password.equals(passwordConfirmation)) 
						return "Confirmação de senha deve ser igual a senha!";
				}
			}
			
		}		
		return null;
	}

}
