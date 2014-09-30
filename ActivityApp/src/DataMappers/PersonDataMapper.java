package DataMappers;

import DomainModel.Person;
import Gateways.FriendsTableDataGateway;
import Gateways.PendingFriendsTableDataGateway;
import Gateways.PersonRowDataGateway;

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
		personGateway = new PersonRowDataGateway();
	}
	public Person findPerson(String username, String password) {
		return personGateway.findPerson(username, password);
	}
}
