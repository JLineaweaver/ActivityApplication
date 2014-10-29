package domainLogic;

/**
 * @author matthew
 * This is the person that is selected by means of the CommandToSelectUser
 * This way you can easily grab him/her once he/she is selected.
 */
public class SelectedPerson extends Person
{
	private static Person selectedPerson = null;
	

	private SelectedPerson() 
	{

	}	
	
	/**
	 */
	public static void initializeSelectedPerson(Person sPerson)
	{
		if(selectedPerson == null)
		{
			selectedPerson = new SelectedPerson();
			selectedPerson = sPerson;
		}
	}
	
	/**
	 * @return the instance of the ride
	 */
	public static Person getSelectedPerson()
	{
		return selectedPerson;
	}
	
	public static void resetSelectedPerson()
	{
		selectedPerson = null;
	}
}
