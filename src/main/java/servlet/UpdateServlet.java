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
@WebServlet("/Update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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
		String product_id = ((Product) session.getAttribute("findId")).getProduct_id();
		Boolean error = false;
		ProductDao productDao;
		Connection connection;
		connection = DbUtil.getConnection();
		productDao = new ProductDao(connection);
		String o = "product_id";
		Product check;
		check = productDao.findById(logId);
		
		try {
			if(check != null) {
				request.setAttribute("error","商品IDが重複しています");
				request.getRequestDispatcher("updateInput.jsp").forward(request, response);									
			}
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
				request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			}
			else if(error == false){
				int price = Integer.parseInt(tel);
				int categoryId = Integer.parseInt(roleId);

				productDao.update(logId,userName,price,categoryId,description,product_id);

				List<Product> list2 = productDao.findAll(o);

				session.setAttribute("list", list2);

				request.setAttribute("insert","更新処理が完了しました");
			}
			request.getRequestDispatcher("menu.jsp").forward(request, response);				
		}
		catch(Exception e) {
			request.setAttribute("error","更新時にエラーが発生しました。");
			request.getRequestDispatcher("detail.jsp").forward(request, response);							
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
