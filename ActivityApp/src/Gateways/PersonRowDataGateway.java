package Gateways;



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
	
	String url = "lsagroup2.cbzhjl6tpflt.us-east-1.rds.amazonaws.com:3306";
	String user = "jl3828";
	String password = "redfred78";

	public PersonRowDataGateway() throws SQLException {
	
		//con = DriverManager.getConnection(url,user,password);
		//Currently can't connect
		
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
