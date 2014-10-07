package Tests.Commands;

import static org.junit.Assert.*;

import org.junit.Test;

import Commands.AcceptFriendRequest;
import DomainModel.Person;

public class TestAcceptFriendRequest 
{

	@Test
	public void test() 
	{
		Person person = new Person();
		String userNameOfRequestor = person.getDisplayName();
		Person friend = new Person();
		int ID = friend.getID();
		AcceptFriendRequest command = new AcceptFriendRequest(ID, userNameOfRequestor);
		ThreadLocal tl = new ThreadLocal();
		tl.
		//assertEquals(0, person.getNumberOfFriends());
		command.execute(); // When executed, it essentially needs to mock because the DB is NOT up and running!!!
		assertEquals(1, person.getNumberOfFriends());	
	}

}
