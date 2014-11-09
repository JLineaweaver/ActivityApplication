package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

import dataMappers.DataMapper;

public class TestCommandToSelectUser 
{

	@Test
	public void testInitialization() 
	{
		String uName = "theUserName";
		String pw = "thePassword";
		CommandToSelectUser cmd = new CommandToSelectUser(uName, pw);
		assertEquals("theUserName", cmd.getUserName());
		assertEquals("thePassword", cmd.getPassword());
	}
	
	@Test
	public void testSelectOneFromTwoPeople()
	{
		
		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();

		CommandToSelectUser cmd3 = new CommandToSelectUser("testPerson1", "testPerson1PW");
		cmd3.execute();
		Person selectedPerson = cmd3.getResult();
		assertEquals("testPerson1", selectedPerson.getUserName());	
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
	
	@Test
	public void testSelectOtherFromTwoPeople()
	{
		UnitOfWork unit = new UnitOfWork();
		UnitOfWork.newCurrent();
		unit = UnitOfWork.getCurrent();
		
		CommandToSelectUser cmd3 = new CommandToSelectUser("testPerson2", "testPerson2PW");
		cmd3.execute();
		Person selectedPerson = cmd3.getResult();
		assertEquals("testPerson2", selectedPerson.getUserName());	
		
		SelectedPerson.resetSelectedPerson();
		unit.emptyArrayLists();
	}
}
