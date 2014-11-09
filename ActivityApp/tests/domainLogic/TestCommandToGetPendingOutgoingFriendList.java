package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dataMappers.MyThreadLocal;

public class TestCommandToGetPendingOutgoingFriendList {

	@Test
	public void testGetEmptyOutgoingFriendList() 
	{
		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();

		CommandToSelectUser cmd3 = new CommandToSelectUser("testPerson1", "testPerson1PW");
		MyThreadLocal.unset();
		
		cmd3.execute();
		Person selectedPerson = cmd3.getResult();
			
		assertEquals(0, selectedPerson.myOutgoingPendingFriends.outgoingPendingFriends.size());
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
	
	@Test
	public void testGetOutgoingPendingFriendList()
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
		
		selectedPerson.myOutgoingPendingFriends.outgoingPendingFriends.add(testPerson2); //manually added a person to the list
		CommandToGetPendingOutgoingFriendList pendingOutgoingCmd = new CommandToGetPendingOutgoingFriendList(selectedPerson.getUserID());
		pendingOutgoingCmd.execute();
		String result = pendingOutgoingCmd.toString();
		
		assertEquals("testPerson2DN", result);
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
	
	@Test
	public void testGetMultipleOutgoingPendingFriendList()
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
		
		selectedPerson.myOutgoingPendingFriends.outgoingPendingFriends.add(testPerson2); //manually added a person to the list
		selectedPerson.myOutgoingPendingFriends.outgoingPendingFriends.add(testPerson3); //manually added a person to the list
		CommandToGetPendingOutgoingFriendList pendingOutgoingCmd = new CommandToGetPendingOutgoingFriendList(selectedPerson.getUserID());
		pendingOutgoingCmd.execute();
		String result = pendingOutgoingCmd.toString();
		
		assertEquals("testPerson2DN,testPerson3DN", result);
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
}
