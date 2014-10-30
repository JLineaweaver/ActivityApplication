package dataMappers;

import gateways.FriendsTableDataGateway;
import gateways.PendingFriendsTableDataGateway;
import gateways.PersonRowDataGateway;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mockGateways.MockFriendsTableDataGateway;
import mockGateways.MockPendingFriendsTableDataGateway;
import mockGateways.MockPersonRowDataGateway;
import domainLogic.FriendsList;
import domainLogic.IncomingPendingFriendsList;
import domainLogic.OutgoingPendingFriendList;
import domainLogic.Person;
/**
 * @author josh
 * Class for the person data mapper
 * Should run in a thread local
 */
public class DataMapper 
{
	FriendsTableDataGateway friendsGateway;
	PendingFriendsTableDataGateway pendingFriendsGateway;
	PersonRowDataGateway personGateway;
	public DataMapper() {
		//personGateway = new PersonRowDataGateway();
		friendsGateway = new FriendsTableDataGateway();
		pendingFriendsGateway = new PendingFriendsTableDataGateway();
	}

	
	public Person findPerson(String username, String password) throws SQLException {
		PersonRowDataGateway prdg = new PersonRowDataGateway(username,password);
		ResultSet rs = prdg.findPerson();
		
	}
	
	public Person findPerson(int ID) throws SQLException {
		PersonRowDataGateway prdg = new PersonRowDataGateway(ID);
		ResultSet rs = prdg.findPerson();
	}
	public Person findPerson(String userName) {
		MockPersonRowDataGateway mprdg = new MockPersonRowDataGateway();
		return mprdg.findPerson(userName);
	}
	
	public boolean storePerson(Person myPerson) throws SQLException {
		Person oldPerson = findPerson(myPerson.getUserID());
		PersonRowDataGateway prdg = new PersonRowDataGateway(myPerson.getUserID());
		updatePassword(myPerson, oldPerson, prdg);
		updateFriends(myPerson, oldPerson, friendsGateway);
		updatePendingFriends(myPerson,oldPerson, pendingFriendsGateway);
		
		return true;
	}
	
	private boolean updatePassword(Person myPerson, Person oldPerson, PersonRowDataGateway prdg) {
		if(!myPerson.getPassword().equals(oldPerson.getPassword()))
		{
			prdg.updatePassword(myPerson.getPassword());
			return true;
		}
		return false;
	}
	private boolean updateFriends(Person myPerson, Person oldPerson, FriendsTableDataGateway ftdg) {
		
		FriendsList myFriends = myPerson.getFriends();
		ArrayList<Person> myFriendsList = myFriends.getFriendList();
		FriendsList oldFriends = myPerson.getFriends();
		ArrayList<Person> oldFriendsList = oldFriends.getFriendList();

		
		for(int i = 0; i<myFriendsList.size(); i++) {
			boolean found = false;
			for(int j = 0; j<oldFriendsList.size(); j++) {
				if(myFriendsList.get(i).getUserName() == oldFriendsList.get(j).getUserName()) {
					found = true;
				}
			}
			if(!found) {
				ftdg.addFriend(myPerson.getUserID(),myFriendsList.get(i).getUserID());
			}
		}
		
		for(int j = 0; j<oldFriendsList.size(); j++) {
			boolean found = false;
			for(int i = 0; i<myFriendsList.size(); i++) {
				if(myFriendsList.get(i).getUserName() == oldFriendsList.get(j).getUserName()) {
					found = true;
				}
			}
			if(!found) {
				ftdg.removeFriend(myPerson.getUserID(),oldFriendsList.get(j).getUserID());
			}
		}
		
		return true;
	}
	
	private boolean updatePendingFriends(Person myPerson, Person oldPerson, PendingFriendsTableDataGateway pftdg) {

		OutgoingPendingFriendList myFriends = myPerson.getOutgoingPendingFriendList();
		ArrayList<Person> myFriendsList = myFriends.getPendingFriendList();
		OutgoingPendingFriendList oldFriends = oldPerson.getOutgoingPendingFriendList();
		ArrayList<Person> oldFriendsList = oldFriends.getPendingFriendList();

		
		for(int i = 0; i<myFriendsList.size(); i++) {
			boolean found = false;
			for(int j = 0; j<oldFriendsList.size(); j++) {
				if(myFriendsList.get(i).getUserName() == oldFriendsList.get(j).getUserName()) {
					found = true;
				}
			}
			if(!found) {
				pftdg.addFriend(myPerson.getUserID(),myFriendsList.get(i).getUserID());
			}
		}
		
		for(int j = 0; j<oldFriendsList.size(); j++) {
			boolean found = false;
			for(int i = 0; i<myFriendsList.size(); i++) {
				if(myFriendsList.get(i).getUserName() == oldFriendsList.get(j).getUserName()) {
					found = true;
				}
			}
			if(!found) {
				pftdg.removeFriend(myPerson.getUserID(),oldFriendsList.get(j).getUserID());
			}
		}
		
		return true;
	}
	
}
