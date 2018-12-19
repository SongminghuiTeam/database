package domain;

public class User {
	private String userID;
	private String phone;
	private String mail;
	private String password;
	
	public User(String userID, String phone, String mail, String password) {
		this.userID = userID;
		this.phone = phone;
		this.mail = mail;
		this.password = password;
	}
	
	public User() {}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
