package DomainModel;

import DataMappers.PersonDataMapper;
import DataMappers.PersonThreadLocal;

/**
 * @author josh
 *
 */
public class Person extends DomainObject
{
	FriendsList myFriends;
	PendingFriendsList myPendingFriends;
	
	/**
	 * Constructor
	 */
	public Person() 
	{
		myFriends = new FriendsList();
		myPendingFriends = new PendingFriendsList();
	}
	
	/**
	 * Constructor with parameters
	 * @param myFriends
	 * @param myPendingFriends 
	 */
	public Person(FriendsList myFriends, PendingFriendsList myPendingFriends) 
	{
		this.myFriends = myFriends;
		this.myPendingFriends = myPendingFriends;
	}
	
	/**
	 * Static find method for persisting a person
	 * @param username
	 * @param password
	 * @return
	 */
	public static Person findPerson(String username, String password) 
	{
		PersonDataMapper pdm;
		if(PersonThreadLocal.get() != null) 
		{
			pdm = PersonThreadLocal.get();
		return pdm.findPerson(username,password);
		}
		else 
		{
			pdm = new PersonDataMapper();
			PersonThreadLocal.set(pdm);
			return pdm.findPerson(username,password);
		}
		
	}

	public void acceptFriendRequest(int userIDOfRequestee, String userNameOfRequester)
	{
		Person friend = new Person();
		friend = findPerson(userIDOfRequestee);
		this.markDirty();
	}

}
