package les.core.impl.dao.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.product.Capacity;
import les.domain.product.Color;
import les.domain.product.Phone;
import les.domain.product.Reference;

public class ReferenceDAO extends AbstractJdbcDAO{
    
	public ReferenceDAO() {
		super("phone_references", "id");	
	}

	@Override
	public void save(DomainEntity entity) {	
		openConnection();
		PreparedStatement pst = null;
		Reference reference = (Reference)entity;
		
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();			
			sql.append("INSERT INTO phone_references(name, capacity_id, color_id, phone_id, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?,?)");
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, reference.getName());
			pst.setInt(2, reference.getCapacity().getId());
			pst.setInt(3, reference.getColor().getId());
			pst.setInt(4, reference.getPhone().getId());			
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(5, time);
			pst.setTimestamp(6, time);

			pst.executeUpdate();				
			ResultSet rs = pst.getGeneratedKeys();
			
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
			reference.setId(id);
			
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
		Reference reference = (Reference)entity;
		openConnection();
		PreparedStatement pst = null;		
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM phone_references WHERE id=?");	

		try {
			connection.setAutoCommit(false);
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, reference.getId());

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
				if(ctrlTransaction)
					connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		Reference reference = (Reference)entity;
		PreparedStatement pst = null;
		String sql = "SELECT * " + 
				", colors.description as color_description " + 
				", capacities.description as capacity_description " + 
				", phones.model as phone_model " + 
				", phones.front_image as front_image " + 
				", phones.back_image as back_image " + 
				", phones.details_image as details_image " + 
				", phones.sale_price as sale_price " + 
				"FROM phone_references " + 
				"INNER JOIN capacities ON capacities.id = phone_references.capacity_id " + 
				"INNER JOIN colors ON colors.id = phone_references.color_id " + 
				"INNER JOIN phones ON phones.id = phone_references.phone_id ";

		if (reference.getPhone() != null && reference.getColor() != null && reference.getCapacity() != null) {
			sql += "WHERE phone_id=? AND color_id=? AND capacity_id=?";
		} else if (reference.getPhone() != null) {
			sql += "WHERE phone_id=?";
		} else if(reference.getId() != null) {
			sql += "WHERE phone_references.id=?";
		}	
		
		sql += " ORDER BY color_id, capacity_id";

		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			if (reference.getPhone() != null && reference.getColor() != null && reference.getCapacity() != null) {
				pst.setInt(1, reference.getPhone().getId());
				pst.setInt(2, reference.getColor().getId());
				pst.setInt(3, reference.getCapacity().getId());
			} else if (reference.getPhone() != null) {
				pst.setInt(1, reference.getPhone().getId());
			} else if(reference.getId() != null) {
				pst.setInt(1, reference.getId());
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Reference r = new Reference();
				r.setId(rs.getInt("id"));
				r.setName(rs.getString("name"));
				r.setCapacity(new Capacity(rs.getInt("capacity_id"), rs.getString("capacity_description")));
				r.setColor(new Color(rs.getInt("color_id"), rs.getString("color_description")));
				r.setPhone(new Phone(rs.getString("phone_model"), rs.getDouble("sale_price"), 
						rs.getString("front_image"),rs.getString("back_image"), rs.getString("details_image")));
				all.add(r);
			}
			
			return all;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}   finally{
			
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
