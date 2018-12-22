package domain;

public class Product {
	private Long productID;
	private String productName;
	private Float weight;
	private Long visitVolume;
	private String model;
	private String description;
	private Long categoryID;
	private Long jdBean;
	private boolean status;
	private Float price;
	private Long storeID;
	
	public Product(String productName, Float weight, String model, String description, Long categoryID, Long jdBean, Float price, Long storeID) {
		this.productName = productName;
		this.weight = weight;
		this.visitVolume = (long)0;
		this.model = model;
		this.description = description;
		this.categoryID = categoryID;
		this.jdBean = jdBean;
		this.status = true;
		this.price = price;
		this.storeID = storeID;
	}
	
	public Product(String productName, String model, String description, Long categoryID, Long jdBean, Float price, Long storeID) {
		this.productName = productName;
		this.weight = (float)0.0;
		this.visitVolume = (long)0;
		this.model = model;
		this.description = description;
		this.categoryID = categoryID;
		this.jdBean = jdBean;
		this.status = true;
		this.price = price;
		this.storeID = storeID;
	}
	
	public Product(String productName, Float weight, String model, String description, Long categoryID, Float price,Long storeID) {
		this.productName = productName;
		this.weight = weight;
		this.visitVolume = (long)0;
		this.model = model;
		this.description = description;
		this.categoryID = categoryID;
		this.jdBean = (long)0;
		this.status = true;
		this.price = price;
		this.storeID = storeID;
	}
	
	public Product(String productName, String model, String description, Long categoryID, Float price,Long storeID) {
		this.productName = productName;
		this.weight = (float)0.0;
		this.visitVolume = (long)0;
		this.model = model;
		this.description = description;
		this.categoryID = categoryID;
		this.jdBean = (long)0;
		this.status = true;
		this.price = price;
		this.storeID = storeID;
	}

	public Product() {}
	
	
	public Long getStoreID() {
		return storeID;
	}

	public void setStoreID(Long storeID) {
		this.storeID = storeID;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public Long getJdBean() {
		return jdBean;
	}
	
	public void setJdBean(Long jdBean) {
		this.jdBean = jdBean;
	}
}
