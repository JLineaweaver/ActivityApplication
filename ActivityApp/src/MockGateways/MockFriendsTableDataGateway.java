package MockGateways;

import DomainModel.FriendsList;
import DomainModel.Person;

public class MockFriendsTableDataGateway
{

	public MockFriendsTableDataGateway() {
		
	}
	
	public FriendsList findFriends(int ID) {
		FriendsList myFL = new FriendsList();
		Person p = new Person();
		myFL.add(p);
		return myFL;
	}
	
	public FriendsList findFriends(String username) {
		FriendsList myFL = new FriendsList();
		Person p = new Person();
		myFL.add(p);
		return myFL;
	}
}
