package a00754887.assignment2.data;

/**
 * this is the data class for Inventory
 * 
 * @author AoAo_Feng
 * 
 */
public class Inventory {
	private String make;
	private String description;
	private String modelNumber;
	private String sku;
	private int quantityInStock;
	private int quantitySold;
	private double purchasePrice;
	private double retailPrice;
	private int numberRented;

	/**
	 * default constructor for Inventory
	 */
	public Inventory() {

	}

	/**
	 * @param make
	 * @param description
	 * @param modelNumber
	 * @param sku
	 * @param quantityInStock
	 * @param quantitySold
	 * @param purchasePrice
	 * @param retailPrice
	 * @param numberRented
	 */
	public Inventory(String make, String description, String modelNumber,
			String sku, int quantityInStock, int quantitySold,
			double purchasePrice, double retailPrice, int numberRented) {
		super();
		this.make = make;
		this.description = description;
		this.modelNumber = modelNumber;
		this.sku = sku;
		this.quantityInStock = quantityInStock;
		this.quantitySold = quantitySold;
		this.purchasePrice = purchasePrice;
		this.retailPrice = retailPrice;
		this.numberRented = numberRented;
	}

	/**
	 * @return the numberRented
	 */
	public int getNumberRented() {
		return numberRented;
	}

	/**
	 * @param numberRented
	 *            the numberRented to set
	 */
	public void setNumberRented(int numberRented) {
		this.numberRented = numberRented;
	}

	/**
	 * @return the maker
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the modelNumber
	 */
	public String getModelNumber() {
		return modelNumber;
	}

	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @return the quantityInStock
	 */
	public int getQuantityInStock() {
		return quantityInStock;
	}

	/**
	 * @return the quantitySold
	 */
	public int getQuantitySold() {
		return quantitySold;
	}

	/**
	 * @return the purchasePrice
	 */
	public double getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * @return the retailPrice
	 */
	public double getRetailPrice() {
		return retailPrice;
	}

	/**
	 * @param maker
	 *            the maker to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param modelNumber
	 *            the modelNumber to set
	 */
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	/**
	 * @param sku
	 *            the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @param quantityInStock
	 *            the quantityInStock to set
	 */
	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	/**
	 * @param quantitySold
	 *            the quantitySold to set
	 */
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}

	/**
	 * @param purchasePrice
	 *            the purchasePrice to set
	 */
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * @param retailPrice
	 *            the retailPrice to set
	 */
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [_make=" + make + ", _modelNumber=" + modelNumber
				+ ", _description=" + description + ", _sku=" + sku
				+ ", _purchasePrice=" + purchasePrice + ", _retailPrice="
				+ retailPrice + ", _quantityInStock=" + quantityInStock
				+ ", _quantitySold=" + quantitySold + ", _numberRented="
				+ numberRented + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Inventory))
			return false;
		// TODO Auto-generated method stub
		Inventory rhsInv = (Inventory) obj;
		if (rhsInv.getMake().equals(make)
				&& rhsInv.getDescription().equals(description)
				&& rhsInv.getModelNumber().equals(modelNumber)
				&& rhsInv.getSku().equals(sku)
				&& rhsInv.getQuantityInStock() == quantityInStock
				&& rhsInv.getQuantitySold() == quantitySold
				&& rhsInv.getPurchasePrice() == purchasePrice
				&& rhsInv.getRetailPrice() == retailPrice
				&& rhsInv.getNumberRented() == numberRented)
			return true;
		return false;
	}

	/**
	 * return a simple version of toString, only show make and modelNumber
	 * 
	 * @return string of detail
	 */
	public String toSimpleString() {
		return "Item [_make: " + make + ", _model: " + modelNumber + "]";
	}

	/**
	 * this method returns a string representation of details of an inventory
	 * object
	 * 
	 * @return String of detail
	 */
	public String detailToString() {
		return make + ", " + modelNumber + ", " + description
				+ " , Unit Price: $" + retailPrice;
	}

}
