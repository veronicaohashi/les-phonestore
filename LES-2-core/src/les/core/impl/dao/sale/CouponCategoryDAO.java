package les.core.impl.dao.sale;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.sale.CouponCategory;

public class CouponCategoryDAO extends AbstractJdbcDAO{
    
	public CouponCategoryDAO() {
		super("coupon_categories", "id");	
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
		CouponCategory coupon = (CouponCategory) entity;
		PreparedStatement pst = null;
		String sql ="SELECT * FROM coupon_categories ";
		
		if(coupon.getId() != null) {
			sql += "WHERE id=?";			
		}
		
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			if(coupon.getId() != null) {
				pst.setInt(1, coupon.getId());				
			}
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				CouponCategory c = new CouponCategory();
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
		return null;
	}

}
