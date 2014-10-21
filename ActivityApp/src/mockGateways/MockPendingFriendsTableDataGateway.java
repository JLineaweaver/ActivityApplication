package mockGateways;

import domainLogic.IncomingPendingFriendsList;

/**
 * @author josh
 * The firstID is always the initial person making the request
 * The secondID is always the person receiving the initial request
 */
public class MockPendingFriendsTableDataGateway
{

	/**
	 * 
	 */
	public MockPendingFriendsTableDataGateway() {
		
	}
	
	/**
	 * @param firstID
	 * @param secondID
	 * @return true if no error
	 */
	public boolean requestFriend(int firstID, int secondID) {
		//Add to table
		return true;
	}
	
	/**
	 * @param firstID
	 * @param secondID
	 * @return true if no error
	 */
	public boolean acceptFriend(int firstID, int secondID) {
		//Remove from table
		//Add to Friends table
		return true;
	}
	/**
	 * @param firstID
	 * @param secondID
	 * @return true if no error
	 */
	public boolean declineFriend(int firstID, int secondID) {
		//Remove from table
		return true;
	}
	
	public IncomingPendingFriendsList findPendingFriends(int ID) {
		return new IncomingPendingFriendsList();
	}
}
