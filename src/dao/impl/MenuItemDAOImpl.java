package dao.impl;

import dao.MenuItemDAO;
import db.DatabaseConnection;
import model.MenuItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAOImpl implements MenuItemDAO {
	
	@Override
	public List<MenuItem> getAllbyCategory(String category){
		List<MenuItem> items = new ArrayList<>();
		String sql = "SELECT * FROM menu_items WHERE category = ?";
		
		try( Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			
			statement.setString(1,  category);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				MenuItem item = new MenuItem(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("category"),
						rs.getDouble("price")
						);
				items.add(item);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return items;
	}

	@Override
	public MenuItem getById(int id) {
		String sql = "SELECT * FROM menu_items WHERE id = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				return new MenuItem(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("category"),
						rs.getDouble("price")
						);
			}
			
		} catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public MenuItem getByName(String name) {
		String sql = "SELECT * FROM menu_items WHERE name = ?";
		
		try (Connection connection  = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                return new MenuItem(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price")
                );
            }
		} catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	
}
