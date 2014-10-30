package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import mockGateways.MockPersonRowDataGateway;

import org.junit.Test;

public class TestCommandToAcceptFriendRequest 
{

	/**
	 * CommandToCreateUser for both Persons. CommandToSelectUser of the user I want. 
	 * CommandToMakeFriendRequest to the other user. CommandToPersistChanges. 
	 * CommandToSelectUser of the other user. CommandToAcceptFriendRequest.
	 * CommandToRetrieveFriendList should be 1.
	 */
	@Test
	public void testInitialization() 
	{
		int uIDOfRequestee = -1;
		String uName = "userNameOfRequester";
		CommandToAcceptFriendRequest cmd = new CommandToAcceptFriendRequest(uIDOfRequestee, uName);
		assertEquals(-1, cmd.getUserIDOfRequestee());
		assertEquals("userNameOfRequester", cmd.getUserNameOfRequester());	
	}
	
	@Test
	public void testFriendRequest()
	{
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person2 = new Person("John", "","Jonny", 2);
		SelectedPerson.initializeSelectedPerson(person1); // simulates creating a person
		
		CommandToAcceptFriendRequest cmd = new CommandToAcceptFriendRequest(person1.getUserID(), person2.getUserName());
		
		UnitOfWork.newCurrent();
		cmd.execute();
		
		assertEquals(1, person1.getNumberOfFriends());
		
		Person.emptyMockDB();
		SelectedPerson.resetSelectedPerson();
	}
	
	@Test
	public void testMultipleFriendRequests()
	{

		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person2 = new Person("John", "","Jonny", 2);
		Person person3 = new Person("George","", "GeorgeyGeorge", 3);
		SelectedPerson.initializeSelectedPerson(person1); //simulates selecting a person
		
		CommandToAcceptFriendRequest cmd = new CommandToAcceptFriendRequest(person1.getUserID(), person2.getUserName());
		CommandToAcceptFriendRequest cmd2 = new CommandToAcceptFriendRequest(person1.getUserID(), person3.getUserName());
		person1.myIncomingPendingFriends.incomingPendingFriends.add(person2);
		person1.myIncomingPendingFriends.incomingPendingFriends.add(person3);
		
		
		UnitOfWork.newCurrent();
		cmd.execute();
		cmd2.execute();
		
		assertEquals(2, person1.getNumberOfFriends());	
		assertEquals(0, person1.myIncomingPendingFriends.incomingPendingFriends.size());
		
		Person.emptyMockDB();
		SelectedPerson.resetSelectedPerson();
	}
	
	@Test
	public void testSelectingMultipleTimesAndAcceptingFriendRequest()
	{
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person2 = new Person("John", "","Jonny", 2);
		Person person3 = new Person("George","", "GeorgeyGeorge", 3);
		
		CommandToAcceptFriendRequest cmd = new CommandToAcceptFriendRequest(person1.getUserID(), person2.getUserName());
		CommandToAcceptFriendRequest cmd2 = new CommandToAcceptFriendRequest(person2.getUserID(), person3.getUserName());
		CommandToAcceptFriendRequest cmd3 = new CommandToAcceptFriendRequest(person1.getUserID(), person3.getUserName());
		person1.myIncomingPendingFriends.incomingPendingFriends.add(person2);
		person2.myIncomingPendingFriends.incomingPendingFriends.add(person3);
		
		
		UnitOfWork.newCurrent();
		
		SelectedPerson.initializeSelectedPerson(person1); //simulates selecting a person
		cmd.execute();
		SelectedPerson.resetSelectedPerson();
		
		SelectedPerson.initializeSelectedPerson(person2); //simulates selecting a person
		cmd2.execute();
		SelectedPerson.resetSelectedPerson();
		
		assertEquals(2, person2.getNumberOfFriends());
		
		SelectedPerson.initializeSelectedPerson(person1); // selected person 1 again
		cmd3.execute();
		
		assertEquals(2, person1.getNumberOfFriends());
		
		Person.emptyMockDB();
		SelectedPerson.resetSelectedPerson();
	}

}
