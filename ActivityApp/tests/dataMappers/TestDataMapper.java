package dataMappers;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import domainLogic.Friend;
import domainLogic.Person;

/**
 * @author josh
 *
 */
public class TestDataMapper
{

	/**
	 * Simple test testing the multiple findPerson methods
	 * @throws SQLException
	 */
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
	
	@Test
	public void test2() throws SQLException
	{
		DataMapper pdm = MyThreadLocal.get();
		Friend f = pdm.findFriend(5);
		Person p = pdm.findDBPerson(2);
		assertTrue(!f.getUserName().equals(""));
		assertTrue(!p.getUserName().equals(""));
		
	}

	@Test
	public void test3() throws SQLException
	{
		DataMapper pdm = MyThreadLocal.get();
		Person p = new Person();
		pdm.createPerson(p);
		p.setDisplayName("d");
		pdm.storePerson(p);
		
	}
}
