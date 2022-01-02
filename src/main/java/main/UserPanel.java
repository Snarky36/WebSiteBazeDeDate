package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserPanel
 */
@WebServlet("/UserPanel")
public class UserPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPanel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		session.setAttribute("view", "");
	  
		request.getRequestDispatcher("/WEB-INF/Pages/UserPanel.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OracleSQL sql = new OracleSQL();
		HttpSession session = request.getSession();
		System.out.println("emailul este:"+ session.getAttribute("username"));
		String view;
		 try { 
			 view = sql.totalFundsButton();
	     int sold = sql.viewFunds((String) session.getAttribute("username"));
		 session.setAttribute("totalFunds",sold+".00");
		 //System.out.println(sold);
		 session.setAttribute("view", view);
		 System.out.println(session.getAttribute("view"));
		  
		  } catch (ClassNotFoundException e) { // TODO Auto-generated catch block
		  e.printStackTrace(); } 
		 catch (SQLException e) { // TODO Auto-generated catch
		   e.printStackTrace(); }
		 request.getRequestDispatcher("/WEB-INF/Pages/UserPanel.jsp").forward(request, response);
	}

}
