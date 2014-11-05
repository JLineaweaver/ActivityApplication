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
		
		MockUnitOfWork.newCurrent();
		
		cmd.testExecute();
		Person person = cmd.getTestResult();
		assertEquals(true, MockUnitOfWork.getCurrent().getNewObjects().contains(person));		
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
		
		MockUnitOfWork.newCurrent();
		
		cmd.testExecute();
		cmd2.testExecute();
		Person person = cmd.getTestResult();
		Person person2 = cmd2.getTestResult();
		
		assertEquals(true, MockUnitOfWork.getCurrent().getNewObjects().contains(person));
		assertEquals(true, MockUnitOfWork.getCurrent().getNewObjects().contains(person2));
		
		Person.emptyMockDB();
	}
	
	@Test
	public void testMakeDirtyObject()
	{
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person2 = new Person("John", "","Jonny", 2);
		SelectedPerson.initializeSelectedPerson(person1); //simulates selecting a person
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(person1.getUserID(), person2.getUserName());
		
		MockUnitOfWork.newCurrent();
		
		cmd.testExecute();
		Person result = cmd.getTestResult();
		assertEquals(true, MockUnitOfWork.getCurrent().getDirtyObjects().contains(result));
		
		Person.emptyMockDB();
		SelectedPerson.resetSelectedPerson();
	}
	
	@Test
	public void testMultipleDirtyObjects()
	{
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person2 = new Person("John", "","Jonny", 2);
		SelectedPerson.initializeSelectedPerson(person1); //simulates selecting a person
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(person1.getUserID(), person2.getUserName());
		
		MockUnitOfWork.newCurrent();
		cmd.testExecute();
		
		Person person3 = new Person("Bob", "","BobbyB", 3);
		Person person4 = new Person("George", "","Georgie", 4);
		CommandToMakeFriendRequest cmd2 = new CommandToMakeFriendRequest(person3.getUserID(), person4.getUserName());
		
		SelectedPerson.resetSelectedPerson();
		SelectedPerson.initializeSelectedPerson(person3); //simulates selecting a person
		cmd2.testExecute();
		
		Person result = cmd.getTestResult();
		Person result2 = cmd.getTestResult();
		assertEquals(true, MockUnitOfWork.getCurrent().getDirtyObjects().contains(result));
		assertEquals(true, MockUnitOfWork.getCurrent().getDirtyObjects().contains(result2));
		
		Person.emptyMockDB();
		SelectedPerson.resetSelectedPerson();
	}
	
	@Test
	public void testNewToDirtyObject()
	{
		MockUnitOfWork work = new MockUnitOfWork();
		work.newCurrent();
		
		Person person = new Person("Johnny", "JPassword", "JohnnyJohn", -1);
		person.testMarkNew(person); // Manually added person to the new objects
		
		assertEquals(true, work.getCurrent().getNewObjects().contains(person));
		
		person.testMarkDirty(person);
		assertEquals(false, work.getCurrent().getNewObjects().contains(person));
		assertEquals(true, work.getCurrent().getDirtyObjects().contains(person));
		
		Person.emptyMockDB();
		
	}
	
	@Test
	public void testNewToRemovedObject()
	{
		MockUnitOfWork work = new MockUnitOfWork();
		work.newCurrent();
		
		Person person = new Person("Johnny", "JPassword", "JohnnyJohn", -1);
		person.testMarkNew(person); // Manually added person to the new objects
				
		person.testMarkRemoved(person);
		assertEquals(false, work.getCurrent().getNewObjects().contains(person));
		assertEquals(true, work.getCurrent().getRemovedObjects().contains(person));
	}
	
	@Test
	public void testDirtyToRemoved()
	{
		MockUnitOfWork work = new MockUnitOfWork();
		work.newCurrent();
		
		Person person = new Person("Johnny", "JPassword", "JohnnyJohn", -1);
		person.testMarkDirty(person); // Manually added person to the new objects
		assertEquals(true, work.getCurrent().getDirtyObjects().contains(person));
				
		person.testMarkRemoved(person);
		assertEquals(false, work.getCurrent().getDirtyObjects().contains(person));
		assertEquals(true, work.getCurrent().getRemovedObjects().contains(person));
		
		Person.emptyMockDB();
	}
}
