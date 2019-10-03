package les.core.impl.dao.stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.product.Reference;
import les.domain.stock.Movstock;
import les.domain.stock.MovstockType;
import les.domain.stock.Supplier;

public class MovstockDAO extends AbstractJdbcDAO{
    
	public MovstockDAO() {
		super("movstocks", "id");	
	}

	@Override
	public void save(DomainEntity entity) {		
		openConnection();
		PreparedStatement pst = null;
		Movstock movstock = (Movstock)entity;		
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();			
			sql.append("INSERT INTO movstocks(quantity, price, date, movstock_type_id, supplier_id, phone_reference_id,"
					+ "origin, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setDouble(1, movstock.getQuantity());
			pst.setDouble(2, movstock.getPrice());
			pst.setString(3, movstock.getDate());
			pst.setInt(4, movstock.getMovstockType().getId());
			pst.setInt(5, movstock.getSupplier().getId());
			pst.setInt(6, movstock.getReference().getId());
			pst.setInt(7, movstock.getOrigin());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(8, time);
			pst.setTimestamp(9, time);
			pst.executeUpdate();				
			ResultSet rs = pst.getGeneratedKeys();
			
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
				movstock.setId(id);
							
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
		Movstock movstock = (Movstock) entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM movstocks ";

		if(movstock.getReference() != null && movstock.getMovstockType() != null){
			sql += "WHERE phone_reference_id=? AND movstock_type_id=? ORDER BY date";
		}
		
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			if(movstock.getReference() != null && movstock.getMovstockType() != null){
				pst.setInt(1, movstock.getReference().getId());				
				pst.setInt(2, movstock.getMovstockType().getId());				
			}		

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Movstock m = new Movstock();

				m.setId(rs.getInt("id"));	
				m.setQuantity(rs.getInt("quantity"));	
				m.setPrice(rs.getDouble("price"));	
				m.setDate(rs.getString("date"));
				m.setSupplier(new Supplier(rs.getInt("supplier_id")));
				m.setMovstockType(new MovstockType(rs.getInt("movstock_type_id")));
				m.setReference(new Reference(rs.getInt("phone_reference_id")));
				
				all.add(m);
			}
			return all;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
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
