package les.core.impl.dao.sale;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.stock.Stock;

public class ReservedStockDAO extends AbstractJdbcDAO{
    
	public ReservedStockDAO() {
		super("stocks", "id");	
	}

	@Override
	public void save(DomainEntity entity) {				
	}
	
	@Override
	public void update(DomainEntity entity) {
		// TODO Auto-generated method stub	
		Stock stock = (Stock)entity;		
		PreparedStatement pst = null;
		openConnection();

		try {
			connection.setAutoCommit(false);					
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE stocks SET reserved_quantity=?, updated_at=?");
			sql.append("WHERE phone_reference_id=?");
			
			if(stock.getId() != null) {
				sql.append(" AND id=?");
			}	
			
			pst = connection.prepareStatement(sql.toString());
			pst.setDouble(1, stock.getReserved());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(2, time);
			pst.setInt(3, stock.getReference().getId());
			
			if(stock.getId() != null) {
				pst.setInt(4, stock.getId());
			}
			
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
