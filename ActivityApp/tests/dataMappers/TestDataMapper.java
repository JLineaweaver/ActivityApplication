package dataMappers;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import domainLogic.Person;

public class TestDataMapper
{

	@Test
	public void test() throws SQLException
	{
		DataMapper pdm = MyThreadLocal.get(); 
		Person p = pdm.findPerson(2);
		assertTrue(p.getUserName().equals("Josh"));
		assertTrue(p.getPassword().equals("Woot"));
		Person f = pdm.findPerson("Frank","beardy");
		assertTrue(f.getUserName().equals("Frank"));
		assertTrue(f.getPassword().equals("beardy"));
		p.UnFriend(p.getUserID(), f.getUserName());
		//p.setPassword("Woot");
		pdm.storePerson(p);
	}

}
