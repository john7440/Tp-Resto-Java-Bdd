package dao;

import model.Order;

public interface OrderDao {
	int saveOrder(Order order);
	int getLastOrderNumber();
	Order getOrderById(int id);

}
