package DomainModel;

import java.util.ArrayList;

/**
 * @author josh
 *
 */
public class FriendsList
{
	ArrayList<Person> friends;
	
	/**
	 * Constructor
	 */
	public FriendsList() 
	{
		friends = new ArrayList<Person>();
	}
	
	/**
	 * Constructor with parameter
	 * @param friends
	 */
	public FriendsList(ArrayList<Person> friends) 
	{
		this.friends = friends;
	}
	
	public ArrayList<Person> getFriendList()
	{
		return friends;
	}

	public void add(Person friend) {
		friends.add(friend);
		
	}
}
