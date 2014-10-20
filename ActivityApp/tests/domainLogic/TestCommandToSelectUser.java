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
	public void testCommandFunctionality()
	{
		String uName = "userName";
		String pw = "password";
		CommandToSelectUser cmd = new CommandToSelectUser(uName, pw);
		cmd.execute();
		Person selectedPerson = cmd.getResult();// This will return the mock person and it's information
		assertEquals("MockUserName", selectedPerson.getUserName());
		assertEquals("MockPassword", selectedPerson.getPassword());
	}
	
	@Test
	public void testSelectOneFromTwoPeople()
	{
		String uName = "Johnny";
		String pw = "JPassword";
		String dName = "JohnnyJohn";
		CommandToCreateUser cmd = new CommandToCreateUser(uName, pw, dName);
		UnitOfWork.newCurrent();
		cmd.execute();
		String uName2 = "Matthew";
		String pw2 = "password";
		String dName2 = "MattyMatt3000";
		CommandToCreateUser cmd2 = new CommandToCreateUser(uName2, pw2, dName2);
		cmd2.execute();
		CommandToSelectUser cmd3 = new CommandToSelectUser(cmd.getUserName(), cmd.getPassword());
		cmd3.execute();
		Person selectedPerson = cmd.getResult();
		assertEquals("Johnny", selectedPerson.getUserName());	
	}
	
	@Test
	public void testSelectOtherFromTwoPeople()
	{
		String uName = "Johnny";
		String pw = "JPassword";
		String dName = "JohnnyJohn";
		CommandToCreateUser cmd = new CommandToCreateUser(uName, pw, dName);
		cmd.execute();
		String uName2 = "Matthew";
		String pw2 = "password";
		String dName2 = "MattyMatt3000";
		CommandToCreateUser cmd2 = new CommandToCreateUser(uName2, pw2, dName2);
		cmd2.execute();
		CommandToSelectUser cmd3 = new CommandToSelectUser(cmd2.getUserName(), cmd2.getPassword());
		cmd3.execute();
		Person selectedPerson = cmd2.getResult();
		assertEquals("Matthew", selectedPerson.getUserName());	
	}
}
