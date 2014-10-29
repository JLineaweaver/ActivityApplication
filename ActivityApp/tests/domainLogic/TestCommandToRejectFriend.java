package domainLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCommandToRejectFriend {

	@Test
	public void test() 
	{
		Person person1 = new Person("Matt", "","mattyc", 1);
		Person person2 = new Person("John", "","Jonny", 2);
		SelectedPerson.initializeSelectedPerson(person1); // simulates selecting person1
		
		person1.myIncomingPendingFriends.incomingPendingFriends.add(person2);
		assertEquals(1, person1.myIncomingPendingFriends.incomingPendingFriends.size());
		
		CommandToRejectFriendRequest cmd = new CommandToRejectFriendRequest(person1.getUserID(), person2.getUserName());
		UnitOfWork.newCurrent();
		cmd.execute();
		
		assertEquals(0, person2.myIncomingPendingFriends.incomingPendingFriends.size());
		SelectedPerson.resetSelectedPerson();
	}

}
