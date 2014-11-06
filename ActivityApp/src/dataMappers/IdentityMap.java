package dataMappers;

import java.util.ArrayList;

import domainLogic.Person;

public class IdentityMap
{
	ArrayList<Person> list;
	public IdentityMap() {
		list = new ArrayList<Person>();
	}
	
	public void add(Person p) {
		list.add(p);
	}
	
	public Person find(int id) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getUserID() == id) {
				return list.get(i);
			}
		}
		return null;
	}
	public Person find(String username) {
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getUserName().equals(username)) {
				return list.get(i);
			}
		}
		return null;
	}
}
