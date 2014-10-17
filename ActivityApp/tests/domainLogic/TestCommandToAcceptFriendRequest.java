package domainLogic;

import static org.junit.Assert.*;

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
		Person person = new Person("uName", "pw", "dName", -1);
		Person person2 = new Person("uName2", "pw2", "dName2", -2);
		CommandToAcceptFriendRequest cmd = new CommandToAcceptFriendRequest(-2, "uName");
		
	}

}
