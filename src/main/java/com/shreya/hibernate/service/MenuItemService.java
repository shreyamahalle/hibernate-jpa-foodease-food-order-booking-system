package com.shreya.hibernate.service;

import com.shreya.hibernate.model.MenuItem;

import java.sql.SQLException;
import java.util.List;

public interface MenuItemService {

    void addMenuItem(MenuItem menuItem) throws SQLException;

    List<MenuItem> getAllMenuItems();

    MenuItem getMenuItemById(long id);

    void deleteMenuItem(long id);

    void updateMenuItem(MenuItem menuItem);
}
