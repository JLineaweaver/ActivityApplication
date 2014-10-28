package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommandToCancelChanges {

	@Test
	public void testCommandToCancelChanges()
	{
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person2 = new Person("John", "","Jonny", 2);
		assertEquals(0, person1.getNumberOfFriends());
		
		UnitOfWork.newCurrent();
		UnitOfWork unit = UnitOfWork.getCurrent();
		assertEquals(0, person1.myIncomingPendingFriends.incomingPendingFriends.size());
		
		CommandToMakeFriendRequest cmd = new CommandToMakeFriendRequest(person1.getUserID(), person2.getUserName());
		
		cmd.execute();
		Person result = cmd.getResult();
		
		assertEquals(1, person1.myIncomingPendingFriends.incomingPendingFriends.size());
		assertEquals(1, result.myIncomingPendingFriends.incomingPendingFriends.size());

		assertEquals(2, unit.getDirtyObjects().size());
		CommandToCancelChanges cmd1 = new CommandToCancelChanges();
		cmd1.execute();
		
		assertEquals(0, unit.getDirtyObjects().size());
		
	}

}
