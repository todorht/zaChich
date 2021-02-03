package mk.ukim.finki.wp.kol2021.restaurant.service;

import mk.ukim.finki.wp.kol2021.restaurant.model.Role;
import mk.ukim.finki.wp.kol2021.restaurant.model.User;

public interface UserService {
    User create(String username, String password, Role role);
}
