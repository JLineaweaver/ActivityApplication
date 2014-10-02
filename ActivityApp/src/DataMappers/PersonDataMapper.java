package DataMappers;

import java.sql.SQLException;

import DomainModel.Person;
import Gateways.FriendsTableDataGateway;
import Gateways.OutgoingPendingFriendsTableDataGateway;
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
	OutgoingPendingFriendsTableDataGateway pendingFriendsGateway;
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
}
