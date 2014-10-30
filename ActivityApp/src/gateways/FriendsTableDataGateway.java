package gateways;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class FriendsTableDataGateway
{
		
		Connection con = null;
		ResultSet rs = null;
		
		 //lsagroup2.cbzhjl6tpflt.us-east-1.rds.amazonaws.com
		String db = "fitness2";
		String url = "lsagroup2.cbzhjl6tpflt.us-east-1.rds.amazonaws.com";
		String dbUser = "lsagroup2";
		String dbPassword = "lsagroup2";
		

	public FriendsTableDataGateway() throws SQLException {
		
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
	
	public ResultSet getFriends(int userID) {
		ResultSet rs = null;
		try
		{
			String sql = "SELECT * FROM Friends F WHERE userIDOfRequester=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,userID);
			rs = ps.executeQuery();
			rs.getArray("")
			//while(rs.)
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void addFriend(int userID, String username) throws SQLException {
		PersonRowDataGateway prdg = new PersonRowDataGateway(username);
		int secondID = prdg.getID();
		try
		{
			String sql = "INSERT INTO Friends (userIDOfRequester, userIDOfRequestee) VALUES (?,?)";
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
	
	public void removeFriend(int userID, String username) throws SQLException {
		PersonRowDataGateway prdg = new PersonRowDataGateway(username);
		int secondID = prdg.getID();
		try
		{
			String sql = "DELETE FROM Friends WHERE userIDOfRequester = ? AND userIDOfRequestee = ?";
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
