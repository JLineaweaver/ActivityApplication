package DataMappers;

/**
 * @author josh
 *
 */
public class PersonThreadLocal
{
	private static final ThreadLocal<PersonDataMapper> userThreadLocal = new ThreadLocal<PersonDataMapper>();
	
	/**
	 * Set the thread local
	 * @param dm
	 */
	public static void set(final PersonDataMapper dm) {
	getUserthreadlocal().set(dm);
	}
	 
	public static void unset() {
	getUserthreadlocal().remove();
	}
	 
	/**
	 * Checks to see if the threadlocal has been set and if not sets it with a new 
	 * PersonDataMapper.  Then it returns the set PersonDataMapper.
	 * @return the pdm
	 */
	public static PersonDataMapper get() {
		if ((PersonDataMapper) getUserthreadlocal().get() == null)
		{
			PersonDataMapper dm = new PersonDataMapper();
			getUserthreadlocal().set(dm);
		}
	return (PersonDataMapper) getUserthreadlocal().get();
	}

	public static ThreadLocal<PersonDataMapper> getUserthreadlocal()
	{
		return userThreadLocal;
	}
}
