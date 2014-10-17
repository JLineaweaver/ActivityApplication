package domainLogic;

import DataMappers.DataMapper;
import DataMappers.MyThreadLocal;

/**
 * You all will have one of these.
 * @author merlin
 *
 */
public class Person extends DomainObject
{

	private String userName;
	private String password;
	private String displayName;
	private int userID;
	
	FriendsList myFriends;
	PendingFriendsList myPendingFriends;

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return userName + ":" + password + ":" + displayName;
	}
	
	
	/**
	 * Constructor
	 */
	public Person() 
	{
		myFriends = new FriendsList();
		myPendingFriends = new PendingFriendsList();
	}
	
	public Person(String name, String pw, String username, int ID)
	{
		displayName = name;
		password = pw;
		userName = username;
		userID = ID;
		myFriends = new FriendsList();
		myPendingFriends = new PendingFriendsList();
	}
	
	/**
	 * @return username
	 */
	public String getUsername()
	{
		return userName;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username)
	{
		userName = username;
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
	 * @return Person
	 */
	public static Person findPerson(String username, String password) 
	{
		DataMapper pdm = MyThreadLocal.get(); 
		return pdm.findPerson(username, password);
	}

	/**
	 * @param ID
	 * @return Person
	 */
	public static Person findPerson(int ID) 
	{
		DataMapper pdm = MyThreadLocal.get();
		return pdm.findPerson(ID);
	}
	
	/**
	 * 
	 * @param userNameOfRequester 
	 * Find the person based on the Username
	 * @return the person
	 */
	private Person findPerson(String userNameOfRequester) 
	{
		DataMapper  dm = MyThreadLocal.get();
		return dm.findPerson(userNameOfRequester);
	}

	/**
	 * 
	 * @param userIDOfRequestee is the person that is being as to be someone's friend
	 * @param userNameOfRequester is the person asking to be someone's friend
	 * 
	 * Find both people, add each other to their friends list, mark person as dirty.
	 */
//	public void CommandToAcceptFriendRequest(int userIDOfRequestee, String userNameOfRequester)
//	{
//		Person friend = new Person();
//		friend = findPerson(userIDOfRequestee);
//		Person requester = new Person();
//		requester = findPerson(userNameOfRequester);
//		friend.myFriends.add(requester);
//		requester.myFriends.add(friend);
//		
//		this.markDirty(friend);
//		this.markDirty(requester);
//	}
	
	/**
	 * @return the array list of friends for that person
	 */
	public FriendsList findFriends()
	{
		DataMapper pdm = MyThreadLocal.get();
		return pdm.findFriends(userID);
	}
	
	
	/**
	 * @return get the number of friends
	 */
	public int getNumberOfFriends()
	{
		int size = myFriends.getFriendList().size();
		return size;
	}

	public void setID(int ID) 
	{
		userID = ID;
	}
	
	public int getID()
	{
		return userID;
	}
}
