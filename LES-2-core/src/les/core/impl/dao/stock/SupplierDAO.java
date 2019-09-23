package les.core.impl.dao.stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.stock.Supplier;

public class SupplierDAO extends AbstractJdbcDAO{
    
	public SupplierDAO() {
		super("suppliers", "id");	
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
		Supplier supplier = (Supplier)entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM suppliers ";

		if (supplier.getId() != null) {
			sql += "WHERE id=?";
		}

		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (supplier.getId() != null) {
				pst.setInt(1, supplier.getId());
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Supplier s = new Supplier();

				s.setId(rs.getInt("id"));		
				s.setName(rs.getString("name"));
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
		// termino equivocado
		return null;
	}
}
