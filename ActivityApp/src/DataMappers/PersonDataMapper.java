package DataMappers;

import DomainModel.Person;
import Gateways.FriendsTableDataGateway;
import Gateways.PendingFriendsTableDataGateway;
import Gateways.PersonRowDataGateway;

public class PersonDataMapper
{
	FriendsTableDataGateway friendsGateway;
	PendingFriendsTableDataGateway pendingFriendsGateway;
	PersonRowDataGateway personGateway;
	public PersonDataMapper() {
		
	}
	public Person findPerson(String username, String password) {
		return personGateway.findPerson(username, password);
	}
}
