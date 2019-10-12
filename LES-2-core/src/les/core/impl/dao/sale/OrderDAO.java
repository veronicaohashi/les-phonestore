package les.core.impl.dao.sale;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.core.impl.dao.client.AddressDAO;
import les.core.impl.dao.client.CreditCardDAO;
import les.domain.DomainEntity;
import les.domain.client.Address;
import les.domain.client.City;
import les.domain.client.Client;
import les.domain.client.CreditCard;
import les.domain.client.ResidenceType;
import les.domain.client.State;
import les.domain.product.Capacity;
import les.domain.product.Color;
import les.domain.product.Phone;
import les.domain.product.Reference;
import les.domain.sale.Coupon;
import les.domain.sale.Order;
import les.domain.sale.OrderAddress;
import les.domain.sale.Orderi;
import les.domain.sale.Payment;
import les.domain.sale.PaymentData;
import les.domain.sale.Status;

public class OrderDAO extends AbstractJdbcDAO{
    
	public OrderDAO() {
		super("orders", "id");	
	}

	@Override
	public void save(DomainEntity entity) {	
		Order order = (Order)entity;
		CreditCard card = new CreditCard();
		Address address = order.getOrderAddress().getAddress();
		
		CreditCardDAO cardDAO = new CreditCardDAO();
		AddressDAO addressDAO = new AddressDAO();
		PreparedStatement pst = null;
		openConnection();
		
		if(address.getClient() != null) {
			// SALVA NA TABELA DO ENDEREÇO DO CLIENTE		
			addressDAO.save(address);
			address = (Address) addressDAO.consult(address).get(0);	
		}	
		
		if(order.getPayment() != null) {
			for(PaymentData c : order.getPayment().getPaymentDatas()) {
				card = c.getCard();
				
				if(card.getClient() != null) {
					// SALVA NA TABELA DO ENDEREÇO DO CLIENTE	
					cardDAO.save(card);
					card = (CreditCard) cardDAO.consult(card).get(0);	
				} 
				
				try {				
					connection.setAutoCommit(false);
					StringBuilder sql = new StringBuilder();
					int idCreditCard = 0;
					sql.append("INSERT INTO order_credit_cards(number, code, month, year, cardholder_name, cardholder_cpf, flag,"
							+ " created_at, updated_at)");
					sql.append(" VALUES (?,?,?,?,?,?,?,?,?)");
					
					pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
					pst.setString(1, card.getNumber());
					pst.setInt(2, card.getCode());
					pst.setInt(3, card.getMonth());
					pst.setInt(4, card.getYear());
					pst.setString(5, card.getCardholderName());
					pst.setString(6, card.getCardholderCpf());
					pst.setString(7, card.getFlag());
					Timestamp time0 = new Timestamp(System.currentTimeMillis());
					pst.setTimestamp(8, time0);
					pst.setTimestamp(9, time0);					
					pst.executeUpdate();				
					ResultSet rs = pst.getGeneratedKeys();
										
					if(rs.next())
						idCreditCard = rs.getInt(1);
						card.setId(idCreditCard);
					sql.setLength(0);
					pst.close();					
				} catch (SQLException e) {
					e.printStackTrace();	
				}
			}
		}
		
		
		try {				
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			int idAddress = 0;
			sql.append("INSERT INTO order_addresses(name, street, number, complement, district, postal_code, observation,"
					+ " residence_type_id, city_id, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?)");
						
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, address.getName());
			pst.setString(2, address.getStreet());
			pst.setString(3, address.getNumber());
			pst.setString(4, address.getComplement());
			pst.setString(5, address.getDistrict());
			pst.setString(6, address.getPostalCode());
			pst.setString(7, address.getObservation());
			pst.setInt(8, address.getResidenceType().getId());
			pst.setInt(9, address.getCity().getId());
			Timestamp time1 = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(10, time1);
			pst.setTimestamp(11, time1);
			
			pst.executeUpdate();				
			ResultSet rs = pst.getGeneratedKeys();
			
			if(rs.next())
				idAddress = rs.getInt(1);
			order.getOrderAddress().setId(idAddress);
			sql.setLength(0);
			pst.close();
			
			sql.append("INSERT INTO orders(price, quantity, clients_id, status_id, order_address_id, order_date, total_orderi_price, "
					+ "total_discount_price, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			pst.setDouble(1, order.getPrice());
			pst.setInt(2, order.getQuantity());
			pst.setInt(3, order.getClient().getId());
			pst.setInt(4, order.getStatus().getId());
			pst.setInt(5, order.getOrderAddress().getId());
			pst.setString(6, order.getOrderDate());
			pst.setDouble(7, order.getTotalItemsPrice());
			pst.setDouble(8, order.getTotalDiscountPrice());
			Timestamp time3 = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(9, time3);
			pst.setTimestamp(10, time3);
			pst.executeUpdate();				
			rs = pst.getGeneratedKeys();
			
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
			order.setId(id);
			sql.setLength(0);
			pst.close();

			if(order.getPayment() != null) {
				sql.append("INSERT INTO payments(price, quantity, credit_card_id, order_id, created_at, updated_at)");
				sql.append(" VALUES (?,?,?,?,?,?)");
	
				for (int i = 0; i < order.getPayment().getPaymentDatas().size(); i++) {		
					pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
							
					pst.setDouble(1, order.getPayment().getPaymentDatas().get(i).getPrice());
					pst.setInt(2, order.getPayment().getPaymentDatas().get(i).getQuantity());
					pst.setInt(3, order.getPayment().getPaymentDatas().get(i).getCard().getId());
					pst.setInt(4, order.getId());
					Timestamp time2 = new Timestamp(System.currentTimeMillis());
					pst.setTimestamp(5, time2);
					pst.setTimestamp(6, time2);
					
					pst.executeUpdate();				
					rs = pst.getGeneratedKeys();
				};
							
				sql.setLength(0);
				pst.close();
			}
						
			sql.append("INSERT INTO order_items(price, quantity, phone_reference_id, order_id, status_id, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?,?,?)");
			
			List<Orderi> items =  order.getItems();
			
			for(Orderi o : items) {
				pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				pst.setDouble(1, o.getPrice());
				pst.setInt(2, o.getQuantity());
				pst.setInt(3, o.getReference().getId());
				pst.setInt(4, order.getId());
				pst.setInt(5, o.getStatus().getId());
				Timestamp time4 = new Timestamp(System.currentTimeMillis());
				pst.setTimestamp(6, time4);
				pst.setTimestamp(7, time4);
				
				pst.executeUpdate();			
				pst.close();				
			}
			sql.setLength(0);
			pst.close();

			if(order.getOrderCoupons() != null) {
				sql.append("INSERT INTO order_coupons(coupon_id, order_id, created_at, updated_at)");
				sql.append(" VALUES (?,?,?,?)");
				

				for(Coupon c : order.getOrderCoupons().getCoupons()) {	
					pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
					
					pst.setInt(1, c.getId());
					pst.setInt(2, order.getId());
					Timestamp time2 = new Timestamp(System.currentTimeMillis());
					pst.setTimestamp(3, time2);
					pst.setTimestamp(4, time2);
					
					pst.executeUpdate();				
					rs = pst.getGeneratedKeys();
					
				}	
			}
			connection.commit();		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	@Override
	public void update(DomainEntity entity) {
		// TODO Auto-generated method stub
		Order order = (Order)entity;
		PreparedStatement pst = null;
		openConnection();

		try {
			connection.setAutoCommit(false);					
			StringBuilder sql = new StringBuilder();			
			StringBuilder sql2 = new StringBuilder();
			sql.append("UPDATE orders SET status_id=?, delivery_date=?, updated_at=?");
			sql.append(" WHERE id IN(");
			
			sql2.append("UPDATE order_items SET status_id=?, updated_at=?");
			sql2.append(" WHERE order_id IN(");		
			
	        int size = order.getOrderIds().size();
	        
			for (int i = 0; i < size; i++) {
	            if (i + 1 == size) {
	            	sql.append("?)");
	            	sql2.append("?)");
	            } else {
	            	sql.append("?,");
	            	sql2.append("?,");
	            }
	        }		
			
			pst = connection.prepareStatement(sql.toString());			

			pst.setInt(1, order.getStatus().getId());
			pst.setString(2, order.getDeliveryDate());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(3, time);
			int i = 4;
	        
			for (Integer value : order.getOrderIds()) {
				pst.setInt(i++, value);
	        }
	        
			pst.executeUpdate();			
			connection.commit();

			pst.close();
			pst = connection.prepareStatement(sql2.toString());			

			pst.setInt(1, order.getStatus().getId());
			Timestamp time1 = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(2, time1);
			i = 3;
	        
			for (Integer value : order.getOrderIds()) {
				pst.setInt(i++, value);
	        }
	        
			pst.executeUpdate();			
			connection.commit();
						
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(DomainEntity entity) {	
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		Order order = (Order) entity;
		PreparedStatement pst = null;
		String sql ="SELECT orders.*"
				+ ", clients.firstname"
				+ ", clients.cpf"
				+ ", status.description AS status"
				+ " FROM orders"
				+ " JOIN clients ON clients.id = orders.clients_id "
				+ " JOIN status ON status.id = orders.status_id ";

		
		if(order.getId() != null) {
			sql += "WHERE orders.id=?";			
		} else if(order.getId() == null && order.getStatus() != null && order.getClient() == null) {
			sql += "WHERE orders.status_id=?";					
		} else if(order.getId() == null && order.getClient() != null) {
			sql += "WHERE orders.clients_id=?";					
		}
			
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			if(order.getId() != null) {
				pst.setInt(1, order.getId());				
			} else if(order.getId() == null && order.getStatus() != null && order.getClient() == null) {
				pst.setInt(1, order.getStatus().getId());					
			} else if(order.getId() == null && order.getClient() != null) {
				pst.setInt(1, order.getClient().getId());					
			}
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Order o = new Order();
				o.setId(rs.getInt("id"));
				o.setPrice(rs.getDouble("price"));
				o.setQuantity(rs.getInt("quantity"));
				o.setClient(new Client(rs.getInt("clients_id"), rs.getString("cpf"), rs.getString("firstname")));
				o.setOrderAddress(new OrderAddress(rs.getInt("order_address_id")));
				o.setStatus(new Status(rs.getInt("status_id"), rs.getString("status")));
				o.setOrderDate(rs.getString("order_date"));
				o.setDeliveryDate(rs.getString("delivery_date"));
				
				if(order.getId() != null) {
					openConnection();

					sql = "SELECT order_items.*"
							+ ", status.description as status_description " 
							+ ", phone_references.id as reference_id " 
							+ ", phone_references.name as reference_name " 
							+ ", colors.id as color_id " 
							+ ", colors.description as color_description " 
							+ ", capacities.id as capacity_id " 
							+ ", capacities.description as capacity_description " 
							+ ", phones.model as phone_model " 
							+ "FROM order_items " 
							+ "INNER JOIN phone_references ON phone_references.id = order_items.phone_reference_id " 
							+ "INNER JOIN capacities ON capacities.id = phone_references.capacity_id " 
							+ "INNER JOIN colors ON colors.id = phone_references.color_id "
							+ "INNER JOIN phones ON phones.id = phone_references.phone_id "
							+ "INNER JOIN status ON status.id = order_items.status_id "
							+ "WHERE order_items.order_id = ? ";
					
					try {
						pst = connection.prepareStatement(sql);
						pst.setInt(1, order.getId());
						ResultSet rsi = pst.executeQuery();
						
						while (rsi.next()) {							
							o.addItem(new Orderi(rsi.getInt("id"), rsi.getDouble("price"), rsi.getInt("quantity"), 
									new Status(rsi.getInt("status_id"), rsi.getString("status_description")),
									new Reference(rsi.getInt("reference_id"), rsi.getString("reference_name"), 
										new Color(rsi.getInt("color_id"), rsi.getString("color_description"))
											, new Capacity(rsi.getInt("capacity_id"), rsi.getString("capacity_description"))
													, new Phone(rsi.getString("phone_model")))));
							
						}
						
						pst.close();
						
						sql= "SELECT order_addresses.*, cities.name AS city, states.name AS state, residence_types.description AS residence_type "
							+ "FROM order_addresses "
							+ "JOIN cities ON cities.id = order_addresses.city_id "
							+ "JOIN states ON states.id = cities.state_id "
							+ "JOIN residence_types ON residence_types.id = order_addresses.residence_type_id "
							+ "WHERE order_addresses.id=?";
						pst = connection.prepareStatement(sql);
						pst.setInt(1, o.getOrderAddress().getId());
												
						ResultSet rsa = pst.executeQuery();						
						while (rsa.next()) {	
							Address a = new Address();
	
							a.setId(rsa.getInt("id"));
							a.setName(rsa.getString("name"));
							a.setStreet(rsa.getString("street"));
							a.setNumber(rsa.getString("number"));
							a.setComplement(rsa.getString("complement"));
							a.setDistrict(rsa.getString("district"));
							a.setPostalCode(rsa.getString("postal_code"));
							a.setObservation(rsa.getString("observation"));
							a.setResidenceType(new ResidenceType(rsa.getInt("residence_type_id"), rsa.getString("residence_type")));
							a.setCity(new City(rsa.getInt("city_id"), rsa.getString("city"), new State(rsa.getString("state"))));
							
							o.getOrderAddress().setAddress(a);							
						}
						
						pst.close();
						

						sql= "SELECT payments.*, order_credit_cards.* "
							+ "FROM payments "
							+ "JOIN order_credit_cards ON order_credit_cards.id = payments.credit_card_id "
							+ "WHERE payments.order_id=?";
						pst = connection.prepareStatement(sql);
						pst.setInt(1, order.getId());
												
						ResultSet rsc = pst.executeQuery();	
						Payment payment = new Payment();
						List<PaymentData> paymentDatas = new ArrayList<PaymentData>();
						while (rsc.next()) {	
							CreditCard c = new CreditCard();
							PaymentData p = new PaymentData();
							
							c.setNumber(rsc.getString("number"));
							c.setCode(rsc.getInt("code"));
							c.setMonth(rsc.getInt("month"));
							c.setYear(rsc.getInt("year"));
							c.setCardholderName(rsc.getString("cardholder_name"));
							c.setCardholderCpf(rsc.getString("cardholder_cpf"));
							c.setFlag(rsc.getString("flag"));
							
							p.setQuantity(rsc.getInt("quantity"));
							p.setPrice(rsc.getDouble("price"));
							p.setCard(c);
							paymentDatas.add(p);						
						}

						payment.setPaymentDatas(paymentDatas);						
						o.setPayment(payment);	
						
						pst.close();

						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				all.add(o);
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
			try {
				pst.close();
				if(ctrlTransaction)
					connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return null;
	}

}
