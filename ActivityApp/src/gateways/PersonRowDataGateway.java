package gateways;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author josh
 *
 */
public class PersonRowDataGateway
{
	int id = -1;
	String username = "";
	String password = "";
	
	Connection con = null;
	ResultSet rs = null;
	
	

	/**
	 * @param id
	 * @param con
	 * @throws SQLException
	 */
	public PersonRowDataGateway(int id, Connection con) throws SQLException {
	
		this.id = id;
		this.con = con;
	}
	
	/**
	 * @param username
	 * @param password
	 * @param con
	 * @throws SQLException
	 */
	public PersonRowDataGateway(String username, String password, Connection con) throws SQLException {
			this.con = con;
			this.username = username;
			this.password = password;
			try
			{
				String sql = "SELECT * FROM Person P WHERE P.userName = ? AND P.password = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				rs = ps.executeQuery();
				rs.next();
				id = rs.getInt("userID");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * @param username
	 * @param con
	 * @throws SQLException
	 */
	public PersonRowDataGateway(String username, Connection con) throws SQLException {
		
			this.con = con;
			this.username = username;
			try
			{
				String sql = "SELECT * FROM Person P WHERE P.userName = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				rs = ps.executeQuery();
				rs.next();
				id = rs.getInt("userID");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	/**
	 * @return ResultSet
	 */
	public ResultSet findPerson() {
		ResultSet rs = null;
		try
		{
			String sql = "SELECT * FROM Person P WHERE P.userID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			//System.out.println(rs);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * @return id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * @param displayName
	 */
	public void updateDisplayName(String displayName) {
		try
		{
			String sql = "UPDATE Person P SET displayName=? WHERE P.userID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,displayName);
			ps.setInt(2,id);
			ps.executeUpdate();
			//System.out.println(rs);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param userName
	 * @param displayName
	 * @param password
	 */
	public void createPerson(String userName, String displayName, String password) {
		try
		{
			String sql = "INSERT INTO Person (userName, displayName, password) VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,userName);
			ps.setString(2,displayName);
			ps.setString(3,password);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}
