package model;

public class OrderDetail {
	private int id;
    private int orderId;
    private int menuNumber;
    private MenuItem appetizer;
    private MenuItem mainCourse;
    private MenuItem side;
    private MenuItem drink;
    private MenuItem dessert;
    private double menuTotal;
    
    //constructeurs
	public OrderDetail() {}
	    
	
	public OrderDetail(int menuNumber, MenuItem appetizer, MenuItem mainCourse, 
	                      MenuItem side, MenuItem drink, MenuItem dessert) {
	        this.menuNumber = menuNumber;
	        this.appetizer = appetizer;
	        this.mainCourse = mainCourse;
	        this.side = side;
	        this.drink = drink;
	        this.dessert = dessert;
	        calculateTotal();
	  }
	
	// methode
	public void calculateTotal() {
		this.menuTotal = 0;
		if (appetizer != null) menuTotal += appetizer.getPrice();
		if (mainCourse != null) menuTotal += mainCourse.getPrice();
		if (side != null) menuTotal += side.getPrice();
        if (drink != null) menuTotal += drink.getPrice();
        if (dessert != null) menuTotal += dessert.getPrice();
	}

    // getters et setters
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getMenuNumber() {
		return menuNumber;
	}


	public void setMenuNumber(int menuNumber) {
		this.menuNumber = menuNumber;
	}
	
	public MenuItem getAppetizer() { 
		return appetizer; 
	}
	
	public void setAppetizer(MenuItem appetizer) {
		this.appetizer = appetizer;
		calculateTotal();
	}


	public MenuItem getMainCourse() {
		return mainCourse;
	}


	public void setMainCourse(MenuItem mainCourse) {
		this.mainCourse = mainCourse;
		calculateTotal();
	}


	public MenuItem getSide() {
		return side;
	}


	public void setSide(MenuItem side) {
		this.side = side;
		calculateTotal();
	}


	public MenuItem getDrink() {
		return drink;
	}


	public void setDrink(MenuItem drink) {
		this.drink = drink;
		calculateTotal();
	}


	public MenuItem getDessert() {
		return dessert;
	}


	public void setDessert(MenuItem dessert) {
		this.dessert = dessert;
		calculateTotal();
	}


	public double getMenuTotal() {
		return menuTotal;
	}


	public void setMenuTotal(double menuTotal) {
		this.menuTotal = menuTotal;
	}
	
	

}
