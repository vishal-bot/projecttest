package pharmamanagement.web;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.pharma.login.bean.LoginBean;
import net.pharma.login.database.LoginDao;
import pharmamanagement.dao.MedDAO;
import pharmamanagement.model.med;

@WebServlet("/")
public class PharmaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
	private MedDAO medDAO;

	public void init() {
		medDAO = new MedDAO();
		loginDao = new LoginDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/loginA":
				loginform(request, response);
				break;
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertMed(request, response);
				break;
			case "/delete":
				deleteMed(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateMed(request, response);
				break;
			case "/list":
				listMed(request, response);
				break;
			case "/logout":
				logOut(request, response);
				break;
			case "/login":
				login(request, response);
				break;
			default:
				login(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}
	private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("logout.jsp");
		dispatcher.forward(request, response);

	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);

	}

	private void loginform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		try {
			if (loginDao.validate(loginBean)) {
				response.sendRedirect("list");
			} else {
				response.sendRedirect("loginE.jsp");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void listMed(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List <med> listMed = medDAO.selectAllMeds();
		request.setAttribute("listMed", listMed);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("addmeds.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		med existingUser = medDAO.selectMed(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addmeds.jsp");
		request.setAttribute("med", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertMed(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		String company = request.getParameter("company");
		med newMed = new med(name, company);
		medDAO.insertMed(newMed);
		response.sendRedirect("list");
	}

	private void updateMed(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String company = request.getParameter("company");
		med book = new med(id, name, company);
		medDAO.updateMed(book);
		response.sendRedirect("list");
	}

	private void deleteMed(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		medDAO.deleteMed(id);
		response.sendRedirect("list");

	}
}
