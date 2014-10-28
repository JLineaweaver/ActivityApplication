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
	String PendingIncomingFriendList;

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return userName + ":" + password + ":" + displayName;
		//return mockDB.get(0).getUserName() + ":" + mockDB.get(0).getPassword() + ":" + mockDB.get(0).getDisplayName();
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
	public void AcceptFriendRequestCommand(int userIDOfRequestee, String userNameOfRequester)
	{
		//user = Person.findPerson(userIDOfRequestee);
		//requester = Person.findPerson(userNameOfRequester);
		//user.myFriends.add(requester);
		
		user = Person.findUser1(userIDOfRequestee);
		Person requester = Person.findUser1(userNameOfRequester);
		requester.myFriends.add(user);
		user.myFriends.add(requester);
		
		user.myIncomingPendingFriends.incomingPendingFriends.remove(requester);
		requester.myOutgoingPendingFriends.outgoingPendingFriends.remove(user);
		
		this.markDirty(user);
		this.markDirty(requester);
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
		Person selectedUser = Person.findUser1(userName, pw);
		//selectedUser = new Person("fred", "pw1" ,"happyFred" , -1);
		if(selectedUser != SelectedPerson.getInstance())
		{
			SelectedPerson.resetInstance();
			SelectedPerson.initializeInstance(selectedUser);
		}
		//user = Person.findPerson(userName, pw);
			
	}


	public void MakeFriendRequest(int userIDOfRequester, String userNameOfRequestee) 
	{
//		user = Person.findPerson(userIDOfRequester);
//		Person requestee = new Person();
//		requestee = Person.findPerson(userNameOfRequestee);

		
		//user = Person.findUser1(userIDOfRequester);
		user = SelectedPerson.getInstance();
		Person requestee = Person.findUser1(userNameOfRequestee);
		
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
		this.markDirty(requestee);
		this.markDirty(user);
		
	}


	public void rejectFriendRequest(int userIDOfRequestee, String userNameOfRequester)
	{
		Person requestee = new Person();
		//requestee = Person.findPerson(userIDOfRequestee);
		requestee = Person.findUser1(userIDOfRequestee);
		user = Person.findUser1(userNameOfRequester);
		requestee.myIncomingPendingFriends.incomingPendingFriends.remove(user);
		this.markDirty(requestee);
		this.markDirty(user);
	}


	public ArrayList<Person> retrieveFriendList(int userID)
	{
		user = Person.findUser1(userID);
		return user.myFriends.getFriendList();
	}

	public void PendingIncomingFriendList(int userID) 
	{
		//user = Person.findPerson(userID);
		user = Person.findUser1(userID);
		ArrayList<Person> incomingFriendsList = new ArrayList<Person>();
		incomingFriendsList = user.myIncomingPendingFriends.getPendingFriendList();
		for(int i = 0; i < incomingFriendsList.size(); i++)
		{
			if(i == 0)
			{
				PendingIncomingFriendList = incomingFriendsList.get(i).getUserName();
			}else
			{
				PendingIncomingFriendList = PendingIncomingFriendList + "," + PendingIncomingFriendList;
			}
		}
		
	}
	
	public String getPendingIncomingFriendListString()
	{
		return PendingIncomingFriendList;
	}


	public void cancelChanges()
	{
		UnitOfWork unit = UnitOfWork.getCurrent();
		unit.emptyArrayLists();
	}


	public void modifyUser(int userID, String newDisplayName)
	{
		//user = Person.findPerson(userID);
		user = Person.findUser1(userID);
		user.setDisplayName(newDisplayName);
		
	}


	public void PendingOutgoingFriendList(int userID) 
	{
		//user = Person.findPerson(userID);
		//user = Person.findUser1(userID);
		user = SelectedPerson.getInstance();
	}
	
	public ArrayList<Person> getTheOutgoingPendingFriendList()
	{
		return user.myOutgoingPendingFriends.getPendingFriendList();
	}
	
	public OutgoingPendingFriendList getOutgoingPendingFriendList() {
		return myOutgoingPendingFriends;
	}
	
}
