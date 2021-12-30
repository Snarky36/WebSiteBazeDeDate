package main;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void paginaInitiala(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("numeValue", "");
		request.setAttribute("prenumeValue", "");
		request.setAttribute("emailValue", "");
		request.setAttribute("adresaValue", "");
		request.setAttribute("genValue", "");
		request.setAttribute("dataValue", "");
		
		request.setAttribute("numevisbun", "hidden");
		request.setAttribute("prenumevisbun", "hidden");
		request.setAttribute("emailvisbun", "hidden");
		request.setAttribute("nrtelvisbun", "hidden");
		request.setAttribute("parolavisbun", "hidden");
		request.setAttribute("parola2visbun", "hidden");
		
		request.setAttribute("numevis", "hidden");
		request.setAttribute("prenumevis", "hidden");
		request.setAttribute("emailvis", "hidden");
		request.setAttribute("nrtelvis", "hidden");
		request.setAttribute("parolavis", "hidden");
		request.setAttribute("parola2vis", "hidden");
		
		request.setAttribute("numetext", "");
		request.setAttribute("prenumetext", "");
		request.setAttribute("emailtext", "");
		request.setAttribute("nrteltext", "");
		request.setAttribute("parolatext", "");
		request.setAttribute("parola2text", "");
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		paginaInitiala(request,response);
		request.getRequestDispatcher("/WEB-INF/Pages/NewUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OracleSQL sql = new OracleSQL();
		
		String email = (String) request.getParameter("email");
		String nume = (String) request.getParameter("nume");
		String prenume = (String) request.getParameter("prenume");
		String adresa = (String) request.getParameter("adresa");
		String data = (String) request.getParameter("data");
		String gen = (String) request.getParameter("gen");
		
		request.setAttribute("emailValue", request.getParameter("email"));	
		try {
			sql.registerUser(email,nume,prenume,adresa,gen,data);
			request.setAttribute("contInregistrat", "Contul a fost inregistrat cu succes! Te poti conecta.");
			request.getRequestDispatcher("/WEB-INF/Pages/Login.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			request.getRequestDispatcher("/WEB-INF/Pages/NewUser.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			request.getRequestDispatcher("/WEB-INF/Pages/NewUser.jsp").forward(request, response);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
