package les.core.impl.dao.client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.client.ResidenceType;

public class ResidenceTypeDAO extends AbstractJdbcDAO{
    
	public ResidenceTypeDAO() {
		super("residence_types", "id");	
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
		ResidenceType residenteType = (ResidenceType) entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM residence_types ";

		if (residenteType.getId() != null) {
			sql += "WHERE id=?";
		}

		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (residenteType.getId() != null) {
				pst.setInt(1, residenteType.getId());
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ResidenceType r = new ResidenceType();
				
				r.setId(rs.getInt("id"));		
				r.setDescription(rs.getString("description"));
				all.add(r);
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
