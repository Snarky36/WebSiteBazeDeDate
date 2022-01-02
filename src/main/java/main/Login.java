package main;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class index
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void setareLoginInitial(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("emailvalue", "");
		request.setAttribute("vis", "hidden");
		request.setAttribute("text", "");
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		OracleSQL sql = new OracleSQL();
	
	if (request.getSession(false) != null) {
		request.getSession().invalidate(); 
		request.getRequestDispatcher("/WEB-INF/Pages/Login.jsp").forward(request, response);
		}
	else
		try {
			setareLoginInitial(request,response);
			HttpSession session = request.getSession();
			session.setAttribute("username", "");
			sql.getInfo();
			request.getRequestDispatcher("/WEB-INF/Pages/Login.jsp").forward(request, response);
			
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
		//doGet(request,response);
		System.out.println(request.getParameter("email") + "\n" + request.getParameter("pass"));
		OracleSQL sql = new OracleSQL();
		request.setAttribute("emailValue", request.getParameter("email"));//setez atributul cand se reincarca pagina ca fiind emailul pentru a nu fi nevoie ca userl sa il scrie de fiecare data cand greseste parola;
		try {
			if(sql.loginCheck(request.getParameter("email"),request.getParameter("pass"))) {
				System.out.println("am preluat numele si parola");
				HttpSession session = request.getSession();
				String email  = request.getParameter("email");
				//String username = email.substring(0,5) + "...";
				session.setAttribute("username", email + " ");
				request.setAttribute("UserEmail", email);
				int permission = sql.getPermission(email);
				System.out.println(permission);
				session.setAttribute("permission", ""+permission);
				System.out.println(session.getAttribute("username"));
				
				request.getRequestDispatcher("/WEB-INF/Pages/UserPanel.jsp").forward(request, response);  	
				
			}
			else{
				request.setAttribute("vis", "visible");
				request.setAttribute("text", "Emailul sau parola au fost introduse gresit");
		        request.getRequestDispatcher("/WEB-INF/Pages/Login.jsp").forward(request, response);  
				
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}