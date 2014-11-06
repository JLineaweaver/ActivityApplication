package domainLogic;

import java.sql.SQLException;
import java.util.ArrayList;

public class IncomingPendingFriendsList
{
	ArrayList<Person> incomingPendingFriends;
	
	/**
	 * Constructor
	 */
	public IncomingPendingFriendsList() 
	{
		incomingPendingFriends = new ArrayList<Person>();
	}
	
	
	/**
	 * Constructor with parameter
	 * @param friends
	 */
	public IncomingPendingFriendsList(ArrayList<Person> friends) 
	{
		incomingPendingFriends = friends;
	}
	
	public ArrayList<Person> getPendingFriendList()
	{
		return incomingPendingFriends;
	}

	public void add(Person friend) 
	{
		incomingPendingFriends.add(friend);
	}

	public void remove(Person requestee)
	{
		incomingPendingFriends.remove(requestee);
	}
	
	
}
