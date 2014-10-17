package dataMappers;

/**
 * @author josh
 *
 */
public class MyThreadLocal
{
	private static final ThreadLocal<DataMapper> userThreadLocal = new ThreadLocal<DataMapper>();
	
	/**
	 * Set the thread local
	 * @param dm
	 */
	public static void set(final DataMapper dm) {
	getUserthreadlocal().set(dm);
	}
	 
	public static void unset() {
	getUserthreadlocal().remove();
	}
	 
	/**
	 * Checks to see if the threadlocal has been set and if not sets it with a new 
	 * DataMapper.  Then it returns the set DataMapper.
	 * @return the pdm
	 */
	public static DataMapper get() {
		if ((DataMapper) getUserthreadlocal().get() == null)
		{
			DataMapper dm = new DataMapper();
			getUserthreadlocal().set(dm);
		}
	return (DataMapper) getUserthreadlocal().get();
	}

	public static ThreadLocal<DataMapper> getUserthreadlocal()
	{
		return userThreadLocal;
	}
}
