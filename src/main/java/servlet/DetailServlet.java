package servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		
		request.setCharacterEncoding("UTF-8");
		String product_id = request.getParameter("product_id");
		ProductDao productDao;
		Connection connection;
		connection = DbUtil.getConnection();
		productDao = new ProductDao(connection);

		Product findId = productDao.findById(product_id);
		
		session.setAttribute("findId", findId);
		request.getRequestDispatcher("detail.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
