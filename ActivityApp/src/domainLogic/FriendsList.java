package domainLogic;

import java.util.ArrayList;
import domainLogic.Person;

/**
 * @author josh
 *
 */
public class FriendsList
{
	ArrayList<Friend> friends;
	
	/**
	 * Constructor
	 */
	public FriendsList() 
	{
		friends = new ArrayList<Friend>();
	}
	
	/**
	 * Constructor with parameter
	 * @param friends
	 */
	public FriendsList(ArrayList<Friend> friends) 
	{
		this.friends = friends;
	}
	
	public ArrayList<Friend> getFriendList()
	{
		return friends;
	}

	public void add(Friend friend) {
		friends.add(friend);
		
	}

	public void remove(Friend requestee)
	{
		friends.remove(requestee);	
	}
}
