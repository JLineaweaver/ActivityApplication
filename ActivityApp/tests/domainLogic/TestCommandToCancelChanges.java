package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

import dataMappers.MyThreadLocal;

public class TestCommandToCancelChanges {

	@Test
	public void testCommandToCancelChanges()
	{
		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();

		CommandToSelectUser cmd3 = new CommandToSelectUser("testPerson1", "testPerson1PW");
		MyThreadLocal.unset();
		cmd3.execute();
		Person selectedPerson = cmd3.getResult();
		
		assertEquals(0, selectedPerson.getNumberOfFriends());
		assertEquals(0, selectedPerson.myIncomingPendingFriends.incomingPendingFriends.size());
		
		Person testPerson2 = new Person("testPerson2", "testPerson2PW", "testPerson2DN", -1);
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(selectedPerson.getUserID(), testPerson2.getUserName());
		
		cmd.execute();
		Person result = cmd.getResult();
		
		assertEquals(1, selectedPerson.myOutgoingPendingFriends.outgoingPendingFriends.size());
		assertEquals(1, result.myOutgoingPendingFriends.outgoingPendingFriends.size());

		assertEquals(2, unit.getDirtyObjects().size());
		CommandToCancelChanges cmd1 = new CommandToCancelChanges();
		cmd1.execute();
		
		assertEquals(0, unit.getDirtyObjects().size());
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
}
