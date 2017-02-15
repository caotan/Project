/**
 * 
 */
package fr.epita.iam.datamodel;

/**
 * @author NgocTanCAO
 *
 */
public class User {
	
	private int uId;
	private String displayName;
	private String address;
	private String email;
	
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getdisplayName() {
		return displayName;
	}
	public void setdisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getaddress() {
		return address;
	}
	public void setaddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public User() {
		super();
	}
}