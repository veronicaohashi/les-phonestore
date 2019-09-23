package les.core.impl.dao.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import les.core.impl.dao.AbstractJdbcDAO;
import les.domain.DomainEntity;
import les.domain.product.Brand;
import les.domain.product.Capacity;
import les.domain.product.Color;
import les.domain.product.ConnectionType;
import les.domain.product.Phone;
import les.domain.product.PricingGroup;
import les.domain.product.Processor;
import les.domain.product.Reference;
import les.domain.product.SO;

public class PhoneDAO extends AbstractJdbcDAO{
    
	public PhoneDAO() {
		super("phone", "id");	
	}

	@Override
	public void save(DomainEntity entity) {		
		openConnection();
		PreparedStatement pst = null;
		Phone phone = (Phone)entity;
		
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();			
			sql.append("INSERT INTO phones(model, screen_size, screen_resol, rcamera_resol, fcamera_resol, camcorder_resol,"
					+ "chips, height, width, depth, weight, package_content, expandability, ram_memory, note, brand_id, pricing_group_id,"
					+ "processor_id, so_id, created_at, updated_at)");
			sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, phone.getModel());
			pst.setDouble(2, phone.getScreenSize());
			pst.setDouble(3, phone.getScreenResol());
			pst.setDouble(4, phone.getRcameraResol());
			pst.setDouble(5, phone.getFcameraResol());
			pst.setDouble(6, phone.getCamcorderResol());
			pst.setInt(7, phone.getChip());
			pst.setDouble(8, phone.getHeight());
			pst.setDouble(9, phone.getWidth());
			pst.setDouble(10, phone.getDepth());
			pst.setDouble(11, phone.getWeight());
			pst.setString(12, phone.getPackageContent());		
			pst.setInt(13, phone.getExpandability());	
			pst.setInt(14, phone.getRamMemory());
			pst.setString(15, phone.getNote());	
			pst.setInt(16, phone.getBrand().getId());
			pst.setInt(17, phone.getPricingGroup().getId());
			pst.setInt(18, phone.getProcessor().getId());
			pst.setInt(19, phone.getSo().getId());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(20, time);
			pst.setTimestamp(21, time);

			pst.executeUpdate();				
			ResultSet rs = pst.getGeneratedKeys();
			
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
				phone.setId(id);
			
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
		Phone phone = (Phone) entity;
		PreparedStatement pst = null;
		openConnection();

		try {
			connection.setAutoCommit(false);					
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE phones SET model=?, screen_size=?, screen_resol=?, rcamera_resol=?, fcamera_resol=?, camcorder_resol=?,"
					+ "chips=?, height=?, width=?, depth=?, weight=?, package_content=?, expandability=?, ram_memory=?, note=?, brand_id=?, pricing_group_id=?,"
					+ "processor_id=?, so_id=?, updated_at=?, cost_price=?, sale_price=?");
			sql.append("WHERE id=?");							

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, phone.getModel());
			pst.setDouble(2, phone.getScreenSize());
			pst.setDouble(3, phone.getScreenResol());
			pst.setDouble(4, phone.getRcameraResol());
			pst.setDouble(5, phone.getFcameraResol());
			pst.setDouble(6, phone.getCamcorderResol());
			pst.setInt(7, phone.getChip());
			pst.setDouble(8, phone.getHeight());
			pst.setDouble(9, phone.getWidth());
			pst.setDouble(10, phone.getDepth());
			pst.setDouble(11, phone.getWeight());
			pst.setString(12, phone.getPackageContent());		
			pst.setInt(13, phone.getExpandability());	
			pst.setInt(14, phone.getRamMemory());
			pst.setString(15, phone.getNote());	
			pst.setInt(16, phone.getBrand().getId());
			pst.setInt(17, phone.getPricingGroup().getId());
			pst.setInt(18, phone.getProcessor().getId());
			pst.setInt(19, phone.getSo().getId());
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pst.setTimestamp(20, time);
			pst.setDouble(21, phone.getCostPrice());
			pst.setDouble(22, phone.getSalePrice());
			pst.setInt(23, phone.getId());

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
		Phone phone = (Phone) entity;

		openConnection();
		PreparedStatement pst = null;		
		StringBuilder sb = new StringBuilder(); 
		
		sb.append("UPDATE phones SET lactive = FALSE, inactivation_categories_id=?  WHERE id = ?");	

		try {
			connection.setAutoCommit(false);
			pst = connection.prepareStatement(sb.toString());
			pst.setInt(1, phone.getInactivationCategory().getId());
			pst.setInt(2, phone.getId());

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
				if(ctrlTransaction)
					connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	
	}	

	@Override
	public List<DomainEntity> consult(DomainEntity entity) {
		Phone phone = (Phone) entity;
		PreparedStatement pst = null;
		String sql = "SELECT * , brands.description as brand_description " + 
				", processors.description as processor_description " + 
				", sos.description as so_description " + 
				", pricing_groups.description as pricing_group_description " + 
				", pricing_groups.percentage as pricing_group_percentage " +
				"FROM phones " + 
				"INNER JOIN brands ON brands.id = phones.brand_id " + 
				"INNER JOIN processors ON processors.id = phones.processor_id " + 
				"INNER JOIN sos ON sos.id = phones.so_id " + 
				"INNER JOIN pricing_groups ON pricing_groups.id = phones.pricing_group_id ";
		
		if (phone.getId() != null) {
			sql += "WHERE phones.id=?";
		}else {
			sql += "WHERE lactive=true";
		}	
		
		sql += " ORDER BY phones.id";
		// executa consulta
		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			if (phone.getId() != null) {
				pst.setInt(1, phone.getId());
			}
			
			List<DomainEntity> all = new ArrayList<DomainEntity>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Phone p = new Phone();
				
				p.setId(rs.getInt("id"));
				p.setModel(rs.getString("model"));
				p.setScreenSize(rs.getDouble("screen_size"));
				p.setScreenResol(rs.getDouble("screen_resol"));
				p.setRcameraResol(rs.getDouble("rcamera_resol"));
				p.setFcameraResol(rs.getDouble("fcamera_resol"));
				p.setCamcorderResol(rs.getDouble("camcorder_resol"));
				p.setChip(rs.getInt("chips"));
				p.setHeight(rs.getDouble("height"));
				p.setWidth(rs.getDouble("width"));
				p.setDepth(rs.getDouble("depth"));
				p.setWeight(rs.getDouble("weight"));
				p.setPackageContent(rs.getString("package_content"));
				p.setExpandability(rs.getInt("expandability"));
				p.setRamMemory(rs.getInt("ram_memory"));
				p.setNote(rs.getString("note"));
				p.setLactive(rs.getBoolean("lactive"));
				p.setCostPrice(rs.getDouble("cost_price"));
				p.setSalePrice(rs.getDouble("sale_price"));
				
				p.setBrand(new Brand(rs.getInt("brand_id"), rs.getString("brand_description")));
				p.setPricingGroup(new PricingGroup(rs.getInt("pricing_group_id"), rs.getString("pricing_group_description")
						, rs.getDouble("pricing_group_percentage")));
				p.setProcessor(new Processor(rs.getInt("processor_id"), rs.getString("processor_description")));
				p.setSo(new SO(rs.getInt("so_id"), rs.getString("so_description")));
				
				if (phone.getId() != null) {

					openConnection();
					
					sql = "SELECT phone_references.* " + 
							", colors.description as color_description " + 
							", capacities.description as capacity_description " + 
							"FROM phone_references " + 
							"INNER JOIN capacities ON capacities.id = phone_references.capacity_id " + 
							"INNER JOIN colors ON colors.id = phone_references.color_id " + 
							"WHERE phone_id = ? " + 
							"ORDER BY color_id, capacity_id";
					try {
						pst = connection.prepareStatement(sql);
						pst.setInt(1, phone.getId());
						ResultSet rrs = pst.executeQuery();
						
						while (rrs.next()) {
							p.addReference(new Reference(rrs.getInt("id"), rrs.getString("name"), new Color(rrs.getInt("color_id"), rrs.getString("color_description"))
							, new Capacity(rrs.getInt("capacity_id"), rrs.getString("capacity_description"))));
							
						}
						
						pst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					sql = "SELECT connection_types_phones.* " + 
						", connection_types.description as connection_type_description " + 
						"FROM connection_types_phones " + 
						"INNER JOIN connection_types ON connection_types.id = connection_types_phones.connection_type_id " + 
						"WHERE phone_id = ?";
					
					try {
						pst = connection.prepareStatement(sql);
						pst.setInt(1, phone.getId());
						
						ResultSet crs = pst.executeQuery();
						
						while (crs.next()) {
							p.addConnectionType(new ConnectionType(crs.getInt("connection_type_id"), crs.getString("connection_type_description")));
							
						}
						
						pst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				all.add(p);
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
		// termino equivocado
		return null;
	}
}
