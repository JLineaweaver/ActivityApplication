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
		Person person = new Person("Kujawski", "KujawskiPassword", "KujawskiUserName");
		String userNameOfRequestor = person.getDisplayName();
		Person friend = new Person();
		friend.setID(-1);
		int ID = friend.getID();
		AcceptFriendRequest command = new AcceptFriendRequest(ID, userNameOfRequestor);
		//assertEquals(0, person.getNumberOfFriends());
		command.execute(); // When executed, it essentially needs to mock because the DB is NOT up and running!!!
		assertEquals(1, person.getNumberOfFriends());	
	}

}
