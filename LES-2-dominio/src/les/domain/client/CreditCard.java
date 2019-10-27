package les.domain.client;

import les.domain.DomainEntity;

public class CreditCard extends DomainEntity {
	public String number;
	public Integer code;
	public String cardholderName;
	public String cardholderCpf;
	public Integer month;
	public Integer year;
	public String flag;
	public Boolean lmain;
	private Client client;
	
	public CreditCard() {
		
	}
	public CreditCard(Client client, Boolean lmain) {
		this.client = client;
		this.lmain = lmain;
	}
	public CreditCard(Integer id) {
		super(id);
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getCardholderName() {
		return cardholderName;
	}
	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}
	public String getCardholderCpf() {
		return cardholderCpf;
	}
	public void setCardholderCpf(String cardholderCpf) {
		this.cardholderCpf = cardholderCpf;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Boolean getLmain() {
		return lmain;
	}
	public void setLmain(Boolean lmain) {
		this.lmain = lmain;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}
