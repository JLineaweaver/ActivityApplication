package DataMappers;

import java.sql.SQLException;

import DomainModel.Person;
import Gateways.FriendsTableDataGateway;
import Gateways.PendingFriendsTableDataGateway;
import Gateways.PersonRowDataGateway;
import MockGateways.MockPersonRowDataGateway;

/**
 * @author josh
 * Class for the person data mapper
 * Should run in a thread local
 */
public class PersonDataMapper
{
	FriendsTableDataGateway friendsGateway;
	PendingFriendsTableDataGateway pendingFriendsGateway;
	PersonRowDataGateway personGateway;
	public PersonDataMapper() {
		try
		{
			personGateway = new PersonRowDataGateway();
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
}
