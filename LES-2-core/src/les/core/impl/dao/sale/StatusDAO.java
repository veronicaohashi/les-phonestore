package les.core.impl.dao.sale;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.sale.Status;

public class StatusDAO extends AbstractJdbcDAO{
    
	public StatusDAO() {
		super("status", "id");	
	}

	@Override
	public void save(DomainEntity entity) {	
	}
	
	@Override
	public void update(DomainEntity entity) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void delete(DomainEntity entity) {	
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		Status status = (Status) entity;
		PreparedStatement pst = null;
		String sql ="SELECT * FROM status ";
		
		if(status.getId() != null) {
			sql += "WHERE id=?";			
		}
		
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			if(status.getId() != null) {
				pst.setInt(1, status.getId());				
			}
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Status s = new Status();
				s.setId(rs.getInt("id"));
				s.setDescription(rs.getString("description"));
				
				all.add(s);
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
