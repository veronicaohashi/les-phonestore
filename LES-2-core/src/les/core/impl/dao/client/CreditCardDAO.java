package les.core.impl.dao.client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.client.CreditCard;

public class CreditCardDAO extends AbstractJdbcDAO{
    
	public CreditCardDAO() {
		super("credit_cards", "id");	
	}

	@Override
	public void save(DomainEntity entity) {	
		openConnection();
		PreparedStatement pst = null;
		CreditCard card = (CreditCard)entity;

		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();			
			sql.append("INSERT INTO credit_cards(number, code, month, year, cardholder_name, cardholder_cpf, flag,"
					+ " lmain, client_id, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, card.getNumber());
			pst.setInt(2, card.getCode());
			pst.setInt(3, card.getMonth());
			pst.setInt(4, card.getYear());
			pst.setString(5, card.getCardholderName());
			pst.setString(6, card.getCardholderCpf());
			pst.setString(7, card.getFlag());
			pst.setBoolean(8, card.getLmain());
			pst.setInt(9, card.getClient().getId());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(10, time);
			pst.setTimestamp(11, time);

			pst.executeUpdate();				
			ResultSet rs = pst.getGeneratedKeys();
			
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
			card.setId(id);			
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
		CreditCard card = (CreditCard) entity;
		PreparedStatement pst = null;
		String sql = "SELECT * FROM credit_cards ";
		
		if(card.getId() != null && card.getClient() == null && card.getNumber() != null) {
			sql += "WHERE number = ?";
		} else if(card.getId() != null && card.getClient() == null) {
			sql += "WHERE id=?";	
		} else if (card.getClient().getId() != null && card.getLmain() == null) {
			sql += "WHERE client_id=?";
		} else if (card.getClient().getId() != null && card.getLmain() != null) {
			sql += "WHERE client_id=? AND lmain = true";
		}
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			if(card.getId() != null && card.getClient() == null && card.getNumber() != null) {
				pst.setString(1, card.getNumber());
				
			} else if(card.getId() != null && card.getClient() == null) {
				pst.setInt(1, card.getId());
				
			} else if (card.getClient().getId() != null) {
				pst.setInt(1, card.getClient().getId());
			}
			
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				CreditCard c = new CreditCard();
				c.setId(rs.getInt("id"));
				c.setNumber(rs.getString("number"));
				c.setCode(rs.getInt("code"));
				c.setMonth(rs.getInt("month"));
				c.setYear(rs.getInt("year"));
				c.setCardholderName(rs.getString("cardholder_name"));
				c.setCardholderCpf(rs.getString("cardholder_cpf"));
				c.setFlag(rs.getString("flag"));	
				c.setLmain(rs.getBoolean("lmain"));
				
				all.add(c);
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
