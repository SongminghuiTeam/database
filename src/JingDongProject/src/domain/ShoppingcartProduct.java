package domain;

public class ShoppingcartProduct {
	private Long shoppingcartID;
	private Long productID;
	
	public ShoppingcartProduct(Long shoppingcartID, Long productID) {
		this.shoppingcartID = shoppingcartID;
		this.productID = productID;
	}
	
	public ShoppingcartProduct() {}
	
	public Long getShoppingcartID() {
		return shoppingcartID;
	}
	
	public void setShoppingcartID(Long shoppingcartID) {
		this.shoppingcartID = shoppingcartID;
	}
	
	public Long getProductID() {
		return productID;
	}
	
	public void setProductID(Long productID) {
		this.productID = productID;
	}
}
