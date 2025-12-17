package dao;

import model.Order;

public interface OrderDAO {
	int saveOrder(Order order);
	int getLastOrderNumber();
	Order getOrderById(int id);

}
