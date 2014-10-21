package domainLogic;

import java.util.ArrayList;

public class OutgoingPendingFriendList
{
	ArrayList<Person> outgoingPendingFriends;
	
	/**
	 * Constructor
	 */
	public OutgoingPendingFriendList() 
	{
		outgoingPendingFriends = new ArrayList<Person>();
	}
	
	/**
	 * Constructor with parameter
	 * @param friends
	 */
	public OutgoingPendingFriendList(ArrayList<Person> friends) 
	{
		outgoingPendingFriends = friends;
	}
	
	public ArrayList<Person> getPendingFriendList()
	{
		return outgoingPendingFriends;
	}

	public void add(Person friend) 
	{
		outgoingPendingFriends.add(friend);
	}

	public void remove(Person requestee)
	{
		outgoingPendingFriends.remove(requestee);
	}
}
