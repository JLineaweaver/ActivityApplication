package domainLogic;


public class DomainObject 
{
	
	protected void markNew(DomainObject obj)
	{
		UnitOfWork.getCurrent().registerNew(obj);
	}
	
//	protected void markClean(DomainObject obj)
//	{
//		UnitOfWork.getCurrent().registerClean(obj);
//	}
	
	protected void markDirty(DomainObject obj)
	{
		UnitOfWork.getCurrent().registerDirty(obj);
	}
	
//	protected void markRemoved(DomainObject obj)
//	{
//		UnitOfWork.getCurrent().registerRemoved(obj);
//	}
	
}
