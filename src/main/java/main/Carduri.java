package main;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Carduri
 */
@WebServlet("/Carduri")
public class Carduri extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carduri() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/Pages/Carduri.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//codul nu mai e in functiune deoarece i-am schimbat functionalitatea butoanelor (deprecated)
		OracleSQL sql = new OracleSQL();
		String visa = request.getParameter("visa");
		System.out.println(visa);
		String master = request.getParameter("master");
		
		String debit = request.getParameter("debit");
		String credit = request.getParameter("credit");
		
		
		System.out.println(master);
		try {
			String html = "";
			if(visa != null)
			{
				html = sql.selectCards("VISA");
				request.setAttribute("htmlTable", html);
			}
			else if(master != null){
				html = sql.selectCards("MASTERCARD");
				//System.out.println(html);
				request.setAttribute("htmlTable", html);
			}
			else if(debit != null) {
				html = sql.titulariCard("DEBIT");
				request.setAttribute("htmlTablePersoane", html);
			
			}
			else if(credit!= null) {
				html = sql.titulariCard("CREDIT");
				request.setAttribute("htmlTablePersoane", html);
				
			}
				
			
			//request.getRequestDispatcher("/WEB-INF/Pages/Tabele/Tabel03a.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
