package domainLogic;

import static org.junit.Assert.*;
import gateways.PersonRowDataGateway;

import java.sql.SQLException;
import java.util.ArrayList;

import mockGateways.MockPersonRowDataGateway;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataMappers.DataMapper;
import dataMappers.MyThreadLocal;

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
		int uIDOfRequestee = -1;
		String uName = "userNameOfRequester";
		CommandToAcceptFriendRequest cmd = new CommandToAcceptFriendRequest(uIDOfRequestee, uName);
		assertEquals(-1, cmd.getUserIDOfRequestee());
		assertEquals("userNameOfRequester", cmd.getUserNameOfRequester());	
	}
	
	@Test
	public void testFriendRequest()
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
		
		assertEquals(1, selectedPerson.getNumberOfFriends());
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
	
	@Test
	public void testMultipleFriendRequests()
	{

		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();
		
		CommandToSelectUser cmd3 = new CommandToSelectUser("testPerson2", "testPerson2PW");
		cmd3.execute();
		Person selectedPerson = cmd3.getResult();
		
		Person person = new Person("testPerson1", "testPerson1PW", "testPerson1DN", -1);
		Person person3 = new Person("testPerson3", "testPerson3PW", "testPerson3DN", -1);
		
		CommandToAcceptFriendRequest cmd = new CommandToAcceptFriendRequest(selectedPerson.getUserID(), person.getUserName());
		CommandToAcceptFriendRequest cmd2 = new CommandToAcceptFriendRequest(selectedPerson.getUserID(), person3.getUserName());
		
		selectedPerson.myIncomingPendingFriends.incomingPendingFriends.add(person);
		selectedPerson.myIncomingPendingFriends.incomingPendingFriends.add(person3);
		
		cmd.execute();
		cmd2.execute();		
				
		assertEquals(2, selectedPerson.getNumberOfFriends());	
		assertEquals(0, cmd2.getResult().myIncomingPendingFriends.incomingPendingFriends.size());
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
}
