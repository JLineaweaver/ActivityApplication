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
		person1.myFriends.add(person2);
		CommandToRetrieveFriendList cmd = new CommandToRetrieveFriendList(person1.getUserID());
		cmd.execute();
		assertEquals(person1.myFriends.getFriendList(), cmd.getResult());
		
	}

}
