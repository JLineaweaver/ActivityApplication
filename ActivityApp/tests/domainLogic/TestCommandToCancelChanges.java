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
		person1.myOutgoingPendingFriends.outgoingPendingFriends.add(person2);
		CommandToGetPendingOutgoingFriendList cmd1 = new CommandToGetPendingOutgoingFriendList(person1.getUserID());
		cmd1.execute();
		assertEquals(1, cmd1.getResult().size());
		CommandToCancelChanges cmd = new CommandToCancelChanges();
		cmd.execute();
//		assertEquals(1, person1.getOutgoingPendingFriendList());
//		assertEquals("Matt", un.getResult().getUserName());
//		assertEquals(0, un.getResult().myFriends.getFriendList().size());
		
	}

}
