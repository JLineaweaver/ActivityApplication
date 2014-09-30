package MockGateways;

import DomainModel.Person;

public class MockPersonRowDataGateway
{

	public MockPersonRowDataGateway() {
		
	}
	
	public Person findPerson(String Username, String password) {
		return new Person();
	}
}
