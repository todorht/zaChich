package mk.ukim.finki.wp.kol2021.restaurant.config;

import mk.ukim.finki.wp.kol2021.restaurant.model.MenuType;
import mk.ukim.finki.wp.kol2021.restaurant.model.Role;
import mk.ukim.finki.wp.kol2021.restaurant.model.User;
import mk.ukim.finki.wp.kol2021.restaurant.service.MenuItemService;
import mk.ukim.finki.wp.kol2021.restaurant.service.MenuService;
import mk.ukim.finki.wp.kol2021.restaurant.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
public class DataInitializer {

    public static final String ADMIN = "admin";

    private final UserService userService;

    private final MenuItemService menuItemService;

    private final MenuService menuService;

    public DataInitializer(UserService userService, MenuItemService menuItemService, MenuService menuService) {
        this.userService = userService;
        this.menuItemService = menuItemService;
        this.menuService = menuService;
    }

    @PostConstruct
    public void initData() {
        User admin = this.userService.create(ADMIN,ADMIN, Role.ROLE_ADMIN);

        for (int i = 1; i < 6; i++) {
            this.menuItemService.create("Food " + i, "Food " + i + " description");
        }

        for (int i = 1; i < 11; i++) {
            this.menuService.create("Restaurant " + i,
                    MenuType.values()[i % MenuType.values().length],
                    Stream.of(1L, i % 5L + 1).collect(Collectors.toList()));
        }
    }
}
