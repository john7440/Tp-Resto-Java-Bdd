package model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private int id;
	private int orderNumber;
	private Date orderDate;
	private double totalPrice;
	private List<OrderDetail> orderDetails;
	
	public Order() {
        this.orderDetails = new ArrayList<>();
    }
    
    public Order(int orderNumber, double totalPrice) {
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.orderDetails = new ArrayList<>();
    }

}
