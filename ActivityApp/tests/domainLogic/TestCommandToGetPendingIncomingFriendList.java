package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dataMappers.MyThreadLocal;

public class TestCommandToGetPendingIncomingFriendList 
{
	

	@Test
	public void testPendingFriend()
	{		
		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();

		CommandToSelectUser cmd3 = new CommandToSelectUser("testPerson1", "testPerson1PW");
		MyThreadLocal.unset();
		
		cmd3.execute();
		Person selectedPerson = cmd3.getResult();
		Person testPerson2 = new Person("testPerson2", "testPerson2PW", "testPerson2DN", -1);
			
		assertEquals(0, selectedPerson.myOutgoingPendingFriends.outgoingPendingFriends.size());
		
		selectedPerson.myIncomingPendingFriends.incomingPendingFriends.add(testPerson2); //manually added a person to the list
		CommandToGetPendingIncomingFriendList pendingIncomingCmd = new CommandToGetPendingIncomingFriendList(selectedPerson.getUserID());
		pendingIncomingCmd.execute();
		String result = pendingIncomingCmd.toString();
		
		assertEquals("testPerson2DN", result);
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
	
	@Test
	public void testMultiplePendingFriends()
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
		
		assertEquals(0, selectedPerson.myOutgoingPendingFriends.outgoingPendingFriends.size());
		
		selectedPerson.myIncomingPendingFriends.incomingPendingFriends.add(testPerson2); //manually added a person to the list
		selectedPerson.myIncomingPendingFriends.incomingPendingFriends.add(testPerson3);
		CommandToGetPendingIncomingFriendList pendingIncomingCmd = new CommandToGetPendingIncomingFriendList(selectedPerson.getUserID());
		pendingIncomingCmd.execute();
		String result = pendingIncomingCmd.toString();
		
		assertEquals("testPerson2DN,testPerson3DN", result);
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
}
