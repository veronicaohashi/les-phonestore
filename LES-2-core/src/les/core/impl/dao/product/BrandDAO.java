package les.core.impl.dao.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.product.Brand;

public class BrandDAO extends AbstractJdbcDAO{
    
	public BrandDAO() {
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
		Brand brand = (Brand)  entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM brands ";

		if (brand.getId() != null) {
			sql += "WHERE id=?";
		}

		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (brand.getId() != null) {
				pst.setInt(1, brand.getId());
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Brand b = new Brand();

				b.setId(rs.getInt("id"));		
				b.setDescription(rs.getString("description"));
				all.add(b);
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		// termino equivocado
		return null;
	}
}
