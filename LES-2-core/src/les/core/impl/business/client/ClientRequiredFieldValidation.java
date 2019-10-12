package les.core.impl.business.client;

import les.core.IStrategy;
import les.domain.DomainEntity;
import les.domain.client.Client;

public class ClientRequiredFieldValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Client){
			Client client = (Client)entity;
			
			String firstname = client.getFirstname();
			String lastname = client.getLastname();
			String cpf = client.getCpf();
			String birthday = client.getBirthday();
			String gender = client.getGender();
			String phone = client.getPhone();
			String email = client.getUser().getEmail();
			
			StringBuilder msg = new StringBuilder();
			if(client.getLactive()) {
				if(firstname.equals("") ) 
					msg.append("O nome deve ser preenchido!");
				
				if(lastname.equals("") ) 
					msg.append("O sobrenome deve ser preenchido!");
					
				if(cpf.equals("") ) 
					msg.append("O CPF deve ser preenchido!");
				
				if(cpf.length() > 11 || cpf.length() < 11)
					msg.append("CPF inválido!");
					
				if(birthday.equals("") ) 
					msg.append("A data de nascimento deve ser preenchida!");
				
				if(gender ==null || gender.equals("") ) 
					msg.append("O gênero deve ser preenchido!");
				
				if(phone.equals("") ) 
					msg.append("O celular deve ser preenchido!");
				
				if(email.equals("") ) 
					msg.append("O email deve ser preenchido!");
				
				if(msg.length() > 0)
					return msg.toString();
			}
		}		
		return null;
	}

}
