package domain;

import java.sql.Date;

public class Detailed {
	private String userID;
	private String nickName;
	private String gender;
	private String birthday;
	private String trueName;
	private String idNumber;
	private Long jdBean;
	
	public Detailed(String userID, String nickName, String gender, String birthday, String trueName, String idNumber) {
		super();
		this.userID = userID;
		this.nickName = nickName;
		this.gender = gender;
		this.birthday = birthday;
		this.trueName = trueName;
		this.idNumber = idNumber;
		this.jdBean = (long)0;
	}
	
	public Detailed(String userID, String nickName, String birthday, String trueName, String idNumber) {
		super();
		this.userID = userID;
		this.nickName = nickName;
		this.gender = "empty";
		this.birthday = birthday;
		this.trueName = trueName;
		this.idNumber = idNumber;
		this.jdBean = (long)0;
	}
	
	public Detailed() {}
	
	public Long getJdBean() {
		return jdBean;
	}

	public void setJdBean(Long jdBean) {
		this.jdBean = jdBean;
	}

	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getTrueName() {
		return trueName;
	}
	
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	public String getIdNumber() {
		return idNumber;
	}
	
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
}
