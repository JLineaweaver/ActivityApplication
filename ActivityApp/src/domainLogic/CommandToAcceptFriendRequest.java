package domainLogic;

/**
 * Accept a friend request from one user to another
 * 
 * @author merlin
 * 
 */
public class CommandToAcceptFriendRequest implements Command
{

	private int userIDOfRequestee;
	private String userNameOfRequester;
	private Person person = new Person();

	/**
	 * 
	 * @param userIDOfRequestee
	 *            the User ID of the user accepting the request
	 * @param userNameOfRequester
	 *            the User Name of the user who initiated the friend request
	 */
	public CommandToAcceptFriendRequest(int userIDOfRequestee, String userNameOfRequester)
	{
		this.userIDOfRequestee = userIDOfRequestee;
		this.userNameOfRequester = userNameOfRequester;

	}

	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		Person selectedPerson = SelectedPerson.getInstance();
		person.AcceptFriendRequestCommand(selectedPerson.getUserID(), userNameOfRequester);
	}

	/**
	 * Nothing needs to be retrieved from this command
	 * 
	 * @see Command#getResult()
	 * 
	 * I have this return requester for testing purposes.
	 * This allows me to see the requester's pending friends
	 */
	@Override
	public Person getResult()
	{
		return person;
		
	}

	/**
	 * For testing purposes, make sure that it remembers its requestee
	 * @return the requestee of the friend request being accepted
	 */
	public int getUserIDOfRequestee()
	{
		return userIDOfRequestee;
	}

	/**
	 * For testing purposes, make sure that it remembers the user name of the requester
	 * @return the user name of the user that initiated the request
	 */
	public String getUserNameOfRequester()
	{
		return userNameOfRequester;
	}

}
