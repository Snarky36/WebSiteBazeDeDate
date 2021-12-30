package Tabele;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.OracleSQL;

/**
 * Servlet implementation class Tabel03a
 */
@WebServlet("/Tabel03a")
public class Tabel03a extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tabel03a() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		OracleSQL sql = new OracleSQL();
		String alegere="";
		if(request.getQueryString()!=null) {
			alegere = request.getQueryString();
		}
		
		
		try {
			String html = "";
			if(alegere.contains("visa"))
			{
				html = sql.selectCards("VISA");
				request.setAttribute("htmlTable", html);
			}
			else if(alegere.contains("mastercard")){
				html = sql.selectCards("MASTERCARD");
				//System.out.println(html);
				request.setAttribute("htmlTable", html);
			}
			else if(alegere.contains("debit")) {
				html = sql.titulariCard("DEBIT");
				request.setAttribute("htmlTablePersoane", html);
			
			}
			else if(alegere.contains("credit")) {
				html = sql.titulariCard("CREDIT");
				request.setAttribute("htmlTablePersoane", html);
				
			}
			else if(alegere.contains("singur")) {
				html = sql.persoaneSingurCard();
				request.setAttribute("PersSingurCard", html);
			}
			else if(alegere.contains("detinatori")) {
				html = sql.detinatoriMaster();
				request.setAttribute("detinatoriMaster", html);
			}
				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/WEB-INF/Pages/Tabele/Tabel03a.jsp").forward(request, response);
		
		//System.out.println("Tabel"+request.getAttribute("htmlTabel"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
