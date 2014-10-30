package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommandToCreateUser {

	@Test
	public void testInitialization() 
	{
		String uName = "Matthew";
		String pw = "password";
		String dName = "MattyMatt3000";
		CommandToCreateUser cmd = new CommandToCreateUser(uName, pw, dName);
		assertEquals("Matthew", cmd.getUserName());
		assertEquals("password", cmd.getPassword());
		assertEquals("MattyMatt3000", cmd.getDisplayName());
	}
	
	@Test
	public void testCommandFunctionality()
	{
		String uName = "Johnny";
		String pw = "JPassword";
		String dName = "JohnnyJohn";
		CommandToCreateUser cmd = new CommandToCreateUser(uName, pw, dName);
		
		UnitOfWork.newCurrent();
		cmd.execute();
		Person person = cmd.getResult();
		assertEquals("Johnny", person.getUserName());
		assertEquals("JPassword", person.getPassword());
		assertEquals("JohnnyJohn", person.getDisplayName());
	}
	
	@Test
	public void testDB()
	{
		String uName = "Johnny";
		String pw = "JPassword";
		String dName = "JohnnyJohn";
		CommandToCreateUser cmd = new CommandToCreateUser(uName, pw, dName);
		UnitOfWork.newCurrent();
		cmd.execute();
		CommandToPersistChanges cmd2 = new CommandToPersistChanges();
		cmd2.execute();
	}
}
