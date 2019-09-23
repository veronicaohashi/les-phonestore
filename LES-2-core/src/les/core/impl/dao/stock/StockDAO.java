package les.core.impl.dao.stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.core.impl.dao.product.ReferenceDAO;
import les.domain.DomainEntity;
import les.domain.product.Reference;
import les.domain.stock.Stock;
import les.domain.stock.Supplier;

public class StockDAO extends AbstractJdbcDAO{
    
	public StockDAO() {
		super("stocks", "id");	
	}

	@Override
	public void save(DomainEntity entity) {		
		openConnection();
		PreparedStatement pst = null;
		Stock stock = (Stock)entity;		
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();			
			sql.append("INSERT INTO stocks(quantity, supplier_id, phone_reference_id,"
					+ "created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setDouble(1, stock.getQuantity());
			pst.setInt(2, stock.getSupplier().getId());
			pst.setInt(3, stock.getReference().getId());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(4, time);
			pst.setTimestamp(5, time);

			pst.executeUpdate();				
			ResultSet rs = pst.getGeneratedKeys();
			
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
				stock.setId(id);
							
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
		// TODO Auto-generated method stub	
		Stock stock = (Stock)entity;		
		PreparedStatement pst = null;
		openConnection();

		try {
			connection.setAutoCommit(false);					
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE stocks SET quantity=?, reserved_quantity=?, supplier_id=?, phone_reference_id=?, updated_at=?");
			sql.append("WHERE id=?");							

			pst = connection.prepareStatement(sql.toString());
			pst.setDouble(1, stock.getQuantity());
			pst.setDouble(2, stock.getReserved());
			pst.setInt(3, stock.getSupplier().getId());
			pst.setInt(4, stock.getReference().getId());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(5, time);
			pst.setInt(6, stock.getId());
			
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
		Stock stock = (Stock) entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM stocks ";

		if(stock.getReference() != null){
			sql += "WHERE phone_reference_id=?";
		} else if (stock.getId() != null) {
			sql += "WHERE id=?";
		}
		
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if(stock.getReference() != null){
				pst.setInt(1, stock.getReference().getId());				
			} else if (stock.getId() != null) {
				pst.setInt(1, stock.getId());				
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Stock s = new Stock();

				s.setId(rs.getInt("id"));	
				s.setQuantity(rs.getInt("quantity"));	
				s.setReserved(rs.getInt("reserved_quantity"));
				s.setAvaiable(s.getQuantity() - s.getReserved());
				
				ReferenceDAO referenceDAO = new ReferenceDAO();
				Reference reference = new Reference();
				reference.setId(rs.getInt("phone_reference_id"));

				List<DomainEntity> references = referenceDAO.consult(reference);
				if( ! references.isEmpty()){
					s.setReference((Reference) references.get(0));
				}
				
				SupplierDAO supplierDAO = new SupplierDAO();
				Supplier supplier = new Supplier();
				supplier.setId(rs.getInt("supplier_id"));

				List<DomainEntity> suppliers = supplierDAO.consult(supplier);
				if( ! suppliers.isEmpty()){
					s.setSupplier((Supplier) suppliers.get(0));
				}
				
				all.add(s);
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
