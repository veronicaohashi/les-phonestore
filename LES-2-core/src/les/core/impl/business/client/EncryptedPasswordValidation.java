package les.core.impl.business.client;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.client.UserDAO;
import les.domain.DomainEntity;
import les.domain.client.Client;
import les.domain.client.User;

public class EncryptedPasswordValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Client){
			Client client = (Client)entity;
			String sal = BCrypt.gensalt();
			String crypt_password = BCrypt.hashpw(client.getUser().getPassword(), sal);
			
			client.getUser().setPassword(crypt_password);;			
			IDAO dao = new UserDAO();
			
			try {
				User user = new User(client.getUser().getEmail(), client.getUser().getPassword(), client.getUser().getLevel());
				dao.save(user);	
				client.setUser(user);
			} catch(SQLException e) {
				e.printStackTrace();
			}				
		}		
		return null;
	}

}
