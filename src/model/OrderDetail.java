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
	
	
	public void calculateTotal() {
		this.menuTotal = 0;
		if (appetizer != null) menuTotal += appetizer.getPrice();
		if (mainCourse != null) menuTotal += mainCourse.getPrice();
		if (side != null) menuTotal += side.getPrice();
        if (drink != null) menuTotal += drink.getPrice();
        if (dessert != null) menuTotal += dessert.getPrice();
	}

}
