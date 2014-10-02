package DomainModel;

import java.util.ArrayList;

import DataMappers.PersonDataMapper;
import DataMappers.PersonThreadLocal;

/**
 * @author josh
 *
 */
public class Person
{
	private String username;
	private String password;
	private String displayName;
	
	FriendsList myFriends;
	PendingFriendsList myPendingFriends;
	
	/**
	 * Constructor
	 */
	public Person() {
		myFriends = new FriendsList();
		myPendingFriends = new PendingFriendsList();
	}
	
	/**
	 * Constructor with parameters
	 * @param myFriends
	 * @param myPendingFriends 
	 */
	public Person(FriendsList myFriends, PendingFriendsList myPendingFriends) {
		this.myFriends = myFriends;
		this.myPendingFriends = myPendingFriends;
	}
	
	/**
	 * @return username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return displayName
	 */
	public String getDisplayName()
	{
		return displayName;
	}

	/**
	 * @param displayName
	 */
	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}



	
	/**
	 * Static find method for persisting a person
	 * @param username
	 * @param password
	 * @return
	 */
	public static Person findPerson(String username, String password) {
		PersonDataMapper pdm = PersonThreadLocal.get();
		return pdm.findPerson(username,password);
	}
	
	public static Person findPerson(int ID) {
		PersonDataMapper pdm = PersonThreadLocal.get();
		return pdm.findPerson(ID);
	}
	

}
