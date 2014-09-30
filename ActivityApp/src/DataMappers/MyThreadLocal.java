package DataMappers;

/**
 * @author josh
 *
 */
public class MyThreadLocal
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
	 * @return the pdm
	 */
	public static PersonDataMapper get() {
	return (PersonDataMapper) getUserthreadlocal().get();
	}

	public static ThreadLocal<PersonDataMapper> getUserthreadlocal()
	{
		return userThreadLocal;
	}
}
