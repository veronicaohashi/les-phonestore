package les.core.impl.business.client;

import java.sql.SQLException;
import java.util.List;

import les.core.IDAO;
import les.core.IStrategy;
import les.core.impl.dao.client.CityDAO;
import les.domain.DomainEntity;
import les.domain.client.Address;
import les.domain.client.City;

public class CityValidation implements IStrategy {

	public String process(DomainEntity entity) {
		if(entity instanceof Address){
			
			Address address = (Address)entity;	
			IDAO dao = new CityDAO();
			
			if(address.getCity() == null) {
				return "O endereço deve estar associado a uma cidade";
			} else {
				try {
					List<DomainEntity> cities = dao.consult(new City(address.getCity().getName().trim()));
					if(!cities.isEmpty()) {
						address.setCity((City) cities.get(0));
					} else {
						return "Cidade não cadastrada!";
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
