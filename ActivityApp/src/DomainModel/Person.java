package DomainModel;

import java.util.ArrayList;

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
	
	//public static Person findPerson(String username, String password) {
		//ThreadLocal<PersonDataMapper> personMapper;
		//return personMapper.findPerson(username);
	//}
	
	

}
