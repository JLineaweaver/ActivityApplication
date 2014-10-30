package gateways;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domainLogic.Person;

public class PersonRowDataGateway
{
	int id = -1;
	String username = "";
	String password = "";
	
	Connection con = null;
	ResultSet rs = null;
	
	 //lsagroup2.cbzhjl6tpflt.us-east-1.rds.amazonaws.com
	String db = "fitness2";
	String url = "lsagroup2.cbzhjl6tpflt.us-east-1.rds.amazonaws.com";
	String dbUser = "lsagroup2";
	String dbPassword = "lsagroup2";

	public PersonRowDataGateway(int id) throws SQLException {
	
		try {
		String connectFormat = "jdbc:mysql://%s/%s?user=%s&password=%s";
		String connectURL = String.format(connectFormat, url, db, dbUser, dbPassword);
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(connectURL);
		}
		catch (Exception e) {
			
		}
		this.id = id;
		//Currently can't connect
		
	}
	
	public PersonRowDataGateway(String username, String password) throws SQLException {
		con = DriverManager.getConnection(url,dbUser,dbPassword);
	}
	
	
	
	public ResultSet findPerson() {
		ResultSet rs = null;
		if(id > -1) {
		try
		{
			String sql = "SELECT * FROM Person P WHERE P.userID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			System.out.println(rs);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else if(!username.equals("") && password.equals("")) {
			try
			{
				String sql = "SELECT * FROM Person P WHERE P.userName = " + username + "P.password = " + password;
				PreparedStatement ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				System.out.println(rs);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public void addPerson(Person p) {
		//String username = p.getUsername();
		//String password = p.getPassword();
		//String displayName = p.getDisplayName();
		
		
	}
}
