package domain;

public class TopicNews {
	private Long topicID;
	private Long newsID;
	
	public TopicNews(Long topicID, Long newsID) {
		super();
		this.topicID = topicID;
		this.newsID = newsID;
	}
	
	public TopicNews() {}

	public Long getTopicID() {
		return topicID;
	}
	
	public void setTopicID(Long topicID) {
		this.topicID = topicID;
	}
	
	public Long getNewsID() {
		return newsID;
	}
	
	public void setNewsID(Long newsID) {
		this.newsID = newsID;
	}
}
