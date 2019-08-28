package les.domain.stock;

import les.domain.DomainEntity;

public class Supplier extends DomainEntity{
	private String name;
	private String cnpj;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
