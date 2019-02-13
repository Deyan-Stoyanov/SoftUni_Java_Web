package service;

import domain.model.service.UserServiceModel;

public interface UserService {
    UserServiceModel findByUsername(String username);

    boolean register(UserServiceModel userServiceModel);

    boolean login(UserServiceModel userServiceModel);

    boolean isAdmin(String username);
}
