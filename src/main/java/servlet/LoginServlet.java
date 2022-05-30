package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import dao.UserDao;
import entity.Product;
import entity.User;
import util.DbUtil;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @param session 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("loginId");		
		String pass = request.getParameter("pass");
		String o = "product_id";
		UserDao userDao;
		ProductDao productDao;
		
		Connection connection;

		
		connection = DbUtil.getConnection();
		//		connection.setAutoCommit(false);

		userDao = new UserDao(connection);
		productDao = new ProductDao(connection);
		
		List<User> list = userDao.findAll();
		List<Product> list2 = productDao.findAll(o);
		HttpSession session = request.getSession(false);
		
		
		if(loginId != null && !loginId.isEmpty()&&pass != null && !pass.isEmpty()){
			session.setAttribute("list", list2);
			
			for(User i:list) {

				if(i.getLogin_id().equals(loginId) && i.getPassword().equals(pass)) {
					session.setAttribute("name", i.getName());
					
					request.getRequestDispatcher("menu.jsp").forward(request, response);						
				}
			}
			request.setAttribute("record", "IDまたはパスワードが不正です");		
		}
		if(loginId == null || loginId.isEmpty()){
			request.setAttribute("loginId", "IDは必須です");
		}
		if(pass == null || pass.isEmpty()){
			request.setAttribute("password", "PASSは必須です");
		}

		request.getRequestDispatcher("index.jsp").forward(request, response);						
	}

}
