package les.core.impl.dao.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.product.Color;

public class ColorDAO extends AbstractJdbcDAO{
    
	public ColorDAO() {
		super("colors", "id");	
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
		Color color = (Color) entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM colors ";

		if (color.getId() != null) {
			sql += "WHERE id=?";
		}

		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (color.getId() != null) {
				pst.setInt(1, color.getId());
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Color c = new Color();

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
