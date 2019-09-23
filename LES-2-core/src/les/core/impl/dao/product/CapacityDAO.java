package les.core.impl.dao.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.product.Capacity;

public class CapacityDAO extends AbstractJdbcDAO{
    
	public CapacityDAO() {
		super("capacities", "id");	
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
		Capacity capacity = (Capacity)entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM capacities ";

		if (capacity.getId() != null) {
			sql += "WHERE id=?";
		}

		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (capacity.getId() != null) {
				pst.setInt(1, capacity.getId());
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Capacity c = new Capacity();

				c.setId(rs.getInt("id"));		
				c.setDescription(rs.getString("description"));
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
