package Gateways;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.junit.Test;

import gateways.PersonRowDataGateway;
/**
 * @author josh
 *
 */
public class TestPersonRowDataGateway
{

	/**
	 * 
	 */
	@Test
	public void test()
	{
		
		Connection con;
		String db = "fitness2";
		String url = "lsagroup2.cbzhjl6tpflt.us-east-1.rds.amazonaws.com";
		String dbUser = "lsagroup2";
		String dbPassword = "lsagroup2";
		/**
		 * Constructor
		 */
		try {
				String connectFormat = "jdbc:mysql://%s/%s?user=%s&password=%s";
				String connectURL = String.format(connectFormat, url, db, dbUser, dbPassword);
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(connectURL);
				
		
		PersonRowDataGateway prdg = new PersonRowDataGateway(2,con);
		System.out.println(prdg.findPerson());
		ResultSet rs = prdg.findPerson();
		rs.next();
		assertTrue(prdg.findPerson() != null);
		}
		catch (Exception e) {
			
		}
		
	}

}
