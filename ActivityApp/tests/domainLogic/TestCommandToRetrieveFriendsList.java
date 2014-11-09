package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dataMappers.MyThreadLocal;

public class TestCommandToRetrieveFriendsList {

	@Test
	public void testRetrieve() 
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
		
		CommandToRetrieveFriendList cmd1 = new CommandToRetrieveFriendList(selectedPerson.getUserID());
		cmd1.execute();
		assertEquals("testPerson2DN", cmd1.toString());
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}

}
