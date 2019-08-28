package les.core.impl.dao.stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.stock.MovstockType;

public class MovstockTypeDAO extends AbstractJdbcDAO{
    
	public MovstockTypeDAO() {
		super("movstock_types", "id");	
	}

	@Override
	public void save(DomainEntity entity) {		
	}
	
	@Override
	public void update(DomainEntity entity) {
	}

	@Override
	public void delete(DomainEntity entity) {	
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		MovstockType movstockType = (MovstockType)entity;
		PreparedStatement pst = null;
		String sql = null;

		if (movstockType.getId() != null) {
			sql = "SELECT * FROM movstock_types WHERE id=?";
		} else {
			sql = "SELECT * FROM movstock_types";
		}

		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (movstockType.getId() != null) {
				pst.setInt(1, movstockType.getId());
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				MovstockType m = new MovstockType();

				m.setId(rs.getInt("id"));		
				m.setDescription(rs.getString("description"));
				all.add(m);
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
