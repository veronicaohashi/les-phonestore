package les.core.impl.dao.sale;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.client.Client;
import les.domain.sale.Coupon;
import les.domain.sale.CouponCategory;
import les.domain.sale.Order;

public class CouponDAO extends AbstractJdbcDAO{
    
	public CouponDAO() {
		super("coupons", "id");	
	}

	@Override
	public void save(DomainEntity entity) {	
		openConnection();
		PreparedStatement pst = null;
		Coupon coupon = (Coupon) entity;
		
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();			
			sql.append("INSERT INTO coupons(name, value, validity, lactive, coupon_category_id,"
					+ "order_id, client_id, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, coupon.getName());
			pst.setDouble(2, coupon.getValue());
			pst.setString(3, coupon.getValidity());	
			pst.setBoolean(4, coupon.getLactive());
			pst.setInt(5, coupon.getCouponCategory().getId());
			pst.setInt(6, coupon.getOrder().getId());
			pst.setInt(7, coupon.getClient().getId());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(8, time);
			pst.setTimestamp(9, time);
	
			pst.executeUpdate();				
			ResultSet rs = pst.getGeneratedKeys();
			
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
			coupon.setId(id);
			
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
		Coupon coupon = (Coupon)entity;
		PreparedStatement pst = null;
		openConnection();

		try {
			connection.setAutoCommit(false);					
			StringBuilder sql = new StringBuilder();							        	
			sql.append("UPDATE coupons SET lactive=?, updated_at=?");
			sql.append(" WHERE id = ?");	
			pst = connection.prepareStatement(sql.toString());			

			pst.setBoolean(1, coupon.getLactive());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(2, time);
			pst.setInt(3, coupon.getId());			
			
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
		Coupon coupon = (Coupon) entity;
		PreparedStatement pst = null;
		String sql ="SELECT * "
				+ ",clients.cpf AS client_cpf "
				+ ",clients.firstname AS client_firstname "
				+ "FROM coupons "
				+ "LEFT JOIN clients ON clients.id = coupons.client_id ";

		if(coupon.getId() != null) {
			sql += "WHERE coupons.id=?";			
		} else if (coupon.getId() == null && coupon.getClient() != null && coupon.getLactive() == null) {
			sql += "WHERE coupons.client_id=?";
		} else if (coupon.getId() == null && coupon.getClient() != null && coupon.getLactive() != null) {
			sql += "WHERE coupons.client_id=? AND coupons.lactive=?";
		} else if (coupon.getId() == null && coupon.getLactive() == null && coupon.getName() != null && coupon.getCouponCategory() != null) {
			sql += "WHERE coupons.name=? AND coupon_category_id=? AND coupons.lactive=true";
		} else {
			sql += "WHERE coupons.lactive=true";
		}	
	
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			if(coupon.getId() != null) {
				pst.setInt(1, coupon.getId());				
			} else if (coupon.getId() == null && coupon.getClient() != null && coupon.getLactive() == null) {
				pst.setInt(1, coupon.getClient().getId());
			} else if (coupon.getId() == null && coupon.getClient() != null && coupon.getLactive() != null) {
				pst.setInt(1, coupon.getClient().getId());
				pst.setBoolean(2, coupon.getLactive());		
			} else if (coupon.getId() == null && coupon.getLactive() == null && coupon.getName() != null && coupon.getCouponCategory() != null) {
				pst.setString(1, coupon.getName());
				pst.setInt(2, coupon.getCouponCategory().getId());
			}
			
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Coupon c = new Coupon();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setValue(rs.getDouble("value"));
				c.setValidity(rs.getString("validity"));
				c.setLactive(rs.getBoolean("lactive"));
				c.setCouponCategory(new CouponCategory(rs.getInt("coupon_category_id")));
				c.setClient(new Client(rs.getInt("client_id"), rs.getString("client_cpf"), rs.getString("client_firstname")));
				c.setOrder(new Order(rs.getInt("order_id")));
				
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
