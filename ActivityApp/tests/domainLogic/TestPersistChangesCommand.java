package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPersistChangesCommand {

	@Test
	public void test() 
	{
		/**
		 * Mocked uop
		 */
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person2 = new Person("John", "","Jonny", 2);
		assertEquals(0, person1.getNumberOfFriends());
		SelectedPerson.initializeSelectedPerson(person1); // simulates creating a person
		
		UnitOfWork.newCurrent();
		UnitOfWork unit = UnitOfWork.getCurrent();
		assertEquals(0, person1.myIncomingPendingFriends.incomingPendingFriends.size());
		
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(person1.getUserID(), person2.getUserName());
		
		cmd.execute();
		Person result = cmd.getResult();
		
		assertEquals(1, person1.myIncomingPendingFriends.incomingPendingFriends.size());
		assertEquals(1, result.myIncomingPendingFriends.incomingPendingFriends.size());

		assertEquals(2, unit.getDirtyObjects().size());
		CommandToPersistChanges cmd1 = new CommandToPersistChanges();
		cmd1.execute();
		
		assertEquals(0, unit.getDirtyObjects().size());
	}

}
