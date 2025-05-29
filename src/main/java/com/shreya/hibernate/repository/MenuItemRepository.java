package com.shreya.hibernate.repository;

import com.shreya.hibernate.model.MenuItem;

import java.util.List;

public interface MenuItemRepository {
    boolean addMenuItem(MenuItem menuItem);
    List<MenuItem> retrieveMenuItems();
    MenuItem findById(long id);
    boolean deleteMenuItem(long id);
    boolean updateMenuItem(MenuItem menuItem);
}
