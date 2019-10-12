package les.core.impl.dao.sale;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.client.Client;
import les.domain.product.Reference;
import les.domain.sale.ExchangeCategory;
import les.domain.sale.Order;
import les.domain.sale.Orderi;
import les.domain.sale.Status;

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
			if(orderi.getStatus().getId() == 5) {
				sql.append("UPDATE order_items SET status_id=?, exchange_categories_id=?, updated_at=?");
				sql.append(" WHERE id = ?");	
				pst = connection.prepareStatement(sql.toString());			

				pst.setInt(1, orderi.getStatus().getId());
				pst.setInt(2, orderi.getExchangeCategory().getId());
				Timestamp time = new Timestamp(System.currentTimeMillis());
				pst.setTimestamp(3, time);
				pst.setInt(4, orderi.getId());
			
			} else {
				sql.append("UPDATE order_items SET status_id=?, updated_at=?");
				sql.append(" WHERE id IN (");	
				
		        int size = orderi.getOrderiIds().size();
		        
				for (int i = 0; i < size; i++) {
		            if (i + 1 == size) {
		            	sql.append("?)");
		            } else {
		            	sql.append("?,");
		            }
		        }		
				pst = connection.prepareStatement(sql.toString());
				pst.setInt(1, orderi.getStatus().getId());
				Timestamp time = new Timestamp(System.currentTimeMillis());
				pst.setTimestamp(2, time);
				int i = 3;
		        
				for (Integer value : orderi.getOrderiIds()) {
					pst.setInt(i++, value);
		        }				
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
		Orderi orderi = (Orderi) entity;
		PreparedStatement pst = null;
		String sql = "SELECT order_items.*"
				+ ", phone_references.name as reference_name"
				+ ", status.description AS status_description"
				+ ", exchange_categories.description as exchange_categories_description"
				+ ", orders.clients_id AS order_client_id"
				+ " FROM order_items "
				+ " JOIN phone_references ON phone_references.id = order_items.phone_reference_id"
				+ " JOIN status ON status.id = order_items.status_id"
				+ " LEFT JOIN exchange_categories ON exchange_categories.id = order_items.exchange_categories_id"
				+ " JOIN orders ON orders.id = order_items.order_id";
		
		if(orderi.getId() != null) {
			sql += " WHERE order_items.id=?";			
		} else if(orderi.getId() == null && orderi.getStatus() != null && orderi.getOrderiIds().isEmpty()) {
			sql += " WHERE order_items.status_id=?";			
		} else if(! orderi.getOrderiIds().isEmpty()) {
			sql += " WHERE order_items.id IN (";	
			
	        int size = orderi.getOrderiIds().size();
	        
			for (int i = 0; i < size; i++) {
	            if (i + 1 == size) {
	            	sql += "?)";
	            } else {
	            	sql += "?,";
	            }
	        }		
		}
		
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			if(orderi.getId() != null) {
				pst.setInt(1, orderi.getId());				
			} else if(orderi.getId() == null && orderi.getStatus() != null && orderi.getOrderiIds().isEmpty()) {
				pst.setInt(1, orderi.getStatus().getId());	
			} else if(! orderi.getOrderiIds().isEmpty()) {
				int i = 1;
	        
				for (Integer value : orderi.getOrderiIds()) {
					pst.setInt(i++, value);
		        }	
				
			}
						
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Orderi o = new Orderi();
				o.setId(rs.getInt("id"));
				o.setPrice(rs.getDouble("price"));
				o.setQuantity(rs.getInt("quantity"));
				o.setReference(new Reference(rs.getInt("phone_reference_id"), rs.getString("reference_name")));
				o.setOrder(new Order(rs.getInt("order_id"), new Client(rs.getInt("order_client_id"))));
				o.setStatus(new Status(rs.getInt("status_id"), rs.getString("status_description")));
				o.setExchangeCategory(new ExchangeCategory(rs.getInt("exchange_categories_id"), rs.getString("exchange_categories_description")));
				
				all.add(o);
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
