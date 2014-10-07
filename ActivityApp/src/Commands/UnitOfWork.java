package Commands;

import java.util.ArrayList;

import org.junit.Assert;

import DomainModel.DomainObject;


public class UnitOfWork 
{
	private ArrayList<DomainObject> newObjects = new ArrayList<DomainObject>();
	private ArrayList<DomainObject> dirtyObjects = new ArrayList<DomainObject>();
	private ArrayList<DomainObject> removedObjects = new ArrayList<DomainObject>();
	
	private static ThreadLocal<UnitOfWork> current = new ThreadLocal<UnitOfWork>();
	
	
	public void registerNew(DomainObject obj)
	{
		Assert.assertNotNull("id not null", obj.getId());
		Assert.assertTrue("object not dirty", !dirtyObjects.contains(obj));
		Assert.assertTrue("object not removed", !removedObjects.contains(obj));
		Assert.assertTrue("object not already registered new", !newObjects.contains(obj));
		newObjects.add(obj);
	}
	
	public void registerDirty(DomainObject obj)
	{
		Assert.assertNotNull("id not null", obj.getId());
		Assert.assertTrue("object not removed", !removedObjects.contains(obj));
		if(!dirtyObjects.contains(obj) && !newObjects.contains(obj))
		{
			dirtyObjects.add(obj);
		}
	}
	
	public void registerRemoved(DomainObject obj)
	{
		Assert.assertNotNull("id not null", obj.getId());
		if(newObjects.remove(obj)) return;
		dirtyObjects.remove(obj);
		if(!removedObjects.contains(obj))
		{
			removedObjects.add(obj);
		}
	}
	
	public void registerClean(DomainObject obj)
	{
		//put an Identity map
	}
	
	public void commit()
	{
		insertNew();
		updateDirty();
		deleteRemoved();
	}

	private void deleteRemoved() {
		// TODO Auto-generated method stub
		
	}

	private void updateDirty() {
		// TODO Auto-generated method stub
		
	}

	private void insertNew() 
	{
		//TODO Auto-generated method stub
		
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
	
	
	
	
}
