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
}
