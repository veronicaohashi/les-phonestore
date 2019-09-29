package les.domain.product;

import java.util.ArrayList;
import java.util.List;

import les.domain.DomainEntity;

public class Phone extends DomainEntity{
	private String model;
	private double screenSize;
	private double screenResol;
	private double rcameraResol;
	private double fcameraResol;
	private double camcorderResol;
	private Integer chip;
	private double height;
	private double width;
	private double depth;
	private double weight;
	private String packageContent;
	private Integer expandability;
	private Integer ramMemory;	
	private String note;
	private Boolean lactive;
	private Brand brand;
	private PricingGroup pricingGroup;
	private Processor processor;
	private SO so;
	private List<Reference> references;
	private List<ConnectionType> connectionsType;
	private double costPrice;
	private double salePrice;
	private InactivationCategory inactivationCategory;
	private ActivationCategory activationCategory;

	public Phone() {
		this.references = new ArrayList<Reference>();
		this.connectionsType = new ArrayList<ConnectionType>();
	}
	
	public Phone(String model) {
		this.model = model;
	}
	
	public Phone(String model, double salePrice) {
		this.model = model;
		this.salePrice = salePrice;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(double screenSize) {
		this.screenSize = screenSize;
	}
	public double getScreenResol() {
		return screenResol;
	}
	public void setScreenResol(double screenResol) {
		this.screenResol = screenResol;
	}
	public double getRcameraResol() {
		return rcameraResol;
	}
	public void setRcameraResol(double rcameraResol) {
		this.rcameraResol = rcameraResol;
	}
	public double getFcameraResol() {
		return fcameraResol;
	}
	public void setFcameraResol(double fcameraResol) {
		this.fcameraResol = fcameraResol;
	}
	public double getCamcorderResol() {
		return camcorderResol;
	}
	public void setCamcorderResol(double camcorderResol) {
		this.camcorderResol = camcorderResol;
	}
	public Integer getChip() {
		return chip;
	}
	public void setChip(Integer chip) {
		this.chip = chip;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getDepth() {
		return depth;
	}
	public void setDepth(double depth) {
		this.depth = depth;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getPackageContent() {
		return packageContent;
	}
	public void setPackageContent(String packageContent) {
		this.packageContent = packageContent;
	}
	public Integer getExpandability() {
		return expandability;
	}
	public void setExpandability(Integer expandability) {
		this.expandability = expandability;
	}
	public Integer getRamMemory() {
		return ramMemory;
	}
	public void setRamMemory(Integer ramMemory) {
		this.ramMemory = ramMemory;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public PricingGroup getPricingGroup() {
		return pricingGroup;
	}
	public void setPricingGroup(PricingGroup pricingGroup) {
		this.pricingGroup = pricingGroup;
	}
	public Processor getProcessor() {
		return processor;
	}
	public void setProcessor(Processor processor) {
		this.processor = processor;
	}
	public SO getSo() {
		return so;
	}
	public void setSo(SO so) {
		this.so = so;
	}
	public Boolean getLactive() {
		return lactive;
	}
	public void setLactive(Boolean lactive) {
		this.lactive = lactive;
	}
	public List<Reference> getReference() {
		return references;
	}
	public void setReference(List<Reference> reference) {
		this.references = reference;
	}
	public List<ConnectionType> getConnectionType() {
		return connectionsType;
	}
	public void setConnectionType(List<ConnectionType> connectionType) {
		this.connectionsType = connectionType;
	}
	public double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public void addReference(Reference reference){
		this.references.add(reference);
	}
	public void addConnectionType(ConnectionType connectionType){
		this.connectionsType.add(connectionType);
	}
	public InactivationCategory getInactivationCategory() {
		return inactivationCategory;
	}
	public void setInactivationCategory(InactivationCategory inactivationCategory) {
		this.inactivationCategory = inactivationCategory;
	}
	public ActivationCategory getActivationCategory() {
		return activationCategory;
	}

	public void setActivationCategory(ActivationCategory activationCategory) {
		this.activationCategory = activationCategory;
	}
}