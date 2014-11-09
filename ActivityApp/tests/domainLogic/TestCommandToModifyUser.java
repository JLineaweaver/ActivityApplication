package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

import dataMappers.MyThreadLocal;

public class TestCommandToModifyUser
{

	@Test
	public void testModify()
	{
		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();

		CommandToSelectUser cmd3 = new CommandToSelectUser("testPerson1", "testPerson1PW");
		MyThreadLocal.unset();
		
		cmd3.execute();
		Person selectedPerson = cmd3.getResult();

		assertEquals("testPerson1DN", selectedPerson.getDisplayName());
		CommandToModifyUser cmd = new CommandToModifyUser(selectedPerson.getUserID(), "Matt170");
		cmd.execute();
		assertEquals("Matt170", selectedPerson.getDisplayName());
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}

}