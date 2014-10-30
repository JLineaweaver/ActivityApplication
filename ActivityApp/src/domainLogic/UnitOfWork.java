package domainLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;

/**
 * There is a unit of work for the commands per session/file.
 * It will keep track of all the manipulation, creation, and deletion of the users.
 */
public class UnitOfWork 
{
	private ArrayList<DomainObject> newObjects = new ArrayList<DomainObject>();
	private ArrayList<DomainObject> dirtyObjects = new ArrayList<DomainObject>();
	private ArrayList<DomainObject> removedObjects = new ArrayList<DomainObject>();
	
	private static ThreadLocal<UnitOfWork> current = new ThreadLocal<UnitOfWork>();
	
	
	public void registerNew(DomainObject obj)
	{
		//Assert.assertNotNull("id not null", obj.getIsObjectNull());
		Assert.assertTrue("object not dirty", !dirtyObjects.contains(obj));
		Assert.assertTrue("object not removed", !removedObjects.contains(obj));
		Assert.assertTrue("object not already registered new", !newObjects.contains(obj));
		newObjects.add(obj);
	}
	
	public void registerDirty(DomainObject obj)
	{
		//Assert.assertNotNull("id not null", obj.getIsObjectNull());
		Assert.assertTrue("object not removed", !removedObjects.contains(obj));
		if(!dirtyObjects.contains(obj))
		{
			if(newObjects.contains(obj))
			{
				newObjects.remove(obj);
				dirtyObjects.add(obj); 
			}else
			{
				dirtyObjects.add(obj);
			}
		}else
		{
			dirtyObjects.remove(obj); //Want the most recent object (Person)
		}
	}
	
	public void registerRemoved(DomainObject obj)
	{
		//Assert.assertNotNull("id not null", obj.getIsObjectNull());
		if(dirtyObjects.contains(obj))
		{
			dirtyObjects.remove(obj);
		}else if(newObjects.contains(obj))
		{
			newObjects.remove(obj);
		}

		if(!removedObjects.contains(obj))
		{
			removedObjects.add(obj);
		}
	}
	
	public void registerClean(DomainObject obj)
	{
		//put an Identity map
	}
	
	public void commit() throws SQLException
	{
		insertNew();
		updateDirty();
		//deleteRemoved();
		this.emptyArrayLists(); //Empty for testing purposes
	}

	private void deleteRemoved() 
	{
		// TODO Auto-generated method stub
		
	}

	private void updateDirty() throws SQLException
	{
		for(int i = 0; i < dirtyObjects.size(); i++)
		{
			Person.StorePerson((Person)dirtyObjects.get(i));
		}
		
	}

	private void insertNew() throws SQLException 
	{
		for(int i = 0; i < newObjects.size(); i++)
		{
			Person.CreatePerson((Person)newObjects.get(i));
		}

		
	}
	
	/**
	 * Each business transaction executes within a single thread.
	 * We can associate the Unit of Work with the currently executing thread.
	 */
	public static void newCurrent()
	{
		setCurrent(new UnitOfWork());
	}

	public static void setCurrent(UnitOfWork unit) 
	{
		current.set(unit);
	}
	
	public static UnitOfWork getCurrent()
	{
		return (UnitOfWork)current.get();
	}
	
	public ArrayList<DomainObject> getNewObjects()
	{
		return newObjects;
	}
	
	public ArrayList<DomainObject> getDirtyObjects()
	{
		return dirtyObjects;
	}
	
	public ArrayList<DomainObject> getRemovedObjects()
	{
		return removedObjects;
	}	

	public void emptyArrayLists()
	{
		newObjects.clear();
		dirtyObjects.clear();
		removedObjects.clear();
	}
}
