package domainLogic;


public class DomainObject 
{
	private String isObjectNull;
	
	
	protected void markNew(DomainObject obj)
	{
		UnitOfWork.getCurrent().registerNew(obj);
	}
	
	protected void markClean(DomainObject obj)
	{
		UnitOfWork.getCurrent().registerClean(obj);
	}
	
	protected void markDirty(DomainObject obj)
	{
		UnitOfWork.getCurrent().registerDirty(obj);
	}
	
	protected void markRemoved(DomainObject obj)
	{
		UnitOfWork.getCurrent().registerRemoved(obj);
	}
	
	protected void testMarkNew(DomainObject obj)
	{
		MockUnitOfWork.getCurrent().registerNew(obj);
	}
	
	protected void testMarkDirty(DomainObject obj)
	{
		MockUnitOfWork.getCurrent().registerDirty(obj);
	}
	
	protected void testMarkRemoved(DomainObject obj)
	{
		MockUnitOfWork.getCurrent().registerRemoved(obj);
	}

	public String getIsObjectNull() 
	{
		isObjectNull = "id not null";
		return isObjectNull;
	}
	
}
