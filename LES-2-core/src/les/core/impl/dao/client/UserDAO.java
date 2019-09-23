package les.core.impl.dao.client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.client.User;

public class UserDAO extends AbstractJdbcDAO{
    
	public UserDAO() {
		super("users", "id");	
	}

	@Override
	public void save(DomainEntity entity) {			
		openConnection();
		PreparedStatement pst = null;
		User user = (User)entity;

		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();			
			sql.append("INSERT INTO users(email, password, level, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getPassword());
			pst.setInt(3, user.getLevel());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(4, time);
			pst.setTimestamp(5, time);

			pst.executeUpdate();				
			ResultSet rs = pst.getGeneratedKeys();
			
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
				user.setId(id);
			
				connection.commit();		
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			e.printStackTrace();			
			}finally{
				try {
					pst.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}				
		
	}
	
	@Override
	public void update(DomainEntity entity) {
		// TODO Auto-generated method stub	
		User user = (User)entity;
		PreparedStatement pst = null;
		openConnection();

		try {
			connection.setAutoCommit(false);					
			StringBuilder sql = new StringBuilder();

			if(user.getPassword() != null) {
				sql.append("UPDATE users SET password=? WHERE id=?");				

				pst = connection.prepareStatement(sql.toString());
				pst.setString(1, user.getPassword());		
				pst.setInt(2, user.getId());
			} else {
				sql.append("UPDATE users SET email=? WHERE id=?");				

				pst = connection.prepareStatement(sql.toString());
				pst.setString(1, user.getEmail());
				pst.setInt(2, user.getId());
			}

			pst.executeUpdate();			
			connection.commit();		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		

	}

	@Override
	public void delete(DomainEntity entity) {	
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		User user = (User) entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM users ";

		if (user.getEmail() != null) {
			sql += "WHERE email=?";
		} 
		
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (user.getEmail() != null) {
				pst.setString(1, user.getEmail());
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				User u = new User();
				
				u.setId(rs.getInt("id"));		
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				u.setLevel(rs.getInt("level"));
				all.add(u);
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
			try {
				pst.close();
				if(ctrlTransaction)
					connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return null;
	}

}
