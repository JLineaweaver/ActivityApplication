package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import mockGateways.MockPersonRowDataGateway;

import org.junit.Test;

public class TestCommandToMakeFriendRequest 
{

	@Test
	public void testInitialization() 
	{
		int idOfRequester = -4;
		String userNameOfRequestee = "Sam";
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(idOfRequester, userNameOfRequestee);
		assertEquals(-4, cmd.getIDOfRequester());
		assertEquals("Sam", cmd.getUserNameOfRequestee());
	}
	
	@Test
	public void testOnePendingFriend()
	{
		
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person2 = new Person("John", "","Jonny", 2);
		assertEquals(0, person1.myIncomingPendingFriends.incomingPendingFriends.size());
		
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(person1.getUserID(), person2.getUserName());
		
		UnitOfWork.newCurrent();
		cmd.execute();
		Person result = cmd.getResult();
		
		assertEquals(1, person1.myIncomingPendingFriends.incomingPendingFriends.size());
		assertEquals(1, result.myIncomingPendingFriends.incomingPendingFriends.size());
		Person.emptyMockDB();
	}
	
	@Test
	public void testMultiplePendingFrinds()
	{
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person2 = new Person("John", "","Jonny", 2);
		Person person3 = new Person("George", "","Georgy", 3);
		
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(person1.getUserID(), person2.getUserName());
		CommandToMakeFriendRequest cmd2 = new CommandToMakeFriendRequest(person1.getUserID(), person3.getUserName());
		
		UnitOfWork.newCurrent();
		cmd.execute();
		cmd.execute();
		Person result = cmd.getResult();
		
		assertEquals(2, person1.myIncomingPendingFriends.incomingPendingFriends.size());
		assertEquals(2, result.myIncomingPendingFriends.incomingPendingFriends.size());
		Person.emptyMockDB();
	}
}
