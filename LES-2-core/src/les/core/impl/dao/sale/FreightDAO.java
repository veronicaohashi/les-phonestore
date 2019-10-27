package les.core.impl.dao.sale;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.sale.Freight;

public class FreightDAO extends AbstractJdbcDAO{
    
	public FreightDAO() {
		super("freight", "id");	
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
		Freight freight = (Freight) entity;
		PreparedStatement pst = null;
		String sql ="SELECT * FROM freight ";
		
		if(freight.getState() != null) {
			sql += "WHERE state_id=?";			
		} else if(freight.getId() != null) {
			sql += "WHERE id=?";		
			
		}
		
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			if(freight.getState() != null) {
				pst.setInt(1, freight.getState().getId());	
			} else if(freight.getId() != null) {
				pst.setInt(1, freight.getId());	
			}
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Freight f = new Freight();
				f.setId(rs.getInt("id"));
				f.setDescription(rs.getString("description"));
				f.setPrice(rs.getDouble("price"));
				
				all.add(f);
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
