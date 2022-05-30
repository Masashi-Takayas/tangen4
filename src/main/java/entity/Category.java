package entity;

public class Category {

	private String c_name;
	private Integer category_id;

	public Category() {

	}

	public Category(Integer category_id,String c_name) {
		this.category_id = category_id;
		this.c_name = c_name;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	
}
