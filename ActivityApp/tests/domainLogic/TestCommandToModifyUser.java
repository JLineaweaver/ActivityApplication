package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommandToModifyUser
{

	@Test
	public void testModify()
	{
		//This test is mocked up not data base tested
		Person person = new Person("Mattyc", "mat12", "Matthew170", 2);
		SelectedPerson.initializeSelectedPerson(person); // simulates selecting the person
		
		assertEquals("Matthew170", person.getDisplayName());
		CommandToModifyUser cmd = new CommandToModifyUser(person.getUserID(), "Matt170");
		cmd.execute();
		assertEquals("Matt170", person.getDisplayName());
		
		SelectedPerson.resetSelectedPerson();
	}

}