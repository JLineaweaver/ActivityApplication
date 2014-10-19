package domainLogic;

import dataMappers.DataMapper;
import dataMappers.MyThreadLocal;

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
	private Person user;
	
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
	}
	
	public Person(String uName, String pw, String dName, int ID)
	{
		displayName = dName;
		password = pw;
		userName = uName;
		userID = ID;
		myFriends = new FriendsList();
		myPendingFriends = new PendingFriendsList();
	}
	
	/**
	 * @return username
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * @param username
	 */
	public void setUserName(String username)
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
	 * @param userNameOfRequestee
	 * Find the person based on the Username
	 * @return the person
	 */
	public static Person findPerson(String userNameOfRequestee) 
	{
		DataMapper  dm = MyThreadLocal.get();
		return dm.findPerson(userNameOfRequestee);
	}

	/**
	 * 
	 * @param userIDOfRequestee is the person that is being as to be someone's friend
	 * @param userNameOfRequester is the person asking to be someone's friend
	 * 
	 * Find both people, add each other to their friends list, mark person as dirty.
	 */
	public void AcceptFriendRequest(int userIDOfRequestee, String userNameOfRequester)
	{
		user = Person.findPerson(userIDOfRequestee);
		Person requester = new Person();
		requester = Person.findPerson(userNameOfRequester);
		user.myFriends.add(requester);
		requester.myFriends.add(user);
		
//		this.markDirty(friend);
//		this.markDirty(requester);
	}
	
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
	
	public int getUserID()
	{
		return userID;
	}


	public void CreateUser(String userName, String password, String displayName) 
	{
		userID = (int) Math.random();
		user = new Person(userName, password, displayName, userID);
	}
	
	public Person getUser()
	{
		return user;
	}


	public void SelectUser(String userName, String pw) 
	{
		user = Person.findPerson(userName, pw);
	}


	public void MakeFriendRequest(int userIDOfRequester, String userNameOfRequestee) 
	{
		user = Person.findPerson(userIDOfRequester);
		Person requestee = new Person();
		requestee = Person.findPerson(userNameOfRequestee);
		user.myPendingFriends.add(requestee);
	}


	public void UnFriend(int userIDOfRequester, String userNameOfRequestee)
	{
		user = Person.findPerson(userIDOfRequester);
		Person requestee = new Person();
		requestee = Person.findPerson(userNameOfRequestee);
		user.myFriends.remove(requestee);
		
	}


	public void rejectFriendRequest(int userIDOfRequestee, String userNameOfRequester)
	{
		Person requestee = new Person();
		requestee = Person.findPerson(userIDOfRequestee);
		user = Person.findPerson(userNameOfRequester);
		user.myPendingFriends.remove(requestee);
	}


	public void retrieveFriendList(int userID)
	{
		user = Person.findPerson(userID);
		user.myFriends.getFriendList();
	}

}
