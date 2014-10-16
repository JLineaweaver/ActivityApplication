package Tests.DomainModel;

import static org.junit.Assert.*;

import org.junit.Test;

import DomainModel.FriendsList;
import DomainModel.Person;

public class TestPerson
{

	@Test
	public void test()
	{
		Person myPerson = Person.findPerson("Lonny", "password");
		assertTrue(myPerson != null);
		myPerson = Person.findPerson(12);
		assertTrue(myPerson != null);
		FriendsList myFL = myPerson.getFriends();
		assertTrue(myFL != null);
	}

}
