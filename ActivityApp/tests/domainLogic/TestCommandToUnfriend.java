package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

import dataMappers.MyThreadLocal;

public class TestCommandToUnfriend 
{

	@Test
	public void testUnfriend() 
	{
		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();

		CommandToSelectUser cmd3 = new CommandToSelectUser("testPerson1", "testPerson1PW");
		MyThreadLocal.unset();
		
		cmd3.execute();
		Person selectedPerson = cmd3.getResult();
		Person testPerson2 = new Person("testPerson2", "testPerson2PW", "testPerson2DN", -1);
		
		CommandToAcceptFriendRequest cmd2 = new CommandToAcceptFriendRequest(selectedPerson.getUserID(), testPerson2.getUserName());
		selectedPerson.myIncomingPendingFriends.incomingPendingFriends.add(testPerson2);
		cmd2.execute();
		
		CommandToUnFriend un = new CommandToUnFriend(selectedPerson.getUserID(), testPerson2.getUserName());
		un.execute();
		assertEquals(0, selectedPerson.getNumberOfFriends());
		assertEquals("testPerson1", un.getResult().getUserName());
		assertEquals(0, un.getResult().myFriends.getFriendList().size());
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();

;
	}
	
	@Test
	public void testUnFriendBothWays()
	{
		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();

		CommandToSelectUser cmd3 = new CommandToSelectUser("testPerson1", "testPerson1PW");
		MyThreadLocal.unset();
		
		cmd3.execute();
		Person selectedPerson = cmd3.getResult();
		Person testPerson2 = new Person("testPerson2", "testPerson2PW", "testPerson2DN", -1);
		
		CommandToAcceptFriendRequest cmd2 = new CommandToAcceptFriendRequest(selectedPerson.getUserID(), testPerson2.getUserName());
		selectedPerson.myIncomingPendingFriends.incomingPendingFriends.add(testPerson2);
		cmd2.execute();
		
		CommandToSelectUser cmd4 = new CommandToSelectUser("testPerson2", "testPerson2PW");
		cmd4.execute();
		
		selectedPerson = cmd4.getResult();
		
		CommandToUnFriend un = new CommandToUnFriend(selectedPerson.getUserID(), "testPerson1");
		un.execute();
		assertEquals(0, testPerson2.getNumberOfFriends());
		assertEquals("testPerson2", un.getResult().getUserName());
		assertEquals(0, un.getResult().myFriends.getFriendList().size());
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
}
