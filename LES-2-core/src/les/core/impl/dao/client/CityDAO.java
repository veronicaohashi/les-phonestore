package les.core.impl.dao.client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.client.City;

public class CityDAO extends AbstractJdbcDAO{
    
	public CityDAO() {
		super("cities", "id");	
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
		City city = (City)entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM cities ";

		if (city.getId() != null) {
			sql += "WHERE id=?";
		} else if(city.getName() != null) {
			sql += "WHERE name LIKE ?";
		}
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			if (city.getId() != null) {
				pst.setInt(1, city.getId());
			} else if(city.getName() != null) {
				pst.setString(1, city.getName());
			}
			
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				City c = new City();
				
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				all.add(c);
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		}   
		// termino equivocado
		return null;
	}

}
