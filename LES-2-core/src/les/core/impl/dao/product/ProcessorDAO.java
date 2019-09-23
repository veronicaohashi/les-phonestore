package les.core.impl.dao.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.product.Processor;

public class ProcessorDAO extends AbstractJdbcDAO{
    
	public ProcessorDAO() {
		super("processors", "id");	
	}

	@Override
	public void save(DomainEntity entity) {		
	}
	
	@Override
	public void update(DomainEntity entity) {

	}

	@Override
	public void delete(DomainEntity entity) {	
	
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		Processor processor = (Processor)  entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM processors ";

		if (processor.getId() != null) {
			sql += "WHERE id=?";
		}

		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (processor.getId() != null) {
				pst.setInt(1, processor.getId());
			}

			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Processor p = new Processor();

				p.setId(rs.getInt("id"));		
				p.setDescription(rs.getString("description"));
				all.add(p);
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		// termino equivocado
		return null;
	}
}
