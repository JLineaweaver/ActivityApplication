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
		
		 //lsagroup2.cbzhjl6tpflt.us-east-1.rds.amazonaws.com
		String db = "fitness2";
		String url = "lsagroup2.cbzhjl6tpflt.us-east-1.rds.amazonaws.com";
		String dbUser = "lsagroup2";
		String dbPassword = "lsagroup2";
		

	public PendingFriendsTableDataGateway() throws SQLException {
		
			try {
			String connectFormat = "jdbc:mysql://%s/%s?user=%s&password=%s";
			String connectURL = String.format(connectFormat, url, db, dbUser, dbPassword);
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(connectURL);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
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
			prdg = new PersonRowDataGateway(username);
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
			System.out.println(rs);
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
			prdg = new PersonRowDataGateway(username);
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
