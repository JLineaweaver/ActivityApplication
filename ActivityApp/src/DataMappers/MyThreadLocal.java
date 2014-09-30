package DataMappers;

/**
 * @author josh
 *
 */
public class MyThreadLocal
{
	public static final ThreadLocal<PersonDataMapper> userThreadLocal = new ThreadLocal();
	
	/**
	 * Set the thread local
	 * @param dm
	 */
	public static void set(final PersonDataMapper dm) {
	userThreadLocal.set(dm);
	}
	 
	public static void unset() {
	userThreadLocal.remove();
	}
	 
	/**
	 * @return the pdm
	 */
	public static PersonDataMapper get() {
	return (PersonDataMapper) userThreadLocal.get();
	}
}
