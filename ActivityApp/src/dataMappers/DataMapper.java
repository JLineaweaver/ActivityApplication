package dataMappers;

import gateways.FriendsTableDataGateway;
import gateways.PendingFriendsTableDataGateway;
import gateways.PersonRowDataGateway;

import java.sql.SQLException;

import MockGateways.MockFriendsTableDataGateway;
import MockGateways.MockPendingFriendsTableDataGateway;
import MockGateways.MockPersonRowDataGateway;
import domainLogic.FriendsList;
import domainLogic.PendingFriendsList;
import domainLogic.Person;
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
	public PendingFriendsList findPendingFriends(int ID) {
		MockPendingFriendsTableDataGateway mpftdg = new MockPendingFriendsTableDataGateway();
		return mpftdg.findPendingFriends(ID);
	}
	
}
