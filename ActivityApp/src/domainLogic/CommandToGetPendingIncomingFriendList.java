package domainLogic;

import java.util.ArrayList;

/**
 * Cause the list of friend requests from other user to this user to be fetched
 * from the domain model (may or may not cause reading from the DB depending on
 * the state of the domain model)
 * 
 * @author merlin
 *
 */
public class CommandToGetPendingIncomingFriendList implements Command
{

	private int userID;
	private Person person = new Person();
	private ArrayList<Person> result; 

	/**
	 * The userID of the current user
	 * 
	 * @param userID
	 *            unique
	 */
	public CommandToGetPendingIncomingFriendList(int userID)
	{
		this.userID = userID;
	}

	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		person.PendingIncomingFriendList();
	}
	

	/**
	 * A comma-separated list of the friends associated with the given user
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public ArrayList<Person> getResult()
	{
		Person person = SelectedPerson.getSelectedPerson();
		result = person.getPendingIncomingFriendList();
		return result;
	}
	
	
	public String toString()
	{
		String str = "";
		result = this.getResult();
		for(int i = 0; i < result.size(); i++)
		{
			if(i == 0)
			{
				str = result.get(i).getDisplayName();
			}else
			{
				str = str + "," + result.get(i).getDisplayName();
			}
		}
		
		return str;
	}

	public int getUserID() 
	{
		return userID;
	}
}
