package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

import dataMappers.MyThreadLocal;

public class TestCommandToPersistChanges {

	@Test
	public void test() 
	{
		/**
		 * Mocked uop
		 */
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
		
		assertEquals(2, unit.getDirtyObjects().size());
		CommandToPersistChanges cmd1 = new CommandToPersistChanges();
		unit.emptyArrayLists();
		
		assertEquals(0, unit.getDirtyObjects().size());
		SelectedPerson.resetSelectedPerson();
	}
}
