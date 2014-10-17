package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		int idOfRequester = -3;
		String userNameOfRequestee = "Smith";
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(idOfRequester, userNameOfRequestee);
		cmd.execute();
		Person result = cmd.getResult();
		ArrayList<Person> pendingFriends = new ArrayList<Person>();
		pendingFriends = result.myPendingFriends.getPendingFriendList();
		//Person Matthew = new Person("KujawskiUserName", "KujawskiPassword", "KujawskiDisplayName", -1)  
		//This is what MockPersonRowDataGateway will return (above) and will add him to the requesters pending friend list
		assertEquals("KujawskiUserName", pendingFriends.get(0).getUserName());
		assertEquals("KujawskiPassword", pendingFriends.get(0).getPassword());
		assertEquals("KujawskiDisplayName", pendingFriends.get(0).getDisplayName());
	}

}
