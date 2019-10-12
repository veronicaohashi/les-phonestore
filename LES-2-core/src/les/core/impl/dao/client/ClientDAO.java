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
import les.domain.client.Client;
import les.domain.client.User;

public class ClientDAO extends AbstractJdbcDAO{
    
	public ClientDAO() {
		super("clients", "id");	
	}

	@Override
	public void save(DomainEntity entity) {			
		openConnection();
		PreparedStatement pst = null;
		Client client = (Client)entity;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();			
			sql.append("INSERT INTO clients(firstname, lastname, gender, cpf, birthday, phone, users_id, lactive, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, client.getFirstname());
			pst.setString(2, client.getLastname());
			pst.setString(3, client.getGender());
			pst.setString(4, client.getCpf());
			pst.setString(5, client.getBirthday());
			pst.setString(6, client.getPhone());
			pst.setInt(7, client.getUser().getId());
			pst.setBoolean(8, client.getLactive());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(9, time);
			pst.setTimestamp(10, time);

			pst.executeUpdate();				
			ResultSet rs = pst.getGeneratedKeys();
			
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
				client.setId(id);
			
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
		Client client = (Client)entity;		
		PreparedStatement pst = null;
		openConnection();

		try {
			connection.setAutoCommit(false);					
			StringBuilder sql = new StringBuilder();
			
			sql.append("UPDATE clients SET firstname=?, lastname=?, gender=?, cpf=?, birthday=?, phone=?, lactive=?, updated_at=?");
			sql.append("WHERE id=?");				

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, client.getFirstname());
			pst.setString(2, client.getLastname());
			pst.setString(3, client.getGender());
			pst.setString(4, client.getCpf());
			pst.setString(5, client.getBirthday());
			pst.setString(6, client.getPhone());
			pst.setBoolean(7, client.getLactive());
			Timestamp time = new Timestamp(System.currentTimeMillis());	
			pst.setTimestamp(8, time);			
			pst.setInt(8, client.getId());

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
		Client client = (Client) entity;
		PreparedStatement pst = null;
		String sql = "SELECT clients.*, users.* FROM clients JOIN users ON users.id = clients.users_id ";

		if (client.getId() != null) {
			sql += "WHERE clients.id=?";
		} else if (client.getCpf() != null) {
			sql += "WHERE cpf=?";
		} else if(client.getUser().getId() != null) {
			sql += "WHERE users_id=?";
		} else {
			sql += "WHERE lactive = true";
		}

		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (client.getId() != null) {
				pst.setInt(1, client.getId());
			} else if (client.getCpf() != null) {
				pst.setString(1, client.getCpf());
			} else if(client.getUser().getId() != null) {
				pst.setInt(1, client.getUser().getId());			
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Client c = new Client();
				
				c.setId(rs.getInt("id"));		
				c.setFirstname(rs.getString("firstname"));
				c.setLastname(rs.getString("lastname"));
				c.setCpf(rs.getString("cpf"));
				c.setBirthday(rs.getString("birthday"));
				c.setPhone(rs.getString("phone"));
				c.setLactive(rs.getBoolean("lactive"));
				c.setGender(rs.getString("gender"));
				c.setUser(new User(rs.getInt("users_id"),rs.getString("email"),rs.getString("password"),rs.getInt("level")));
				all.add(c);
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
