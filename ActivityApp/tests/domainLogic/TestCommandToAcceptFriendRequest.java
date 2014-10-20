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
	public void testAcceptFriendRequest()
	{
		int uIDOfRequestee = -1;// Person("CroftUserName", "CroftPassword", "CroftDisplayName", -1)
		String uName = "userNameOfRequester"; // Person("KujawskiUserName", "KujawskiPassword", "KujawskiDisplayName", -1)
		CommandToAcceptFriendRequest cmd = new CommandToAcceptFriendRequest(uIDOfRequestee, uName);
		cmd.execute();
		Person result = cmd.getResult(); // This is the requestee
		ArrayList<Person> friends = new ArrayList<Person>();
		friends = result.myFriends.getFriendList(); //friends of the requestee
		assertEquals("KujawskiUserName", friends.get(0).getUserName());
		assertEquals("CroftUserName", friends.get(0).myFriends.getFriendList().get(0).getUserName());	
	}
	
	@Test
	public void testMultipleFriendRequests()
	{
		int uIDOfRequestee = -1;// Person("CroftUserName", "CroftPassword", "CroftDisplayName", -1)
		String uName = "userNameOfRequester"; // Person("KujawskiUserName", "KujawskiPassword", "KujawskiDisplayName", -1)
		String uName2 = "userNameOfRequester2"; // Person("KujawskiUserName", "KujawskiPassword", "KujawskiDisplayName", -1)
		CommandToAcceptFriendRequest cmd = new CommandToAcceptFriendRequest(uIDOfRequestee, uName);
		CommandToAcceptFriendRequest cmd2 = new CommandToAcceptFriendRequest(uIDOfRequestee, uName2);
		ArrayList<Person> friends = new ArrayList<Person>();
		cmd.execute();
		Person result = cmd.getResult(); // This is the requestee
		friends = result.myFriends.getFriendList(); //friends of the requestee
		assertEquals("KujawskiUserName", friends.get(0).getUserName());
		assertEquals("CroftUserName", friends.get(0).myFriends.getFriendList().get(0).getUserName());	
		cmd2.execute();
		result = cmd.getResult(); // This is the requestee
		friends = result.myFriends.getFriendList(); //friends of the requestee
		assertEquals("KujawskiUserName", friends.get(0).getUserName());
		assertEquals("CroftUserName", friends.get(0).myFriends.getFriendList().get(0).getUserName());
	}
}
