package gateways;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domainLogic.Person;


public class PersonRowDataGateway
{
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	 //lsagroup2.cbzhjl6tpflt.us-east-1.rds.amazonaws.com
	String url = "lsagroup2.cbzhjl6tpflt.us-east-1.rds.amazonaws.com:3306";
	String dbUser = "lsagroup2";
	String dbPassword = "lsagroup2";

	public PersonRowDataGateway(int id) throws SQLException {
	
		con = DriverManager.getConnection(url,dbUser,dbPassword);
		//Currently can't connect
		
	}
	
	public PersonRowDataGateway(String username, String password) throws SQLException {
		con = DriverManager.getConnection(url,dbUser,dbPassword);
	}
	
	public Person findPerson(String username, String password) {
		
		//SQL to retrieve person
		//return person
		return new Person();	//bad change later
	}
	
	public Person findPerson(int ID) {
		return new Person(); //bad change later
	}
	
	public void addPerson(Person p) {
		//String username = p.getUsername();
		//String password = p.getPassword();
		//String displayName = p.getDisplayName();
		
		
	}
}
