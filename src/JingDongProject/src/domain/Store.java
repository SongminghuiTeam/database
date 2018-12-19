package domain;

public class Store {
	private Long storeID;
	private String userID;
	private String storeName;
	private Float score;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public Long getStoreID() {
		return storeID;
	}
	public void setStoreID(Long storeID) {
		this.storeID = storeID;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}

	
}
