package dataMappers;

import java.util.ArrayList;

import domainLogic.Person;

/**
 * @author josh
 *
 */
public class IdentityMap
{
	ArrayList<Person> list;
	/**
	 * Constructor
	 */
	public IdentityMap() {
		list = new ArrayList<Person>();
	}
	
	/**
	 * @param p
	 */
	public void add(Person p) {
		list.add(p);
	}
	
	/**
	 * @param id
	 * @return Person
	 */
	public Person find(int id) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getUserID() == id) {
				return list.get(i);
			}
		}
		return null;
	}
	/**
	 * @param username
	 * @return Person
	 */
	public Person find(String username) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getUserName().equals(username)) {
				return list.get(i);
			}
		}
		return null;
	}
	/**
	 * simple reset
	 */
	public void clear() {
		list = new ArrayList<Person>();
	}
}
