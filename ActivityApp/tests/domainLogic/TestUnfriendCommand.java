package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUnfriendCommand {

	@Test
	public void testUnfriend() 
	{
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person2 = new Person("John", "","Jonny", 2);
		assertEquals(0, person1.getNumberOfFriends());
		person1.myFriends.add(person2);
		assertEquals(1, person1.getNumberOfFriends());
		CommandToUnFriend un = new CommandToUnFriend(person1.getUserID(), person2.getUserName());
		un.execute();
		assertEquals(0, person1.getNumberOfFriends());
		assertEquals("Matt", un.getResult().getUserName());
		assertEquals(0, un.getResult().myFriends.getFriendList().size());

	}

}
