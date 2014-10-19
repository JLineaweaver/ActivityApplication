package domainLogic;

import java.util.ArrayList;

public class PendingFriendsList
{
	ArrayList<Person> pendingFriends;
	
	/**
	 * Constructor
	 */
	public PendingFriendsList() 
	{
		pendingFriends = new ArrayList<Person>();
	}
	
	/**
	 * Constructor with parameter
	 * @param friends
	 */
	public PendingFriendsList(ArrayList<Person> friends) 
	{
		pendingFriends = friends;
	}
	
	public ArrayList<Person> getPendingFriendList()
	{
		return pendingFriends;
	}

	public void add(Person friend) 
	{
		pendingFriends.add(friend);
	}

	public void remove(Person requestee)
	{
		pendingFriends.remove(requestee);
	}
}
