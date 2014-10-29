package domainLogic;
/**
 * Used to change information associated with a person (at this point, only the display name)
 * @author merlin
 *
 */
public class CommandToModifyUser implements Command
{
	Person person = new Person();
	private int userID;
	private String newDisplayName;

	/**
	 * 
	 * @param userID the unique ID of this user
	 * @param newDisplayName the name this user wants to be known by
	 */
	public CommandToModifyUser(int userID, String newDisplayName)
	{
		this.userID = userID;
		this.newDisplayName = newDisplayName;
	}

	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		person.modifyUser(userID, newDisplayName);
	}

	/**
	 * Nothing needs to be returned from this one. These tests will persist and
	 * re-retrieve the user to be sure that it is updated correctly
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public Person getResult()
	{
		return person.getUser();
	}
}
