package DataMappers;

import java.sql.SQLException;

import domainLogic.FriendsList;
import domainLogic.Person;
import Gateways.FriendsTableDataGateway;
import Gateways.PendingFriendsTableDataGateway;
import Gateways.PersonRowDataGateway;
import MockGateways.MockFriendsTableDataGateway;
import MockGateways.MockPersonRowDataGateway;

/**
 * @author josh
 * Class for the person data mapper
 * Should run in a thread local
 */
public class DataMapper
{
	FriendsTableDataGateway friendsGateway;
	PendingFriendsTableDataGateway pendingFriendsGateway;
	PersonRowDataGateway personGateway;
	public DataMapper() {
		try
		{
			personGateway = new PersonRowDataGateway();
			friendsGateway = new FriendsTableDataGateway();
			pendingFriendsGateway = new PendingFriendsTableDataGateway();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Person findPerson(String username, String password) {
		MockPersonRowDataGateway mprdg = new MockPersonRowDataGateway();
		return mprdg.findPerson(username,password);
		//return personGateway.findPerson(username, password);
	}
	
	public Person findPerson(int ID) {
		MockPersonRowDataGateway mprdg = new MockPersonRowDataGateway();
		return mprdg.findPerson(ID);
	}
	public Person findPerson(String userNameOfRequester) {
		MockPersonRowDataGateway mprdg = new MockPersonRowDataGateway();
		return mprdg.findPerson(userNameOfRequester);
	}
	public FriendsList findFriends(int ID) {
		MockFriendsTableDataGateway mftdg = new MockFriendsTableDataGateway();
		return mftdg.findFriends(ID);
	}
	
}
