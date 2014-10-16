package MockGateways;

import DomainModel.FriendsList;

public class MockFriendsTableDataGateway
{

	public MockFriendsTableDataGateway() {
		
	}
	
	public FriendsList findFriends(int ID) {
		return new FriendsList();
	}
}
