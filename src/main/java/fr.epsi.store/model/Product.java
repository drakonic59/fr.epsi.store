package t.model;

public class Product {
	
	private Long id;
	
	private String name;
	private String detail;
	private String info;
	private String image;
	
	private double price;
	private int quantity;
	
	public Product() {}
	
	public Product(Long id, String name, String detail, String info, String image, double price, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.info = info;
		this.image = image;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
