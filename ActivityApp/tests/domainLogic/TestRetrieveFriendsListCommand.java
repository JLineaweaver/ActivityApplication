package domainLogic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestRetrieveFriendsListCommand {

	@Test
	public void testRetrieve() 
	{
		/**
		 * Not sure if it works right
		 */
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person2 = new Person("John", "","Jonny", 2);
		SelectedPerson.initializeSelectedPerson(person1); // simulates creating a person
		
		CommandToAcceptFriendRequest cmd = new CommandToAcceptFriendRequest(person1.getUserID(), person2.getUserName());
		
		UnitOfWork.newCurrent();
		cmd.execute();
		assertEquals(1, person1.getNumberOfFriends());
		CommandToRetrieveFriendList cmd1 = new CommandToRetrieveFriendList(person1.getUserID());
		cmd1.execute();
		assertEquals("John", cmd1.toString());
		
	//	Person.emptyMockDB();
	//	SelectedPerson.resetSelectedPerson();
		
	}

}
