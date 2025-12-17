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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
}
