package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommandToUnfriend 
{

	@Test
	public void testUnfriend() 
	{
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person3 = new Person("John", "","Johnny", 2);
		Friend person3AsFriend = new Friend("John","Johnny");
		SelectedPerson.initializeSelectedPerson(person1); // simulates creating a person
		
		assertEquals(0, person1.getNumberOfFriends());
		person1.myFriends.add(person3AsFriend);
		assertEquals(1, person1.getNumberOfFriends());
		
		CommandToUnFriend un = new CommandToUnFriend(person1.getUserID(), person3AsFriend.getUserName());
		MockUnitOfWork.newCurrent();
		un.testExecute();
		assertEquals(0, person1.getNumberOfFriends());
		assertEquals("Matt", un.getTestResult().getUserName());
		assertEquals(0, un.getTestResult().myFriends.getFriendList().size());
		
		Person.emptyMockDB();
		SelectedPerson.resetSelectedPerson();
	}
	
	@Test
	public void testUnFriendBothWays()
	{
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person3 = new Person("John", "","Johnny", 2);
		Friend person3AsFriend = new Friend("John","Johnny");
		SelectedPerson.initializeSelectedPerson(person1); // simulates creating a person

		person1.myFriends.add(person3AsFriend);
		
		CommandToUnFriend un = new CommandToUnFriend(person1.getUserID(), person3AsFriend.getUserName());
		MockUnitOfWork.newCurrent();
		un.testExecute();
		assertEquals(0, person3.getNumberOfFriends());
		
		Person.emptyMockDB();
		SelectedPerson.resetSelectedPerson();
	}
}
