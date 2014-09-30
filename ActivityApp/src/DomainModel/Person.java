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
	GoalList myGoals;
	PendingFriendsList myPendingFriends;
	PersonDataMapper personMapper;
	
	/**
	 * Constructor
	 */
	public Person() {
		myFriends = new FriendsList();
		myGoals = new GoalList();
		myPendingFriends = new PendingFriendsList();
		personMapper = new PersonDataMapper();
	}
	
	/**
	 * Constructor with parameters
	 * @param myFriends
	 * @param myGoals
	 * @param myPendingFriends
	 */
	public Person(FriendsList myFriends, GoalList myGoals, PendingFriendsList myPendingFriends) {
		this.myFriends = myFriends;
		this.myGoals = myGoals;
		this.myPendingFriends = myPendingFriends;
		personMapper = new PersonDataMapper();
	}
	
	public static Person findPerson(String username, String password) {
		PersonDataMapper pdm;
		try{
		pdm = MyThreadLocal.get();
		}
		catch(Exception e) {
			MyThreadLocal.set(new PersonDataMapper());
			pdm = MyThreadLocal.get();
		}
		return pdm.findPerson("Lonny","pw");
	}
	
	

}
