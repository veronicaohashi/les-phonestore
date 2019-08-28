package les.core.impl.dao.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.product.ConnectionType;

public class ConnectionTypeDAO extends AbstractJdbcDAO{
    
	public ConnectionTypeDAO() {
		super("connection_types", "id");	
	}

	@Override
	public void save(DomainEntity entity) {		
		openConnection();
		PreparedStatement pst = null;
		ConnectionType connectionType = (ConnectionType)entity;
		
		if(connectionType.getPhone() != null) {
			try {
				connection.setAutoCommit(false);
				StringBuilder sql = new StringBuilder();			
				sql.append("INSERT INTO connection_types_phones(phone_id, connection_type_id, created_at, updated_at)");
				sql.append(" VALUES (?,?,?,?)");
				
				pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, connectionType.getPhone().getId());
				pst.setInt(2, connectionType.getId());
				Timestamp time = new Timestamp(System.currentTimeMillis());
				pst.setTimestamp(3, time);
				pst.setTimestamp(4, time);

				pst.executeUpdate();				
				ResultSet rs = pst.getGeneratedKeys();
				
				int id=0;
				if(rs.next())
					id = rs.getInt(1);
					connectionType.setId(id);
				
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
		
	}
	
	@Override
	public void update(DomainEntity entity) {

	}

	@Override
	public void delete(DomainEntity entity) {	
	
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		ConnectionType connectionType = (ConnectionType)entity;
		PreparedStatement pst = null;
		String sql = null;
		
		if (connectionType.getId() != null) {
			sql = "SELECT * FROM connection_types WHERE id=?";
		} else if (connectionType.getPhone() != null) {
			sql = "SELECT * FROM connection_types_phones WHERE phone_id=?";			
		} else {		
			sql = "SELECT * FROM connection_types";
		}

		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (connectionType.getId() != null) {
				pst.setInt(1, connectionType.getId());
			} else if (connectionType.getPhone() != null) {
				pst.setInt(1, connectionType.getPhone().getId());
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ConnectionType c = new ConnectionType();
				c.setId(rs.getInt("id"));	
				if(connectionType.getPhone() == null) {	
					c.setDescription(rs.getString("description"));
				}
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
		// termino equivocado
		return null;
	}
	
}
