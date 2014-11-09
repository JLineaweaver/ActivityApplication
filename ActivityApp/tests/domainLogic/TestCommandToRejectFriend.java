package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

import dataMappers.MyThreadLocal;

public class TestCommandToRejectFriend {

	@Test
	public void test() 
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
		
		CommandToRejectFriendRequest cmd = new CommandToRejectFriendRequest(selectedPerson.getUserID(), testPerson2.getUserName());
		cmd.execute();
		assertEquals(0, testPerson2.myIncomingPendingFriends.incomingPendingFriends.size());
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}

}
