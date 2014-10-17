package mockGateways;

import domainLogic.Person;


public class MockPersonRowDataGateway
{

	public MockPersonRowDataGateway() 
	{
		
	}
	
	public Person findPerson(String Username, String password) 
	{
		Person user = new Person("MockUserName", "MockPassword", "MockDisplayName", -1);
		return user;
	}
	public Person findPerson(int ID) 
	{
		Person Croft = new Person("CroftUserName", "CroftPassword", "CroftDisplayName", -1);
		return Croft;
	}
	
	public Person findPerson(String Username) 
	{
		Person Matthew = new Person("KujawskiUserName", "KujawskiPassword", "KujawskiDisplayName", -1);
		return Matthew;
	}
}
