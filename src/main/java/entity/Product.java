package entity;

public class Product {

	private String product_id;
	private Integer price;
	private String description;
	private String p_name;
	private Category category;
	private String c_name;
	public Product() {
		
	}
	
	public Product(String product_id,Integer price,Integer category_id,String c_name,String p_name,String description) {
		this.product_id = product_id;
		this.p_name = p_name;
		this.price = price;	
		this.description = description;
		this.c_name = c_name;
		this.category = new Category(category_id,c_name);
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
}
