package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestRetrieveFriendsListCommand {

	@Test
	public void testRetrieve() 
	{
		int uIDOfRequestee = -1;// Person("CroftUserName", "CroftPassword", "CroftDisplayName", -1)
		String uName = "userNameOfRequester"; // Person("KujawskiUserName", "KujawskiPassword", "KujawskiDisplayName", -1)
		CommandToAcceptFriendRequest cmd = new CommandToAcceptFriendRequest(uIDOfRequestee, uName);
		
		UnitOfWork.newCurrent();
		cmd.execute();
		CommandToRetrieveFriendList ret = new CommandToRetrieveFriendList(uIDOfRequestee);
		ret.execute();
		assertEquals("KujawskiDisplayName", ret.getResult());
		
		
	}

}
