package les.core.impl.dao.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.product.InactivationCategory;

public class InactivationCategoryDAO extends AbstractJdbcDAO{
    
	public InactivationCategoryDAO() {
		super("inactivation_categories", "id");	
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
		InactivationCategory ic = (InactivationCategory)entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM inactivation_categories ";

		if (ic.getId() != null) {
			sql += "WHERE id=?";
		}

		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (ic.getId() != null) {
				pst.setInt(1, ic.getId());
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				InactivationCategory i = new InactivationCategory();

				i.setId(rs.getInt("id"));		
				i.setDescription(rs.getString("description"));
				all.add(i);
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
