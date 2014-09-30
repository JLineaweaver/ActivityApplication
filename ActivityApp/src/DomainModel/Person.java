package DomainModel;

import java.util.ArrayList;

import DataMappers.MyThreadLocal;
import DataMappers.PersonDataMapper;

/**
 * @author josh
 *
 */
public class Person
{
	FriendsList myFriends;
	PendingFriendsList myPendingFriends;
	
	/**
	 * Constructor
	 */
	public Person() {
		myFriends = new FriendsList();
		myPendingFriends = new PendingFriendsList();
	}
	
	/**
	 * Constructor with parameters
	 * @param myFriends
	 * @param myPendingFriends 
	 */
	public Person(FriendsList myFriends, PendingFriendsList myPendingFriends) {
		this.myFriends = myFriends;
		this.myPendingFriends = myPendingFriends;
	}
	
	/**
	 * Static find method for persisting a person
	 * @param username
	 * @param password
	 * @return
	 */
	public static Person findPerson(String username, String password) {
		PersonDataMapper pdm;
		if(MyThreadLocal.get() != null) {
			pdm = MyThreadLocal.get();
		return pdm.findPerson(username,password);
		}
		else {
			pdm = new PersonDataMapper();
			MyThreadLocal.set(pdm);
			return pdm.findPerson(username,password);
		}
		
	}
	
	

}
