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
import les.domain.sale.Order;
import les.domain.sale.OrderAddress;
import les.domain.sale.Orderi;
import les.domain.sale.PaymentData;
import les.domain.sale.Status;
import les.domain.stock.Entryi;

public class OrderDAO extends AbstractJdbcDAO{
    
	public OrderDAO() {
		super("orders", "id");	
	}

	@Override
	public void save(DomainEntity entity) {	
		Order order = (Order)entity;
		CreditCard card = new CreditCard();
		Address address = new Address();
		
		CreditCardDAO cardDAO = new CreditCardDAO();
		AddressDAO addressDAO = new AddressDAO();
		PreparedStatement pst = null;
		openConnection();
		
		if(order.getAddress().getId() == null) {
			address = order.getAddress();
			if(address.getId() == null) {
				addressDAO.save(address);
				address = (Address) addressDAO.consult(address).get(0);	
			}
		}		
		
		for(PaymentData c : order.getPayments().getPaymentDatas()) {
			card = c.getCard();
			if(card.getId() == null) {
				cardDAO.save(card);
				card = (CreditCard) cardDAO.consult(card).get(0);	
			}
		}
		
		try {				
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			String column = "price_installment_0, quantity_installment_0, credit_card_id_0, created_at, updated_at";
			String values = "?,?,?,?,?";
			int idPaymentTable = 0;
			
			if(order.getPayments().getPaymentDatas().size() > 1) {
				column += ", price_installment_1, quantity_installment_1, credit_card_id_1";
				values = ",?,?,?";
			}

			sql.append("INSERT INTO payments(" + column + ")");
			sql.append(" VALUES (" + values + ")");
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setDouble(1, order.getPayments().getPaymentDatas().get(0).getPrice());
			pst.setInt(2, order.getPayments().getPaymentDatas().get(0).getQuantity());
			pst.setInt(3, order.getPayments().getPaymentDatas().get(0).getCard().getId());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(4, time);
			pst.setTimestamp(5, time);
			
			if(order.getPayments().getPaymentDatas().size() > 1) {
				pst.setDouble(6, order.getPayments().getPaymentDatas().get(1).getPrice());
				pst.setInt(7, order.getPayments().getPaymentDatas().get(1).getQuantity());
				pst.setInt(8, order.getPayments().getPaymentDatas().get(1).getCard().getId());
				
			}
			
			pst.executeUpdate();				
			ResultSet rs = pst.getGeneratedKeys();
			System.out.println(pst);
			
			if(rs.next())
				idPaymentTable = rs.getInt(1);
				System.out.println("idPaymentTable " + idPaymentTable);
				order.getPayments().setId(idPaymentTable);
			
			sql.setLength(0);
			pst.close();
			
			

			
				
				
				
				
			
			sql.append("INSERT INTO orders(price, quantity, clients_id, status_id, payment_id, address_id, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setDouble(1, order.getPrice());
			pst.setInt(2, order.getQuantity());
			pst.setInt(3, order.getClient().getId());
			pst.setInt(4, order.getStatus().getId());
			pst.setInt(5, order.getPayments().getId());
			pst.setInt(6, order.getAddress().getId());
			Timestamp time1 = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(7, time1);
			pst.setTimestamp(8, time1);
			System.out.println("2 " + pst);
			pst.executeUpdate();				
			rs = pst.getGeneratedKeys();
			
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
				order.setId(id);

				sql.setLength(0);
				pst.close();
				
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
					Timestamp time2 = new Timestamp(System.currentTimeMillis());
					pst.setTimestamp(6, time2);
					pst.setTimestamp(7, time2);
					
					pst.executeUpdate();			
					pst.close();				
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
	}

	@Override
	public void delete(DomainEntity entity) {	
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		Order order = (Order) entity;
		PreparedStatement pst = null;
		String sql ="SELECT orders.*, clients.firstname, clients.cpf FROM orders "
				+ "JOIN clients ON clients.id = orders.clients_id ";
		
		if(order.getId() != null) {
			sql += "WHERE id=?";			
		} else if(order.getStatus().getId() != null) {
			sql += "WHERE status_id=?";					
		}
				
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			if(order.getId() != null) {
				pst.setInt(1, order.getId());				
			} else if(order.getStatus().getId() != null) {
				pst.setInt(1, order.getStatus().getId());					
			}
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Order o = new Order();
				o.setId(rs.getInt("id"));
				o.setPrice(rs.getDouble("price"));
				o.setQuantity(rs.getInt("quantity"));
				o.setStatus(new Status(rs.getInt("status_id")));
				o.setClient(new Client(rs.getString("cpf"), rs.getString("firstname")));
				
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
