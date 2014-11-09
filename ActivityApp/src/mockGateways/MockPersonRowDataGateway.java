package mockGateways;

import java.util.ArrayList;

import domainLogic.Person;


public class MockPersonRowDataGateway
{
	private Person person;
	static ArrayList<Person> mockDB = new ArrayList<Person>();

	public MockPersonRowDataGateway(Person p) 
	{
		mockDB.add(p);
	}
	
	public static Person findUser1(int id)
	{
		for(int i = 0; i < mockDB.size(); i++)
		{
			if(mockDB.get(i).userID == id)
			{
				return mockDB.get(i);
			}
		}
		return null;
	}
	public static Person findUser1(String userName)
	{
		for(int i = 0; i < mockDB.size(); i++)
		{
			if(mockDB.get(i).userName == userName)
			{
				return mockDB.get(i);
			}
		}
		return null;
	}
	public static Person findUser1(String userName, int id)
	{
		for(int i = 0; i < mockDB.size(); i++)
		{
			if(mockDB.get(i).userName == userName)
			{
				if(mockDB.get(i).userID == id)
				{
					return mockDB.get(i);
				}
			}
		}
		return null;
	}
	public static Person findUser1(String userName, String pw)
	{
		for(int i = 0; i < mockDB.size(); i++)
		{
			if(mockDB.get(i).userName == userName)
			{
				if(mockDB.get(i).getPassword() == pw)
				{
					return mockDB.get(i);
				}
			}
		}
		return null;
	}
	
	public static void emptyMockDB()
	{
		mockDB.clear();
	}
	
	public Person getPerson()
	{
		return person;
	}
	
	
}
