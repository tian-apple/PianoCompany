package pianoCompany.entity;

public class Instrument {
	private int id;//乐器标识符
	private String brand;//品牌
	private String type;//种类
	private double price;//价格
	private String model;//型号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void print() {
		System.out.println("乐器号: "+id+"\t品牌: "+brand+"\t种类: "+type+"\t价格: "+price+"\t型号:"+model);
	}
}
