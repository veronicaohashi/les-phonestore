package les.domain.sale;

import java.util.ArrayList;
import java.util.List;

import les.domain.DomainEntity;
import les.domain.client.Client;

public class Order extends DomainEntity {
	private double price;
	private String date;
	private Integer quantity;
	private Payment payment;
	private OrderAddress orderAddress;
	private Client client;
	private Status status;
	private List<Orderi> items;
	private List<Integer> orderIds;
	
	public Order() {
		this.items = new ArrayList<Orderi>();
		this.orderIds = new ArrayList<Integer>();
	}
	public Order(OrderAddress orderAddress, Payment payment, Client client, List<Orderi> items, double price,
			Integer quantity, String date) {
		this.orderAddress = orderAddress;
		this.payment = payment;
		this.client = client;
		this.items = items;
		this.price = price;
		this.quantity = quantity;
		this.date = date;
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
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public OrderAddress getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}
	public void addItem(Orderi item){
		this.items.add(item);
	}
	public List<Integer> getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(List<Integer> orderIds) {
		this.orderIds = orderIds;
	}
	public void addIds(Integer id){
		this.orderIds.add(id);
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
