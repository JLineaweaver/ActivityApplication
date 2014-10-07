package DomainModel;

import Commands.UnitOfWork;

public class DomainObject 
{
	private String ID;
	
	protected void markNew()
	{
		UnitOfWork.getCurrent().registerNew(this);
	}
	
	protected void markClean()
	{
		UnitOfWork.getCurrent().registerClean(this);
	}
	
	protected void markDirty()
	{
		UnitOfWork.getCurrent().registerDirty(this);
	}
	
	protected void markRemoved()
	{
		UnitOfWork.getCurrent().registerRemoved(this);
	}

	public String getId() 
	{
		ID = "id not null";
		return ID;
	}
	
	public void setID(String newID)
	{
		ID = newID;
	}
}
