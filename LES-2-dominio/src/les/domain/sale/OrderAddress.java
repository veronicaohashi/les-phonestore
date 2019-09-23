package les.domain.sale;

import les.domain.DomainEntity;
import les.domain.client.City;
import les.domain.client.ResidenceType;

public class OrderAddress extends DomainEntity {	
	private String name;
	private String street;
	private String number;
	private String complement;
	private String district;
	private String postalCode;
	private String observation;
	private ResidenceType residenceType;
	private City city;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public ResidenceType getResidenceType() {
		return residenceType;
	}
	public void setResidenceType(ResidenceType residenceType) {
		this.residenceType = residenceType;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
}