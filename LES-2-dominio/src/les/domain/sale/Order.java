package les.domain.sale;

import java.util.List;

import les.domain.DomainEntity;
import les.domain.client.Address;
import les.domain.client.Client;

public class Order extends DomainEntity {
	private double price;
	private Integer quantity;
	private Payment payment;
	private Address address;
	private Client client;
	private Status status;
	private List<Orderi> items;
	
	public Order() {
	}
	public Order(Address address, Payment payment, Client client, List<Orderi> items, double price,
			Integer quantity) {
		this.address = address;
		this.payment = payment;
		this.client = client;
		this.items = items;
		this.price = price;
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public List<Orderi> getItems() {
		return items;
	}
	public void setItems(List<Orderi> items) {
		this.items = items;
	}
	public Payment getPayments() {
		return payment;
	}
	public void setPayments(Payment payment) {
		this.payment = payment;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
