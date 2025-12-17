package dao.impl;

import java.sql.*;
import dao.OrderDAO;
import model.Order;
import db.DatabaseConnection;
import model.OrderDetail;


public class OrderDAOImpl implements OrderDAO{

	@Override
	public int saveOrder(Order order) {
		Connection connection = null;
		PreparedStatement pstatementOrder = null;
		PreparedStatement pstatementDetail = null;
		int orderId = -1;
		
		try {
			connection = DatabaseConnection.getConnection();
			connection.setAutoCommit(false);
			
			String sqlOrder = "INSERT INTO orders (order_number, total_price) VALUES (?,?)";

			pstatementOrder = connection.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
			pstatementOrder.setInt(1, order.getOrderNumber());
			pstatementOrder.setDouble(2, order.getTotalPrice());
			
			int rowsAffected = pstatementOrder.executeUpdate();
			
			if (rowsAffected > 0 ) {
				ResultSet generatedKeys = pstatementOrder.getGeneratedKeys();
				if (generatedKeys.next()) {
					orderId = generatedKeys.getInt(1);
					order.setId(orderId);		
				}
				
			 String sqlDetail = "INSERT INTO order_details (order_id, menu_number, appetizer_id," +
					 				"main_course_id, side_id, drink_id, dessert_id, menu_total)" +
					 				"VALUES (?,?,?,?,?,?,?,?)";
			 
			 pstatementDetail = connection.prepareStatement(sqlDetail);
			 
			 for (OrderDetail detail : order.getOrderDetails()) {
				 pstatementDetail.setInt(1, orderId);
				 pstatementDetail.setInt(2, detail.getMenuNumber());
				 pstatementDetail.setObject(3, detail.getAppetizer() != null ? detail.getAppetizer().getId() : null);
				 pstatementDetail.setObject(4, detail.getMainCourse() != null ? detail.getMainCourse().getId() : null);
				 pstatementDetail.setObject(5, detail.getSide() != null ? detail.getSide().getId() : null);
				 pstatementDetail.setObject(6, detail.getDrink() != null ? detail.getDrink().getId() : null);
				 pstatementDetail.setObject(7, detail.getDessert() != null ? detail.getDessert().getId() : null);
				 pstatementDetail.setDouble(8, detail.getMenuTotal());
				 pstatementDetail.addBatch();
             }
             
			 pstatementDetail.executeBatch();
         }
			 
			connection.commit();
			System.out.println("Commande enregistrée avec succès !");
			
			}catch (SQLException e) {
				if (connection != null) {
					try {
						connection.rollback();
						System.err.println("Transaction annulée: " + e.getMessage());
						
					} catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
				}
			  e.printStackTrace();
			} finally {
				try {
					if (pstatementDetail != null) pstatementDetail.close();
					if (pstatementOrder != null) pstatementOrder.close();
					if (connection != null) connection.setAutoCommit(true);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		return orderId;
		
	}

	
	@Override
	public int getLastOrderNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Order getOrderById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
