package domainLogic;

import java.sql.SQLException;

/**
 * Tells the system to save any pending changes
 * 
 * @author merlin
 *
 */
public class CommandToPersistChanges implements Command
{
	Person person = new Person();
	/**
	 * 
	 * @see Command#execute()
	 */
	@Override
	public void execute()
	{
		try {
			person.persistChanges();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	/**
	 * Nothing needs to be returned here (null). The tests will retrieve
	 * anything they want to check by re-finding appropriate records
	 * 
	 * @see Command#getResult()
	 */
	@Override
	public Object getResult()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
