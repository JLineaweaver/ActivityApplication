package dataMappers;

import static org.junit.Assert.*;

import org.junit.Test;

import domainLogic.Person;

public class TestIdentityMap
{

	@Test
	public void test()
	{
		IdentityMap im = new IdentityMap();
		Person p = new Person();
		im.add(p);
		assertTrue(im.find(-1)!= null);
	}

}
