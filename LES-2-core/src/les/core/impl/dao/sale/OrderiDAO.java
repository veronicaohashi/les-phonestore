package les.core.impl.dao.sale;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.sale.Orderi;

public class OrderiDAO extends AbstractJdbcDAO{
    
	public OrderiDAO() {
		super("order_items", "id");	
	}

	@Override
	public void save(DomainEntity entity) {	
		
	}
	
	@Override
	public void update(DomainEntity entity) {
		// TODO Auto-generated method stub
		Orderi orderi = (Orderi)entity;
		PreparedStatement pst = null;
		openConnection();

		try {
			connection.setAutoCommit(false);					
			StringBuilder sql = new StringBuilder();			
			sql.append("UPDATE order_items SET status_id=?, updated_at=?");
			sql.append(" WHERE id = ?");	
				        			
			pst = connection.prepareStatement(sql.toString());			
			pst.setInt(1, orderi.getStatus().getId());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(2, time);
			pst.setInt(3, orderi.getId());
	        
			pst.executeUpdate();			
			connection.commit();
						
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(DomainEntity entity) {	
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		return null;
	}

}
