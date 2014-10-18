package mockGateways;

import domainLogic.Person;


public class MockPersonRowDataGateway
{
	private Person person;

	public MockPersonRowDataGateway() 
	{
		
	}
	
	public Person findPerson(String Username, String password) 
	{
		person = new Person("MockUserName", "MockPassword", "MockDisplayName", -1);
		return person;
	}
	public Person findPerson(int ID) 
	{
		person = new Person("CroftUserName", "CroftPassword", "CroftDisplayName", -1);
		return person;
	}
	
	public Person findPerson(String Username) 
	{
		person = new Person("KujawskiUserName", "KujawskiPassword", "KujawskiDisplayName", -1);
		return person;
	}
	
	public Person getPerson()
	{
		return person;
	}
	
	
}
