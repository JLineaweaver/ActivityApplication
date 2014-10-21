package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommandToGetPendingIncomingFriendList 
{

	@Test
	public void testInitialization() 
	{
		int uID = -1;
		CommandToGetPendingIncomingFriendList cmd = new CommandToGetPendingIncomingFriendList(uID);
		assertEquals(-1, cmd.getUserID());
	}
	
	
	/**
	 * Doesn't actually test anything
	 */
//	@Test
//	public void testCommandFunctionality()
//	{
//		String uName = "Johnny";
//		String pw = "JPassword";
//		String dName = "JohnnyJohn";
//		CommandToCreateUser createCmd = new CommandToCreateUser(uName, pw, dName);
//		
//		UnitOfWork.newCurrent();
//		createCmd.execute();
//		Person person = createCmd.getResult();
//		
//		int uID = person.getUserID();
//		CommandToGetPendingIncomingFriendList cmd = new CommandToGetPendingIncomingFriendList(uID);
//		cmd.execute();
//		String list = cmd.getResult();
//		assertEquals("", list);
//	}
//	
//	@Test
//	public void testAddPendingFriend()
//	{
//		Person person = new Person("George", "pw", "GeorgyGeorge", -3);
//		Person friend = new Person("Fred" , "pw", "FreddyFred", -8);
//		person.myIncomingPendingFriends.incomingPendingFriends.add(friend);
//		
//		int uID = person.getUserID();
//		CommandToGetPendingIncomingFriendList cmd = new CommandToGetPendingIncomingFriendList(uID);
//		cmd.execute();
//		String list = cmd.getResult();
//		assertEquals("Fred", list);
//	}
}
