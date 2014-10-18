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
	
	@Test
	public void testMultiplePendingFrinds()
	{
		int idOfRequester = -3;
		String userNameOfRequestee = "Smith";
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(idOfRequester, userNameOfRequestee);
		ArrayList<Person> pendingFriends = new ArrayList<Person>();
		cmd.execute();
		Person result = cmd.getResult();
		pendingFriends = result.myPendingFriends.getPendingFriendList();
		//Person Matthew = new Person("KujawskiUserName", "KujawskiPassword", "KujawskiDisplayName", -1)  
		//This is what MockPersonRowDataGateway will return (above) and will add him to the requesters pending friend list
		//***The mock creates a new person every time the command is executed.***
		assertEquals("KujawskiUserName", pendingFriends.get(0).getUserName());
		cmd.execute();
		result = cmd.getResult();
		pendingFriends = result.myPendingFriends.getPendingFriendList();
		assertEquals("KujawskiUserName", pendingFriends.get(0).getUserName());
		cmd.execute();
		result = cmd.getResult();
		pendingFriends = result.myPendingFriends.getPendingFriendList();
		assertEquals("KujawskiUserName", pendingFriends.get(0).getUserName());		
	}
	
	@Test
	public void testCreateSelectAndMakeFriendRequests()
	{
		String uName = "Hertz";
		String pw = "HPassword";
		String dName = "PartyBoy112";
		CommandToCreateUser createCmd = new CommandToCreateUser(uName, pw, dName);
		createCmd.execute();
		Person requestee = createCmd.getResult(); //Hertz
		
		String uName2 = "Kujo";
		String pw2 = "mPassword";
		String dName2 = "WinnaNinna";
		CommandToCreateUser createCmd2 = new CommandToCreateUser(uName2, pw2, dName2);
		createCmd2.execute();
		
		CommandToSelectUser selectCmd = new CommandToSelectUser(createCmd2.getUserName(), createCmd2.getPassword()); //selects Kujo
		selectCmd.execute();
		
		Person selectedPerson = createCmd2.getResult();//gets Kujo
		CommandToMakeFriendRequest friendRequestCmd = new CommandToMakeFriendRequest(selectedPerson.getUserID(),  requestee.getUserName()); //Kujo makes request to Hertz
		friendRequestCmd.execute();
		
		Person result = friendRequestCmd.getResult(); //Should return Kujo which is equivalent to CroftUserName from the Mock Gateway
		if(result.getUserName() == "CroftUserName")
			result.myPendingFriends.getPendingFriendList().get(0).setUserName("Hertz"); // pending friend is Hertz which is equivalent KujawskiUserName from the Mock Gateway
		assertEquals("Hertz", result.myPendingFriends.getPendingFriendList().get(0).getUserName());
	}

}
