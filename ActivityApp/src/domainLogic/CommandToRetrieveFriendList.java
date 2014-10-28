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

	/**
	 * A list of the friends associated with the given user
	 * @see Command#getResult()
	 */
	@Override
	////FRIEND
	public ArrayList<Person> getResult()
	{
		// TODO Auto-generated method stub
		return person.getUser().myFriends.getFriendList();
	}

}
