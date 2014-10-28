package domainLogic;
/**
 * Initiates a friend request from one user to another
 * @author merlin
 *
 */
public class CommandToMakeFriendRequest implements Command
{

	private int userIDOfRequester;
	private String userNameOfRequestee;
	private Person person = new Person();


	/**
	 * 
	 * @param userIDOfRequester the User ID of the user making the request
	 * @param userNameOfRequestee the User Name of the user being friended
	 */
	public CommandToMakeFriendRequest(int userIDOfRequester, String userNameOfRequestee)
	{
		this.userIDOfRequester = userIDOfRequester;
		this.userNameOfRequestee = userNameOfRequestee;
		
	}
	
	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		person.MakeFriendRequest(userNameOfRequestee);
	}

	/**
	 * Nothing needs to be retrieved from this command
	 * @see Command#getResult()
	 * 
	 * I have this return requester for testing purposes.
	 * This allows me to see the requester's pending friends
	 */
	@Override
	public Person getResult()
	{
		Person result = person.getUser();
		return result;
	}
	
	public int getIDOfRequester()
	{
		return userIDOfRequester;
	}
	
	public String getUserNameOfRequestee()
	{
		return userNameOfRequestee;
	}

}
