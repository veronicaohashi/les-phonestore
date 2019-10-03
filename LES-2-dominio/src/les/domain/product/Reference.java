package les.domain.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import les.domain.DomainEntity;

public class Reference extends DomainEntity{
	private String name;
	private Phone phone;
	private Color color;
	private Capacity capacity;
	private Map<Color, List<Capacity>> cc = new HashMap<Color, List<Capacity>>();
	
	public Reference() {
		
	}
	public Reference(Integer id) {
		super(id);
	}
	
	public Reference(Integer id, String name,  Color color, Capacity capacity) {
		super(id);
		this.name = name;
		this.color = color;
		this.capacity = capacity;
	}

	public Reference(Integer id, String name,  Color color, Capacity capacity, Phone phone) {
		super(id);
		this.name = name;
		this.color = color;
		this.capacity = capacity;
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Capacity getCapacity() {
		return capacity;
	}
	public void setCapacity(Capacity capacity) {
		this.capacity = capacity;
	}	
	public void setColorCapacity(Color color, List<Capacity> capacity){
		this.cc.put(color, capacity);
	}
	public Map<Color, List<Capacity>> getColorCapacity(){
		return this.cc;	
	}
}
