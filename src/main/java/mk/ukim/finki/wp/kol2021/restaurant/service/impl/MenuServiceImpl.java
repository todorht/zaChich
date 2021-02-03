package mk.ukim.finki.wp.kol2021.restaurant.service.impl;

import mk.ukim.finki.wp.kol2021.restaurant.model.Menu;
import mk.ukim.finki.wp.kol2021.restaurant.model.MenuType;
import mk.ukim.finki.wp.kol2021.restaurant.model.exceptions.InvalidMenuIdException;
import mk.ukim.finki.wp.kol2021.restaurant.model.exceptions.InvalidMenuItemIdException;
import mk.ukim.finki.wp.kol2021.restaurant.repository.MenuItemRepository;
import mk.ukim.finki.wp.kol2021.restaurant.repository.MenuRepository;
import mk.ukim.finki.wp.kol2021.restaurant.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;

    public MenuServiceImpl(MenuRepository menuRepository, MenuItemRepository menuItemRepository) {
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<Menu> listAll() {
        return this.menuRepository.findAll();
    }

    @Override
    public Menu findById(Long id) {
        return this.menuRepository.findById(id).orElseThrow(InvalidMenuIdException::new);
    }

    @Override
    public Menu create(String restaurantName, MenuType menuType, List<Long> menuItems) {
        for(Long id: menuItems){
            this.menuItemRepository.findById(id).orElseThrow(InvalidMenuItemIdException::new);
        }
        return this.menuRepository.save(new Menu(restaurantName,menuType,this.menuItemRepository.findAllById(menuItems)));
    }

    @Override
    public Menu update(Long id, String restaurantName, MenuType menuType, List<Long> menuItems) {
        Menu menu = this.menuRepository.findById(id).orElseThrow(InvalidMenuIdException::new);
        menu.setRestaurantName(restaurantName);
        menu.setMenuItems(this.menuItemRepository.findAllById(menuItems));
        menu.setMenuType(menuType);
        return this.menuRepository.save(menu);
    }

    @Override
    public Menu delete(Long id) {
        this.menuRepository.deleteById(id);
        return this.menuRepository.findById(id).orElse(null);
    }

    @Override
    public List<Menu> listMenuItemsByRestaurantNameAndMenuType(String restaurantName, MenuType menuType) {
        String NAME = "%" + restaurantName + "%";
        if(restaurantName!=null && !restaurantName.isEmpty() && menuType!=null) return this.menuRepository.findAllByRestaurantNameLikeAndMenuTypeIs(NAME, menuType);
        else if(restaurantName!=null && !restaurantName.isEmpty()) return this.menuRepository.findAllByRestaurantNameLike(NAME);
        else if(menuType!=null) return this.menuRepository.findAllByMenuTypeIs(menuType);
        else return this.menuRepository.findAll();
    }
}
