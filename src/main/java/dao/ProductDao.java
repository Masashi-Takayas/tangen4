package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDao {
	Connection connection;

	public ProductDao(Connection connection) {
		this.setConnection(connection);
	}

	public List<Product> findAll(String o) {

		final String SQL_SELECT_ALL = "SELECT * FROM "
				+ "(SELECT product_id,category_id,p.name p_name,price,c.name c_name,description FROM"
				+ " categories c JOIN products p ON p.category_id = c.id )a ORDER BY "+ o;

		List<Product> list = new ArrayList<Product>();

		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Product p = new Product(rs.getString("product_id"),rs.getInt("price"),rs.getInt("category_id"),rs.getString("c_name"),rs.getString("p_name"),rs.getString("description"));
				list.add(p);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public Product findById(String o) {

		final String SQL_SELECT_FIND_ID = "SELECT * FROM (SELECT product_id,category_id,p.name p_name,price,c.name c_name,description FROM categories c JOIN products p ON product_id = ? )a";

		Product p = null;
		
		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_FIND_ID)) {
			stmt.setString(1, o);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				p = new Product(rs.getString("product_id"),rs.getInt("price"),rs.getInt("category_id"),rs.getString("c_name"),rs.getString("p_name"),rs.getString("description"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return p;
	}
	
	public List<Product> search(String s, String o) {
		final String SQL_SELECT_SEARCH = "SELECT * FROM "
				+ "(SELECT product_id,category_id,p.name p_name,price,c.name c_name,description FROM"
				+ " categories c JOIN products p ON p.category_id = c.id)a WHERE p_name like ? or c_name like ? ORDER BY "+ o;

		List<Product> list = new ArrayList<Product>();

		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_SEARCH)) {
			stmt.setString(1, "%"+s+"%");
			stmt.setString(2, "%"+s+"%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Product p = new Product(rs.getString("product_id"),rs.getInt("price"),rs.getInt("category_id"),rs.getString("c_name"),rs.getString("p_name"),rs.getString("description"));
				list.add(p);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public int insert(String id,String name,Integer price,Integer cateId,String description) {
		final String SQL_INSERT = 
				"INSERT INTO products (product_id,name,price,category_id,description) VALUES (?,?,?,?,?);";

		try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
			stmt.setString(1, id);	
			stmt.setString(2, name);	
			stmt.setInt(3, price);	
			stmt.setInt(4, cateId);	
			stmt.setString(5, description);	

			int num = stmt.executeUpdate();

			return num;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int update(String id,String name,Integer price,Integer cateId,String description,String product_id) {
		final String SQL_UPDATE = "UPDATE products SET product_id=?,name=?,price=?,category_id=?,description=? WHERE product_id=?";
		
		try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
			stmt.setString(1, id);	
			stmt.setString(2, name);	
			stmt.setInt(3, price);	
			stmt.setInt(4, cateId);	
			stmt.setString(5, description);	
			stmt.setString(6, product_id);	

			int num = stmt.executeUpdate();

			return num;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int delete(String id) {
		final String SQL_DELETE = "DELETE FROM Products WHERE Product_id=?";
		
		try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
			stmt.setString(1, id);	

			int num = stmt.executeUpdate();

			return num;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
