package domain;

public class Classification {
	private Long classificationID;
	private String classificationName;
	private Long categoryID;
	public Long getClassificationID() {
		return classificationID;
	}
	public void setClassificationID(Long classificationID) {
		this.classificationID = classificationID;
	}
	public String getClassificationName() {
		return classificationName;
	}
	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}
	public Long getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}
	
}
