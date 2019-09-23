package les.core.impl.business.client;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.client.UserDAO;
import les.domain.DomainEntity;
import les.domain.client.User;

public class DecryptedPasswordValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof User){
			User user = (User)entity;
			IDAO dao = new UserDAO();
			
			try {
				List<DomainEntity> users = dao.consult(new User(user.getEmail()));
				if(! users.isEmpty()) {
					User u = (User)users.get(0);
					if(! BCrypt.checkpw(user.getPassword(), u.getPassword())) {
						return "E-mail ou senha inválido(s)";
					}					
				} else {
					return "E-mail ou senha inválido(s)";
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}				
		}		
		return null;
	}

}
