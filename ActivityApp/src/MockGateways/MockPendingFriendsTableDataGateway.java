package MockGateways;

public class MockPendingFriendsTableDataGateway
{

	public MockPendingFriendsTableDataGateway() {
		
	}
	
	public boolean acceptFriend(int firstID, int secondID) {
		//Remove from table
		//Add to Friends table
		return true;
	}
	public boolean declineFriend(int firstID, int secondID) {
		//Remove from table
		return true;
	}
}
