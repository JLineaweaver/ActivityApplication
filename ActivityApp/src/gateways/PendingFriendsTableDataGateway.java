package gateways;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author josh
 *
 */
public class PendingFriendsTableDataGateway
{
		
		Connection con = null;
		ResultSet rs = null;
		
	

	/**
	 * @param con
	 * @throws SQLException
	 */
	public PendingFriendsTableDataGateway(Connection con) throws SQLException {
		this.con = con;
	}
	
	/**
	 * @param userID
	 * @return ResultSet
	 */
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
	
	/**
	 * @param userID
	 * @return ResultSet
	 */
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
	/**
	 * @param userID
	 * @param username
	 */
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
	
	/**
	 * @param userID
	 * @param username
	 */
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
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
