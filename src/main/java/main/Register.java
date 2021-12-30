package main;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class index
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void paginaInitiala(HttpServletRequest request, HttpServletResponse response) {
	
		request.setAttribute("emailValue", "");
		request.setAttribute("passValue", "");
		request.setAttribute("passValue2", "");

		request.setAttribute("emailvisbun", "hidden");
		request.setAttribute("parolavisbun", "hidden");
		request.setAttribute("parola2visbun", "hidden");
		
		request.setAttribute("emailvis", "hidden");
		request.setAttribute("parolavis", "hidden");
		request.setAttribute("parola2vis", "hidden");

		request.setAttribute("emailtext", "");
		request.setAttribute("parolatext", "");
		request.setAttribute("parola2text", "");
	}
    
    protected boolean verificaEmail(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		String email = request.getParameter("email");
		boolean emailbun = true;
		OracleSQL sql = new OracleSQL();
		if(sql.checkUserExists(email)) {
			emailbun = false;
			request.setAttribute("emailvisbun", "hidden");
			request.setAttribute("emailtext", "Adresa de email este deja folosita");
			request.setAttribute("emailvis", "visible");
			
		}else if(email.isEmpty()) {
			emailbun = false;
			request.setAttribute("emailvisbun", "hidden");
			request.setAttribute("emailtext", "Campul email trebuie sa fie completat");
			request.setAttribute("emailvis", "visible");
		}else if(!email.contains("@yahoo.com")) {
			emailbun = false;
			request.setAttribute("emailvisbun", "hidden");
			request.setAttribute("emailtext", "Trebuie sa folositi o adresa de email valida");
			request.setAttribute("emailvis", "visible");
		}else if(emailbun == true) {
			
			request.setAttribute("emailvalue", email);
			request.setAttribute("emailvisbun", "visible");
			request.setAttribute("emailtext", "");
			request.setAttribute("emailvis", "hidden");
		}
		return emailbun;
			
	}
    
    
    protected boolean verificaParola(HttpServletRequest request, HttpServletResponse response){
		boolean parolabun = true;
		String parola = request.getParameter("pass");
		String parolac = request.getParameter("pass2");
		
		if(!parola.equals(parolac)) {
			parolabun = false;
			
			request.setAttribute("parola2visbun", "hidden");
			request.setAttribute("parola2text", "Parolele nu coincid");
			request.setAttribute("parola2vis", "visible");
			
			request.setAttribute("parolavisbun", "hidden");
			request.setAttribute("parolatext", "Parolele nu coincid");
			request.setAttribute("parolavis", "visible");
		}
		
		if(parolabun == true) {
			request.setAttribute("parolatext", "");
			request.setAttribute("parolavis", "hidden");
		}
		
		return parolabun;
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		OracleSQL sql = new OracleSQL();
		try {
			sql.getInfo();
			paginaInitiala(request,response);
			request.getRequestDispatcher("/WEB-INF/Pages/Register.jsp").forward(request, response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("email") + "\n" + request.getParameter("pass"));
		OracleSQL sql = new OracleSQL();
		request.setAttribute("emailValue", request.getParameter("email"));//setez atributul cand se reincarca pagina ca fiind emailul pentru a nu fi nevoie ca userl sa il scrie de fiecare data cand greseste parola;
		try {
			if(verificaEmail(request,response) && verificaParola(request,response)) {
				System.out.println("a trecut de verificari");
			sql.registerForm(request.getParameter("email"),request.getParameter("pass"),0);
			
			request.getRequestDispatcher("/WEB-INF/Pages/NewUser.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/WEB-INF/Pages/Register.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/Pages/Register.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/Pages/Register.jsp").forward(request, response);
		}
		
	
	}

}