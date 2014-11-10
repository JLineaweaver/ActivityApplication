package dataMappers;

import static org.junit.Assert.*;

import org.junit.Test;

import domainLogic.Person;

/**
 * @author josh
 *
 */
public class TestIdentityMap
{

	/**
	 * Simple test making sure you can add to the Identity Map
	 */
	@Test
	public void test()
	{
		IdentityMap im = new IdentityMap();
		Person p = new Person();
		im.add(p);
		assertTrue(im.find(-1)!= null);
	}

}
