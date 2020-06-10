package net.grocerymanagement.web;

import java.io.IOException;


import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.grocery.login.bean.LoginBean;
import net.grocery.login.database.LoginDao;
import net.grocerymanagement.dao.GroceryDAO;
import net.grocerymanagement.model.Grocery;



@WebServlet("/")
public class GroceryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GroceryDAO groceryDAO;
    private LoginDao loginDao;

    public void init() {
        groceryDAO = new GroceryDAO();
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
                    insertGrocery(request, response);
                    break;
                case "/delete":
                    deleteGrocery(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateGrocery(request, response);
                    break;
                case "/list":
    				listGrocery(request, response);
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
    private void listGrocery(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Grocery > listGrocery = groceryDAO.selectAllGrocerys();
        request.setAttribute("listGrocery", listGrocery);
        RequestDispatcher dispatcher = request.getRequestDispatcher("grocery-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("grocery-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Grocery existingGrocery = groceryDAO.selectGrocery(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("grocery-form.jsp");
        request.setAttribute("grocery", existingGrocery);
        dispatcher.forward(request, response);

    }

    private void insertGrocery(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String company = request.getParameter("company");
        Grocery newGrocery = new Grocery(name, type, company);
        groceryDAO.insertGrocery(newGrocery);
        response.sendRedirect("list");
    }

    private void updateGrocery(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String company = request.getParameter("company");

        Grocery book = new Grocery(id, name, type, company);
        groceryDAO.updateGrocery(book);
        response.sendRedirect("list");
    }

    private void deleteGrocery(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        groceryDAO.deleteGrocery(id);
        response.sendRedirect("list");

    }
}