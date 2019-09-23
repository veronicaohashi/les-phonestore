  
package les.core.impl.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import les.core.IDAO;
import les.core.IFacade;
import les.core.IStrategy;
import les.core.application.Result;
import les.core.impl.business.client.CPFUniqueValidation;
import les.core.impl.business.client.CityValidation;
import les.core.impl.business.client.DecryptedPasswordValidation;
import les.core.impl.business.client.EncryptedPasswordValidation;
import les.core.impl.business.client.MainAddressUniqueValidation;
import les.core.impl.business.client.PasswordValidation;
import les.core.impl.business.client.RequiredFieldAddressValidation;
import les.core.impl.business.client.RequiredFieldClientValidation;
import les.core.impl.business.client.RequiredFieldCreditCardValidation;
import les.core.impl.business.client.RequiredFieldUserValidation;
import les.core.impl.business.client.ResidenceTypeValidation;
import les.core.impl.business.client.UserUniqueValidation;
import les.core.impl.business.product.BrandValidation;
import les.core.impl.business.product.ConnectionTypeInsertValidation;
import les.core.impl.business.product.ConnectionTypeValidation;
import les.core.impl.business.product.InactivationCategoryValidation;
import les.core.impl.business.product.PricingGroupValidation;
import les.core.impl.business.product.ProcessorValidation;
import les.core.impl.business.product.ReferenceQtdValidation;
import les.core.impl.business.product.ReferenceStockValidation;
import les.core.impl.business.product.ReferenceValidation;
import les.core.impl.business.product.RequiredFieldValidation;
import les.core.impl.business.product.SOValidation;
import les.core.impl.business.product.UniqueReferenceValidation;
import les.core.impl.business.sale.AvaiableQtdValidation;
import les.core.impl.business.sale.CartNewItemValidation;
import les.core.impl.business.sale.CartRemoveItemValidation;
import les.core.impl.business.sale.CartValidation;
import les.core.impl.business.sale.OrderFirstStatusValidation;
import les.core.impl.business.sale.ReserveItemValidation;
import les.core.impl.business.stock.GenerateMovstockValidation;
import les.core.impl.business.stock.GeneratePriceValidation;
import les.core.impl.business.stock.GenerateStockValidation;
import les.core.impl.business.stock.SupplierValidation;
import les.core.impl.business.stock.TotalPriceValidation;
import les.core.impl.business.stock.TotalQuantityValidation;
import les.core.impl.dao.client.AddressDAO;
import les.core.impl.dao.client.ClientDAO;
import les.core.impl.dao.client.CreditCardDAO;
import les.core.impl.dao.client.ResidenceTypeDAO;
import les.core.impl.dao.client.UserDAO;
import les.core.impl.dao.product.BrandDAO;
import les.core.impl.dao.product.CapacityDAO;
import les.core.impl.dao.product.ColorDAO;
import les.core.impl.dao.product.ConnectionTypeDAO;
import les.core.impl.dao.product.InactivationCategoryDAO;
import les.core.impl.dao.product.PhoneDAO;
import les.core.impl.dao.product.PricingGroupDAO;
import les.core.impl.dao.product.ProcessorDAO;
import les.core.impl.dao.product.ReferenceDAO;
import les.core.impl.dao.product.SODAO;
import les.core.impl.dao.sale.OrderDAO;
import les.core.impl.dao.sale.PaymentDAO;
import les.core.impl.dao.sale.ReservedStockDAO;
import les.core.impl.dao.sale.StatusDAO;
import les.core.impl.dao.stock.EntryDAO;
import les.core.impl.dao.stock.MovstockDAO;
import les.core.impl.dao.stock.MovstockTypeDAO;
import les.core.impl.dao.stock.StockDAO;
import les.core.impl.dao.stock.SupplierDAO;
import les.domain.DomainEntity;
import les.domain.client.Address;
import les.domain.client.Client;
import les.domain.client.CreditCard;
import les.domain.client.ResidenceType;
import les.domain.client.User;
import les.domain.product.Brand;
import les.domain.product.Capacity;
import les.domain.product.Color;
import les.domain.product.ConnectionType;
import les.domain.product.InactivationCategory;
import les.domain.product.Phone;
import les.domain.product.PricingGroup;
import les.domain.product.Processor;
import les.domain.product.Reference;
import les.domain.product.SO;
import les.domain.sale.Cart;
import les.domain.sale.Order;
import les.domain.sale.Payment;
import les.domain.sale.Status;
import les.domain.stock.Entry;
import les.domain.stock.Movstock;
import les.domain.stock.MovstockType;
import les.domain.stock.Stock;
import les.domain.stock.Supplier;

public class Facade implements IFacade {

	private Map<String, IDAO> daos;
	private Map<String, Map<String, List<IStrategy>>> rns;
	private Map<String, Map<String, List<IStrategy>>> rnsAfter;
	private Result result;
	
	
	public Facade(){
		daos = new HashMap<String, IDAO>();
		rns  = new HashMap<String, Map<String, List<IStrategy>>>();			
		rnsAfter = new HashMap<String, Map<String, List<IStrategy>>>();		
		
//		PRODUCT
		BrandDAO brandDAO = new BrandDAO();
		PhoneDAO phoneDAO = new PhoneDAO();
		ColorDAO colorDAO = new ColorDAO();
		CapacityDAO capacityDAO = new CapacityDAO();
		PricingGroupDAO pricingGroupDAO = new PricingGroupDAO();
		ProcessorDAO processorDAO = new ProcessorDAO();
		ConnectionTypeDAO connectionTypeDAO = new ConnectionTypeDAO();
		SODAO soDAO = new SODAO();
		ReferenceDAO referenceDAO = new ReferenceDAO();
		InactivationCategoryDAO inactivationCategoryDAO = new InactivationCategoryDAO();
		daos.put(Brand.class.getName(), brandDAO);
		daos.put(Phone.class.getName(), phoneDAO);
		daos.put(Color.class.getName(), colorDAO);
		daos.put(Capacity.class.getName(), capacityDAO);
		daos.put(PricingGroup.class.getName(), pricingGroupDAO);
		daos.put(Processor.class.getName(), processorDAO);
		daos.put(ConnectionType.class.getName(), connectionTypeDAO);
		daos.put(SO.class.getName(), soDAO);
		daos.put(Reference.class.getName(), referenceDAO);
		daos.put(InactivationCategory.class.getName(), inactivationCategoryDAO);

//		STOCK
		StockDAO stockDAO = new StockDAO();
		SupplierDAO supplierDAO = new SupplierDAO();
		EntryDAO entryDAO = new EntryDAO();
		MovstockDAO movstockDAO = new MovstockDAO();
		MovstockTypeDAO movstockTypeDAO = new MovstockTypeDAO();		
		daos.put(Stock.class.getName(), stockDAO);
		daos.put(Supplier.class.getName(), supplierDAO);
		daos.put(Entry.class.getName(), entryDAO);
		daos.put(Movstock.class.getName(), movstockDAO);
		daos.put(MovstockType.class.getName(), movstockTypeDAO);		

//		SALE
		ReservedStockDAO reservedStockDAO = new ReservedStockDAO();
		StatusDAO statusDAO = new StatusDAO();
		OrderDAO orderDAO = new OrderDAO();
		PaymentDAO paymentDAO = new PaymentDAO();
		daos.put(Cart.class.getName(), reservedStockDAO);
		daos.put(Status.class.getName(), statusDAO);
		daos.put(Order.class.getName(), orderDAO);
		daos.put(Payment.class.getName(), paymentDAO);

//		CLIENT
		ClientDAO clientDAO = new ClientDAO();
		ResidenceTypeDAO residenceTypeDAO = new ResidenceTypeDAO(); 
		UserDAO userDAO = new UserDAO();
		AddressDAO addressDAO = new AddressDAO();
		CreditCardDAO creditCardDAO = new CreditCardDAO();
		daos.put(Client.class.getName(), clientDAO);	
		daos.put(ResidenceType.class.getName(), residenceTypeDAO);	
		daos.put(User.class.getName(), userDAO);	
		daos.put(Address.class.getName(), addressDAO);		
		daos.put(CreditCard.class.getName(), creditCardDAO);			
		
//		PRODUCT
		RequiredFieldValidation requiredFieldValidation = new RequiredFieldValidation();
		BrandValidation brandValidation = new BrandValidation();
		PricingGroupValidation pricingGroupValidation = new PricingGroupValidation();
		ProcessorValidation processorValidation = new ProcessorValidation();
		SOValidation soValidation = new SOValidation();
		ReferenceValidation referenceValidation = new ReferenceValidation();
		UniqueReferenceValidation uniqueReferenceValidation = new UniqueReferenceValidation();
		ConnectionTypeValidation connectionTypeValidation = new ConnectionTypeValidation();
		ConnectionTypeInsertValidation connectionTypeInsertValidation = new ConnectionTypeInsertValidation();
		InactivationCategoryValidation inactivationCategoryValidation = new InactivationCategoryValidation();
		
		List<IStrategy> rnsSavePhone = new ArrayList<IStrategy>();
		rnsSavePhone.add(requiredFieldValidation);
		rnsSavePhone.add(brandValidation);
		rnsSavePhone.add(pricingGroupValidation);
		rnsSavePhone.add(processorValidation);
		rnsSavePhone.add(soValidation);
		rnsSavePhone.add(referenceValidation);
		rnsSavePhone.add(connectionTypeValidation);		

		List<IStrategy> rnsUpdatePhone = new ArrayList<IStrategy>();
		rnsUpdatePhone.add(requiredFieldValidation);
		rnsUpdatePhone.add(uniqueReferenceValidation);
		
		List<IStrategy> rnsDeletePhone = new ArrayList<IStrategy>();
		rnsDeletePhone.add(inactivationCategoryValidation);
		
		Map<String, List<IStrategy>> rnsPhone= new HashMap<String, List<IStrategy>>();	
		rnsPhone.put("SAVE", rnsSavePhone);
		rnsPhone.put("UPDATE", rnsUpdatePhone);
		rnsPhone.put("DELETE", rnsDeletePhone);
		rns.put(Phone.class.getName(), rnsPhone);
				
		
		List<IStrategy> rnsSavePhoneAfter = new ArrayList<IStrategy>();
		rnsSavePhoneAfter.add(uniqueReferenceValidation);
		rnsSavePhoneAfter.add(connectionTypeInsertValidation);		
		
		Map<String, List<IStrategy>> rnsPhoneAfter = new HashMap<String, List<IStrategy>>();	
		rnsPhoneAfter.put("SAVE", rnsSavePhoneAfter);
		rnsAfter.put(Phone.class.getName(), rnsPhoneAfter);
		
//		REFERENCE
		ReferenceQtdValidation referenceQtdValidation = new ReferenceQtdValidation();
		ReferenceStockValidation referenceStockValidation = new ReferenceStockValidation();
		
		List<IStrategy> rnsDeleteReference = new ArrayList<IStrategy>();
		rnsDeleteReference.add(referenceQtdValidation);
		rnsDeleteReference.add(referenceStockValidation);
		
		Map<String, List<IStrategy>> rnsReference = new HashMap<String, List<IStrategy>>();	
		rnsReference.put("DELETE", rnsDeleteReference);
		rns.put(Reference.class.getName(), rnsReference);
		
//		ENTRY
		RequiredFieldValidation requiredFieldValidationEntry = new RequiredFieldValidation();
		ReferenceValidation referenceValidationEntry = new ReferenceValidation();
		SupplierValidation supplierValidation = new SupplierValidation();
		TotalPriceValidation totalPriceValidation = new TotalPriceValidation();
		TotalQuantityValidation totalQuantityValidation = new TotalQuantityValidation();
		
		List<IStrategy> rnsSaveEntry = new ArrayList<IStrategy>();
		rnsSaveEntry.add(requiredFieldValidationEntry);
		rnsSaveEntry.add(referenceValidationEntry);
		rnsSaveEntry.add(supplierValidation);
		rnsSaveEntry.add(totalQuantityValidation);
		rnsSaveEntry.add(totalPriceValidation);

		Map<String, List<IStrategy>> rnsEntry = new HashMap<String, List<IStrategy>>();	
		rnsEntry.put("SAVE", rnsSaveEntry);
		
		rns.put(Entry.class.getName(), rnsEntry);		

		GenerateMovstockValidation generateMovstockValidation = new GenerateMovstockValidation(); 
		GenerateStockValidation generateStockValidation = new GenerateStockValidation();  
		GeneratePriceValidation generatePriceValidation = new GeneratePriceValidation();
		
		List<IStrategy> rnsSaveEntryAfter = new ArrayList<IStrategy>();
		rnsSaveEntryAfter.add(generateMovstockValidation);		
		rnsSaveEntryAfter.add(generateStockValidation);		
		rnsSaveEntryAfter.add(generatePriceValidation);		
		
		Map<String, List<IStrategy>> rnsEntryAfter = new HashMap<String, List<IStrategy>>();	
		rnsEntryAfter.put("SAVE", rnsSaveEntryAfter);
		
		rnsAfter.put(Entry.class.getName(), rnsEntryAfter);
		
//		CART
		CartValidation cartValidation = new CartValidation();
		AvaiableQtdValidation avaiableQtdValidation = new AvaiableQtdValidation();
		CartNewItemValidation cartNewItemValidation = new CartNewItemValidation();
		ReserveItemValidation reserveItemValidation = new ReserveItemValidation();
		
		List<IStrategy> rnsSaveCart = new ArrayList<IStrategy>();
		rnsSaveCart.add(cartValidation);
		rnsSaveCart.add(avaiableQtdValidation);
		rnsSaveCart.add(reserveItemValidation);
		rnsSaveCart.add(cartNewItemValidation);
		
		CartRemoveItemValidation cartRemoveItemValidation = new CartRemoveItemValidation();
		List<IStrategy> rnsDeleteCart = new ArrayList<IStrategy>();
		rnsDeleteCart.add(cartRemoveItemValidation);
		
		Map<String, List<IStrategy>> rnsCart = new HashMap<String, List<IStrategy>>();	
		rnsCart.put("SAVE", rnsSaveCart);
		rnsCart.put("DELETE", rnsDeleteCart);
		
		rns.put(Cart.class.getName(), rnsCart);		
		
//		CLIENT
		RequiredFieldClientValidation requiredFieldClientValidation = new RequiredFieldClientValidation();
		CPFUniqueValidation CPFUniqueValidation = new CPFUniqueValidation();
		UserUniqueValidation userUniqueValidation = new UserUniqueValidation();
		PasswordValidation passwordValidation = new PasswordValidation();
		EncryptedPasswordValidation encryptedPasswordValidation = new EncryptedPasswordValidation();

		List<IStrategy> rnsSaveClient = new ArrayList<IStrategy>();
		rnsSaveClient.add(requiredFieldClientValidation);
		rnsSaveClient.add(CPFUniqueValidation);
		rnsSaveClient.add(userUniqueValidation);
		rnsSaveClient.add(passwordValidation);
		rnsSaveClient.add(encryptedPasswordValidation);
		
		List<IStrategy> rnsUpdateClient = new ArrayList<IStrategy>();
		rnsUpdateClient.add(requiredFieldClientValidation);
		rnsUpdateClient.add(userUniqueValidation);
		rnsSaveClient.add(passwordValidation);


		Map<String, List<IStrategy>> rnsClient = new HashMap<String, List<IStrategy>>();	
		rnsClient.put("SAVE", rnsSaveClient);
		rnsClient.put("UPDATE", rnsUpdateClient);
		
		
		rns.put(Client.class.getName(), rnsClient);	
		
//		USER
		RequiredFieldUserValidation requiredFieldUserValidation = new RequiredFieldUserValidation();
		
		List<IStrategy> rnsConsultUser = new ArrayList<IStrategy>();
		rnsConsultUser.add(requiredFieldUserValidation);
		
		Map<String, List<IStrategy>> rnsUser = new HashMap<String, List<IStrategy>>();	
		rnsUser.put("CONSULT", rnsSaveClient);
		
		rns.put(User.class.getName(), rnsUser);	
		
		DecryptedPasswordValidation decryptedPasswordValidation = new DecryptedPasswordValidation();
		List<IStrategy> rnsConsultUserAfter = new ArrayList<IStrategy>();
		rnsConsultUserAfter.add(decryptedPasswordValidation);
		
		Map<String, List<IStrategy>> rnsUserAfter = new HashMap<String, List<IStrategy>>();	
		rnsUserAfter.put("CONSULT", rnsConsultUserAfter);
		
		rnsAfter.put(User.class.getName(), rnsUserAfter);		
		
//		ADDRESS
		RequiredFieldAddressValidation requiredFieldAddressValidation = new RequiredFieldAddressValidation();
		CityValidation cityValidation = new CityValidation();
		ResidenceTypeValidation residenceTypeValidation = new ResidenceTypeValidation();
		MainAddressUniqueValidation mainAddressUniqueValidation = new MainAddressUniqueValidation();

		List<IStrategy> rnsSaveAddress = new ArrayList<IStrategy>();
		rnsSaveAddress.add(requiredFieldAddressValidation);
		rnsSaveAddress.add(cityValidation);
		rnsSaveAddress.add(residenceTypeValidation);
		rnsSaveAddress.add(mainAddressUniqueValidation);

		Map<String, List<IStrategy>> rnsAddress = new HashMap<String, List<IStrategy>>();	
		rnsAddress.put("SAVE", rnsSaveAddress);
		
		rns.put(Address.class.getName(), rnsAddress);	
		
//		CREDIT CARD
		RequiredFieldCreditCardValidation requiredFieldCreditCardValidation = new RequiredFieldCreditCardValidation();
		
		List<IStrategy> rnsSaveCreditCard = new ArrayList<IStrategy>();
		rnsSaveCreditCard.add(requiredFieldCreditCardValidation);
		
		Map<String, List<IStrategy>> rnsCreditCard = new HashMap<String, List<IStrategy>>();	
		rnsCreditCard.put("SAVE", rnsSaveCreditCard);
		
		rns.put(CreditCard.class.getName(), rnsCreditCard);
		
//		ORDER
		OrderFirstStatusValidation orderFirstStatusValidation = new OrderFirstStatusValidation();

		List<IStrategy> rnsSaveOrder = new ArrayList<IStrategy>();
		rnsSaveOrder.add(orderFirstStatusValidation);

		Map<String, List<IStrategy>> rnsOrder = new HashMap<String, List<IStrategy>>();	
		rnsOrder.put("SAVE", rnsSaveOrder);
		
		rns.put(Order.class.getName(), rnsOrder);
	}
	
	@Override
	public Result save(DomainEntity entity) {
		result = new Result();
		String nmClasse = entity.getClass().getName();	
		String msg ;
		msg = executeRules(entity, "SAVE", 1);		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.save(entity);
				List<DomainEntity> entities = new ArrayList<DomainEntity>();
				entities.add(entity);
				result.setEntities(entities);
			} catch (SQLException e) {
				e.printStackTrace();
				result.setMsg("Não foi possível realizar o registro!");
			}			

			msg = executeRules(entity, "SAVE", 2);
			
			if(msg != null){
				result.setMsg(msg);
			} 
		}else{
			result.setMsg(msg);
		}

		return result;		  
	}

	@Override
	public Result update(DomainEntity entity) {
		result = new Result();
		String nmClasse = entity.getClass().getName();	
		
		String msg = executeRules(entity, "UPDATE", 1);
	
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.update(entity);
				List<DomainEntity> entities = new ArrayList<DomainEntity>();
				entities.add(entity);
				result.setEntities(entities);
			} catch (SQLException e) {
				e.printStackTrace();
				result.setMsg("Não foi possível realizar o registro!");	
			}
			msg = executeRules(entity, "UPDATE", 2);
			
			if(msg != null){
				result.setMsg(msg);
			} 
		}else{
			result.setMsg(msg);
		}
		return result;		  
	}

	@Override
	public Result delete(DomainEntity entity) {
		result = new Result();
		String nmClasse = entity.getClass().getName();	
		
		String msg = executeRules(entity, "DELETE", 1);
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.delete(entity);
				List<DomainEntity> entities = new ArrayList<DomainEntity>();
				entities.add(entity);
				result.setEntities(entities);
			} catch (SQLException e) {
				e.printStackTrace();
				result.setMsg("Não foi possível realizar o registro!");
			}
		}else{
			result.setMsg(msg);			
		}
		msg = executeRules(entity, "DELETE", 2);
		if(msg != null){
			result.setMsg(msg);
		} 
		return result;
	}

	@Override
	public Result consult(DomainEntity entity) {
		result = new Result();
		String nmClasse = entity.getClass().getName();	
		
		String msg = executeRules(entity, "CONSULT", 1);
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				result.setEntities(dao.consult(entity));
			} catch (SQLException e) {
				e.printStackTrace();
				result.setMsg("Não foi possível realizar o registro!");
			}
		}else{
			result.setMsg(msg);			
		}	
		msg = executeRules(entity, "CONSULT", 2);
		if(msg != null){
			result.setMsg(msg);
		} 
		return result;
	}
	
	@Override
	public Result list(DomainEntity entity) {
		result = new Result();
		result.setEntities(new ArrayList<DomainEntity>(1));
		result.getEntities().add(entity);		
		return result;
	}

	
	private String executeRules(DomainEntity entity, String operation, int order){
		String nmClasse = entity.getClass().getName();		
		StringBuilder msg = new StringBuilder();
		if (order == 1) {
			Map<String, List<IStrategy>> OperationRules = rns.get(nmClasse);
			if(OperationRules != null){
				List<IStrategy> rules = OperationRules.get(operation);
				
				if(rules != null){
					for(IStrategy s: rules){			
						String m = s.process(entity);			
						
						if(m != null){
							msg.append(m);
							msg.append("\n");
							return msg.toString();
						}			
					}	
				}			
			}
		} else {
			Map<String, List<IStrategy>> OperationRulesAfter = rnsAfter.get(nmClasse);
			if(OperationRulesAfter != null) {
				List<IStrategy> regrasDepois = OperationRulesAfter.get(operation);
				
				if(regrasDepois != null){
					for(IStrategy s: regrasDepois){			
						String m = s.process(entity);			
						
						if(m != null){
							msg.append(m);
							msg.append("\n");
						}			
					}	
				}				
			}
		}	
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}
}
