package gateways;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PendingFriendsTableDataGateway
{
		
		Connection con = null;
		ResultSet rs = null;
		
	

	public PendingFriendsTableDataGateway(Connection con) throws SQLException {
		this.con = con;
	}
	
	public ResultSet getOutgoingPendingFriends(int userID) {
		ResultSet rs = null;
		try
		{
			String sql = "SELECT * FROM PendingFriends F WHERE userIDOfRequester=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,userID);
			rs = ps.executeQuery();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getIncomingPendingFriends(int userID) {
		ResultSet rs = null;
		try
		{
			String sql = "SELECT * FROM PendingFriends F WHERE userIDOfRequestee=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,userID);
			rs = ps.executeQuery();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public void addFriend(int userID, String username) {
		PersonRowDataGateway prdg = null;
		try
		{
			prdg = new PersonRowDataGateway(username, con);
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int secondID = prdg.getID();
		try
		{
			String sql = "INSERT INTO PendingFriends (userIDOfRequester, userIDOfRequestee) VALUES (?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,userID);
			ps.setInt(2,secondID);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeFriend(int userID, String username) {
		PersonRowDataGateway prdg = null;
		try
		{
			prdg = new PersonRowDataGateway(username, con);
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int secondID = prdg.getID();
		try
		{
			String sql = "DELETE FROM PendingFriends WHERE userIDOfRequester = ? AND userIDOfRequestee = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,userID);
			ps.setInt(2,secondID);
			ps.execute();
			System.out.println(rs);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
