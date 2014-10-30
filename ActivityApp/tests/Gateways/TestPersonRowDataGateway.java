package Gateways;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.Test;
import gateways.PersonRowDataGateway;
public class TestPersonRowDataGateway
{

	@Test
	public void test()
	{
		try {
		PersonRowDataGateway prdg = new PersonRowDataGateway(2);
		System.out.println(prdg.findPerson());
		ResultSet rs = prdg.findPerson();
		String username = rs.getString("userName");
		System.out.println("fuckboy >>>" + username);
		assertTrue(prdg.findPerson() != null);
		}
		catch (Exception e) {
			
		}
		
	}

}
