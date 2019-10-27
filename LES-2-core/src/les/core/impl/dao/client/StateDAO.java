package les.core.impl.dao.client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.client.State;

public class StateDAO extends AbstractJdbcDAO{
    
	public StateDAO() {
		super("states", "id");	
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
		State state = (State)entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM states ";

		if (state.getId() != null) {
			sql += "WHERE id=?";
		} else if(state.getAcronym() != null) {
			sql += "WHERE acronym=?";
		}
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			if (state.getId() != null) {
				pst.setInt(1, state.getId());
			} else if(state.getAcronym() != null) {
				pst.setString(1, state.getAcronym());
			}
			
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				State s = new State();
				
				s.setId(rs.getInt("id"));
				s.setAcronym(rs.getString("acronym"));
				all.add(s);
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		}   
		// termino equivocado
		return null;
	}

}
