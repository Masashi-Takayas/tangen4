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
import entity.Product;
import util.DbUtil;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Search")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		String keyWord = request.getParameter("keyWord");	
		String order = request.getParameter("orderBy");
		String o = "product_id";
		ProductDao productDao;
		Connection connection;
		connection = DbUtil.getConnection();
		productDao = new ProductDao(connection);
		List<Product> list2 = productDao.findAll(o);
		HttpSession session = request.getSession(false);

		if(order == null || order.isEmpty()) {
			order = "product_id";
		}
		if(keyWord != null && !keyWord.isEmpty()){
			List<Product> list = productDao.search(keyWord,order);
			request.setAttribute("list",list);
		}
		else{
			List<Product> list = productDao.findAll(order);
			request.setAttribute("list",list);
		}
		session.setAttribute("list", list2);
		request.getRequestDispatcher("menu.jsp").forward(request, response);		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
