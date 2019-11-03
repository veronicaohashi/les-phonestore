package les.core.impl.dao.client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.client.Address;
import les.domain.client.City;
import les.domain.client.ResidenceType;
import les.domain.client.State;

public class AddressDAO extends AbstractJdbcDAO{
    
	public AddressDAO() {
		super("addresses", "id");	
	}

	@Override
	public void save(DomainEntity entity) {	
		openConnection();
		PreparedStatement pst = null;
		Address address = (Address)entity;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();			
			sql.append("INSERT INTO public.addresses(name, street, number, complement, district, postal_code, observation,"
					+ " lmain, residence_type_id, city_id ,client_id, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, address.getName());
			pst.setString(2, address.getStreet());
			pst.setString(3, address.getNumber());
			pst.setString(4, address.getComplement());
			pst.setString(5, address.getDistrict());
			pst.setString(6, address.getPostalCode());
			pst.setString(7, address.getObservation());
			pst.setBoolean(8, address.getLmain());
			pst.setInt(9, address.getResidenceType().getId());
			pst.setInt(10, address.getCity().getId());
			pst.setInt(11, address.getClient().getId());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(12, time);
			pst.setTimestamp(13, time);
			pst.executeUpdate();				
			ResultSet rs = pst.getGeneratedKeys();
			
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
				address.setId(id);
			
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
	}

	@Override
	public void delete(DomainEntity entity) {			
		Address address = (Address)entity;
		openConnection();
		PreparedStatement pst = null;		
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM addresses WHERE id=?");	
	
		try {
			connection.setAutoCommit(false);
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, address.getId());
	
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
		Address address = (Address) entity;
		PreparedStatement pst = null;
		String sql ="SELECT addresses.*, cities.name AS city, states.name AS state, states.id AS state_id, residence_types.description AS residence_type "
				+ "FROM addresses "
				+ "JOIN cities ON cities.id = addresses.city_id "
				+ "JOIN states ON states.id = cities.state_id "
				+ "JOIN residence_types ON residence_types.id = addresses.residence_type_id ";
		
		if(address.getId() != null) {
			sql += "WHERE addresses.id=?";			
		} else if (address.getClient().getId() != null && address.getLmain() == null) {
			sql += "WHERE client_id=?";
		} else if (address.getClient().getId() != null && address.getLmain() != null) {
			sql += "WHERE client_id=? AND lmain = true";
		}  else {
			sql += "ORDER BY addresses.id DESC LIMIT 1";
		}
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			if(address.getId() != null) {
				pst.setInt(1, address.getId());				
			} else if (address.getClient().getId() != null) {
				pst.setInt(1, address.getClient().getId());
			}
			
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Address a = new Address();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setStreet(rs.getString("street"));
				a.setNumber(rs.getString("number"));
				a.setComplement(rs.getString("complement"));
				a.setDistrict(rs.getString("district"));
				a.setPostalCode(rs.getString("postal_code"));
				a.setObservation(rs.getString("observation"));
				a.setLmain(rs.getBoolean("lmain"));
				a.setResidenceType(new ResidenceType(rs.getInt("residence_type_id"), rs.getString("residence_type")));
				a.setCity(new City(rs.getInt("city_id"), rs.getString("city"), new State(rs.getInt("state_id"), rs.getString("state"))));
				//a.setClient(new Client(rs.getInt("client_id")));
				all.add(a);
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
