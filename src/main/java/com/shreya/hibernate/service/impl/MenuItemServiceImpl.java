package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.MenuItemDomain;
import com.shreya.hibernate.exception.MenuItemDeletionException;
import com.shreya.hibernate.exception.MenuItemNotFoundException;
import com.shreya.hibernate.exception.MenuItemUpdateException;
import com.shreya.hibernate.model.MenuItem;
import com.shreya.hibernate.repository.MenuItemRepository;
import com.shreya.hibernate.service.MenuItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public void addMenuItem(MenuItem menuItem) {
        log.info("Saving menu item: {}", menuItem);
        menuItemRepository.save(toDomain(menuItem));
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        log.info("Fetching all menu items");
        return menuItemRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public MenuItem getMenuItemById(long id) {
        log.info("Fetching menu item by id: {}", id);
        Optional<MenuItemDomain> optional = menuItemRepository.findById((int) id);
        return optional.map(this::toModel)
                .orElseThrow(() -> new MenuItemNotFoundException("MenuItem not found with id: " + id));
    }

    @Override
    public void deleteMenuItem(long id) {
        log.info("Deleting menu item with id: {}", id);
        if (!menuItemRepository.existsById((int) id)) {
            throw new MenuItemDeletionException("MenuItem not found with id: " + id);
        }
        menuItemRepository.deleteById((int) id);
    }

    @Override
    public void updateMenuItem(MenuItem menuItem) {
        log.info("Updating menu item: {}", menuItem);
        if (!menuItemRepository.existsById(Math.toIntExact(menuItem.getId()))) {
            throw new MenuItemUpdateException("Cannot update. MenuItem not found with id: " + menuItem.getId());
        }
        menuItemRepository.save(toDomain(menuItem));
    }

    // === Mapping Methods ===
    private MenuItem toModel(MenuItemDomain domain) {
        return MenuItem.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .price(domain.getPrice())
                .restaurantId(domain.getRestaurantId())
                .build();
    }

    private MenuItemDomain toDomain(MenuItem model) {
        return MenuItemDomain.builder()
                .id(model.getId())
                .name(model.getName())
                .description(model.getDescription())
                .price(model.getPrice())
                .restaurantId(model.getRestaurantId())
                .build();
    }
}
