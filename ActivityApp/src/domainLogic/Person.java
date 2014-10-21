package domainLogic;

import java.util.ArrayList;

import dataMappers.DataMapper;
import dataMappers.MyThreadLocal;

/**
 * You all will have one of these.
 * @author merlin
 *
 */
public class Person extends DomainObject
{
	static ArrayList<Person> mockDB = new ArrayList<Person>();
	private String userName;
	private String password;
	private String displayName;
	private int userID = -1;
	private Person user;
	private int size = 0;
	FriendsList myFriends;
	IncomingPendingFriendsList myIncomingPendingFriends;
	OutgoingPendingFriendList myOutgoingPendingFriends;
	String newPendingIncomingFriendList = "";
	ArrayList<Person> outgoingPendingFriendsList;

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
		myIncomingPendingFriends = new IncomingPendingFriendsList();
		myOutgoingPendingFriends = new OutgoingPendingFriendList();
		mockDB.add(this);
	}
	
	
//	private void addPeople() {
//		Person per = new Person(userName, password, displayName, userID);
//		mockDB.add(per);
//		
//	}
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
	public void setID(int i) 
	{
		userID = i;
		
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
		
		this.markDirty(user);
		this.markDirty(requester);
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
		size = this.myFriends.getFriendList().size();
		return size;
	}
	
	public int getUserID()
	{
		return userID;
	}


	public void CreateUser(String userName, String password, String displayName) 
	{
		user = new Person(userName, password, displayName, userID);
		
		this.markNew(user);
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
		user.myIncomingPendingFriends.add(requestee);
		requestee.myOutgoingPendingFriends.add(user);
		
		this.markDirty(user);
		this.markDirty(requestee);
	}


	public void UnFriend(int userIDOfRequester, String userNameOfRequestee)
	{
		//user = Person.findPerson(userIDOfRequester);
		user = Person.findUser1(userIDOfRequester);
		Person requestee = new Person();
		requestee = Person.findUser1(userNameOfRequestee);
		//requestee = Person.findPerson(userNameOfRequestee);
		user.myFriends.remove(requestee);
		
	}


	public void rejectFriendRequest(int userIDOfRequestee, String userNameOfRequester)
	{
		Person requestee = new Person();
		requestee = Person.findPerson(userIDOfRequestee);
		user = Person.findPerson(userNameOfRequester);
		user.myIncomingPendingFriends.remove(requestee);
	}


	public void retrieveFriendList(int userID)
	{
		user = Person.findPerson(userID);
		user.myFriends.getFriendList();
	}

	public void PendingIncomingFriendList(int userID) 
	{
		user = Person.findPerson(userID);
		ArrayList<Person> incomingFriendsList = new ArrayList<Person>();
		incomingFriendsList = user.myIncomingPendingFriends.getPendingFriendList();
		for(int i = 0; i < incomingFriendsList.size(); i++)
		{
			newPendingIncomingFriendList = newPendingIncomingFriendList + "," + incomingFriendsList.get(i);
		}
	}
	
	public String getPendingIncomingFriendListString()
	{
		return newPendingIncomingFriendList;
	}


	public void cancelChanges()
	{
		if(user.myIncomingPendingFriends != null)
		{
			user.myIncomingPendingFriends = null;
		}
	}


	public void modifyUser(int userID, String newDisplayName)
	{
		user = Person.findPerson(userID);
		user.setDisplayName(newDisplayName);
		
	}


	public void PendingOutgoingFriendList(int userID) 
	{
		user = Person.findPerson(userID);
		outgoingPendingFriendsList = user.myOutgoingPendingFriends.getPendingFriendList();
	}
	
	public ArrayList<Person> getOutgoingPendingFriendList()
	{
		return outgoingPendingFriendsList;
	}
	
}
