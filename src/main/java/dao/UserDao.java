package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDao {

	private static final String SQL_SELECT_ALL = "SELECT * FROM users";

	Connection connection;
	
	public UserDao(Connection connection) {
		this.setConnection(connection);
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<User>();

		try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				User p = new User(rs.getString("login_id"), rs.getString("password"), rs.getString("name"),rs.getInt("role"));
				list.add(p);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
