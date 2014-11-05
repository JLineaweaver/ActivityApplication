package domainLogic;
/**
 * Cancels a friend request between two users
 * @author merlin
 *
 */
public class CommandToUnFriend implements Command
{

	private int userIDOfRequester;
	private String userNameOfRequestee;
	Person person = new Person();

	/**
	 * 
	 * @param userIDOfRequester the User ID of the user cancel the relationship
	 * @param userNameOfRequestee the User Name of the user being unfriended
	 */
	public CommandToUnFriend(int userIDOfRequester, String userNameOfRequestee)
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
		person.UnFriend(userIDOfRequester, userNameOfRequestee);
	}
	
	public void testExecute()
	{
		person.testUnFriend(userIDOfRequester, userNameOfRequestee);
	}

	/**
	 * Nothing needs to be retrieved from this command
	 * @see Command#getResult()
	 */
	@Override
	public Person getResult()
	{
		return person.getUser();
	}
	
	public Person getTestResult()
	{
		return person.getTestUser();
	}

}
