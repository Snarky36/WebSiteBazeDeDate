package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleSQL {
	private Connection getcon() throws SQLException, ClassNotFoundException {
		
//		String myDriver = "org.sqlite.JDBC";
//		Class.forName(myDriver);
//		
//		Connection con = null;
//		String url ="jdbc:sqlite:" + this.getClass().getResource("/").getPath().substring(1);
//		String urls[] = url.split("classes");
//		urls[0]=urls[0] + "todaytech.db";
//		urls[0]=urls[0].replaceAll("%20", " ");
//		//System.out.println(url);
//		con = DriverManager.getConnection(urls[0]);
		
			//step1 load the driver class  
		     Class.forName("oracle.jdbc.driver.OracleDriver");  
			
			  Connection con=DriverManager.getConnection(
			 "jdbc:oracle:thin:@localhost:1521:XE","BANCAZAHA","admin");
			 
			  //step3 create the statement object 
			  
		return con;
}
	public void getInfo() throws SQLException, ClassNotFoundException {
		Connection con = getcon();
		Statement stmt=con.createStatement();	  
		ResultSet rs=stmt.executeQuery("select * from Persoane");
		 // while(rs.next())
		   //System.out.println(rs.getInt(1) + " "+ rs.getString(2));
		  
		  con.close(); 
	}

}
