package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/OracleSQL")
public class OracleSQL {
	private static int idAccounts;
	private static int idPersoane;
	private Connection getcon() throws SQLException, ClassNotFoundException {

		     Class.forName("oracle.jdbc.driver.OracleDriver");  
			
			  Connection con=DriverManager.getConnection(
			 "jdbc:oracle:thin:@localhost:1521:XE","BANCAZAHA","admin");
			 
			  //step3 create the statement object 
			  
		return con;
}
	public void getInfo() throws SQLException, ClassNotFoundException {
		Connection con = getcon();
		Statement stmt = con.createStatement();	  
		ResultSet rs = stmt.executeQuery("select * from Persoane");
		System.out.println("Conexiunea cu baza de date functioneaza!");
		  con.close(); 
	}
	
	public boolean checkUserExists(String email) throws ClassNotFoundException, SQLException {
        boolean exista=false;
        Connection con = getcon();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM accounts WHERE email ='"+ email +"'");
        rs.next();
        if(rs.getInt(1)==1)
            exista=true;
        con.close();
        return exista;
    }
	
	
	public int getPermission(String email) throws SQLException, ClassNotFoundException {
		Connection con = getcon();
		Statement stmt = con.createStatement();	 
		ResultSet rs2 = stmt.executeQuery("Select perm_level from accounts WHERE email = '" + email +"'");
		rs2.next();
	    int permission =rs2.getInt(1);
	    return permission;
	}
	
	public void registerUser(String email, String nume, String prenume, String adresa,String gen, String data) throws SQLException, ClassNotFoundException {
		String numeFinal = nume + " " + prenume;
		Connection con = getcon();
		Statement stmt = con.createStatement();	 
		ResultSet rs2 = stmt.executeQuery("Select max(idpers) from Persoane");
		rs2.next();
	    idPersoane=rs2.getInt(1);
	    System.out.println(idPersoane);
	    idPersoane ++;
			
		ResultSet rs = stmt.executeQuery("Insert into Persoane(idpers,nume,adresa,gen,data_nasterii,email) Values('"+idPersoane + "','"+numeFinal + "','"+adresa+"','"+ gen
				+"', TO_DATE('"+ data +"','mm/dd/yyyy'),'"+ email+"')");
	}
	
	
	public void registerForm(String email, String parola,int permissionLevel) throws ClassNotFoundException, SQLException  {
	
		Connection con = getcon();
		Statement stmt = con.createStatement();	  
		
			ResultSet rs2 = stmt.executeQuery("Select max(id) from accounts");
			rs2.next();
		    idAccounts =rs2.getInt(1);
		    System.out.println(idAccounts);
		    idAccounts ++;
		    if(!checkUserExists(email)){
		    	ResultSet rs = stmt.executeQuery("Insert into accounts(id,parola,email,perm_level) Values('" + idAccounts + "','" + parola + "','"+ email + "','" + permissionLevel +"')");
			 }
		    else {
		    	System.out.println("Acest email este folosit deja! Te rugam incearca un alt email.");
		    }
	}
	
	public boolean loginCheck(String email,String parola) throws ClassNotFoundException, SQLException, ServletException, IOException{
		Connection con = getcon();
		Statement stmt = con.createStatement();
		if(checkUserExists(email)) {
			ResultSet rs = stmt.executeQuery("SELECT parola FROM accounts WHERE email = " + "'" + email + "'");
			rs.next();
			if(rs.getString(1).equals(parola)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public String deLaAnValue(int anValue, int delaAn,int panalaAn)throws SQLException, ClassNotFoundException {
		Connection con = getcon();
		String test = "01/01/";
		String test2 = "12/30/";
		Statement stmt = con.createStatement();
		System.out.println(anValue + " " + delaAn + " " + panalaAn);
		ResultSet rs = stmt.executeQuery("Select AVG(valoare) FROM Miscari WHERE "+"data_ora BETWEEN "+"TO_DATE('01/01"+delaAn+"','mm/dd/yyyy')"+" AND TO_DATE('12/30/"+panalaAn+"','mm/dd/yyyy') AND "
				+ "nrcard IN (SELECT nrcard from Carduri WHERE nrcont IN (SELECT nrcont FROM Conturi WHERE idpers IN(SELECT idpers FROM Persoane where data_nasterii BETWEEN "
				+ "TO_DATE('01/01/"+anValue+"','mm/dd/yyyy')"+" AND "
				+ "TO_DATE('12/30/"+anValue+"','mm/dd/yyyy'))))");
		
		
	    
		StringBuilder buf = new StringBuilder();
		buf.append(
				   "<table class = "+"'tableButtons'"+">" +
		           "<tr>" +
		           "<th>Valoare medie cheltuita intre anii "+delaAn+"-"+panalaAn+" de catre persoanele nascute in anul: "+anValue+"</th>" +
		           "</tr>");
		while(rs.next()) {
			buf.append("<tr><td>")
		       .append(rs.getFloat(1))
		       .append("</td></tr>");
		}
		
		buf.append("</table>" 
		           );
		String html = buf.toString();
	    
		return html;
		
	}
	
	public String conturiCorespund()throws SQLException, ClassNotFoundException{
		Connection con = getcon();
		Statement stmt = con.createStatement();
		
		String comanda = "SELECT Distinct cont1.nrcont, cont2.nrcont, cont1.idpers, p.nume"
				+ " FROM Conturi cont1 JOIN Conturi cont2 ON cont1.idpers = cont2.idpers,Persoane p "
				+ "WHERE EXISTS (SELECT nrcont FROM Carduri WHERE cont1.nrcont = nrcont)  AND "
				+ "EXISTS(SELECT nrcont FROM Carduri WHERE cont2.nrcont = nrcont)  AND cont1.nrcont < cont2.nrcont AND p.idpers = cont1.idpers";
		ResultSet rs = stmt.executeQuery(comanda);
		
		
	    
		StringBuilder buf = new StringBuilder();
		buf.append(
				   "<table class = "+"'tableButtons'"+">" +
		           "<tr>" +
		           "<th>Id Persoana</th>" +
		           "<th>Nume Persoana</th>" +
		           "<th>Numar Cont 1</th>" +
		           "<th>Numar Cont 2</th>" +
		           "</tr>");
		while(rs.next()) {
			buf.append("<tr><td>")
		       .append(rs.getString(3))
		       .append("</td><td>")
		       .append(rs.getString(4))
		       .append("</td><td>")
		       .append(rs.getString(1))
		       .append("</td><td>")
		       .append(rs.getString(2))
		       .append("</td></tr>");
		}
		
		buf.append("</table>" 
		           );
		String html = buf.toString();
	    
		return html;
		
	}
	
	
	public String miscariPetrecute(int nrcard)throws SQLException, ClassNotFoundException {
		Connection con = getcon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT nrcard, TO_CHAR(data_ora,'mm/dd/yyyy-HH') FROM miscari WHERE TO_CHAR(data_ora,'mm/dd/yyyy-HH') IN(select TO_CHAR(data_ora,'mm/dd/yyyy-HH') FROM MISCARI WHERE nrcard = '" + nrcard 
				+ "') AND nrcard != '"+ nrcard + "'");
		
		
	    
		StringBuilder buf = new StringBuilder();
		buf.append(
				   "<table class = "+"'tableButtons'"+">" +
		           "<tr>" +
		           "<th>Data si ora Tranzactiei</th>" +
		           "<th>Numar card</th>" +
		           "</tr>");
		while(rs.next()) {
			buf.append("<tr><td>")
		       .append(rs.getString(2))
		       .append("</td><td>")
		       .append(rs.getString("nrcard"))
		       .append("</td></tr>");
		}
		
		buf.append("</table>" 
		           );
		String html = buf.toString();
	    
		return html;
	}
	
	public String intervalTranzactii(int x, int y) throws SQLException, ClassNotFoundException{
		Connection con = getcon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM miscari WHERE valoare BETWEEN '"+ x +"' AND '"+ y+"' ORDER BY valoare ASC, scop DESC");
		
		
	    
		StringBuilder buf = new StringBuilder();
		buf.append(
				   "<table class = "+"'tableButtons'"+">" +
		           "<tr>" +
		           "<th>Data si ora Tranzactiei</th>" +
		           "<th>Numar card</th>" +
		           "<th>Valoare tranzactie</th>" +
		           "<th>Scopul tranzactiei</th>"+
		           "</tr>");
		while(rs.next()) {
			buf.append("<tr><td>")
		       .append(rs.getString("data_ora"))
		       .append("</td><td>")
		       .append(rs.getString("nrcard"))
		       .append("</td><td>")
		       .append(rs.getString("valoare"))
		       .append("</td><td>")
		       .append(rs.getString("scop"))
		       .append("</td></tr>");
		}
		
		buf.append("</table>" 
		           );
		String html = buf.toString();
	    
		return html;
	}
	
	
	public String persoaneSingurCard() throws SQLException, ClassNotFoundException {
		Connection con = getcon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT nume,idpers,email FROM PERSOANE\r\n"
				+ "		WHERE idpers NOT IN(SELECT idpers FROM Conturi WHERE nrcont IN(SELECT c1.nrcont FROM Carduri c1 JOIN Carduri c2 ON (c1.nrcont = c2.nrcont)\r\n"
				+ "		WHERE c1.nrcard != c2.nrcard))");
		
		
	    
		StringBuilder buf = new StringBuilder();
		buf.append(
				   "<table class = "+"'tableButtons'"+">" +
		           "<tr>" +
		           "<th>Id Persoana</th>" +
		           "<th>Pers cu un singur Card</th>" +
		           "<th>email</th>" +
		           "</tr>");
		while(rs.next()) {
			buf.append("<tr><td>")
		       .append(rs.getString("idpers"))
		       .append("</td><td>")
		       .append(rs.getString("nume"))
		       .append("</td><td>")
		       .append(rs.getString("email"))
		       .append("</td></tr>");
		}
		
		buf.append("</table>" 
		           );
		String html = buf.toString();
	    
		return html;
	}
	
	
	public String detinatoriMaster() throws ClassNotFoundException, SQLException {
		Connection con = getcon();
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		ResultSet rsf = stmt.executeQuery("SELECT COUNT(nrcard) FROM Carduri WHERE nrcont IN (SELECT nrcont FROM Conturi WHERE idpers IN (SELECT idpers FROM Persoane WHERE gen = 'F')) AND (categorie = 'CREDIT')");
		ResultSet rsm = stmt2.executeQuery("SELECT COUNT(nrcard) FROM Carduri WHERE nrcont IN (SELECT nrcont FROM Conturi WHERE idpers IN (SELECT idpers FROM Persoane WHERE gen = 'M')) AND (categorie = 'CREDIT')");

		
	
		StringBuilder buf = new StringBuilder();
		buf.append(
				   "<table class = "+"'tableButtons'"+">" +
		           "<tr>" +
		           "<th>Femei care detin Mastercard</th>" +
		           "<th>Brabati care detin Mastercard</th>" +
		           "</tr>");
		    rsf.next();
		    rsm.next();
			buf.append("<tr><td>")
		       .append(rsf.getInt(1))
		       .append("</td><td>")
		       .append(rsm.getInt(1))
		       .append("</td></tr>");
		
		
		buf.append("</table>" 
		           );
		String html = buf.toString();
	    
		return html;
		
	}
	
	public String titulariCard(String categorie) throws ClassNotFoundException, SQLException{
		Connection con = getcon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT p.nume, c.categorie , c.nrcard "+
		"FROM Persoane p JOIN (Conturi cont JOIN Carduri c ON cont.nrcont = c.nrcont) ON(cont.idpers = p.idpers) "
				+ "WHERE c.categorie = " + "'" + categorie+ "'");
		
		
	
		StringBuilder buf = new StringBuilder();
		buf.append(
				   "<table class = "+"'tableButtons'"+">" +
		           "<tr>" +
		           "<th>Nume Persoana</th>" +
		           "<th>Categoria de Card</th>" +
		           "<th>Numarul cardului</th>" +
		           "</tr>");
		while(rs.next()) {
			buf.append("<tr><td>")
		       .append(rs.getString("nume"))
		       .append("</td><td>")
		       .append(rs.getString("categorie"))
		       .append("</td><td>")
		       .append(rs.getString("nrcard"))
		       .append("</td></tr>");
		}
		
		buf.append("</table>" 
		           );
		String html = buf.toString();
	    
		return html;
	}
	
	
	public String selectCards(String tip) throws ClassNotFoundException, SQLException {
		Connection con = getcon();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Carduri WHERE tip = " + "'" + tip + "' ORDER BY data_de_la");
		
	
		StringBuilder buf = new StringBuilder();
		buf.append("<table class = "+"'tableButtons'"+">" +
		           "<tr>" +
		           "<th>NumarCard</th>" +
		           "<th>Data_de_la</th>" +
		           "<th>Limita</th>" +
		           "<th>Numar Cont</th>" +
		           "<th>Categorie</th>" +
		           "</tr>");
		while(rs.next()) {
			buf.append("<tr><td>")
		       .append(rs.getString("nrcard"))
		       .append("</td><td>")
		       .append(rs.getString("data_de_la"))
		       .append("</td><td>")
		       .append(rs.getString("limita"))
		       .append("</td><td>")
		       .append(rs.getString("nrcont"))
		       .append("</td><td>")
		       .append(rs.getString("categorie"))
		       .append("</td></tr>");
		}
		
		buf.append("</table>"
		           );
		String html = buf.toString();
	    
		return html;
	}
	

}
