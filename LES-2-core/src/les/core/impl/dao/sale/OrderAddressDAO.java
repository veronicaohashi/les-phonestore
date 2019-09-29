package les.core.impl.dao.sale;

import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.core.impl.dao.client.AddressDAO;
import les.domain.DomainEntity;
import les.domain.client.Address;
import les.domain.sale.OrderAddress;

public class OrderAddressDAO extends AbstractJdbcDAO{
    
	public OrderAddressDAO() {
		super("order_addresses", "id");	
	}

	@Override
	public void save(DomainEntity entity) {	
		// Não executa nada pois vai salvar depois de finalizar o pedido	
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
		OrderAddress address = (OrderAddress)entity;
		AddressDAO addressDAO = new AddressDAO();
		List<DomainEntity> entities = new ArrayList<DomainEntity>();
		
		Address a = (Address) addressDAO.consult(address.getAddress()).get(0);
		address.setAddress(a);
		
		entities.add(address);
		return entities;
		
	}

}
