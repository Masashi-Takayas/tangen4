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
@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");

		String o = "product_id";
		String loginId = request.getParameter("loginId");
		ProductDao productDao;
		Connection connection;
		connection = DbUtil.getConnection();
		productDao = new ProductDao(connection);

		try {
			productDao.delete(loginId);
			List<Product> list2 = productDao.findAll(o);
			session.setAttribute("list", list2);
			request.setAttribute("insert","削除が完了しました");
			request.getRequestDispatcher("menu.jsp").forward(request, response);
		}
		catch(Exception e) {
			request.setAttribute("error","削除に失敗しました");
			request.getRequestDispatcher("detail.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
