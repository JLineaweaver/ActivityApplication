package dataMappers;
import java.sql.Connection;

import gateways.FriendsTableDataGateway;
import gateways.PendingFriendsTableDataGateway;
import gateways.PersonRowDataGateway;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	Connection con;
	IdentityMap im;
	Person testPerson1 = new Person("testPerson1", "testPerson1PW", "testPerson1DN", -1);
	Person testPerson2 = new Person("testPerson2", "testPerson2PW", "testPerson2DN", -1);
	Person testPerson3 = new Person("testPerson3", "testPerson3PW", "testPerson3DN", -1); 
	
	String db = "fitness2";
	String url = "lsagroup2.cbzhjl6tpflt.us-east-1.rds.amazonaws.com";
	String dbUser = "lsagroup2";
	String dbPassword = "lsagroup2";
	/**
	 * Constructor
	 */
	public DataMapper() {
		try {
			String connectFormat = "jdbc:mysql://%s/%s?user=%s&password=%s";
			String connectURL = String.format(connectFormat, url, db, dbUser, dbPassword);
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(connectURL);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		im = new IdentityMap();
		im.add(testPerson1);
		im.add(testPerson2);
		im.add(testPerson3);
		
	}

	/**
	 * @param username
	 * @param password
	 * @return Person
	 * @throws SQLException
	 */
	public Person findPerson(String username, String password) throws SQLException {
		if(im.find(username)!= null) {
			return im.find(username);
		}
		PersonRowDataGateway prdg = new PersonRowDataGateway(username,password, con);
		ResultSet rs = prdg.findPerson();
		rs.next();
		int id = rs.getInt("userID");
		
		Person f = new Person(rs.getString("userName"), rs.getString("password"), rs.getString("displayName"), id);
		im.add(f);
		im.find(id).addLists(getFriendsList(id), getIncomingPendingFriendsList(id), getOutgoingPendingFriendList(id));
		f.addLists(getFriendsList(id), getIncomingPendingFriendsList(id), getOutgoingPendingFriendList(id));
		return f;
	
	}
	/**
	 * @param ID
	 * @return Person
	 * @throws SQLException
	 */
	public Person findPerson(int ID) throws SQLException {
		if(im.find(ID)!= null) {
			return im.find(ID);
		}
		PersonRowDataGateway prdg = new PersonRowDataGateway(ID, con);
		ResultSet rs = prdg.findPerson();
		rs.next();
		int id = rs.getInt("userID");
		Person f = new Person(rs.getString("userName"), rs.getString("password"), rs.getString("displayName"), id);
		im.add(f);
		im.find(id).addLists(getFriendsList(id), getIncomingPendingFriendsList(id), getOutgoingPendingFriendList(id));
		f.addLists(getFriendsList(id), getIncomingPendingFriendsList(id), getOutgoingPendingFriendList(id));
		return f;
	}
	/**
	 * @param userName
	 * @return Person
	 * @throws SQLException
	 */
	public Person findPerson(String userName) throws SQLException {
		if(im.find(userName)!= null) {
			return im.find(userName);
		}
		PersonRowDataGateway prdg = new PersonRowDataGateway(userName, con);
		ResultSet rs = prdg.findPerson();
		rs.next();
		int id = rs.getInt("userID");
		Person f = new Person(rs.getString("userName"), rs.getString("password"), rs.getString("displayName"), id);
		im.add(f);
		im.find(id).addLists(getFriendsList(id), getIncomingPendingFriendsList(id), getOutgoingPendingFriendList(id));
		f.addLists(getFriendsList(id), getIncomingPendingFriendsList(id), getOutgoingPendingFriendList(id));
		return f;
	}
	/**
	 * @param ID
	 * @return Friend
	 * @throws SQLException
	 */
	public Friend findFriend(int ID) throws SQLException {
		PersonRowDataGateway prdg = new PersonRowDataGateway(ID, con);
		ResultSet rs = prdg.findPerson();
		rs.next();

		Friend f = new Friend(rs.getString("userName"), rs.getString("displayName"));
		
		return f;
	}
	/**
	 * @param ID
	 * @return Person
	 * @throws SQLException
	 */
	public Person findDBPerson(int ID) throws SQLException  {
		PersonRowDataGateway prdg = new PersonRowDataGateway(ID, con);
		ResultSet rs = prdg.findPerson();
		rs.next();
		int id = rs.getInt("userID");
		Person f = new Person(rs.getString("userName"), rs.getString("password"), rs.getString("displayName"), id);
		f.addLists(getFriendsList(id), getIncomingPendingFriendsList(id), getOutgoingPendingFriendList(id));
		return f;
	}
	/**
	 * @param myPerson
	 * @return Boolean
	 * @throws SQLException
	 */
	public boolean storePerson(Person myPerson) throws SQLException {
		Person oldPerson = findDBPerson(myPerson.getUserID());
		PersonRowDataGateway prdg = new PersonRowDataGateway(myPerson.getUserID(), con);
		updateDisplayName(myPerson, oldPerson, prdg);
		FriendsTableDataGateway ftdg = new FriendsTableDataGateway(con);
		updateFriends(myPerson, oldPerson, ftdg);
		
		PendingFriendsTableDataGateway pftdg = new PendingFriendsTableDataGateway(con);
		updatePendingFriends(myPerson,oldPerson, pftdg);
		
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
		
		if(myPerson.getFriends() == null);
		FriendsList myFriends = myPerson.getFriends();
		ArrayList<Friend> myFriendsList = myFriends.getFriendList();
		FriendsList oldFriends = oldPerson.getFriends();
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

		if(myPerson.getTheOutgoingPendingFriendList() == null) 
			return false;
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
	/**
	 * @param p
	 * 
	 */
	public void createPerson(Person p) {
		PersonRowDataGateway prdg = null;
		try
		{
			prdg = new PersonRowDataGateway(p.getUserID(), con);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prdg.createPerson(p.getUserName(),p.getDisplayName(),p.getPassword());
		
		
	}

	
	/**
	 * @param ID
	 * @return FriendsList
	 */
	public FriendsList getFriendsList(int ID) {
		FriendsTableDataGateway ftdg = null;
		ArrayList<Friend> friendList = null;
		try
		{
			ftdg = new FriendsTableDataGateway(con);
		
		ResultSet rs = ftdg.getFriendsRequester(ID);
		friendList = new ArrayList<Friend>();
		int requestee;
		int requester;
		while(rs.next()) {
			 requestee = rs.getInt("UserIDOfRequestee");
			 requester = rs.getInt("UserIDOfRequester");
			 if(requestee == ID) {
				 friendList.add(findFriend(requester));
			 }
			 else {
			friendList.add(findFriend(requestee));
			 }
		} 
			 rs = ftdg.getFriendsRequestee(ID);
				friendList = new ArrayList<Friend>();
				
				while(rs.next()) {
					 requestee = rs.getInt("UserIDOfRequestee");
					 requester = rs.getInt("UserIDOfRequester");
					 if(requestee == ID) {
						 friendList.add(findFriend(requester));
					 }
					 else {
					friendList.add(findFriend(requestee));
					 }
		}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FriendsList fr = new FriendsList(friendList);
		
		
		return fr;
	}
	
	/**
	 * @param ID
	 * @return IncomingPendingFriendsList
	 */
	public IncomingPendingFriendsList getIncomingPendingFriendsList(int ID) {
		PendingFriendsTableDataGateway pftdg = null;
		ArrayList<Person> incomingList = null;
		try
		{
			pftdg = new PendingFriendsTableDataGateway(con);
		
		ResultSet rs = pftdg.getIncomingPendingFriends(ID);
		incomingList = new ArrayList<Person>();
		while(rs.next()) {
			incomingList.add(findPerson(rs.getInt("UserIDOfRequester")));
			 }
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IncomingPendingFriendsList fr = new IncomingPendingFriendsList(incomingList);
		
		return fr;
	}
	/**
	 * @param ID
	 * @return OutgoingPendingFriendList
	 */
	public OutgoingPendingFriendList getOutgoingPendingFriendList(int ID) {
		PendingFriendsTableDataGateway pftdg = null;
		ArrayList<Person> outgoingList = null;
		try
		{
			pftdg = new PendingFriendsTableDataGateway(con);
		
		ResultSet rs = pftdg.getOutgoingPendingFriends(ID);
		outgoingList = new ArrayList<Person>();
		while(rs.next()) {
			outgoingList.add(findPerson(rs.getInt("UserIDOfRequestee")));
			 }
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OutgoingPendingFriendList fr = new OutgoingPendingFriendList(outgoingList);
		
		return fr;
	
	}
	/**
	 * resetMap
	 */
	public void resetMap() 
	{
		im.clear();
	}	
}


