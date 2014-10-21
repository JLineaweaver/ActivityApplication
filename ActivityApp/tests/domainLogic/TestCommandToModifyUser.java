package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommandToModifyUser
{

	@Test
	public void testModify()
	{

		Person person = new Person();
		person.setDisplayName("Matthew123");
		person.setID(2);
		assertEquals("Matthew123", person.getDisplayName());
		CommandToModifyUser cmd = new CommandToModifyUser(person.getUserID(), "Matthew170");
		cmd.execute();
		assertEquals("Matthew170", person.getDisplayName());
		
	}

}