package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestCommandToGetPendingOutgoingFriendList {

	@Test
	public void testGetEmptyOutgoingFriendList() 
	{
		Person person = new Person("Matt", "pw", "MattyMatt", -2);
		SelectedPerson.initializeSelectedPerson(person); //simulates selecting a person
		CommandToGetPendingOutgoingFriendList cmd = new CommandToGetPendingOutgoingFriendList(person.getUserID());
		cmd.testExecute();
		assertEquals(0, cmd.getTestResult().size());
		
		Person.emptyMockDB();
		SelectedPerson.resetSelectedPerson();
	}
	
	@Test
	public void testGetOutgoingPendingFriendList()
	{
		Person person = new Person("Matt", "pw", "MattyMatt", -2);
		Person person2 = new Person("Fred", "pw", "FreddyFred", -9);
		SelectedPerson.initializeSelectedPerson(person);//Simulates selecting a person
		person.myOutgoingPendingFriends.outgoingPendingFriends.add(person2); //manually added a friend to the list
		CommandToGetPendingOutgoingFriendList cmd = new CommandToGetPendingOutgoingFriendList(person.getUserID());
		cmd.testExecute();
		assertEquals(person2, cmd.getTestResult().get(0));
		
		Person.emptyMockDB();
		SelectedPerson.resetSelectedPerson();
	}
	
	@Test
	public void testGetMultipleOutgoingPendingFriendList()
	{
		Person person = new Person("Matt", "pw", "MattyMatt", -2);
		Person person2 = new Person("Fred", "pw", "FreddyFred", -9);
		Person person3 = new Person("Josh", "pw", "JoshyJosh", -5);
		SelectedPerson.initializeSelectedPerson(person); //simulates selecting a person
		person.myOutgoingPendingFriends.outgoingPendingFriends.add(person2); //manually added a friend to the list
		person.myOutgoingPendingFriends.outgoingPendingFriends.add(person3);
		CommandToGetPendingOutgoingFriendList cmd = new CommandToGetPendingOutgoingFriendList(person.getUserID());
		cmd.testExecute();
		assertEquals(person2, cmd.getTestResult().get(0));
		assertEquals(person3, cmd.getTestResult().get(1));
		
		Person.emptyMockDB();
		SelectedPerson.resetSelectedPerson();
	}
	
	@Test
	public void testOutgoingPendingFriendListString()
	{
		{
			Person person = new Person("Matt", "pw", "MattyMatt", -2);
			Person person2 = new Person("Fred", "pw", "FreddyFred", -9);
			Person person3 = new Person("Josh", "pw", "JoshyJosh", -5);
			SelectedPerson.initializeSelectedPerson(person); //simulates selecting a person
			
			person.myOutgoingPendingFriends.outgoingPendingFriends.add(person2); //manually added a friend to the list
			person.myOutgoingPendingFriends.outgoingPendingFriends.add(person3);
			
			CommandToGetPendingOutgoingFriendList cmd = new CommandToGetPendingOutgoingFriendList(person.getUserID());
			cmd.testExecute();
		
			assertEquals("Fred,Josh", cmd.toString());
			
			Person.emptyMockDB();
			SelectedPerson.resetSelectedPerson();
		}
	}
}
