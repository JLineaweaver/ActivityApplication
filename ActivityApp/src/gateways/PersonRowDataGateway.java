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
			e.printStackTrace();
		}
		this.id = id;
		
	}
	
	public PersonRowDataGateway(String username, String password) throws SQLException {
		try {
			String connectFormat = "jdbc:mysql://%s/%s?user=%s&password=%s";
			String connectURL = String.format(connectFormat, url, db, dbUser, dbPassword);
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(connectURL);
			}
			catch (Exception e) {
				
			}
			this.username = username;
			this.password = password;
			try
			{
				String sql = "SELECT * FROM Person P WHERE P.userName = ? AND P.password = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				rs = ps.executeQuery();
				rs.next();
				id = rs.getInt("userID");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public PersonRowDataGateway(String username) throws SQLException {
		try {
			String connectFormat = "jdbc:mysql://%s/%s?user=%s&password=%s";
			String connectURL = String.format(connectFormat, url, db, dbUser, dbPassword);
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(connectURL);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			this.username = username;
			try
			{
				String sql = "SELECT * FROM Person P WHERE P.userName = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				rs = ps.executeQuery();
				rs.next();
				id = rs.getInt("userID");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	public ResultSet findPerson() {
		ResultSet rs = null;
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
		
		return rs;
	}
	
	public int getID() {
		return id;
	}
	
	public void updatePassword(String password) {
		ResultSet rs = null;
		try
		{
			String sql = "UPDATE Person P SET password=? WHERE P.userID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,password);
			ps.setInt(2,id);
			ps.executeUpdate();
			System.out.println(rs);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addPerson(Person p) {
		//String username = p.getUsername();
		//String password = p.getPassword();
		//String displayName = p.getDisplayName();
		
		
	}
}
