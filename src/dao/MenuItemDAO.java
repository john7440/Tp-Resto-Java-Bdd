package dao;

import model.MenuItem;
import java.util.List;

public interface MenuItemDAO {
	List<MenuItem> getAllbyCategory(String category);
	MenuItem getById(int id);
	MenuItem getByName(String name);

}
