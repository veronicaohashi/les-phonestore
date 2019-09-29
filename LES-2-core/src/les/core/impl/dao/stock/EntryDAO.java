package les.core.impl.dao.stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.stock.Entry;
import les.domain.stock.Entryi;

public class EntryDAO extends AbstractJdbcDAO{
    
	public EntryDAO() {
		super("entries", "id");	
	}

	@Override
	public void save(DomainEntity entity) {	
		openConnection();
		PreparedStatement pst = null;
		Entry entry = (Entry)entity;
	
	
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();			
			sql.append("INSERT INTO entries(quantity, price, data, supplier_id, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, entry.getQuantity());
			pst.setDouble(2, entry.getPrice());
			pst.setString(3, entry.getDate());
			pst.setInt(4, entry.getSupplier().getId());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(5, time);
			pst.setTimestamp(6, time);
	
			pst.executeUpdate();				
			ResultSet rs = pst.getGeneratedKeys();
			
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
				entry.setId(id);
			
				StringBuilder new_sql = new StringBuilder();
				new_sql.append("INSERT INTO entry_items(quantity, price, phone_reference_id, entry_id, created_at, updated_at)");
				new_sql.append(" VALUES (?,?,?,?,?,?)");
			
				List<Entryi> items = entry.getItems();
			
				PreparedStatement ps = null;
				for(Entryi e : items) {
					e.setEntry(entry);
					
					ps = connection.prepareStatement(new_sql.toString(), Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, e.getQuantity());
					ps.setDouble(2, e.getPrice());
					ps.setInt(3, e.getReference().getId());	
					ps.setInt(4, e.getEntry().getId());	
					Timestamp t = new Timestamp(System.currentTimeMillis());
					ps.setTimestamp(5, t);
					ps.setTimestamp(6, t);
	
					ps.executeUpdate();				
					ps.close();
				}	
			
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
	public void update(DomainEntity entity) {
	}

	@Override
	public void delete(DomainEntity entity) {	
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		return null;
	}
}
