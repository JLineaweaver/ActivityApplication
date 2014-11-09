package domainLogic;
import java.util.ArrayList;

/**
 * Cause the list of pending friend requests from this user to other users to be fetched
 * from the domain model (may or may not cause reading from the DB depending on
 * the state of the domain model)
 * 
 * @author merlin
 *
 */
public class CommandToGetPendingOutgoingFriendList implements Command
{

	private Person person = new Person();
	private ArrayList<Person> result;

	/**
	 * The userID of the current user
	 * 
	 * @param userID
	 *            unique
	 */
	public CommandToGetPendingOutgoingFriendList(int userID)
	{
	}

	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		person.PendingOutgoingFriendList();
	}
	

	/**
	 * A list of the friends associated with the given user
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public ArrayList<Person> getResult()
	{
		Person person = SelectedPerson.getSelectedPerson();
		result = person.getTheOutgoingPendingFriendList();
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

}
