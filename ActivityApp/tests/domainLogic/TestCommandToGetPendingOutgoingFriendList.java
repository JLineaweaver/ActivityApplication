package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommandToGetPendingOutgoingFriendList {

	@Test
	public void testGetEmptyOutgoingFriendList() 
	{
		Person person = new Person("Matt", "pw", "MattyMatt", -2);
		CommandToGetPendingOutgoingFriendList cmd = new CommandToGetPendingOutgoingFriendList(person.getUserID());
		cmd.execute();
		assertEquals(0, cmd.getResult().size());
		
		Person.emptyMockDB();
	}
	
	@Test
	public void testGetOutgoingPendingFriendList()
	{
		Person person = new Person("Matt", "pw", "MattyMatt", -2);
		Person person2 = new Person("Fred", "pw", "FreddyFred", -9);
		person.myOutgoingPendingFriends.outgoingPendingFriends.add(person2); //manually added a friend to the list
		CommandToGetPendingOutgoingFriendList cmd = new CommandToGetPendingOutgoingFriendList(person.getUserID());
		cmd.execute();
		assertEquals(person2, cmd.getResult().get(0));
		
		Person.emptyMockDB();
	}
	
	@Test
	public void testGetMultipleOutgoingPendingFriendList()
	{
		Person person = new Person("Matt", "pw", "MattyMatt", -2);
		Person person2 = new Person("Fred", "pw", "FreddyFred", -9);
		Person person3 = new Person("Josh", "pw", "JoshyJosh", -5);
		person.myOutgoingPendingFriends.outgoingPendingFriends.add(person2); //manually added a friend to the list
		person.myOutgoingPendingFriends.outgoingPendingFriends.add(person3);
		CommandToGetPendingOutgoingFriendList cmd = new CommandToGetPendingOutgoingFriendList(person.getUserID());
		cmd.execute();
		assertEquals(person2, cmd.getResult().get(0));
		assertEquals(person3, cmd.getResult().get(1));
		
		Person.emptyMockDB();
	}

}
