package Tabele;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.OracleSQL;

/**
 * Servlet implementation class Tabel04
 */
@WebServlet("/Tabel04")
public class Tabel04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tabel04() {
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
			System.out.println(alegere);
		}
		 
		
	
		
	   try {
		String html = "";
		if(alegere.contains("interval")) {
			String deLa="";
			String panaLa ="";
			 
			int nr1 = -1;
			int nr2 = -1;
			String interval = "interval";
			
			if(alegere.indexOf("=") > 0) {
		    deLa = alegere.substring(0 + interval.length(),alegere.indexOf("="));
		    panaLa = alegere.substring(alegere.indexOf("=")+1, alegere.length());
		    try {
		    	 nr1 = Integer.parseInt(deLa);
		    	 nr2 = Integer.parseInt(panaLa);
		    } catch (NumberFormatException e) {
		        request.setAttribute("invalidNumber", "Ati introdus o valoare invalida!");
		    
		    }
		    
			
			}
			System.out.println(nr1 + " " + nr2);
			if(nr1!=-1 && nr2!=-1) {
				
			html = sql.intervalTranzactii(nr1, nr2);
			request.setAttribute("intervalTranzactii", html);
			}
			else
				request.setAttribute("invalidNumber", "Ati introdus o valoare invalida! Va rugam introduceti un numar!");
		
		}
		else if(alegere.contains("dataCard")) {
			int nrcard = -1;
			try {
				
			
			 nrcard = Integer.parseInt(request.getParameter("dataCard"));
			}catch(NumberFormatException e) {
				request.setAttribute("invalidCard", "Ati introdus o valoare invalida! Va rugam introduceti un numar!");
			}
			if(nrcard == -1) {
				request.setAttribute("invalidCard", "Ati introdus o valoare invalida! Va rugam introduceti un numar!");
			}
			else {					
			html = sql.miscariPetrecute(nrcard);
			request.setAttribute("MiscariPetrecute", html);
			}
			
		}
		else if(alegere.contains("delaAnValue")) {
			int delaAn = -1;
			int panalaAn= -1;
			int anValue = -1;
			System.out.println(alegere);
			System.out.println(request.getParameter("delaAnValue"));
			try {
			 
			 delaAn = Integer.parseInt(request.getParameter("delaAnValue"));
			 panalaAn = Integer.parseInt(request.getParameter("panalaAnValue"));
			 anValue = Integer.parseInt(request.getParameter("anValue"));
				
		    } catch (NumberFormatException e) {
		       request.setAttribute("invalidAn", "Ati introdus o valoare invalida! Va rugam introduceti un numar!");		        	    
		    }
			if(delaAn == -1 || panalaAn == -1 || anValue == -1) {
				 request.setAttribute("invalidAn", "Ati introdus o valoare invalida! Va rugam introduceti un numar!");
			}
			else {
				System.out.println(anValue+" dela; "+delaAn);
				html = sql.deLaAnValue(anValue, delaAn, panalaAn);
				request.setAttribute("ValoareMedie", html);
			}
			
		
		}
		else {
			System.out.println(alegere);
		}
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	   request.getRequestDispatcher("/WEB-INF/Pages/Tabele/Tabel04.jsp").forward(request, response);
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
