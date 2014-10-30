package domainLogic;
import java.util.ArrayList;

/**
 * Cause a user's friend list to be fetched from the domain model (may or may
 * not cause reading from the DB depending on the state of the domain model
 * 
 * @author merlin
 *
 */
public class CommandToRetrieveFriendList implements Command
{
	ArrayList<Friend> result;
	Person person = new Person();
	private int userID;

	/**
	 * The userID of the current user
	 * @param userID unique
	 */
	public CommandToRetrieveFriendList(int userID)
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
		person.retrieveFriendList(userID);

	}
	public String toString()
	{
		String str = "";
		result = this.getResult();
		for(int i = 0; i < result.size(); i++)
		{
			if(i == 0)
			{
				str = result.get(i).getUserName();
			}else
			{
				str = str + "," + result.get(i).getUserName();
			}
		}
		
		return str;
	}
	/**
	 * A list of the friends associated with the given user
	 * @see Command#getResult()
	 */
	@Override
	public ArrayList<Friend> getResult()
	{
		result = person.getUser().myFriends.getFriendList();
		return result;
	}

}
