package les.core.impl.dao.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.product.PricingGroup;

public class PricingGroupDAO extends AbstractJdbcDAO{
    
	public PricingGroupDAO() {
		super("brands", "id");	
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
		PricingGroup pricing = (PricingGroup)  entity;
		PreparedStatement pst = null;
		String sql = null;

		if (pricing.getId() != null) {
			sql = "SELECT * FROM pricing_groups WHERE id=?";
		} else {
			sql = "SELECT * FROM pricing_groups";
		}

		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (pricing.getId() != null) {
				pst.setInt(1, pricing.getId());
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				PricingGroup pg = new PricingGroup();

				pg.setId(rs.getInt("id"));		
				pg.setDescription(rs.getString("description"));
				pg.setPercentage(rs.getDouble("percentage"));
				all.add(pg);
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		// termino equivocado
		return null;
	}
}
