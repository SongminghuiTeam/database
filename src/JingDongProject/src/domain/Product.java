package domain;

public class Product {
	private Long productID;
	private String productName;
	private Float weight;
	private Long visitVolume;
	private String model;
	private String description;
	private Long storeID;
	private Long classificationID;
	private Long jdBean;
	
	public Product(String productName, Float weight, String model, String description,
			Long storeID, Long classificationID, Long jdBean) {
		this.productName = productName;
		this.weight = weight;
		this.visitVolume = (long)0;
		this.model = model;
		this.description = description;
		this.storeID = storeID;
		this.classificationID = classificationID;
		this.jdBean = jdBean;
	}
	
	public Product(String productName, Float weight, String model, String description,
			Long storeID, Long classificationID) {
		this.productName = productName;
		this.weight = weight;
		this.visitVolume = (long)0;
		this.model = model;
		this.description = description;
		this.storeID = storeID;
		this.classificationID = classificationID;
		this.jdBean = (long)0;
	}

	public Long getProductID() {
		return productID;
	}
	
	public void setProductID(Long productID) {
		this.productID = productID;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Float getWeight() {
		return weight;
	}
	
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
	public Long getVisitVolume() {
		return visitVolume;
	}
	
	public void setVisitVolume(Long visitVolume) {
		this.visitVolume = visitVolume;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getStoreID() {
		return storeID;
	}
	
	public void setStoreID(Long storeID) {
		this.storeID = storeID;
	}
	
	public Long getClassificationID() {
		return classificationID;
	}
	
	public void setClassificationID(Long classificationID) {
		this.classificationID = classificationID;
	}
	
	public Long getJdBean() {
		return jdBean;
	}
	
	public void setJdBean(Long jdBean) {
		this.jdBean = jdBean;
	}
}
