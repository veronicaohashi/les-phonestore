package les.domain.client;

import java.util.List;

import les.domain.DomainEntity;

public class Client extends DomainEntity {
	private String firstname;
	private String lastname;
	private String cpf;
	private String birthday;
	private String phone;
	private String gender;
	private Boolean lactive;
	private User user;
	private List<Address> addresses;
	private List<CreditCard> creditCards;
	
	public Client() {
		
	}
	public Client(String cpf) {
		this.cpf = cpf;
	}
	public Client(String cpf, String firstname) {
		this.cpf = cpf;
		this.firstname = firstname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getLactive() {
		return lactive;
	}
	public void setLactive(Boolean lactive) {
		this.lactive = lactive;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<CreditCard> getCreditCards() {
		return creditCards;
	}
	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}
}