package les.domain.sale;

import les.domain.DomainEntity;

public class OrderCreditCard extends DomainEntity {
	public String number;
	public Integer code;
	public String cardholderName;
	public String cardholderCpf;
	public Integer month;
	public Integer year;
	public String flag;
	
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
}
