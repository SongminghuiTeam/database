package domain;

public class News {

	private Long newsID;
	private Long storeID;
	private String content;
	private String publishTime;
	private Long visitVolume;
	public Long getNewsID() {
		return newsID;
	}
	public void setNewsID(Long newsID) {
		this.newsID = newsID;
	}
	public Long getStoreID() {
		return storeID;
	}
	public void setStoreID(Long storeID) {
		this.storeID = storeID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public Long getVisitVolume() {
		return visitVolume;
	}
	public void setVisitVolume(Long visitVolume) {
		this.visitVolume = visitVolume;
	}
	
}
