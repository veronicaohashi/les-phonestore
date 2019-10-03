package les.core.impl.dao.sale;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.sale.ExchangeCategory;

public class ExchangeCategoriesDAO extends AbstractJdbcDAO{
    
	public ExchangeCategoriesDAO() {
		super("exchange_categories", "id");	
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
		ExchangeCategory categories = (ExchangeCategory) entity;
		PreparedStatement pst = null;
		String sql ="SELECT * FROM exchange_categories ";
		
		if(categories.getId() != null) {
			sql += "WHERE id=?";			
		}
		
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			if(categories.getId() != null) {
				pst.setInt(1, categories.getId());				
			}
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ExchangeCategory e = new ExchangeCategory();
				e.setId(rs.getInt("id"));
				e.setDescription(rs.getString("description"));
				
				all.add(e);
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
