package les.core;

import java.sql.SQLException;
import java.util.List;

import les.domain.DomainEntity;

public interface IDAO {

	public void save(DomainEntity entity) throws SQLException;
	public void update(DomainEntity entity)throws SQLException;
	public void delete(DomainEntity entity)throws SQLException;
	public List<DomainEntity> consult(DomainEntity entity)throws SQLException;
	
	
}
