package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommandToModifyUser
{

	@Test
	public void testModify()
	{
		//This test is mocked up not data base tested
		Person person = new Person();
		person.setDisplayName("Matthew123");
		person.setID(2);
		assertEquals("Matthew123", person.getDisplayName());
		CommandToModifyUser cmd = new CommandToModifyUser(person.getUserID(), "Matthew170");
		cmd.execute();
//		assertEquals(2, cmd.getResult().getUserID());
		assertEquals("Matthew170", cmd.getResult().getDisplayName());
		
	}

}