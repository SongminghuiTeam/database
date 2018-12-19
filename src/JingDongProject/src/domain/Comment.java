package domain;

public class Comment {
	private Long commentID;
	private String commentTime;
	private String content;
	private Integer score;
	private Long orderID;
	private Long productID;
	public Long getCommentID() {
		return commentID;
	}
	public void setCommentID(Long commentID) {
		this.commentID = commentID;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Long getOrderID() {
		return orderID;
	}
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}
	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long productID) {
		this.productID = productID;
	}
	
}
