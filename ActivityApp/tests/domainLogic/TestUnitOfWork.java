package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dataMappers.MyThreadLocal;

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
		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();

		CommandToSelectUser cmd3 = new CommandToSelectUser("testPerson1", "testPerson1PW");
		cmd3.execute();
		Person selectedPerson = cmd3.getResult();
		Person testPerson2 = new Person("testPerson2", "testPerson2PW", "testPerson2DN", -1);
		
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(selectedPerson.getUserID(), testPerson2.getUserName());
		
		cmd.execute();
		Person result = cmd.getResult();
		assertEquals(true, UnitOfWork.getCurrent().getDirtyObjects().contains(result));
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
	
	
	@Test
	public void testNewToDirtyObject()
	{		
		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();
		
		Person person = new Person("testPerson1", "testPerson1PW", "testPerson1DN", -1);
		person.markNew(person); // Manually added person to the new objects
		
		assertEquals(true, unit.getCurrent().getNewObjects().contains(person));
		
		person.markDirty(person);
		assertEquals(false, unit.getCurrent().getNewObjects().contains(person));
		assertEquals(true, unit.getCurrent().getDirtyObjects().contains(person));
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
		
	}
}
