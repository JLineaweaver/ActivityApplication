package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestUnitOfWork {

	@Test
	public void testInitialization() 
	{
		String uName = "Johnny";
		String pw = "JPassword";
		String dName = "JohnnyJohn";
		CommandToCreateUser cmd = new CommandToCreateUser(uName, pw, dName);
		
		UnitOfWork.newCurrent();
		
		cmd.execute();
		Person person = cmd.getResult();
		assertEquals(true, UnitOfWork.getCurrent().getNewObjects().contains(person));		
	}
	
	@Test
	public void testCreateTwoPeople()
	{
		String uName = "Johnny";
		String pw = "JPassword";
		String dName = "JohnnyJohn";
		CommandToCreateUser cmd = new CommandToCreateUser(uName, pw, dName);
		
		String uName2 = "Fred";
		String pw2 = "fPassword";
		String dName2 = "FreddyFred";
		CommandToCreateUser cmd2 = new CommandToCreateUser(uName2, pw2, dName2);
		
		UnitOfWork.newCurrent();
		
		cmd.execute();
		cmd2.execute();
		Person person = cmd.getResult();
		Person person2 = cmd2.getResult();
		
		assertEquals(true, UnitOfWork.getCurrent().getNewObjects().contains(person));
		assertEquals(true, UnitOfWork.getCurrent().getNewObjects().contains(person2));
	}
	
	@Test
	public void testMakeDirtyObject()
	{
		int idOfRequester = -3;
		String userNameOfRequestee = "Smith";
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(idOfRequester, userNameOfRequestee);
		
		UnitOfWork.newCurrent();
		
		cmd.execute();
		Person result = cmd.getResult();
		assertEquals(true, UnitOfWork.getCurrent().getDirtyObjects().contains(result));
	}
	
	@Test
	public void testMultipleDirtyObjects()
	{
		int idOfRequester = -3;
		String userNameOfRequestee = "Smith";
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(idOfRequester, userNameOfRequestee);
		
		int idOfRequester2 = -4;
		String userNameOfRequestee2 = "Ben";
		CommandToMakeFriendRequest cmd2 = new CommandToMakeFriendRequest(idOfRequester2, userNameOfRequestee2);
		
		UnitOfWork.newCurrent();
		
		cmd.execute();
		cmd2.execute();
		Person result = cmd.getResult();
		Person result2 = cmd.getResult();
		assertEquals(true, UnitOfWork.getCurrent().getDirtyObjects().contains(result));
		assertEquals(true, UnitOfWork.getCurrent().getDirtyObjects().contains(result2));
	}
	
	@Test
	public void testNewToDirtyObject()
	{
		UnitOfWork work = new UnitOfWork();
		work.newCurrent();
		
		Person person = new Person("Johnny", "JPassword", "JohnnyJohn", -1);
		person.markNew(person); // Manually added person to the new objects
		
		assertEquals(true, work.getCurrent().getNewObjects().contains(person));
		
		person.markDirty(person);
		assertEquals(false, work.getCurrent().getNewObjects().contains(person));
		assertEquals(true, work.getCurrent().getDirtyObjects().contains(person));
		
	}
	
	@Test
	public void testNewToRemovedObject()
	{
		UnitOfWork work = new UnitOfWork();
		work.newCurrent();
		
		Person person = new Person("Johnny", "JPassword", "JohnnyJohn", -1);
		person.markNew(person); // Manually added person to the new objects
				
		person.markRemoved(person);
		assertEquals(false, work.getCurrent().getNewObjects().contains(person));
		assertEquals(true, work.getCurrent().getRemovedObjects().contains(person));
	}
	
	@Test
	public void testDirtyToRemoved()
	{
		UnitOfWork work = new UnitOfWork();
		work.newCurrent();
		
		Person person = new Person("Johnny", "JPassword", "JohnnyJohn", -1);
		person.markDirty(person); // Manually added person to the new objects
		assertEquals(true, work.getCurrent().getDirtyObjects().contains(person));
				
		person.markRemoved(person);
		assertEquals(false, work.getCurrent().getDirtyObjects().contains(person));
		assertEquals(true, work.getCurrent().getRemovedObjects().contains(person));
	}
}
