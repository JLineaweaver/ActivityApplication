package MockGateways;

import domainLogic.Person;


public class MockPersonRowDataGateway
{

	public MockPersonRowDataGateway() 
	{
		
	}
	
	public Person findPerson(String Username, String password) 
	{
		return new Person();
	}
	public Person findPerson(int ID) 
	{
		Person Croft = new Person("Croft", "CroftPassword", "CroftUserName", -1);
		return Croft;
	}
	
	public Person findPerson(String Username) 
	{
		Person Matthew = new Person("Kujawski", "KujawskiPassword", "KujawskiUserName", -2);
		return Matthew;
	}
}
