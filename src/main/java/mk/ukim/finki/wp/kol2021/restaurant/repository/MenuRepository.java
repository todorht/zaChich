package mk.ukim.finki.wp.kol2021.restaurant.repository;

import mk.ukim.finki.wp.kol2021.restaurant.model.Menu;
import mk.ukim.finki.wp.kol2021.restaurant.model.MenuType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findAllByRestaurantNameLike(String name);
    List<Menu> findAllByRestaurantNameLikeAndMenuTypeIs(String name, MenuType menuType);
    List<Menu> findAllByMenuTypeIs(MenuType menuType);
}
