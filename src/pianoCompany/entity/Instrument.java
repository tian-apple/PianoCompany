package pianoCompany.entity;

public class Instrument {
	private int id;//������ʶ��
	private String brand;//Ʒ��
	private String type;//����
	private double price;//�۸�
	private String model;//�ͺ�
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
		System.out.println("������: "+id+"\tƷ��: "+brand+"\t����: "+type+"\t�۸�: "+price+"\t�ͺ�:"+model);
	}
}
