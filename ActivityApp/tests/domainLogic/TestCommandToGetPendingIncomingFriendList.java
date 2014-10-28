package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestCommandToGetPendingIncomingFriendList 
{
	

	@Test
	public void testPendingFriend()
	{		
		String uName = "Johnny";
		String pw = "JPassword";
		String dName = "JohnnyJohn";
		CommandToCreateUser createCmd = new CommandToCreateUser(uName, pw, dName);
		
		String uName2 = "George";
		String pw2 = "GPassword";
		String dName2 = "GeorgyGeorge";
		CommandToCreateUser createCmd2 = new CommandToCreateUser(uName2, pw2, dName2);
		
		UnitOfWork.newCurrent();
		createCmd.execute();
		createCmd2.execute();
		
		Person person1 = createCmd.getResult();
		Person person2 = createCmd2.getResult();
		
		assertEquals(0, person1.myIncomingPendingFriends.incomingPendingFriends.size());
		
		SelectedPerson.initializeInstance(person1);
		person1.myIncomingPendingFriends.incomingPendingFriends.add(person2); //manually added a person to the list
		CommandToGetPendingIncomingFriendList pendingIncomingCmd = new CommandToGetPendingIncomingFriendList(person1.getUserID());
		pendingIncomingCmd.execute();
		String result = pendingIncomingCmd.toString();
		
		assertEquals("George", result);
		
		Person.emptyMockDB();
		SelectedPerson.resetInstance();
	}
	
	@Test
	public void testMultiplePendingFriends()
	{
		String uName = "Johnny";
		String pw = "JPassword";
		String dName = "JohnnyJohn";
		CommandToCreateUser createCmd = new CommandToCreateUser(uName, pw, dName);
		
		String uName2 = "George";
		String pw2 = "GPassword";
		String dName2 = "GeorgyGeorge";
		CommandToCreateUser createCmd2 = new CommandToCreateUser(uName2, pw2, dName2);
		
		UnitOfWork.newCurrent();
		createCmd.execute();
		createCmd2.execute();
		
		Person person1 = createCmd.getResult();
		Person person2 = createCmd2.getResult();
		Person person3 = createCmd2.getResult();
		
		person1.myIncomingPendingFriends.incomingPendingFriends.add(person2); //manually added a person to the list
		person1.myIncomingPendingFriends.incomingPendingFriends.add(person3); //manually added a person to the list
		
		SelectedPerson.initializeInstance(person1); // simulates selecting a person
		CommandToGetPendingIncomingFriendList pendingIncomingCmd = new CommandToGetPendingIncomingFriendList(person1.getUserID());
		pendingIncomingCmd.execute();
		String result = pendingIncomingCmd.toString();
		
		assertEquals("George,George", result);
		
		Person.emptyMockDB();
		SelectedPerson.resetInstance();
	}
}
