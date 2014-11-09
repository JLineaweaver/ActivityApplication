package domainLogic;

import java.sql.SQLException;
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
	public String userName;
	private String password;
	private String displayName;
	public int userID = -1;
	private Person user;
	private int size = 0;
	FriendsList myFriends;
	IncomingPendingFriendsList myIncomingPendingFriends;
	OutgoingPendingFriendList myOutgoingPendingFriends;

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
		myIncomingPendingFriends = new IncomingPendingFriendsList();
		myOutgoingPendingFriends = new OutgoingPendingFriendList();
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
	}
	
	public Person(String uName, String pw, String dName, int ID, FriendsList myFriends, IncomingPendingFriendsList myIncomingPendingFriends, OutgoingPendingFriendList myOutgoingPendingFriends) 
	{
		displayName = dName;
		password = pw;
		userName = uName;
		userID = ID;
		this.myFriends = myFriends;
		this.myIncomingPendingFriends = myIncomingPendingFriends;
		this.myOutgoingPendingFriends = myOutgoingPendingFriends;
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
	 * @throws SQLException 
	 */
	public static Person findPerson(String username, String password) throws SQLException 
	{
		DataMapper pdm = MyThreadLocal.get(); 
		return pdm.findPerson(username, password);
	}

	/**
	 * @param ID
	 * @return Person
	 * @throws SQLException 
	 */
	public static Person findPerson(int ID) throws SQLException 
	{
		DataMapper pdm = MyThreadLocal.get();
		return pdm.findPerson(ID);
	}
	
	/**
	 * 
	 * @param userNameOfRequestee
	 * Find the person based on the Username
	 * @return the person
	 * @throws SQLException 
	 */
	public static Person findPerson(String userNameOfRequestee) throws SQLException 
	{
		DataMapper  dm = MyThreadLocal.get();
		return dm.findPerson(userNameOfRequestee);
	}

	public static void CreatePerson(Person p) throws SQLException 
	{
		DataMapper pdm = MyThreadLocal.get();
		pdm.createPerson(p);
	}
	
	public static void StorePerson(Person p) throws SQLException
	{
		DataMapper pdm = MyThreadLocal.get();
		pdm.storePerson(p);
		pdm.resetMap();
	}
	
	/**
	 * 
	 * @param userIDOfRequestee is the person that is being as to be someone's friend
	 * @param userNameOfRequester is the person asking to be someone's friend
	 * 
	 * Find the requester, add each other to their friends list, mark person as dirty.
	 * @throws SQLException 
	 */
	public void AcceptFriendRequestCommand(String userNameOfRequester) throws SQLException
	{		
		user = SelectedPerson.getSelectedPerson();		
		Friend userAsFriend = new Friend(user.userName, user.displayName);
		Person theRequester = Person.findPerson(userNameOfRequester);
		Friend requesterAsFriend = new Friend(theRequester.userName, theRequester.displayName);
		theRequester.myFriends.add(userAsFriend);
		user.myFriends.add(requesterAsFriend);
		user.myIncomingPendingFriends.incomingPendingFriends.remove(theRequester);
		theRequester.myOutgoingPendingFriends.outgoingPendingFriends.remove(user);
		this.markDirty(user);
		this.markDirty(theRequester);
	}
		
	/**
	 * @return the array list of friends for that person
	 */
	public FriendsList getFriends()
	{
		return myFriends;
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

	/**
	 * 
	 * @param userName
	 * @param password
	 * @param displayName
	 * 
	 * Creates a new user with the above parameters.
	 * marks the user as new in the unit of work.
	 */
	public void CreateUser(String userName, String password, String displayName) 
	{
		user = new Person(userName, password, displayName, userID);
		this.markNew(user);
	}

	
	public Person getUser()
	{
		return user;
	}

	
	public void SelectUser(String userName, String pw) throws SQLException 
	{
		user = Person.findPerson(userName, pw);
		if(user != SelectedPerson.getSelectedPerson())
		{
			SelectedPerson.resetSelectedPerson();
			SelectedPerson.initializeSelectedPerson(user);
		}			
	}

	/**
	 * 
	 * @param userIDOfRequester of the selected person
	 * @param userNameOfRequestee 
	 * Finds the requestee.
	 * Adds each other to one another's pending friend lists
	 * Mark both people as dirty in the unit of work.
	 * @throws SQLException 
	 */
	public void MakeFriendRequest(String userNameOfRequestee) throws SQLException 
	{
		user = SelectedPerson.getSelectedPerson();
		Person requestee = Person.findPerson(userNameOfRequestee);
		
		user.myOutgoingPendingFriends.add(requestee);
		requestee.myIncomingPendingFriends.add(user);

		this.markDirty(user);
		this.markDirty(requestee);
	}
	

	/**
	 * @param userIDOfRequester
	 * @param userNameOfRequestee
	 * @throws SQLException
	 * 
	 * What I want is to find the people, and delete the mapping of userID in the friends table 
	 * that correspond to these two people.
	 */
	public void UnFriend(int userIDOfRequester, String userNameOfRequestee) throws SQLException
	{
		user = SelectedPerson.getSelectedPerson();
		Friend userAsFriend = new Friend(user.userName, user.displayName);
		Person requestee = Person.findPerson(userNameOfRequestee);
		Friend requsteeAsFriend = new Friend(requestee.userName, requestee.displayName);
		for(int i = 0; i < user.myFriends.friends.size(); i++)
		{
			if(user.myFriends.friends.get(i).getUserName().equals(requsteeAsFriend.getUserName()) && 
					user.myFriends.friends.get(i).getDisplayName().equals(requsteeAsFriend.getDisplayName()))
			{
				user.myFriends.remove(user.myFriends.friends.get(i));
			}
		}
		for(int i = 0; i < requestee.myFriends.friends.size(); i++)
		{
			if(requestee.myFriends.friends.get(i).getUserName().equals(userAsFriend.getUserName()) && 
					requestee.myFriends.friends.get(i).getDisplayName().equals(userAsFriend.getDisplayName()))
			{
				requestee.myFriends.remove(requestee.myFriends.friends.get(i));
			}
		}
		this.markDirty(requestee);
		this.markDirty(user);
		
	}
	

	public void rejectFriendRequest(int userIDOfRequestee, String userNameOfRequester) throws SQLException
	{
		Person requestee = SelectedPerson.getSelectedPerson();
		user = Person.findPerson(userNameOfRequester);
		user.myOutgoingPendingFriends.outgoingPendingFriends.remove(requestee);
		requestee.myIncomingPendingFriends.incomingPendingFriends.remove(user);
		this.markDirty(requestee);
		this.markDirty(user);
	}
	

	public ArrayList<Friend> retrieveFriendList(int userID)
	{
		user = SelectedPerson.getSelectedPerson();
		return user.myFriends.getFriendList();
	}
	

	/**
	 * 
	 * @param userID of the selected person
	 * Already found that person, so I don't need to find that person again.
	 * Goes through that person's pending incoming friend list and toStrings it.
	 */
	public void PendingIncomingFriendList() 
	{
		user = SelectedPerson.getSelectedPerson();		
	}
	
	
	/**
	 * @return the list of pending incoming friend list as a string
	 */
	public ArrayList<Person> getPendingIncomingFriendList()
	{
		return this.myIncomingPendingFriends.incomingPendingFriends;
	}
	

	public void cancelChanges()
	{
		UnitOfWork unit = UnitOfWork.getCurrent();
		unit.emptyArrayLists();
	}
	

	public void modifyUser(int userID, String newDisplayName)
	{
		user = SelectedPerson.getSelectedPerson();
		user.setDisplayName(newDisplayName);
		this.markDirty(user);	
	}
	

	/**
	 * 
	 * @param userID of the selected person
	 * Already found that person, so I do not need to find that person again.
	 * Set user = to the selected person.
	 * Then I can call getTheOutgoingPendingFriendList to grab the user's/ selected person's 
	 * outgoing pending friends.
	 */
	public void PendingOutgoingFriendList() 
	{
		user = SelectedPerson.getSelectedPerson();
	}
	
	
	public ArrayList<Person> getTheOutgoingPendingFriendList()
	{
		return this.myOutgoingPendingFriends.getPendingFriendList();
	}
	
	
	public OutgoingPendingFriendList getOutgoingPendingFriendList() 
	{
		return myOutgoingPendingFriends;
	}


	public void persistChanges() throws SQLException 
	{
		UnitOfWork unit = UnitOfWork.getCurrent();
		unit.commit();
	}
 
	public void addLists(FriendsList fl, IncomingPendingFriendsList ipfl, OutgoingPendingFriendList opfl) 
	{
		myFriends = fl;
		myIncomingPendingFriends = ipfl;
		myOutgoingPendingFriends = opfl;
	}
	
}
