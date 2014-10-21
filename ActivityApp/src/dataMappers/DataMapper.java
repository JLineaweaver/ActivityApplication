package dataMappers;

import gateways.FriendsTableDataGateway;
import gateways.PendingFriendsTableDataGateway;
import gateways.PersonRowDataGateway;

import java.sql.SQLException;

import mockGateways.MockFriendsTableDataGateway;
import mockGateways.MockPendingFriendsTableDataGateway;
import mockGateways.MockPersonRowDataGateway;
import domainLogic.FriendsList;
import domainLogic.IncomingPendingFriendsList;
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
	public Person findPerson(String userName) {
		MockPersonRowDataGateway mprdg = new MockPersonRowDataGateway();
		return mprdg.findPerson(userName);
	}
	
	public boolean storePerson(Person myPerson) {
		return true;
	}
	
	public boolean deletePerson(Person myPerson) {
		return true;
	}
	
}
