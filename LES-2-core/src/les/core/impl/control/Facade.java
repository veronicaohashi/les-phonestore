  
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
import les.core.impl.business.client.ClientCpfUniqueValidation;
import les.core.impl.business.client.AddressCityValidation;
import les.core.impl.business.client.AddressDeleteValidation;
import les.core.impl.business.client.ClientUserDecryptedPasswordValidation;
import les.core.impl.business.client.ClientUserEncryptedPasswordValidation;
import les.core.impl.business.client.AddressUniqueMainValidation;
import les.core.impl.business.client.ClientUserPasswordValidation;
import les.core.impl.business.client.AddressRequiredFieldValidation;
import les.core.impl.business.client.ClientRequiredFieldValidation;
import les.core.impl.business.client.CreditCardRequiredFieldValidation;
import les.core.impl.business.client.CreditCardUniqueMainValidation;
import les.core.impl.business.client.UserRequiredFieldValidation;
import les.core.impl.business.client.AddressResidenceTypeValidation;
import les.core.impl.business.client.ClientUniqueUserValidation;
import les.core.impl.business.product.ProductActivationCategoryValidation;
import les.core.impl.business.product.ProductBrandValidation;
import les.core.impl.business.product.ProductConnectionTypeInsertValidation;
import les.core.impl.business.product.ProductConnectionTypeValidation;
import les.core.impl.business.product.ProductInactivationCategoryValidation;
import les.core.impl.business.product.ProductPriceValidation;
import les.core.impl.business.product.ProductPricingGroupValidation;
import les.core.impl.business.product.ProductProcessorValidation;
import les.core.impl.business.product.ReferenceQtdValidation;
import les.core.impl.business.product.ReferenceStockValidation;
import les.core.impl.business.product.ProductReferenceValidation;
import les.core.impl.business.product.ProductRequiredFieldValidation;
import les.core.impl.business.product.ProductSOValidation;
import les.core.impl.business.product.ProductUniqueReferenceValidation;
import les.core.impl.business.sale.CartAvaiableQtdValidation;
import les.core.impl.business.sale.CartNewItemValidation;
import les.core.impl.business.sale.CartRemoveItemValidation;
import les.core.impl.business.sale.CartValidation;
import les.core.impl.business.sale.ExchangeCouponsValidation;
import les.core.impl.business.sale.FreightValidation;
import les.core.impl.business.sale.OrderCouponValueValidation;
import les.core.impl.business.sale.OrderDeliveryDateValidation;
import les.core.impl.business.sale.OrderDifferenceCouponValidation;
import les.core.impl.business.sale.OrderFirstStatusValidation;
import les.core.impl.business.sale.OrderUpdateCouponValidation;
import les.core.impl.business.sale.PaymentRequiredFieldValidation;
import les.core.impl.business.sale.CartReserveItemValidation;
import les.core.impl.business.sale.OrderUpdateStatusValidation;
import les.core.impl.business.sale.OrderUpdateValidation;
import les.core.impl.business.sale.OrderiExchangeDateValidation;
import les.core.impl.business.sale.OrderiExchangeCategoryValidation;
import les.core.impl.business.sale.OrderiUpdateStatusValidation;
import les.core.impl.business.stock.EntryRequiredFieldValidation;
import les.core.impl.business.stock.EntryMovstockValidation;
import les.core.impl.business.stock.EntryPriceValidation;
import les.core.impl.business.stock.EntryStockValidation;
import les.core.impl.business.stock.EntryReferenceValidation;
import les.core.impl.business.stock.EntrySupplierValidation;
import les.core.impl.business.stock.EntryTotalPriceValidation;
import les.core.impl.business.stock.EntryTotalQuantityValidation;
import les.core.impl.business.stock.ExchangeMovstockValidation;
import les.core.impl.business.stock.ExchangeStockValidation;
import les.core.impl.business.stock.OrderMovstockValidation;
import les.core.impl.business.stock.OrderStockValidation;
import les.core.impl.dao.client.AddressDAO;
import les.core.impl.dao.client.ClientDAO;
import les.core.impl.dao.client.CreditCardDAO;
import les.core.impl.dao.client.ResidenceTypeDAO;
import les.core.impl.dao.client.StateDAO;
import les.core.impl.dao.client.UserDAO;
import les.core.impl.dao.product.ActivationCategoryDAO;
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
import les.core.impl.dao.sale.CouponDAO;
import les.core.impl.dao.sale.ExchangeCategoriesDAO;
import les.core.impl.dao.sale.FreightDAO;
import les.core.impl.dao.sale.OrderAddressDAO;
import les.core.impl.dao.sale.OrderCouponsDAO;
import les.core.impl.dao.sale.OrderDAO;
import les.core.impl.dao.sale.OrderiDAO;
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
import les.domain.client.State;
import les.domain.client.User;
import les.domain.product.ActivationCategory;
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
import les.domain.sale.Coupon;
import les.domain.sale.ExchangeCategory;
import les.domain.sale.Freight;
import les.domain.sale.Order;
import les.domain.sale.OrderAddress;
import les.domain.sale.OrderCoupons;
import les.domain.sale.Orderi;
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
		ActivationCategoryDAO activationCategoryDAO = new ActivationCategoryDAO();
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
		daos.put(ActivationCategory.class.getName(), activationCategoryDAO);
		
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
		OrderAddressDAO orderAddressDAO = new OrderAddressDAO();
		OrderiDAO orderiDAO = new OrderiDAO();
		ExchangeCategoriesDAO exchangeCategoriesDAO = new ExchangeCategoriesDAO();
		CouponDAO couponDAO = new CouponDAO();
		OrderCouponsDAO orderCouponsDAO = new OrderCouponsDAO();
		FreightDAO freightDAO = new FreightDAO();
		daos.put(Cart.class.getName(), reservedStockDAO);
		daos.put(Status.class.getName(), statusDAO);
		daos.put(Order.class.getName(), orderDAO);
		daos.put(Payment.class.getName(), paymentDAO);
		daos.put(OrderAddress.class.getName(), orderAddressDAO);
		daos.put(ExchangeCategory.class.getName(), exchangeCategoriesDAO);
		daos.put(Orderi.class.getName(), orderiDAO);
		daos.put(Coupon.class.getName(), couponDAO);
		daos.put(OrderCoupons.class.getName(), orderCouponsDAO);
		daos.put(Freight.class.getName(), freightDAO);

//		CLIENT
		ClientDAO clientDAO = new ClientDAO();
		ResidenceTypeDAO residenceTypeDAO = new ResidenceTypeDAO(); 
		UserDAO userDAO = new UserDAO();
		AddressDAO addressDAO = new AddressDAO();
		CreditCardDAO creditCardDAO = new CreditCardDAO();
		StateDAO stateDAO = new StateDAO();
		daos.put(Client.class.getName(), clientDAO);	
		daos.put(ResidenceType.class.getName(), residenceTypeDAO);	
		daos.put(User.class.getName(), userDAO);	
		daos.put(Address.class.getName(), addressDAO);		
		daos.put(CreditCard.class.getName(), creditCardDAO);		
		daos.put(State.class.getName(), stateDAO);			
		
//		PRODUCT
		ProductRequiredFieldValidation productRequiredFieldValidation = new ProductRequiredFieldValidation();
		ProductBrandValidation productBrandValidation = new ProductBrandValidation();
		ProductPricingGroupValidation productPricingGroupValidation = new ProductPricingGroupValidation();
		ProductProcessorValidation productProcessorValidation = new ProductProcessorValidation();
		ProductSOValidation productSOValidation = new ProductSOValidation();
		ProductReferenceValidation productReferenceValidation = new ProductReferenceValidation();
		ProductUniqueReferenceValidation productUniqueReferenceValidation = new ProductUniqueReferenceValidation();
		ProductConnectionTypeValidation productConnectionTypeValidation = new ProductConnectionTypeValidation();
		ProductInactivationCategoryValidation productInactivationCategoryValidation = new ProductInactivationCategoryValidation();
		ProductActivationCategoryValidation productActivationCategoryValidation = new ProductActivationCategoryValidation();
		ProductPriceValidation productPriceValidation = new ProductPriceValidation();
		
		List<IStrategy> rnsSavePhone = new ArrayList<IStrategy>();
		rnsSavePhone.add(productRequiredFieldValidation);
		rnsSavePhone.add(productBrandValidation);
		rnsSavePhone.add(productPricingGroupValidation);
		rnsSavePhone.add(productProcessorValidation);
		rnsSavePhone.add(productSOValidation);
		rnsSavePhone.add(productReferenceValidation);
		rnsSavePhone.add(productConnectionTypeValidation);		

		List<IStrategy> rnsUpdatePhone = new ArrayList<IStrategy>();
		rnsUpdatePhone.add(productRequiredFieldValidation);
		rnsUpdatePhone.add(productUniqueReferenceValidation);
		rnsUpdatePhone.add(productActivationCategoryValidation);
		rnsUpdatePhone.add(productPriceValidation);
		
		List<IStrategy> rnsDeletePhone = new ArrayList<IStrategy>();
		rnsDeletePhone.add(productInactivationCategoryValidation);
		
		Map<String, List<IStrategy>> rnsPhone= new HashMap<String, List<IStrategy>>();	
		rnsPhone.put("SAVE", rnsSavePhone);
		rnsPhone.put("UPDATE", rnsUpdatePhone);
		rnsPhone.put("DELETE", rnsDeletePhone);
		rns.put(Phone.class.getName(), rnsPhone);
				
		
		List<IStrategy> rnsSavePhoneAfter = new ArrayList<IStrategy>();
		rnsSavePhoneAfter.add(productUniqueReferenceValidation);
		
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
		EntryRequiredFieldValidation entryRequiredFieldValidation = new EntryRequiredFieldValidation();
		EntryReferenceValidation entryReferenceValidation = new EntryReferenceValidation();
		EntrySupplierValidation entrySupplierValidation = new EntrySupplierValidation();
		EntryTotalQuantityValidation entryTotalQuantityValidation = new EntryTotalQuantityValidation();
		EntryTotalPriceValidation entryTotalPriceValidation = new EntryTotalPriceValidation();
		
		List<IStrategy> rnsSaveEntry = new ArrayList<IStrategy>();
		rnsSaveEntry.add(entryRequiredFieldValidation);
		rnsSaveEntry.add(entryReferenceValidation);
		rnsSaveEntry.add(entrySupplierValidation);
		rnsSaveEntry.add(entryTotalQuantityValidation);
		rnsSaveEntry.add(entryTotalPriceValidation);

		Map<String, List<IStrategy>> rnsEntry = new HashMap<String, List<IStrategy>>();	
		rnsEntry.put("SAVE", rnsSaveEntry);
		
		rns.put(Entry.class.getName(), rnsEntry);		

		EntryMovstockValidation entryMovstockValidation = new EntryMovstockValidation(); 
		EntryStockValidation entryStockValidation = new EntryStockValidation();  
		EntryPriceValidation entryPriceValidation = new EntryPriceValidation();
		
		List<IStrategy> rnsSaveEntryAfter = new ArrayList<IStrategy>();
		rnsSaveEntryAfter.add(entryMovstockValidation);		
		rnsSaveEntryAfter.add(entryStockValidation);		
		rnsSaveEntryAfter.add(entryPriceValidation);		
		
		Map<String, List<IStrategy>> rnsEntryAfter = new HashMap<String, List<IStrategy>>();	
		rnsEntryAfter.put("SAVE", rnsSaveEntryAfter);
		
		rnsAfter.put(Entry.class.getName(), rnsEntryAfter);
		
//		CART
		CartValidation cartValidation = new CartValidation();
		CartNewItemValidation cartNewItemValidation = new CartNewItemValidation();
		CartReserveItemValidation cartReserveItemValidation = new CartReserveItemValidation();
		
		List<IStrategy> rnsSaveCart = new ArrayList<IStrategy>();
		rnsSaveCart.add(cartValidation);
		//rnsSaveCart.add(cartAvaiableQtdValidation);
		rnsSaveCart.add(cartReserveItemValidation);
		rnsSaveCart.add(cartNewItemValidation);
		
		CartRemoveItemValidation cartRemoveItemValidation = new CartRemoveItemValidation();
		List<IStrategy> rnsDeleteCart = new ArrayList<IStrategy>();
		rnsDeleteCart.add(cartRemoveItemValidation);
		
		Map<String, List<IStrategy>> rnsCart = new HashMap<String, List<IStrategy>>();	
		rnsCart.put("SAVE", rnsSaveCart);
		rnsCart.put("DELETE", rnsDeleteCart);
		
		rns.put(Cart.class.getName(), rnsCart);		
		
//		CLIENT
		ClientRequiredFieldValidation clientRequiredFieldValidation = new ClientRequiredFieldValidation();
		ClientCpfUniqueValidation clientCpfUniqueValidation = new ClientCpfUniqueValidation();
		ClientUniqueUserValidation clientUniqueUserValidation = new ClientUniqueUserValidation();
		ClientUserPasswordValidation clientUserPasswordValidation = new ClientUserPasswordValidation();
		ClientUserEncryptedPasswordValidation clientUserEncryptedPasswordValidation = new ClientUserEncryptedPasswordValidation();

		List<IStrategy> rnsSaveClient = new ArrayList<IStrategy>();
		rnsSaveClient.add(clientRequiredFieldValidation);
		rnsSaveClient.add(clientCpfUniqueValidation);
		rnsSaveClient.add(clientUniqueUserValidation);
		rnsSaveClient.add(clientUserPasswordValidation);
		rnsSaveClient.add(clientUserEncryptedPasswordValidation);
		
		List<IStrategy> rnsUpdateClient = new ArrayList<IStrategy>();
		rnsUpdateClient.add(clientRequiredFieldValidation);
		rnsUpdateClient.add(clientUniqueUserValidation);
		rnsUpdateClient.add(clientUserPasswordValidation);


		Map<String, List<IStrategy>> rnsClient = new HashMap<String, List<IStrategy>>();	
		rnsClient.put("SAVE", rnsSaveClient);
		rnsClient.put("UPDATE", rnsUpdateClient);
		
		
		rns.put(Client.class.getName(), rnsClient);	
		
//		USER
		UserRequiredFieldValidation userRequiredFieldValidation = new UserRequiredFieldValidation();
		
		List<IStrategy> rnsConsultUser = new ArrayList<IStrategy>();
		rnsConsultUser.add(userRequiredFieldValidation);
		
		Map<String, List<IStrategy>> rnsUser = new HashMap<String, List<IStrategy>>();	
		rnsUser.put("CONSULT", rnsSaveClient);
		
		rns.put(User.class.getName(), rnsUser);	
		
		ClientUserDecryptedPasswordValidation clientUserDecryptedPasswordValidation = new ClientUserDecryptedPasswordValidation();
		List<IStrategy> rnsConsultUserAfter = new ArrayList<IStrategy>();
		rnsConsultUserAfter.add(clientUserDecryptedPasswordValidation);
		
		Map<String, List<IStrategy>> rnsUserAfter = new HashMap<String, List<IStrategy>>();	
		rnsUserAfter.put("CONSULT", rnsConsultUserAfter);
		
		rnsAfter.put(User.class.getName(), rnsUserAfter);		
		
//		ADDRESS
		AddressRequiredFieldValidation addressRequiredFieldValidation = new AddressRequiredFieldValidation();
		AddressCityValidation addressCityValidation = new AddressCityValidation();
		AddressResidenceTypeValidation addressResidenceTypeValidation = new AddressResidenceTypeValidation();
		AddressUniqueMainValidation addressUniqueMainValidation = new AddressUniqueMainValidation();
		AddressDeleteValidation addressDeleteValidation = new AddressDeleteValidation();

		List<IStrategy> rnsSaveAddress = new ArrayList<IStrategy>();
		rnsSaveAddress.add(addressRequiredFieldValidation);
		rnsSaveAddress.add(addressCityValidation);
		rnsSaveAddress.add(addressResidenceTypeValidation);
		rnsSaveAddress.add(addressUniqueMainValidation);
		
		List<IStrategy> rnsDeleteAddress = new ArrayList<IStrategy>();
		rnsDeleteAddress.add(addressDeleteValidation);
		
		Map<String, List<IStrategy>> rnsAddress = new HashMap<String, List<IStrategy>>();	
		rnsAddress.put("SAVE", rnsSaveAddress);			
		rnsAddress.put("DELETE", rnsDeleteAddress);		
		rns.put(Address.class.getName(), rnsAddress);	
		
//		CREDIT CARD
		CreditCardRequiredFieldValidation creditCardRequiredFieldValidation = new CreditCardRequiredFieldValidation();
		CreditCardUniqueMainValidation creditCardUniqueMainValidation = new CreditCardUniqueMainValidation();
		List<IStrategy> rnsSaveCreditCard = new ArrayList<IStrategy>();
		rnsSaveCreditCard.add(creditCardRequiredFieldValidation);
		rnsSaveCreditCard.add(creditCardUniqueMainValidation);
		
		Map<String, List<IStrategy>> rnsCreditCard = new HashMap<String, List<IStrategy>>();	
		rnsCreditCard.put("SAVE", rnsSaveCreditCard);		
		rns.put(CreditCard.class.getName(), rnsCreditCard);
		
//		PAYMENT		
		PaymentRequiredFieldValidation paymentRequiredFieldValidation = new PaymentRequiredFieldValidation();
		
		List<IStrategy> rnsSavePayment = new ArrayList<IStrategy>();
		rnsSavePayment.add(creditCardRequiredFieldValidation);
		rnsSavePayment.add(paymentRequiredFieldValidation);		

		List<IStrategy> rnsConsultPayment = new ArrayList<IStrategy>();
		rnsConsultPayment.add(paymentRequiredFieldValidation);
		
		Map<String, List<IStrategy>> rnsPayment = new HashMap<String, List<IStrategy>>();	
		rnsPayment.put("SAVE", rnsSavePayment);		
		rnsPayment.put("CONSULT", rnsConsultPayment);		
		rns.put(Payment.class.getName(), rnsPayment);
		
//		ORDER ADDRESS
		FreightValidation freightValidation = new FreightValidation();
		List<IStrategy> rnsSaveOrderAddress = new ArrayList<IStrategy>();
		rnsSaveOrderAddress.add(addressRequiredFieldValidation);
		rnsSaveOrderAddress.add(addressCityValidation);
		rnsSaveOrderAddress.add(addressResidenceTypeValidation);
		rnsSaveOrderAddress.add(freightValidation);
		
		List<IStrategy> rnsConsultOrderAddress = new ArrayList<IStrategy>();
		rnsConsultOrderAddress.add(freightValidation);

		Map<String, List<IStrategy>> rnsOrderAddress = new HashMap<String, List<IStrategy>>();	
		rnsOrderAddress.put("SAVE", rnsSaveOrderAddress);		
		rnsOrderAddress.put("CONSULT", rnsConsultOrderAddress);
		
		rns.put(OrderAddress.class.getName(), rnsOrderAddress);	
		
//		ORDER
		OrderFirstStatusValidation orderFirstStatusValidation = new OrderFirstStatusValidation();
		OrderUpdateStatusValidation orderUpdateStatusValidation = new OrderUpdateStatusValidation();
		OrderUpdateValidation orderUpdateValidation = new OrderUpdateValidation();
		OrderDeliveryDateValidation orderDeliveryDateValidation = new OrderDeliveryDateValidation();
		OrderCouponValueValidation orderCouponValueValidation = new OrderCouponValueValidation();
		OrderDifferenceCouponValidation orderDifferenceCouponValidation = new OrderDifferenceCouponValidation();
		OrderUpdateCouponValidation orderUpdateCouponValidation = new OrderUpdateCouponValidation();
		
		List<IStrategy> rnsSaveOrder = new ArrayList<IStrategy>();
		rnsSaveOrder.add(orderFirstStatusValidation);
		rnsSaveOrder.add(orderCouponValueValidation);
		
		List<IStrategy> rnsUpdateOrder = new ArrayList<IStrategy>();
		rnsUpdateOrder.add(orderUpdateValidation);
		rnsUpdateOrder.add(orderUpdateStatusValidation);
		rnsUpdateOrder.add(orderDeliveryDateValidation);

		Map<String, List<IStrategy>> rnsOrder = new HashMap<String, List<IStrategy>>();	
		rnsOrder.put("SAVE", rnsSaveOrder);			
		rnsOrder.put("UPDATE", rnsUpdateOrder);		
		rns.put(Order.class.getName(), rnsOrder);

		OrderMovstockValidation orderMovstockValidation = new OrderMovstockValidation();
		OrderStockValidation orderStockValidation = new OrderStockValidation();
		
		List<IStrategy> rnsSaveOrderAfter = new ArrayList<IStrategy>();
		rnsSaveOrderAfter.add(orderMovstockValidation);		
		rnsSaveOrderAfter.add(orderStockValidation);	
		rnsSaveOrderAfter.add(orderDifferenceCouponValidation);	
		rnsSaveOrderAfter.add(orderUpdateCouponValidation);	
		
		Map<String, List<IStrategy>> rnsOrderAfter = new HashMap<String, List<IStrategy>>();	
		rnsOrderAfter.put("SAVE", rnsSaveOrderAfter);
		
		rnsAfter.put(Order.class.getName(), rnsOrderAfter);
		
//		ORDERI
		OrderiUpdateStatusValidation orderiUpdateStatusValidation = new OrderiUpdateStatusValidation();
		OrderiExchangeCategoryValidation orderiExchangeCategoryValidation = new OrderiExchangeCategoryValidation();
		OrderiExchangeDateValidation orderiExchangeDateValidation = new OrderiExchangeDateValidation();
		
		List<IStrategy> rnsUpdateOrderi = new ArrayList<IStrategy>();
		rnsUpdateOrderi.add(orderiUpdateStatusValidation);
		rnsUpdateOrderi.add(orderiExchangeCategoryValidation);
		rnsUpdateOrderi.add(orderiExchangeDateValidation);

		Map<String, List<IStrategy>> rnsOrderi = new HashMap<String, List<IStrategy>>();	
		rnsOrderi.put("UPDATE", rnsUpdateOrderi);		
		rns.put(Orderi.class.getName(), rnsOrderi);
		
		ExchangeMovstockValidation exchangeMovstockValidation = new ExchangeMovstockValidation();
		ExchangeStockValidation exchangeStockValidation = new ExchangeStockValidation();
		ExchangeCouponsValidation exchangeCouponsValidation = new ExchangeCouponsValidation();
		
		List<IStrategy> rnsUpdateOrderiAfter = new ArrayList<IStrategy>();
		rnsUpdateOrderiAfter.add(exchangeMovstockValidation);		
		rnsUpdateOrderiAfter.add(exchangeStockValidation);		
		rnsUpdateOrderiAfter.add(exchangeCouponsValidation);		
		
		Map<String, List<IStrategy>> rnsOrderiAfter = new HashMap<String, List<IStrategy>>();	
		rnsOrderiAfter.put("UPDATE", rnsUpdateOrderiAfter);
		
		rnsAfter.put(Orderi.class.getName(), rnsOrderiAfter);
		
//		ORDER COUPONS
		

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
