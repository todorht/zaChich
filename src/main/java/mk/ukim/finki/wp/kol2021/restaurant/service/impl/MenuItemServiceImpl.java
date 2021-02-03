package mk.ukim.finki.wp.kol2021.restaurant.service.impl;

import mk.ukim.finki.wp.kol2021.restaurant.model.MenuItem;
import mk.ukim.finki.wp.kol2021.restaurant.model.exceptions.InvalidMenuItemIdException;
import mk.ukim.finki.wp.kol2021.restaurant.repository.MenuItemRepository;
import mk.ukim.finki.wp.kol2021.restaurant.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository repository;

    public MenuItemServiceImpl(MenuItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public MenuItem findById(Long id) {
        return this.repository.findById(id).orElseThrow(InvalidMenuItemIdException::new);
    }

    @Override
    public List<MenuItem> listAll() {
        return this.repository.findAll();
    }

    @Override
    public MenuItem create(String name, String description) {
        return this.repository.save(new MenuItem(name,description));
    }
}
