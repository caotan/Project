/**
 * 
 */
package fr.epita.iam.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.datamodel.User;

/**
 * @author Administrator
 *
 */
public class UserDAO {
	
	public List<User> findAll(){
		try {
			List<User> lstUser = new ArrayList<User>();
			PreparedStatement ps = ConnectDB.getConection().prepareStatement("Select * from User");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				User u = new User();
				u.setuId(rs.getInt("uId"));
				u.setdisplayName(rs.getString("displayName"));
				u.setEmail(rs.getString("email"));
				u.setaddress(rs.getString("address"));
				lstUser.add(u);
			}
			return lstUser;
		} catch (Exception e) {
			return null;
		}
	}
	
	public User find(int id){
		try {
			User u = new User();
			PreparedStatement ps = ConnectDB.getConection().prepareStatement("Select * from User where uId = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				u.setuId(rs.getInt("uId"));
				u.setdisplayName(rs.getString("displayName"));
				u.setEmail(rs.getString("email"));
				u.setaddress(rs.getString("address"));
			}
			return u;
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean create(User u){
		try {
			PreparedStatement ps = ConnectDB.getConection().prepareStatement("Insert into User values (?,?,?,?)");
			ps.setInt(1, 0);
			ps.setString(2, u.getdisplayName());
			ps.setString(3, u.getaddress());
			ps.setString(4, u.getEmail());
			
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean edit(User u){
		try {
			PreparedStatement ps = ConnectDB.getConection().prepareStatement("Update User set displayName = ?, address = ?, email = ? where uId = ?");
			ps.setString(1, u.getdisplayName());
			ps.setString(2, u.getaddress());
			ps.setString(3, u.getEmail());
			ps.setInt(4, u.getuId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean delete(int id){
		try {
			PreparedStatement ps = ConnectDB.getConection().prepareStatement("Delete from User where uId = ?");
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}
}
