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
@WebServlet("/Insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		String logId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String tel = request.getParameter("tel");
		String roleId = request.getParameter("roleId");
		String description = request.getParameter("description");
		String o = "product_id";
		Boolean error = false;

		ProductDao productDao;
		Connection connection;
		connection = DbUtil.getConnection();
		productDao = new ProductDao(connection);
		try{
			if(logId == null || logId.isEmpty()){
				request.setAttribute("idError","商品IDは必須です");
				error = true;
			}
			if(userName == null || userName.isEmpty()) {
				request.setAttribute("productError","商品名は必須です");
				error = true;
			}
			if(tel == null || tel.isEmpty()) {
				request.setAttribute("priceError","単価は必須です");
				error = true;
			}	
			if(error == true) {
				request.getRequestDispatcher("insert.jsp").forward(request, response);
			}
			else if(error == false){
				int price = Integer.parseInt(tel);
				int categoryId = Integer.parseInt(roleId);

				productDao.insert(logId,userName,price,categoryId,description);
				List<Product> list2 = productDao.findAll(o);

				request.setAttribute("insert","登録が完了しました");
				session.setAttribute("list", list2);
			}
			request.getRequestDispatcher("menu.jsp").forward(request, response);
		} catch(Exception e){
			request.setAttribute("error","商品IDが重複しています");
			request.getRequestDispatcher("insert.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
