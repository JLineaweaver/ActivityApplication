package DomainModel;

import Commands.UnitOfWork;

public class DomainObject 
{
	private String isObjectNull;
	
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

	public String getIsObjectNull() 
	{
		isObjectNull = "id not null";
		return isObjectNull;
	}
	
}
