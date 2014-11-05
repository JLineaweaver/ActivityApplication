package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

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
		String uName = "Johnny";
		String pw = "JPassword";
		String dName = "JohnnyJohn";
		CommandToCreateUser cmd = new CommandToCreateUser(uName, pw, dName);
		MockUnitOfWork.newCurrent();
		cmd.testExecute();
		String uName2 = "Matthew";
		String pw2 = "password";
		String dName2 = "MattyMatt3000";
		CommandToCreateUser cmd2 = new CommandToCreateUser(uName2, pw2, dName2);
		cmd2.testExecute();
		CommandToSelectUser cmd3 = new CommandToSelectUser(cmd.getUserName(), cmd.getPassword());
		cmd3.testExecute();
		Person selectedPerson = cmd.getTestResult();
		assertEquals("Johnny", selectedPerson.getUserName());	
		
		Person.emptyMockDB();
		SelectedPerson.resetSelectedPerson();
	}
	
	@Test
	public void testSelectOtherFromTwoPeople()
	{
		String uName = "Johnny";
		String pw = "JPassword";
		String dName = "JohnnyJohn";
		CommandToCreateUser cmd = new CommandToCreateUser(uName, pw, dName);
		MockUnitOfWork.newCurrent();
		cmd.testExecute();
		String uName2 = "Matthew";
		String pw2 = "password";
		String dName2 = "MattyMatt3000";
		CommandToCreateUser cmd2 = new CommandToCreateUser(uName2, pw2, dName2);
		cmd2.testExecute();
		CommandToSelectUser cmd3 = new CommandToSelectUser(cmd2.getUserName(), cmd2.getPassword());
		cmd3.testExecute();
		Person selectedPerson = cmd2.getTestResult();
		assertEquals("Matthew", selectedPerson.getUserName());	
		
		Person.emptyMockDB();
		SelectedPerson.resetSelectedPerson();
	}
}
