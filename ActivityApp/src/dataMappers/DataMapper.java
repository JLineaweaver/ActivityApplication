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
import domainLogic.Friend;
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
		try {
			friendsGateway = new FriendsTableDataGateway();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pendingFriendsGateway = new PendingFriendsTableDataGateway();
	}

	
	public Person findPerson(String username, String password) throws SQLException {
		PersonRowDataGateway prdg = new PersonRowDataGateway(username,password);
		ResultSet rs = prdg.findPerson();
		rs.next();
		return new Person(rs.getString("userName"), rs.getString("password"), rs.getString("displayName"), rs.getInt("userID"));
	}
	
	public Person findPerson(int ID) throws SQLException {
		PersonRowDataGateway prdg = new PersonRowDataGateway(ID);
		ResultSet rs = prdg.findPerson();
		rs.next();
		return new Person(rs.getString("userName"), rs.getString("password"), rs.getString("displayName"), rs.getInt("userID"));
	}
	public Person findPerson(String userName) throws SQLException {
		PersonRowDataGateway prdg = new PersonRowDataGateway(userName);
		ResultSet rs = prdg.findPerson();
		rs.next();
		return new Person(rs.getString("userName"), rs.getString("password"), rs.getString("displayName"), rs.getInt("userID"));
	}
	
	public boolean storePerson(Person myPerson) throws SQLException {
		Person oldPerson = findPerson(myPerson.getUserID());
		PersonRowDataGateway prdg = new PersonRowDataGateway(myPerson.getUserID());
		updatePassword(myPerson, oldPerson, prdg);
		updateFriends(myPerson, oldPerson, friendsGateway);
		//updatePendingFriends(myPerson,oldPerson, pendingFriendsGateway);
		
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
	private boolean updateFriends(Person myPerson, Person oldPerson, FriendsTableDataGateway ftdg) throws SQLException {
		
		FriendsList myFriends = myPerson.getFriends();
		ArrayList<Friend> myFriendsList = myFriends.getFriendList();
		FriendsList oldFriends = myPerson.getFriends();
		ArrayList<Friend> oldFriendsList = oldFriends.getFriendList();

		
		for(int i = 0; i<myFriendsList.size(); i++) {
			boolean found = false;
			for(int j = 0; j<oldFriendsList.size(); j++) {
				if(myFriendsList.get(i).getUserName() == oldFriendsList.get(j).getUserName()) {
					found = true;
				}
			}
			if(!found) {
				ftdg.addFriend(myPerson.getUserID(),myFriendsList.get(i).getUserName());
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
				ftdg.removeFriend(myPerson.getUserID(),oldFriendsList.get(j).getUserName());
			}
		}
		
		return true;
	}
	
	private boolean updatePendingFriends(Person myPerson, Person oldPerson, PendingFriendsTableDataGateway pftdg) {

		OutgoingPendingFriendList myFriends = myPerson.getTheOutgoingPendingFriendList();
		ArrayList<Person> myFriendsList = myFriends.getPendingFriendList();
		OutgoingPendingFriendList oldFriends = oldPerson.getTheOutgoingPendingFriendList();
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
	
	public void createPerson(Person p) {
		PersonRowDataGateway prdg = null;
		try
		{
			prdg = new PersonRowDataGateway(p.getUserID());
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prdg.createPerson(p.getUserName(),p.getDisplayName(),p.getPassword(),p.getFriends(),p.getOutgoingPendingFriendList());
	}
	
}
