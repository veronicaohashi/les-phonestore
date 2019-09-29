package les.domain.client;

import les.domain.DomainEntity;

public class User extends DomainEntity {
	public String email;
	public String password;
	public String passwordConfirmation;
	public Integer level;
	
	public User() {
		
	}
	public User(Integer id, String email, String password, Integer level) {
		super(id);
		this.email = email;
		this.password = password;
		this.level = level;
	}
	public User(String email) {
		this.email = email;
	}	
	public User(String email, String password, Integer level) {
		this.email = email;
		this.password = password;
		this.level = level;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
}
