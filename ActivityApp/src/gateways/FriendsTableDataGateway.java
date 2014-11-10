package gateways;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author josh
 *
 */
public class FriendsTableDataGateway
{
		
		Connection con = null;
		ResultSet rs = null;
		

	/**
	 * @param con
	 * @throws SQLException
	 */
	public FriendsTableDataGateway(Connection con) throws SQLException {
		this.con = con;
			
	}
	
	/**
	 * @param userID
	 * @return ResultSet
	 */
	public ResultSet getFriendsRequester(int userID) {
		ResultSet rs = null;
		try
		{
			String sql = "SELECT * FROM Friends F WHERE userIDOfRequester=?";
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
	public ResultSet getFriendsRequestee(int userID) {
		ResultSet rs = null;
		try
		{
			String sql = "SELECT * FROM Friends F WHERE userIDOfRequestee=?";
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
			String sql = "INSERT INTO Friends (userIDOfRequester, userIDOfRequestee) VALUES (?,?)";
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
	 * @throws SQLException
	 */
	public void removeFriend(int userID, String username) throws SQLException {
		PersonRowDataGateway prdg = new PersonRowDataGateway(username, con);
		int secondID = prdg.getID();
		try
		{
			String sql = "DELETE FROM Friends WHERE userIDOfRequester = ? AND userIDOfRequestee = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,userID);
			ps.setInt(2,secondID);
			ps.execute();
			//System.out.println(rs);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
