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
		try
		{
			pendingFriendsGateway = new PendingFriendsTableDataGateway();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		//if(oldPerson.getUserID() == -1) {
			//createPerson(myPerson);
		//}
		//else {
		PersonRowDataGateway prdg = new PersonRowDataGateway(myPerson.getUserID());
		updateDisplayName(myPerson, oldPerson, prdg);
		updateFriends(myPerson, oldPerson, friendsGateway);
		updatePendingFriends(myPerson,oldPerson, pendingFriendsGateway);
		//}
		return true;
	}
	private boolean updateDisplayName(Person myPerson, Person oldPerson, PersonRowDataGateway prdg) {
		if(!myPerson.getDisplayName().equals(oldPerson.getDisplayName()))
		{
			prdg.updateDisplayName(myPerson.getDisplayName());
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

		ArrayList<Person> myFriendsList = myPerson.getTheOutgoingPendingFriendList();
		ArrayList<Person> oldFriendsList = oldPerson.getTheOutgoingPendingFriendList();

		for(int i = 0; i<myFriendsList.size(); i++) {
			boolean found = false;
			for(int j = 0; j<oldFriendsList.size(); j++) {
				if(myFriendsList.get(i).getUserID() == oldFriendsList.get(j).getUserID()) {
					found = true;
				}
			}
			if(!found) {
				pftdg.addFriend(myPerson.getUserID(),myFriendsList.get(i).getUserName());
			}
		}
		for(int j = 0; j<oldFriendsList.size(); j++) {
			boolean found = false;
			for(int i = 0; i<myFriendsList.size(); i++) {
				if(myFriendsList.get(i).getUserID() == oldFriendsList.get(j).getUserID()) {
					found = true;
				}
			}
			if(!found) {
				pftdg.removeFriend(myPerson.getUserID(),oldFriendsList.get(j).getUserName());
			}
		}
		return true;
	}
	public void createPerson(Person p) {
		PersonRowDataGateway prdg = null;
		FriendsTableDataGateway ftdg = null;
		PendingFriendsTableDataGateway pftdg = null;
		try
		{
			prdg = new PersonRowDataGateway(p.getUserID());
			ftdg = new FriendsTableDataGateway();
			pftdg = new PendingFriendsTableDataGateway();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prdg.createPerson(p.getUserName(),p.getDisplayName(),p.getPassword());
		Person myPerson = null;
		try
		{
			myPerson = findPerson(p.getUserName(),p.getPassword());
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		FriendsList myFriends = p.getFriends();
//		ArrayList<Friend> myFriendsList = myFriends.getFriendList();
//		for(int i = 0; i<myFriendsList.size(); i++) {
//			
//				ftdg.addFriend(myPerson.getUserID(),myFriendsList.get(i).getUserName());
//			}
//		ArrayList<Person> myPFriendsList = p.getTheOutgoingPendingFriendList();
//		for( int i = 0; i<myPFriendsList.size(); i++) {
//			pftdg.addFriend(myPerson.getUserID(),myPFriendsList.get(i).getUserName());
//		}
	}
}