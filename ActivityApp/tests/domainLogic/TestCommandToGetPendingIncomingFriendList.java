package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommandToGetPendingIncomingFriendList {

	@Test
	public void testInitialization() 
	{
		int uID = -1;
		CommandToGetPendingIncomingFriendList cmd = new CommandToGetPendingIncomingFriendList(uID);
		assertEquals(-1, cmd.getUserID());
	}
	
//	@Test
//	public void testCommandFunctionality()
//	{
//		String uName = "Johnny";
//		String pw = "JPassword";
//		String dName = "JohnnyJohn";
//		CommandToCreateUser createCmd = new CommandToCreateUser(uName, pw, dName);
//		createCmd.execute();
//		Person person = createCmd.getResult();
//		int uID = person.getUserID();
//		CommandToGetPendingIncomingFriendList cmd = new CommandToGetPendingIncomingFriendList(uID);
//		cmd.execute();
//		String list = cmd.getResult();
//		assertEquals("", list);
//	}
	
	@Test
	public void testCommandPendingFriends()
	{
		String uName = "Matthew";
		String pw = "password";
		String dName = "MattyMatt3000";
		CommandToCreateUser cmd = new CommandToCreateUser(uName, pw, dName);
		cmd.execute();
		Person person1 = cmd.getResult();
		
		String uName2 = "Johnny";
		String pw2 = "JPassword";
		String dName2 = "JohnnyJohn";
		CommandToCreateUser createCmd = new CommandToCreateUser(uName2, pw2, dName2);
		createCmd.execute();
		Person person2 = createCmd.getResult();
		
		CommandToSelectUser selectCmd = new CommandToSelectUser(person2.getUserName(), person2.getPassword()); //select person2
		selectCmd.execute();
		Person selectedPerson = selectCmd.getResult();
		
		selectedPerson.myPendingFriends.add(person1); // manually added a pending friend (person1)
		int uID = selectedPerson.getUserID();
		
		CommandToGetPendingIncomingFriendList cmd2 = new CommandToGetPendingIncomingFriendList(uID);
		cmd2.execute();
		String list = cmd2.getResult();
		
		assertEquals("Matthew", selectedPerson.myPendingFriends.getPendingFriendList().get(0).getUserName());
	}
}
