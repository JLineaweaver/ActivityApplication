package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Test;

import dataMappers.MyThreadLocal;

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
		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();

		CommandToSelectUser cmd3 = new CommandToSelectUser("testPerson1", "testPerson1PW");
		MyThreadLocal.unset();
		
		cmd3.execute();
		Person selectedPerson = cmd3.getResult();
		Person testPerson2 = new Person("testPerson2", "testPerson2PW", "testPerson2DN", -1);
		
		CommandToMakeFriendRequest cmd2 = new CommandToMakeFriendRequest(selectedPerson.getUserID(), testPerson2.getUserName());
		cmd2.execute();
		
		assertEquals(1, selectedPerson.myOutgoingPendingFriends.outgoingPendingFriends.size());
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
	
	@Test
	public void testMultiplePendingFrinds()
	{
		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();

		CommandToSelectUser cmd3 = new CommandToSelectUser("testPerson1", "testPerson1PW");
		MyThreadLocal.unset();
		
		cmd3.execute();
		Person selectedPerson = cmd3.getResult();
		Person testPerson2 = new Person("testPerson2", "testPerson2PW", "testPerson2DN", -1);
		Person testPerson3 = new Person("testPerson3", "testPerson3PW", "testPerson3DN", -1);
		
		CommandToMakeFriendRequest cmd2 = new CommandToMakeFriendRequest(selectedPerson.getUserID(), testPerson2.getUserName());
		CommandToMakeFriendRequest cmd1 = new CommandToMakeFriendRequest(selectedPerson.getUserID(), testPerson3.getUserName());
		cmd2.execute();
		cmd1.execute();
		
		assertEquals(2, selectedPerson.myOutgoingPendingFriends.outgoingPendingFriends.size());
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
}
